package br.edu.utfpr.nossafaunaiguacu.features.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import br.edu.utfpr.nossafaunaiguacu.databinding.ActivitySplashScreenBinding;
import br.edu.utfpr.nossafaunaiguacu.features.home.HomeActivity;
import br.edu.utfpr.nossafaunaiguacu.features.onboard.OnBoardActivity;
import dagger.android.AndroidInjection;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends FragmentActivity {

    @Inject
    SplashScreenViewModelFactory viewModelFactory;
    private SplashScreenViewModel viewModel;
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setupLayout();
        setupViewModel();

        new Handler().postDelayed(() -> {
            runOnUiThread(() -> {
                if (viewModel.isFirstAccess()) {
                    startActivity(new Intent(this, OnBoardActivity.class));
                } else {
                    startActivity(new Intent(this, HomeActivity.class));
                }
                finish();
            });
        }, 2000L);
    }

    private void setupLayout() {
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(SplashScreenViewModel.class);
    }
}
