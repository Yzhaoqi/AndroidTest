package yzq.com.androidtest.activity_service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import yzq.com.androidtest.MainActivity;
import yzq.com.androidtest.R;

public class TestService extends Service {
    public TestService() {
    }

    private final IBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void Foreground() {
        Notification notification;
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(TestService.this)
                .setContentTitle("Test Service")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(pendingIntent);
        notification = mBuilder.build();
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Foreground();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Foreground();
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(TestService.this, "service unbinded", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    public void showToast() {
        Toast.makeText(TestService.this, "service binded", Toast.LENGTH_SHORT).show();
    }

    public void sleep() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                    Toast.makeText(TestService.this, "work done", Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public class MyBinder extends Binder {
        TestService getService() {
            return TestService.this;
        }
    }
}
