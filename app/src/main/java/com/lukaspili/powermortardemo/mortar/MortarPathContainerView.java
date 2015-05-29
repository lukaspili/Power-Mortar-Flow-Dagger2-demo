package com.lukaspili.powermortardemo.mortar;

import android.content.Context;
import android.util.AttributeSet;

import com.lukaspili.powermortardemo.R;
import com.lukaspili.powermortardemo.flow.FramePathContainerView;
import com.lukaspili.powermortardemo.flow.SimplePathContainer;

import flow.path.Path;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
public class MortarPathContainerView extends FramePathContainerView {

    public MortarPathContainerView(Context context, AttributeSet attrs) {
        super(context, attrs, new SimplePathContainer(R.id.screen_switcher_tag, Path.contextFactory(new BasicMortarContextFactory(new ScreenScoper()))));
    }
}
