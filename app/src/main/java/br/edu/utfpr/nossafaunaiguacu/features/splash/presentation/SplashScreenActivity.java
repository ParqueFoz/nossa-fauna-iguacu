package br.edu.utfpr.nossafaunaiguacu.features.splash.presentation;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Inject
    SplashScreenViewModelFactory viewModelFactory;
    private SplashScreenViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setupSplashScreen();
        setupViewModel();
        fetchApplicationData();
    }

    private void setupSplashScreen() {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepVisibleCondition(() -> viewModel.isKeepSplashScreenVisible());
    }

    private void setupViewModel() {
        createViewModel();
        setupObservers();
    }

    private void createViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(SplashScreenViewModel.class);
    }

    private void setupObservers() {
        viewModel.shouldFinishLiveData.observe(this, this::handleShouldFinish);
        viewModel.showFetchErrorLiveData.observe(this, this::handleFetchShowErrorLayout);
    }

    private void handleShouldFinish(Boolean shouldFinish) {
        if (shouldFinish) {
            finish();
        }
    }

    private void handleFetchShowErrorLayout(Boolean shouldShowFetchErrorLayout) {
        // TODO("implement")
    }

    private void fetchApplicationData() {
        viewModel.fetchApplicationData();
    }
}
