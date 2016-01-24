package com.example.kristychen.ufree3;

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
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.parse.Parse;

import com.parse.FindCallback;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseAnonymousUtils;
//import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import java.util.List;

public class loginActivity extends AppCompatActivity {

    Button signup;
    String usernametxt;
    String passwordtxt;
    EditText password;
    EditText username;

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // Get the view from main.xml
        setContentView(R.layout.app_bar_sidebar_login);
        // Locate EditTexts in main.xml
        username = (EditText) findViewById(R.id.loginuser);
        password = (EditText) findViewById(R.id.pwd);

        signup = (Button) findViewById(R.id.signup);

        // Login Button Click Listener
        signup.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();

                // Send data to Parse.com for verification
                // ParseUser.logInInBackground(usernametxt, passwordtxt,
                //       new LogInCallback() {
                //         public void done(ParseUser user, ParseException e) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                if (currentUser != null) {
                    // If user exist and authenticated, send user to Welcome.class
                    Intent intent = new Intent(
                            loginActivity.this,
                            SidebarHomeActivity.class);
                    startActivity(intent);

                    finish();
                } else {
                    ParseUser user = new ParseUser();
                    user.setUsername(usernametxt);
                    user.setPassword(passwordtxt);
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                Intent intent = new Intent(
                                        loginActivity.this,
                                        SidebarHomeActivity.class);
                                startActivity(intent);

                                finish();
                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Error",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });
    }
}

