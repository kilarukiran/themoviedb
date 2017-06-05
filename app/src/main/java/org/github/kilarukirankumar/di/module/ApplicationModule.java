package org.github.kilarukirankumar.di.module;

import android.content.Context;

import org.github.kilarukirankumar.AndroidApplication;
import org.github.kilarukirankumar.UIThread;
import org.github.kilarukirankumar.executor.JobExecutor;
import org.github.kilarukirankumar.executor.PostExecutionThread;
import org.github.kilarukirankumar.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module that provides objects which will live during the application lifecycle
 */
@Module
public class ApplicationModule {

    private final AndroidApplication mAndroidApplication;

    public ApplicationModule(final AndroidApplication androidApplication) {
        mAndroidApplication = androidApplication;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return this.mAndroidApplication;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

}
