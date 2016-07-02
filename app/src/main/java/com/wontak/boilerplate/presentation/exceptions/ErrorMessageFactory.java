package com.wontak.boilerplate.presentation.exceptions;

import android.content.Context;

import com.wontak.boilerplate.R;
import com.wontak.boilerplate.network.exceptions.NetworkConnectionException;
import com.wontak.boilerplate.network.exceptions.UserNotFoundException;

public class ErrorMessageFactory
{
    private ErrorMessageFactory()
    {

    }

    public static String create(Context context, Exception exception)
    {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException)
        {
            message = context.getString(R.string.exception_message_no_connection);
        }
        else if (exception instanceof UserNotFoundException)
        {
            message = context.getString(R.string.exception_message_user_not_found);
        }

        return message;
    }
}
