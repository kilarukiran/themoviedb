package org.github.kilarukirankumar.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.github.kilarukirankumar.AndroidApplication;
import org.github.kilarukirankumar.di.component.ApplicationComponent;
import org.github.kilarukirankumar.di.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
    }

    /**
     * Method to obtain the main application component for dependency injection
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Method to obtain an Activity module for dependency injection
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
