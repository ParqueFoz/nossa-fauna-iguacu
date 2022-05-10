package br.edu.utfpr.nossafaunaiguacu.features.splash.presentation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import br.edu.utfpr.nossafaunaiguacu.databinding.ActivitySplashScreenBinding;
import dagger.android.AndroidInjection;

// TODO add UTFPR icons in screen
@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Inject
    SplashScreenViewModelFactory viewModelFactory;
    private SplashScreenViewModel viewModel;
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setupLayout();
        setupListeners();
        setupSplashScreen();
        setupViewModel();
        fetchApplicationData();
    }

    private void setupLayout() {
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void setupListeners() {
        binding.errorButton.setOnClickListener(view -> fetchApplicationData());
    }

    private void setupSplashScreen() {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> viewModel.shouldKeepSplashScreenVisible());
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
        int viewVisibility = shouldShowFetchErrorLayout ? View.VISIBLE : View.GONE;
        binding.errorGroup.setVisibility(viewVisibility);
    }

    private void fetchApplicationData() {
        viewModel.fetchApplicationData();
    }
}
