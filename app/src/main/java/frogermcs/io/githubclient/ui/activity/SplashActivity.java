package frogermcs.io.githubclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import frogermcs.io.githubclient.GithubClientApplication;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.ui.activity.module.SplashActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.SplashActivityPresenter;
import frogermcs.io.githubclient.utils.AnalyticsManager;
import rx.android.widget.WidgetObservable;


public class SplashActivity extends BaseActivity {

    @Bind(R.id.etUsername)
    EditText etUsername;
    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;
    @Bind(R.id.btnShowRepositories)
    Button btnShowRepositories;

    //These references will be satisfied by 'SplashActivityComponent.inject(this)' method
    @Inject
    SplashActivityPresenter presenter;
    @Inject
    AnalyticsManager analyticsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        analyticsManager.logScreenView(getClass().getName());
        WidgetObservable.text(etUsername, true).subscribe((onTextChangeEvent) -> {
            presenter.username = onTextChangeEvent.text().toString();
            etUsername.setError(null);
        });

    }

    //Local dependencies graph is constructed here
    @Override
    protected void setupActivityComponent() {
        //Uncomment those lines do measure dependencies creation time
        //Debug.startMethodTracing("SplashTrace");
        GithubClientApplication.get(this)
                .getAppComponent()
                .plus(new SplashActivityModule(this))
                .inject(this);
        //Debug.stopMethodTracing();
    }

    @OnClick(R.id.btnShowRepositories)
    public void onShowRepositoriesClick() {
        presenter.onShowRepositoriesClick();
    }

    public void showRepositoriesListForUser(User user) {
        GithubClientApplication.get(this).createUserComponent(user);
        startActivity(new Intent(this, RepositoriesListActivity.class));
    }

    public void showValidationError() {
        etUsername.setError("Validation error");
    }

    public void showLoading(boolean loading) {
        btnShowRepositories.setVisibility(loading ? View.GONE : View.VISIBLE);
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
}
