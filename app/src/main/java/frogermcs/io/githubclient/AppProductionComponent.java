package frogermcs.io.githubclient;

import com.google.common.util.concurrent.ListenableFuture;

import dagger.producers.ProductionComponent;
import frogermcs.io.githubclient.data.api.UserManager;
import frogermcs.io.githubclient.data.api.UserModule;

/**
 * Created by Miroslaw Stanek on 05.03.2016.
 */
@ProductionComponent(
        dependencies = AppComponent.class,
        modules = GithubApiProducerModule.class
)
public interface AppProductionComponent {
    ListenableFuture<UserManager> userManager();

    ListenableFuture<UserModule.Factory> userModuleFactory();
}