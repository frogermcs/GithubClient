package frogermcs.io.githubclient.inject;

import frogermcs.io.githubclient.AppComponent;
import frogermcs.io.githubclient.AppModule;
import frogermcs.io.githubclient.DaggerAppComponent;
import frogermcs.io.githubclient.GithubClientApplication;
import frogermcs.io.githubclient.data.api.GithubApiModule;

/**
 * Created by Miroslaw Stanek on 24.09.15.
 */
public class ApplicationMock extends GithubClientApplication {

    private AppComponent appComponent;
    private GithubApiModule githubApiModuleMock;

    public void setGithubApiModuleMock(GithubApiModule githubApiModuleMock) {
        this.githubApiModuleMock = githubApiModuleMock;
        setupMockAppComponent();
    }

    public void setupMockAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .githubApiModule(githubApiModuleMock)
                .build();
    }

    @Override
    public AppComponent getAppComponent() {
        return appComponent == null ? super.getAppComponent() : appComponent;
    }
}