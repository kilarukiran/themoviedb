package org.github.kilarukirankumar.di.component;

import android.content.Context;

import org.github.kilarukirankumar.di.module.ApplicationModule;
import org.github.kilarukirankumar.executor.PostExecutionThread;
import org.github.kilarukirankumar.executor.ThreadExecutor;
import org.github.kilarukirankumar.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component that lives for life time of the application.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Context getContext();

    ThreadExecutor getThreadExecutor();

    PostExecutionThread getPostExecutionThread();
}
