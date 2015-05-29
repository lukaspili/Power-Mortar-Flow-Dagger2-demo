package com.lukaspili.powermortardemo.app;

import flow.path.Path;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
@automortar.config.AutoMortarConfig(
        daggerServiceName = DaggerService.SERVICE_NAME,
        screenSuperclass = Path.class
)
public interface AutoMortarConfig {
}
