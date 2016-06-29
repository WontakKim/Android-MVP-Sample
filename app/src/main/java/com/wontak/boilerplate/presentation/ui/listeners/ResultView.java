package com.wontak.boilerplate.presentation.ui.listeners;

import com.wontak.boilerplate.domain.models.User;

public interface ResultView
{
    interface View
    {
        void renderUser(User user);
    }
}
