package com.example.kristychen.ufree3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParsePush;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

//import com.parse.Parse;
//import com.parse.ParseInstallation;


public class sendActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.sendbutton)
    Button button;

    @Bind(R.id.edit_message)
    TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar_send);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i("TEST", "Hey");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = msg.getText().toString();
                ParseQuery<ParseInstallation> matchingNums = ParseQuery.getQuery(ParseInstallation.class);
                matchingNums.whereContains("phonenumber", "3205587854");


                String sampleUsers = "gaboc,trentolol";

                String[] step1Users =sampleUsers.split(",");

                ArrayList proUsers = new ArrayList();

                for (int x = 0; x < step1Users.length; x++) {
                    proUsers.add(step1Users[x]);
                }

         //       Based Josh
         //       Log.i("TEST", message);
         //       ParshPush push = new ParsePush();
         //       push.setMessage(message);
         //       push.setQuery(matchingNums);
         //       push.sendInBackground();


                ParseObject ques = new ParseObject("Question");
                ques.put("question", message);
                ques.put("sender", "---");
                ques.put("recipients", proUsers);
                ques.saveInBackground();





            }
        });


        //Parse.initialize(this, "SU7KXmgEwwa1zIlH0n5p36rQHpto6XMGiYfsLxyH", "TfmTRsQuZivXWmKXNN34nYbNF0ogouHm7FHelkN7");
        //ParseInstallation.getCurrentInstallation().saveInBackground();
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
            Intent intent = new Intent(this, SidebarHomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_friend) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
