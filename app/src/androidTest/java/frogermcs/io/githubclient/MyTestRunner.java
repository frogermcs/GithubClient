package frogermcs.io.githubclient;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import frogermcs.io.githubclient.inject.ApplicationMock;

/**
 * Created by Miroslaw Stanek on 24.09.15.
 */
public class MyTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, ApplicationMock.class.getName(), context);
    }
}
