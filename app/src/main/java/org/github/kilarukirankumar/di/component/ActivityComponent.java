package org.github.kilarukirankumar.di.component;

import org.github.kilarukirankumar.di.ActivityScope;
import org.github.kilarukirankumar.di.module.ActivityModule;
import org.github.kilarukirankumar.view.activity.BaseActivity;

import dagger.Component;

/**
 * ActivityComponent
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

    BaseActivity activity();
}
