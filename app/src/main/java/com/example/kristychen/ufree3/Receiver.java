package com.example.kristychen.ufree3;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseAnalytics;
import com.parse.ParsePushBroadcastReceiver;

import org.json.*;

import android.app.*;
import android.net.*;
import android.os.*;



import android.content.*;
import android.util.Log;

/**
 * Created by trentolol on 1/24/16.
 */
public class Receiver extends ParsePushBroadcastReceiver {

    @Override
    protected void onPushOpen(Context context, Intent intent) {

        ParseAnalytics.trackAppOpenedInBackground(intent);

        String uriString = null;
        try {
            JSONObject pushData = new JSONObject(intent.getStringExtra("com.parse.Data"));
            uriString = pushData.optString("uri");
        } catch (JSONException e) {
            Log.v("com.parse.ParsePushReceiver", "Unexpected JSONException when receiving push data: ", e);
        }
        Class<? extends Activity> cls = getActivity(context, intent);
        Intent activityIntent;
        if (uriString != null && !uriString.isEmpty()) {
            activityIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
        } else {
            activityIntent = new Intent(context, sendActivity.class);
        }
        activityIntent.putExtras(intent.getExtras());
        if (Build.VERSION.SDK_INT >= 16) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(cls);
            stackBuilder.addNextIntent(activityIntent);
            stackBuilder.startActivities();
        } else {
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(activityIntent);
        }
    }
}