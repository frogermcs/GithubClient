package frogermcs.io.githubclient;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import dagger.producers.Production;

/**
 * Created by froger_mcs on 12/02/2018.
 */

@Module
final class ExecutorModule {
    @Provides
    @Production
    static Executor executor() {
        return Executors.newSingleThreadExecutor();
    }
}
