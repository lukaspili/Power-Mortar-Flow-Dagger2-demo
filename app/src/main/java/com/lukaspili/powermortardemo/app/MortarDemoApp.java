package com.lukaspili.powermortardemo.app;

import android.app.Application;

import com.lukaspili.powermortardemo.BuildConfig;

import autodagger.AutoComponent;
import autodagger.AutoInjector;
import mortar.MortarScope;
import timber.log.Timber;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
@AutoComponent(superinterfaces = AppDependencies.class)
@AutoInjector
@DaggerScope(MortarDemoApp.class)
public class MortarDemoApp extends Application {

    private MortarScope mortarScope;

    @Override
    public Object getSystemService(String name) {
        return mortarScope.hasService(name) ? mortarScope.getService(name) : super.getSystemService(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        MortarDemoAppComponent component = DaggerMortarDemoAppComponent.create();
        component.inject(this);

        mortarScope = MortarScope.buildRootScope()
                .withService(DaggerService.SERVICE_NAME, component)
                .build("Root");
    }
}
