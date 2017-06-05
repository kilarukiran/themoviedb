package org.github.kilarukirankumar.interactor;

import org.github.kilarukirankumar.executor.PostExecutionThread;
import org.github.kilarukirankumar.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case in
 * the application should implement this contract).
 */

public abstract class Interactor {
    private final ThreadExecutor threadExecutor;

    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();

    public Interactor(final ThreadExecutor threadExecutor, final PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link Interactor}.
     */
    protected abstract Observable buildObservableForUseCase();

    /**
     * Executes the use case.
     *
     * @param subscriber who will listen to the Observable built using
     *                   {@link #buildObservableForUseCase()}
     */
    @SuppressWarnings("unchecked")
    public void execute(Subscriber subscriber) {
        final Observable observable = buildObservableForUseCase();
        observable.subscribeOn(Schedulers.from(threadExecutor)) // executes the task in a new
                // Scheduler. In this case from a
                // ThreadExecutor.
                .observeOn(postExecutionThread.getScheduler()) // method will provide the result
                // on a specific Scheduler: the
                // postExecutionThread in our case
                .subscribe(subscriber);

    }

    /**
     * Unsubscribes from current {@link Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
