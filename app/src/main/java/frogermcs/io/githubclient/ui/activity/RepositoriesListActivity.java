package frogermcs.io.githubclient.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import frogermcs.io.githubclient.AppComponent;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.activity.component.DaggerRepositoriesListActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.RepositoriesListActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoriesListActivityPresenter;
import frogermcs.io.githubclient.ui.adapter.RepositoriesListAdapter;
import frogermcs.io.githubclient.utils.AnalyticsManager;


public class RepositoriesListActivity extends BaseActivity {

    private static final String ARG_USERNAME = "arg_username";
    @InjectView(R.id.lvRepositories)
    ListView lvRepositories;
    @InjectView(R.id.pbLoading)
    ProgressBar pbLoading;
    @Inject
    RepositoriesListActivityPresenter presenter;
    @Inject
    AnalyticsManager analyticsManager;
    private RepositoriesListAdapter repositoriesListAdapter;

    public static void startWithUsername(String username, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, RepositoriesListActivity.class);
        intent.putExtra(ARG_USERNAME, username);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_list);
        ButterKnife.inject(this);
        presenter.initWithUsername(getIntent().getStringExtra(ARG_USERNAME));

        repositoriesListAdapter = new RepositoriesListAdapter(this, new ArrayList<Repository>());
        lvRepositories.setAdapter(repositoriesListAdapter);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRepositoriesListActivityComponent.builder()
                .appComponent(appComponent)
                .repositoriesListActivityModule(new RepositoriesListActivityModule(this))
                .build()
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
}
