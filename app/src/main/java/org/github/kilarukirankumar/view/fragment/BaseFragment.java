package org.github.kilarukirankumar.view.fragment;

import android.support.v4.app.Fragment;

import org.github.kilarukirankumar.di.HasComponent;


public abstract class BaseFragment extends Fragment {
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
