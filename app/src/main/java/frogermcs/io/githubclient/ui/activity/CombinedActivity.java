package frogermcs.io.githubclient.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import frogermcs.io.githubclient.GithubClientApplication;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.activity.module.CombinedModule;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoriesListActivityPresenter;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoryDetailsActivityPresenter;
import frogermcs.io.githubclient.ui.adapter.RepositoriesListAdapter;
import frogermcs.io.githubclient.utils.AnalyticsManager;


public class CombinedActivity extends BaseActivity implements RepositoriesListUI, DetailUi {
    @Bind(R.id.lvRepositories)
    ListView lvRepositories;
    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;
    @Bind(R.id.tvUserName)
    TextView tvUserName;

    @Inject
    RepositoriesListActivityPresenter listPresenter;

    @Inject
    RepositoryDetailsActivityPresenter detailPresenter;

    @Inject
    AnalyticsManager analyticsManager;

    private RepositoriesListAdapter repositoriesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined);
        ButterKnife.bind(this);
        listPresenter.loadRepositories();
        detailPresenter.init();

        repositoriesListAdapter = new RepositoriesListAdapter(this, new ArrayList<Repository>());
        lvRepositories.setAdapter(repositoriesListAdapter);
    }

    @Override
    protected void setupActivityComponent() {
        GithubClientApplication.get(this).getUserComponent()
                .plus(new CombinedModule(this, this))
                .inject(this);
    }

    @Override
    public void showLoading(boolean loading) {
        lvRepositories.setVisibility(loading ? View.GONE : View.VISIBLE);
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
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

    @Override
    public void setupUserName(final String userName) {
        tvUserName.setText(getString(R.string.repositories_of, userName));
    }
}
