package br.edu.utfpr.nossafaunaiguacu.application.di;

import br.edu.utfpr.nossafaunaiguacu.application.BaseApplication;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class})
public interface ApplicationComponent {
    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        ApplicationComponent build();
    }
}
