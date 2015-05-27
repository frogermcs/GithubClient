package frogermcs.io.githubclient.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import frogermcs.io.githubclient.AppComponent;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.activity.component.DaggerRepositoryDetailsActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.RepositoryDetailsActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoryDetailsActivityPresenter;
import frogermcs.io.githubclient.utils.AnalyticsManager;


public class RepositoryDetailsActivity extends BaseActivity {
    private static final String ARG_REPOSITORY = "arg_repository";

    public static void startWithRepository(Repository repository, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, RepositoryDetailsActivity.class);
        intent.putExtra(ARG_REPOSITORY, repository);
        startingActivity.startActivity(intent);
    }

    @InjectView(R.id.tvRepoName)
    TextView tvRepoName;
    @InjectView(R.id.tvRepoDetails)
    TextView tvRepoDetails;

    @Inject
    AnalyticsManager analyticsManager;
    @Inject
    RepositoryDetailsActivityPresenter presenter;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);
        ButterKnife.inject(this);
        analyticsManager.logScreenView(getClass().getName());

        repository = getIntent().getParcelableExtra(ARG_REPOSITORY);
        tvRepoName.setText(repository.name);
        tvRepoDetails.setText(repository.url);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRepositoryDetailsActivityComponent.builder()
                .appComponent(appComponent)
                .repositoryDetailsActivityModule(new RepositoryDetailsActivityModule(this))
                .build()
                .inject(this);
    }

}