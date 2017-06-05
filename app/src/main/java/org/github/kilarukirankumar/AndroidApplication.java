package org.github.kilarukirankumar;

import android.app.Application;

import org.github.kilarukirankumar.di.component.ApplicationComponent;
import org.github.kilarukirankumar.di.component.DaggerApplicationComponent;
import org.github.kilarukirankumar.di.module.ApplicationModule;

/**
 * AndroidApplication
 */

public class AndroidApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
