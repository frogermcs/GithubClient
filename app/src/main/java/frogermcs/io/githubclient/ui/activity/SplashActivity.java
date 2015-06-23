package frogermcs.io.githubclient.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import frogermcs.io.githubclient.AppComponent;
import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.ui.activity.component.DaggerSplashActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.SplashActivityModule;
import frogermcs.io.githubclient.ui.activity.presenter.SplashActivityPresenter;
import frogermcs.io.githubclient.utils.AnalyticsManager;
import frogermcs.io.githubclient.utils.SimpleObserver;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;


public class SplashActivity extends BaseActivity {

    @InjectView(R.id.etUsername)
    EditText etUsername;
    @InjectView(R.id.pbLoading)
    ProgressBar pbLoading;
    @InjectView(R.id.btnShowRepositories)
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
        ButterKnife.inject(this);

        analyticsManager.logScreenView(getClass().getName());

        WidgetObservable.text(etUsername, true).subscribe(new SimpleObserver<OnTextChangeEvent>() {
            @Override
            public void onNext(OnTextChangeEvent onTextChangeEvent) {
                presenter.username = onTextChangeEvent.text().toString();
                etUsername.setError(null);
            }
        });
    }

    //Local dependencies graph is constructed here
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSplashActivityComponent.builder()
                .appComponent(appComponent)     //Takes appComponent explicitly (depends on it)
                .splashActivityModule(new SplashActivityModule(this))
                .build().inject(this);
    }

    @OnClick(R.id.btnShowRepositories)
    public void onShowRepositoriesClick() {
        presenter.onShowRepositoriesClick();
    }

    public void showRepositoriesForUser(User user) {
        RepositoriesListActivity.startWithUsername(user, this);
    }

    public void showValidationError() {
        etUsername.setError("Validation error");
    }

    public void showLoading(boolean loading) {
        btnShowRepositories.setVisibility(loading ? View.GONE : View.VISIBLE);
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
}
