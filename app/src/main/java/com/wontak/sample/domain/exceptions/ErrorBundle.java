package com.wontak.sample.domain.exceptions;

public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();
}
