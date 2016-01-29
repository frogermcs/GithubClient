package frogermcs.io.githubclient.ui.activity;

import com.google.common.collect.ImmutableList;

import frogermcs.io.githubclient.data.model.Repository;

public interface RepositoriesListUI {
    void showLoading(boolean loading);

    void setRepositories(ImmutableList<Repository> repositories);
}
