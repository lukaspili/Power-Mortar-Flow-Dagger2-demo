package com.lukaspili.powermortardemo.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lukaspili.powermortardemo.R;
import com.lukaspili.powermortardemo.app.DaggerService;
import com.lukaspili.powermortardemo.app.presenter.ViewPostPresenter;
import com.lukaspili.powermortardemo.app.presenter.screen.ViewPostScreenComponent;

import javax.inject.Inject;

import autodagger.AutoInjector;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
@AutoInjector(ViewPostPresenter.class)
public class BannerView extends LinearLayout {

    @Inject
    protected ViewPostPresenter presenter;

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        ViewPostScreenComponent component = DaggerService.getDaggerComponent(context);
        component.inject(this);

        View view = View.inflate(context, R.layout.view_banner, this);
        ButterKnife.inject(view);
    }

    @OnClick(R.id.text)
    void click() {
        presenter.bannerClick();
    }
}
