package com.lukaspili.powermortardemo.app;

import com.lukaspili.powermortardemo.rest.RestClient;

/**
 * Expose objects for DI in all components
 * Scope of objects is DemoMortarAp.Component.class
 *
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
public interface AppDependencies {

    RestClient restClient();
}
