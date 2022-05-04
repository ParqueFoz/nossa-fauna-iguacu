package br.edu.utfpr.nossafaunaiguacu.application.di;

import br.edu.utfpr.nossafaunaiguacu.features.splash.di.SplashScreenFeatureModule;
import dagger.Module;

@Module(includes = {SplashScreenFeatureModule.class})
public interface ApplicationModule {
}
