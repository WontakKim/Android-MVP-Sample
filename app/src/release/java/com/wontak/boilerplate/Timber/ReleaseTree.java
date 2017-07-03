package com.wontak.boilerplate.Timber;

import android.util.Log;

import timber.log.Timber;

public class ReleaseTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG)
            return;

        // Crashlytics
        // Crashlytics.log(priority, tag, message);
        //
        // if(t != null)
        //     Crashlytics.logException(t);
    }
}
