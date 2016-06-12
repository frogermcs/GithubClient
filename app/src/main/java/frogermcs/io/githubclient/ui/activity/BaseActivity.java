package frogermcs.io.githubclient.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import frogermcs.io.githubclient.AppComponent;
import frogermcs.io.githubclient.GithubClientApplication;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    protected abstract void setupActivityComponent();
}