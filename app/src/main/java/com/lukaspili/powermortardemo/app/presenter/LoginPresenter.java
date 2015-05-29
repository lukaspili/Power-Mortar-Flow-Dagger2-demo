package com.lukaspili.powermortardemo.app.presenter;

import com.lukaspili.powermortardemo.R;
import com.lukaspili.powermortardemo.app.AppDependencies;
import com.lukaspili.powermortardemo.app.DaggerScope;
import com.lukaspili.powermortardemo.flow.Layout;
import com.lukaspili.powermortardemo.rest.RestClient;

import autodagger.AutoComponent;
import automortar.AutoScreen;
import mortar.ViewPresenter;
import timber.log.Timber;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
@AutoScreen(
        component = @AutoComponent(dependencies = PostsPresenter.class, superinterfaces = AppDependencies.class),
        screenAnnotations = Layout.class
)
@DaggerScope(LoginPresenter.class)
@Layout(R.layout.screen_login)
public class LoginPresenter extends ViewPresenter {

    private final RestClient restClient;

    public LoginPresenter(RestClient restClient) {
        this.restClient = restClient;
    }

    public void click() {
        Timber.d("Login screen clicked!");
        // do some stuff with rest client to login
    }
}
