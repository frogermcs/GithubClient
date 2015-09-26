package frogermcs.io.githubclient.tests;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import frogermcs.io.githubclient.R;
import frogermcs.io.githubclient.data.api.UserManager;
import frogermcs.io.githubclient.data.model.User;
import frogermcs.io.githubclient.inject.ApplicationMock;
import frogermcs.io.githubclient.inject.GithubApiModuleMock;
import frogermcs.io.githubclient.ui.activity.SplashActivity;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.anyString;

/**
 * Created by Miroslaw Stanek on 23.09.15.
 */
@RunWith(AndroidJUnit4.class)
public class SplashActivityUITests {

    @Mock
    UserManager userManagerMock;

    @Rule
    public ActivityTestRule<SplashActivity> activityRule = new ActivityTestRule<>(
            SplashActivity.class, true, false
    );

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        GithubApiModuleMock githubApiModuleMock = new GithubApiModuleMock(userManagerMock);
        ApplicationMock app = (ApplicationMock) InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.setGithubApiModuleMock(githubApiModuleMock);
        activityRule.launchActivity(new Intent());
    }

    @Test
    public void checkLoadingError() {
        Mockito.when(userManagerMock.getUser(anyString()))
                .thenReturn(Observable.<User>error(new RuntimeException("test")));

        onView(withId(R.id.etUsername)).perform(typeText("frogermcs"));
        onView(withId(R.id.btnShowRepositories)).perform(click());

        onView(withId(R.id.etUsername)).check(matches(hasErrorText("Validation error")));
    }

    @Test
    public void checkLoadingSuccess() {
        User user = new User();
        user.id = 1;
        user.email = "abc@gmail.com";
        user.login = "frogermcs";
        user.url = null;

        Mockito.when(userManagerMock.getUser(anyString())).thenReturn(Observable.just(user));

        onView(withId(R.id.pbLoading)).check(matches(not(isDisplayed())));

        onView(withId(R.id.etUsername)).perform(typeText("frogermcs"));
        onView(withId(R.id.btnShowRepositories)).perform(click());

        onView(withId(R.id.btnShowRepositories)).check(doesNotExist());
    }

}
