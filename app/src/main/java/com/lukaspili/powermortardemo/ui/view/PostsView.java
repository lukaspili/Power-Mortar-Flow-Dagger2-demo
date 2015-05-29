package com.lukaspili.powermortardemo.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lukaspili.powermortardemo.R;
import com.lukaspili.powermortardemo.app.DaggerService;
import com.lukaspili.powermortardemo.app.presenter.PostsPresenter;
import com.lukaspili.powermortardemo.app.presenter.PostsScreenComponent;

import javax.inject.Inject;

import autodagger.AutoInjector;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
@AutoInjector(PostsPresenter.class)
public class PostsView extends LinearLayout {

    @Inject
    protected PostsPresenter presenter;

    @InjectView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @InjectView(R.id.progress)
    public ProgressBar progressBar;

    public PostsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<PostsScreenComponent>getDaggerComponent(context).inject(this);
    }

    public void show() {
        progressBar.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.dropView(this);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onFinishInflate() {
        ButterKnife.inject(this);
    }
}
