package com.example.kristychen.ufree3;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.view.View.OnClickListener;


import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;


import java.util.List;

public class SidebarHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Determine whether the current user is an anonymous user

                    // If current user is NOT anonymous user
                    // Get current user data from Parse.com
        try {
            Parse.initialize(this, "SU7KXmgEwwa1zIlH0n5p36rQHpto6XMGiYfsLxyH", "TfmTRsQuZivXWmKXNN34nYbNF0ogouHm7FHelkN7");
            ParseInstallation.getCurrentInstallation().saveInBackground();
            ParseInstallation.getCurrentInstallation().put("phonenumber", "3205587854");
        }
        catch(Exception e){}
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {

                        // Send logged in users to home page






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        addListenerOnButton();
        addListenerOnButton2();
        addListenerOnButton3();


        /*ParseQuery<ParseObject> questions = ParseQuery.getQuery("Question");
        questions.whereContains("question", "easy");
         questions.findInBackground(new FindCallback<ParseObject>() {
           @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for (ParseObject obj : objects) {
                    Log.i("TEST", "The question is: " + obj.getString("question") + " and the answer is: " + obj.getString("answer"));
                }
            }
        });*/


                    }

                    else {
            // Send user to LoginSignupActivity.class
            Intent intent = new Intent(SidebarHomeActivity.this,
                    loginActivity.class);
            startActivity(intent);
            finish();
        }
    }



    public void addListenerOnButton(){
        final Context context = this;
        button = (Button)findViewById(R.id.notifb);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, yesnoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addListenerOnButton2(){
        final Context context = this;
        button = (Button)findViewById(R.id.sendm);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, sendActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addListenerOnButton3(){
        final Context context = this;
        button = (Button)findViewById(R.id.viewr);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, resultActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sidebar_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            finish();
        } else if (id == R.id.nav_friend) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
