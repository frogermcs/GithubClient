package frogermcs.io.githubclient.ui.activity;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import frogermcs.io.githubclient.BuildConfig;
import frogermcs.io.githubclient.MockAppModule;
import frogermcs.io.githubclient.TestGithubClientApplication;
import frogermcs.io.githubclient.ui.activity.component.SplashActivityComponent;
import frogermcs.io.githubclient.ui.activity.module.MockSplashActivityModule;
import frogermcs.io.githubclient.ui.activity.module.SplashActivityModule;
import frogermcs.io.githubclient.utils.AnalyticsManager;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by Miroslaw Stanek on 19.09.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(
        sdk = 18,
        constants = BuildConfig.class,
        application = TestGithubClientApplication.class
)
public class SplashActivityTests {

    @Mock
    SplashActivityComponent splashActivityComponentMock;
    @Mock
    AnalyticsManager analyticsManagerMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                SplashActivity activity = (SplashActivity) invocation.getArguments()[0];
                activity.analyticsManager = analyticsManagerMock;
                return null;
            }
        }).when(splashActivityComponentMock).inject(any(SplashActivity.class));

        TestGithubClientApplication app = (TestGithubClientApplication) RuntimeEnvironment.application;
        app.setSplashActivityComponent(splashActivityComponentMock);
    }

    @Test
    public void testName() throws Exception {
        SplashActivity activity = Robolectric.setupActivity(SplashActivity.class);
        verify(activity.analyticsManager).logScreenView(anyString());
    }
}
