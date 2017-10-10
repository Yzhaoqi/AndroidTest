package yzq.com.androidtest.activity_broadcast;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import yzq.com.androidtest.R;

public class MyReceiver extends BroadcastReceiver {

    public static final String DEFAULT = ".MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DEFAULT)) {
            NotificationManager notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent notifyIntent = new Intent(context, ActivityTestBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setContentTitle(intent.getExtras().getString("msg"))
                    .setContentIntent(pendingIntent);
            notifyManager.notify(1, mBuilder.build());
        }
    }
}
