package com.example.inshortsassignment.di;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, BuildersModule.class})
public interface AppComponent extends AndroidInjector<InshortsApplication> {
    void inject(InshortsApplication application);
}
