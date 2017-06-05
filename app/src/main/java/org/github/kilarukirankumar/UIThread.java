package org.github.kilarukirankumar;

import org.github.kilarukirankumar.executor.PostExecutionThread;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;


/**
 * UIThread implementation based on a {@link Scheduler} which will execute
 * actions on the Android UI thread.
 */
public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
