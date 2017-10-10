package yzq.com.androidtest.activity_broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import yzq.com.androidtest.R;
import yzq.com.androidtest.activity_service.ActivityTestService;

public class ActivityTestBroadcast extends AppCompatActivity {

    private Button register, unregister, send;
    private EditText edit;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast);

        register = (Button) findViewById(R.id.button_register);
        unregister = (Button) findViewById(R.id.button_unregister);
        send = (Button) findViewById(R.id.broadcast_send);
        edit = (EditText) findViewById(R.id.broadcast_edit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myReceiver = new MyReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(MyReceiver.DEFAULT);
                registerReceiver(myReceiver, intentFilter);
            }
        });

        unregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver(myReceiver);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit.getText().toString();
                Intent intent = new Intent(MyReceiver.DEFAULT);
                intent.putExtra("msg", s);
                sendBroadcast(intent);
            }
        });
    }
}
