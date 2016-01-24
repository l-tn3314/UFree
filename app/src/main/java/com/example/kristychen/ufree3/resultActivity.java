package com.example.kristychen.ufree3;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import java.util.List;
import java.util.ArrayList;


import butterknife.ButterKnife;

/**
 * Created by kristychen on 1/24/16.
 */
public class resultActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar_results);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String sender = ParseUser.getCurrentUser().getString("username");


        text = (TextView) findViewById(R.id.txt);

        ParseQuery<ParseInstallation> database = ParseQuery.getQuery(ParseInstallation.class);


        ParseQuery<ParseObject> dbr = ParseQuery.getQuery("Response");
        ParseQuery<ParseObject> dbq = ParseQuery.getQuery("Question");
        String user = ParseUser.getCurrentUser().getString("username");



        dbq.whereEqualTo("sender",user).orderByDescending("createdAt");
     //   dbq.findInBackground(new FindCallback<ParseObject>() {
        //    @Override
          //  public void done(List<ParseObject> objects, ParseException e) {
                ParseQuery<ParseObject> dbq1 = ParseQuery.getQuery("Question");
        try {
            String ques = dbq.getFirst().getString("question");

            dbr.whereEqualTo("question", ques); //query with y/n

            ParseQuery<ParseObject> dbry = dbr.whereEqualTo("answer","Yes");

            dbry.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    List<String> responders = new ArrayList<String>();
                    for (ParseObject obj : objects) {
                        responders.add(obj.getString("responder"));
                    }
                    String joined = TextUtils.join(",",responders);
                    System.out.println(joined);
                    System.out.println(responders);
                }
            });
        } catch(Exception e){}
          //  }
      //  });

        database.whereEqualTo("username", sender);

        System.out.println(database.whereEqualTo("username", sender));

        text.append("Tina       Yes\n" + "Gabe      No");


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


