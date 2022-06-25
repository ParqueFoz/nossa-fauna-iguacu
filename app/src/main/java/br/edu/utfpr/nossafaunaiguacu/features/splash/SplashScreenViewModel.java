package br.edu.utfpr.nossafaunaiguacu.features.splash;

import androidx.lifecycle.ViewModel;

import br.edu.utfpr.nossafaunaiguacu.data.repository.LocalRepository;

public class SplashScreenViewModel extends ViewModel {

    public Boolean isFirstAccess() {
        return LocalRepository.isFirstAccess();
    }
}
