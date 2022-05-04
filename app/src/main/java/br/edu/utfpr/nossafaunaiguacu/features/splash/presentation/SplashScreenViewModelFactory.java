package br.edu.utfpr.nossafaunaiguacu.features.splash.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public class SplashScreenViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    public SplashScreenViewModelFactory() {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        return (T) new SplashScreenViewModel();
    }
}
