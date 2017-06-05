package org.github.kilarukirankumar.di.module;

import org.github.kilarukirankumar.di.ActivityScope;
import org.github.kilarukirankumar.view.activity.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * ActivityModule
 */
@Module
public class ActivityModule {

    private final BaseActivity mBaseActivity;

    public ActivityModule(final BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
    }

    @Provides
    @ActivityScope
    BaseActivity activity() {
        return mBaseActivity;
    }

}
