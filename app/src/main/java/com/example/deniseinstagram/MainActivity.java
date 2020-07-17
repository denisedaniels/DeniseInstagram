package com.example.deniseinstagram;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


import java.io.File;
import java.util.List;

import fragments.ComposeFragment;
import fragments.PostsFragment;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MainActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    //private Button btnLogout;


    //Reference to bottom navigation bar
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnLogout= findViewById(R.id.btnLogout);
        bottomNavigationView= findViewById(R.id.bottom_navigation);

       /* btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                //Log.i(TAG, "onClick login button");
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                goLoginActivity();
            }
        });*/

        //Bottom navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragment = new PostsFragment();
                        //Adding toast for testing
                        Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_compose:
                       fragment = new ComposeFragment();
                        //Adding toast for testing
                        Toast.makeText(MainActivity.this,"Compose",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ComposeFragment();
                        //Adding toast for testing
                        Toast.makeText(MainActivity.this,"Profile",Toast.LENGTH_SHORT).show();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    // Use intent system to navigate to the new activity
    private void goLoginActivity() {
        Intent i=new Intent (this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}
