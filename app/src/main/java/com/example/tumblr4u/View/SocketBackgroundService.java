package com.example.tumblr4u.View;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.tumblr4u.GeneralPurpose.Prefs;
import com.example.tumblr4u.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

// https://stackoverflow.com/questions/40603404/how-to-handle-socket-events-as-background-service
// -in-android
/**
 * This class runs in the background to listen to events that come from connection
 * An instance is made once the user has access (after login or signup)
 * */
public class SocketBackgroundService extends Service {
    private boolean isRunning;
    private Thread backgroundThread;
    private final static String TAG = "SocketBackgroundService";
    private Socket mSocket;

    {
        try {
            // TODO change URL
            mSocket = IO.socket("http://tumblr4u.eastus.cloudapp.azure.com:5000/");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public SocketBackgroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    /**
     * This function is called implicitly at start of the Service
     * It adds on event listeners to the socket object and starts connection
     * */
    @Override
    public void onCreate() {
        this.isRunning = false;
        this.backgroundThread = new Thread(myTask);

        // should this part go in the run method?

        mSocket.on(Socket.EVENT_CONNECT, args -> {
            Log.i(TAG, "event connect triggered");

            // ----------- socket emit trial ------------
            String token = Prefs.getToken(getApplication());
            try {
                JSONObject jsonObject = new JSONObject().put("token", token);
                Log.i(TAG, jsonObject.toString());
                mSocket.emit("join-room", jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        });
        mSocket.on(Socket.EVENT_CONNECT_ERROR, args -> {
            Log.i(TAG, "error connecting to socket");
        });
        mSocket.on(Socket.EVENT_DISCONNECT, args -> {
            Log.i(TAG, "Socket disconnected");
        });
        mSocket.on("test", args -> {
            for (Object arg : args) {
                Log.i(TAG, (String) arg);
            }
            addNotification("test", "Connection is done");
        });
        mSocket.on("update-notification-list", args -> {
            for (Object arg : args) {
                addNotification("notification", arg.toString().substring(arg.toString().indexOf("content")));
            }
        });
        mSocket.on("test1", args -> {
            for (Object arg : args) {
                Log.i(TAG, (String) arg);
            }
        });
        mSocket.on("test2", args -> {
            for (Object arg : args) {
                Log.i(TAG, (String) arg);
            }
        });
        mSocket.on("test3",args -> {
            for(Object arg:args){
                Log.i(TAG, (String) arg);
            }
        });
        mSocket.on("joined-room", args -> {
            for (Object arg : args) {
                Log.i(TAG, arg.toString());
            }
        });
        mSocket.connect();
    }

    private Runnable myTask = new Runnable() {
        @Override
        public void run() {
            Log.i(TAG, "Socket background service is running");

        }
    };

    private void addNotification(String title, String text) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "1");

        Intent ii = new Intent(getApplicationContext(), this.getClass());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, ii, 0);

        mBuilder.setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(Notification.PRIORITY_MAX);
        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));


        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }
        notificationManager.notify(0, mBuilder.build());
    }

    @Override
    public void onDestroy() {
        this.isRunning = false;
    }
    /**
     * Creates a new thread to run in the background
     * */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!this.isRunning) {
            this.isRunning = true;
            this.backgroundThread.start();
        }
        return START_STICKY;
    }
}