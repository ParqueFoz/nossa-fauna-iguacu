package br.edu.utfpr.nossafaunaiguacu.features.splash;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SplashScreenFeatureModule {
    @ContributesAndroidInjector(modules = {SplashScreenModule.class})
    abstract SplashScreenActivity contributeSplashScreenAndroidInjector();
}
