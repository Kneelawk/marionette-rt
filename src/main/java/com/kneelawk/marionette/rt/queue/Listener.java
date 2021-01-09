package com.kneelawk.marionette.rt.queue;

import java.util.concurrent.ExecutorService;

/**
 * Utility object to help with holding callbacks as well as handling the linked-list setup.
 * @param <C> the type of callback this listener manages.
 */
class Listener<C> {
    final C callback;

    Listener<C> next;

    public Listener(C callback) {
        this.callback = callback;
    }
}
