package frogermcs.io.githubclient.utils;

import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@Singleton
public class Validator {

    @Inject
    public Validator() {
    }

    public boolean validUsername(String username) {
        return !TextUtils.isEmpty(username) && username.length() > 0;
    }
}
