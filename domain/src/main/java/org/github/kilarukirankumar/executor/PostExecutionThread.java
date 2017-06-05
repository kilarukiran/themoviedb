package org.github.kilarukirankumar.executor;

import rx.Scheduler;

/**
 * PostExecutionThread.
 */

public interface PostExecutionThread {
    Scheduler getScheduler();
}
