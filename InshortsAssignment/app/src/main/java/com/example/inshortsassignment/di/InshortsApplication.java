package com.example.inshortsassignment.di;

import android.app.Application;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import javax.inject.Inject;

public class InshortsApplication extends Application implements HasAndroidInjector {

    public static AppComponent appComponent;
    @Inject
    public DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
        appComponent.inject(this);
    }

    public static AppComponent getComponent() {
        return appComponent;
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
