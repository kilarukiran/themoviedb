package org.github.kilarukirankumar.util;

import android.content.Context;

import org.github.kilarukirankumar.R;

import java.io.IOException;

/**
 *
 */

public class ErrorUtils {

    private ErrorUtils() {

    }

    public static String getErrorMessage(Context context, Throwable throwable) {
        String errorMessage = "";
        if (null != context) {
            errorMessage = context.getString(R.string.error_please_try_later);
            if (throwable instanceof IOException) {
                /**
                 * No internet or could not reach the server.
                 */
                errorMessage = context.getString(R.string.error_no_internet);
            }
        }
        return errorMessage;
    }
}
