package frogermcs.io.githubclient.ui.activity.module;

import android.support.v7.widget.LinearLayoutManager;

import java.util.Map;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import frogermcs.io.githubclient.data.api.RepositoriesManager;
import frogermcs.io.githubclient.data.model.Repository;
import frogermcs.io.githubclient.ui.activity.ActivityScope;
import frogermcs.io.githubclient.ui.activity.RepositoriesListActivity;
import frogermcs.io.githubclient.ui.activity.presenter.RepositoriesListActivityPresenter;
import frogermcs.io.githubclient.ui.adapter.RepositoriesListAdapter;
import frogermcs.io.githubclient.ui.adapter.viewholder.RepositoriesListViewHolderFactory;
import frogermcs.io.githubclient.ui.adapter.viewholder.RepositoryViewHolderBigFactory;
import frogermcs.io.githubclient.ui.adapter.viewholder.RepositoryViewHolderFeaturedFactory;
import frogermcs.io.githubclient.ui.adapter.viewholder.RepositoryViewHolderNormalFactory;

/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
@Module
public class RepositoriesListActivityModule {
    private RepositoriesListActivity repositoriesListActivity;

    public RepositoriesListActivityModule(RepositoriesListActivity repositoriesListActivity) {
        this.repositoriesListActivity = repositoriesListActivity;
    }

    @Provides
    @ActivityScope
    RepositoriesListActivity provideRepositoriesListActivity() {
        return repositoriesListActivity;
    }

    @Provides
    @ActivityScope
    RepositoriesListActivityPresenter provideRepositoriesListActivityPresenter(RepositoriesManager repositoriesManager) {
        return new RepositoriesListActivityPresenter(repositoriesListActivity, repositoriesManager);
    }

    @Provides
    @ActivityScope
    RepositoriesListAdapter provideRepositoriesListAdapter(RepositoriesListActivity repositoriesListActivity,
                                                           Map<Integer, RepositoriesListViewHolderFactory> viewHolderFactories) {
        return new RepositoriesListAdapter(repositoriesListActivity, viewHolderFactories);
    }

    @Provides
    @ActivityScope
    LinearLayoutManager provideLinearLayoutManager(RepositoriesListActivity repositoriesListActivity) {
        return new LinearLayoutManager(repositoriesListActivity);
    }

    @Provides
    @IntoMap
    @IntKey(Repository.TYPE_NORMAL)
    RepositoriesListViewHolderFactory provideViewHolderNormal() {
        return new RepositoryViewHolderNormalFactory();
    }

    @Provides
    @IntoMap
    @IntKey(Repository.TYPE_BIG)
    RepositoriesListViewHolderFactory provideViewHolderBig() {
        return new RepositoryViewHolderBigFactory();
    }

    @Provides
    @IntoMap
    @IntKey(Repository.TYPE_FEATURED)
    RepositoriesListViewHolderFactory provideViewHolderFeatured() {
        return new RepositoryViewHolderFeaturedFactory();
    }
}