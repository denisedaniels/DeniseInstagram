package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.deniseinstagram.Post;
import com.example.deniseinstagram.PostsAdapter;
import com.example.deniseinstagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    public static final String TAG= "PostsFragment";
    private RecyclerView rvPosts;

    //Reference the post adapter
    protected PostsAdapter adapter;

    //List of all posts
    protected List<Post> allPosts;

    //Reference swipe container for pull refresh
    SwipeRefreshLayout swipeContainer;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Reference recycler view
        rvPosts= view.findViewById(R.id.rvPosts);
        //Create the adapter
        allPosts= new ArrayList<>();
        adapter= new PostsAdapter(getContext(), allPosts);


        //Reference swipe container by ID
        swipeContainer=view.findViewById(R.id.swipeContainer);
        //Setting color of refresh
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_purple,
                android.R.color.holo_blue_dark,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright);
        //Adding on refresh listener for pull refresh
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                queryPosts();
            }
        });


        //Set the adapter on the recycler view
        rvPosts.setAdapter(adapter);
        //Set the layout manager
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();
    }

    //Query all the post from the parse dashboard
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e!=null){
                    Log.e(TAG, "Error getting posts", e);
                    Toast.makeText(getContext(),"Error getting posts",Toast.LENGTH_SHORT).show();
                    return;
                }

                for(Post post: posts){
                    //Logs information from all posts
                    Log.i(TAG, "Post" +post.getDescription()+ ", username: " +post.getUser().getUsername());
                }

                adapter.clear();
                adapter.addAll(posts);
                swipeContainer.setRefreshing(false);

                //Added before pull refresh was implemented
                // allPosts.addAll(posts);
                // adapter.notifyDataSetChanged();
            }
        });
    }
}
