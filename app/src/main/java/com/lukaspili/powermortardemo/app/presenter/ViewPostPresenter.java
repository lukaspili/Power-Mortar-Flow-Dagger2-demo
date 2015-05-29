package com.lukaspili.powermortardemo.app.presenter;

import android.os.Bundle;

import com.lukaspili.powermortardemo.R;
import com.lukaspili.powermortardemo.app.AppDependencies;
import com.lukaspili.powermortardemo.app.DaggerScope;
import com.lukaspili.powermortardemo.flow.Layout;
import com.lukaspili.powermortardemo.model.Post;
import com.lukaspili.powermortardemo.ui.activity.RootActivity;
import com.lukaspili.powermortardemo.ui.view.ViewPostView;

import autodagger.AutoComponent;
import automortar.AutoScreen;
import automortar.ScreenParam;
import mortar.ViewPresenter;
import timber.log.Timber;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
@AutoScreen(
        component = @AutoComponent(dependencies = RootActivity.class, superinterfaces = AppDependencies.class),
        screenAnnotations = Layout.class
)
@DaggerScope(ViewPostPresenter.class)
@Layout(R.layout.screen_view_post)
public class ViewPostPresenter extends ViewPresenter<ViewPostView> {

    private Post post;

    public ViewPostPresenter(@ScreenParam Post post) {
        this.post = post;
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        getView().titleTextView.setText(post.getTitle());
        getView().contentTextView.setText(post.getBody());
    }

    public void bannerClick() {
        Timber.d("Banner click !");
    }
}