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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MainActivity";

    //Reference to bottom navigation bar
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView= findViewById(R.id.bottom_navigation);

        //Bottom navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        //fragment = fragment1;
                        //Adding toast for testing
                        Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_compose:
                       // fragment = fragment2;
                        //Adding toast for testing
                        Toast.makeText(MainActivity.this,"Compose",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                    default:
                        //fragment = fragment3;
                        //Adding toast for testing
                        Toast.makeText(MainActivity.this,"Profile",Toast.LENGTH_SHORT).show();
                        break;
                }
               // fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

    }

}
