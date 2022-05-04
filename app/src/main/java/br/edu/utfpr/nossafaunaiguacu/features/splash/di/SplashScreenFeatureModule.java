package br.edu.utfpr.nossafaunaiguacu.features.splash.di;

import br.edu.utfpr.nossafaunaiguacu.features.splash.presentation.SplashScreenActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SplashScreenFeatureModule {
    @ContributesAndroidInjector(modules = {SplashScreenModule.class})
    abstract SplashScreenActivity contributeSplashScreenAndroidInjector();
}
