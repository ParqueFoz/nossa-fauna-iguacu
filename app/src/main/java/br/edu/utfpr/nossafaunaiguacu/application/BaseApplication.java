package br.edu.utfpr.nossafaunaiguacu.application;

import android.app.Application;

import javax.inject.Inject;

import br.edu.utfpr.nossafaunaiguacu.application.di.ApplicationComponent;
import br.edu.utfpr.nossafaunaiguacu.application.di.DaggerApplicationComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class BaseApplication extends Application implements HasAndroidInjector {

    private ApplicationComponent applicationComponent;

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDependencyInjection();
    }

    private void setupDependencyInjection() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .build();

        applicationComponent.inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
