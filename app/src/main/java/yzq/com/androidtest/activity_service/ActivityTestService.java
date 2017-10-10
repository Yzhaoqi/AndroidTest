package yzq.com.androidtest.activity_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import yzq.com.androidtest.R;

public class ActivityTestService extends AppCompatActivity {

    private Button start, stop, bind, unbind, work;
    private TestService ts;

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ts = ((TestService.MyBinder)service).getService();
            ts.showToast();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);

        start = (Button) findViewById(R.id.button_start_service);
        stop = (Button) findViewById(R.id.button_stop_service);
        bind = (Button) findViewById(R.id.button_bind_service);
        unbind = (Button) findViewById(R.id.button_unbind_service);
        work = (Button) findViewById(R.id.button_work);
    }

    @Override
    protected void onStart() {
        super.onStart();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTestService.this, TestService.class);
                startService(intent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTestService.this, TestService.class);
                stopService(intent);
            }
        });

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTestService.this, TestService.class);
                bindService(intent, sc, Context.BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(sc);
            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ts != null) ts.sleep();
            }
        });
    }
}
