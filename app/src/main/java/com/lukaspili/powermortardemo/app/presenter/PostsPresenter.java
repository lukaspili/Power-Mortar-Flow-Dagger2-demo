package com.lukaspili.powermortardemo.app.presenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.lukaspili.powermortardemo.R;
import com.lukaspili.powermortardemo.app.AppDependencies;
import com.lukaspili.powermortardemo.app.DaggerScope;
import com.lukaspili.powermortardemo.app.adapter.PostAdapter;
import com.lukaspili.powermortardemo.flow.Layout;
import com.lukaspili.powermortardemo.model.Post;
import com.lukaspili.powermortardemo.rest.RestClient;
import com.lukaspili.powermortardemo.ui.activity.RootActivity;
import com.lukaspili.powermortardemo.ui.view.PostsView;

import java.util.ArrayList;
import java.util.List;

import autodagger.AutoComponent;
import automortar.AutoScreen;
import flow.Flow;
import mortar.ViewPresenter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

/**
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
@AutoScreen(
        component = @AutoComponent(dependencies = RootActivity.class, superinterfaces = AppDependencies.class),
        screenAnnotations = Layout.class
)
@DaggerScope(PostsPresenter.class)
@Layout(R.layout.screen_posts)
public class PostsPresenter extends ViewPresenter<PostsView> implements PostAdapter.Listener {

    private final RestClient restClient;

    private PostAdapter adapter;
    private List<Post> posts = new ArrayList<>();

    public PostsPresenter(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        getView().recyclerView.setLayoutManager(layoutManager);

        adapter = new PostAdapter(getView().getContext(), posts, this);
        getView().recyclerView.setAdapter(adapter);

        if (posts.isEmpty()) {
            load();
        }
    }

    private void load() {
        restClient.getService().getPosts(new Callback<List<Post>>() {
            @Override
            public void success(List<Post> loadedPosts, Response response) {
                if (!hasView()) return;
                Timber.d("Success loaded %s", loadedPosts.size());

                posts.clear();
                posts.addAll(loadedPosts);
                adapter.notifyDataSetChanged();

                getView().show();
            }

            @Override
            public void failure(RetrofitError error) {
                if (!hasView()) return;
                Timber.d("Failure %s", error.getMessage());
            }
        });
    }

    @Override
    protected void onSave(Bundle outState) {
        super.onSave(outState);


    }

    @Override
    public void onItemClick(int position) {
        if (!hasView()) return;

        Post post = posts.get(position);
        Flow.get(getView()).set(new ViewPostScreen(post));
    }
}
