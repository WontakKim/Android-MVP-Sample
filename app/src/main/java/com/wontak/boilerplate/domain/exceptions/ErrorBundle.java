package com.wontak.boilerplate.domain.exceptions;

public interface ErrorBundle
{
    Exception getException();

    String getErrorMessage();
}
