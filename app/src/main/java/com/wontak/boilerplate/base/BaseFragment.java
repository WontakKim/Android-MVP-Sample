package com.wontak.boilerplate.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.wontak.boilerplate.di.HasComponent;

public abstract class BaseFragment extends Fragment {

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
