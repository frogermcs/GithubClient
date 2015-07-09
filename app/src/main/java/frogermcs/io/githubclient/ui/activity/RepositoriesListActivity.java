package frogermcs.io.githubclient.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import frogermcs.io.githubclient.GithubClientApplication;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.activity.module.RepositoriesListActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoriesListActivityPresenter;
import frogermcs.io.githubclient.ui.adapter.RepositoriesListAdapter;
import frogermcs.io.githubclient.utils.AnalyticsManager;


public class RepositoriesListActivity extends BaseActivity {
    @Bind(R.id.lvRepositories)
    ListView lvRepositories;
    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;

    @Inject
    RepositoriesListActivityPresenter presenter;
    @Inject
    AnalyticsManager analyticsManager;

    private RepositoriesListAdapter repositoriesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_list);
        ButterKnife.bind(this);
        presenter.loadRepositories();

        repositoriesListAdapter = new RepositoriesListAdapter(this, new ArrayList<Repository>());
        lvRepositories.setAdapter(repositoriesListAdapter);
    }

    @Override
    protected void setupActivityComponent() {
        GithubClientApplication.get(this).getUserComponent()
                .plus(new RepositoriesListActivityModule(this))
                .inject(this);
    }

    public void showLoading(boolean loading) {
        lvRepositories.setVisibility(loading ? View.GONE : View.VISIBLE);
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public void setRepositories(ImmutableList<Repository> repositories) {
        repositoriesListAdapter.clear();
        repositoriesListAdapter.addAll(repositories);
    }

    @OnItemClick(R.id.lvRepositories)
    public void onRepositoryClick(int position) {
        Repository repository = repositoriesListAdapter.getItem(position);
        RepositoryDetailsActivity.startWithRepository(repository, this);
    }

    @Override
    public void finish() {
        super.finish();
        GithubClientApplication.get(this).releaseUserComponent();
    }
}
