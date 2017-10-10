package yzq.com.androidtest;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import yzq.com.androidtest.activity_async.ActivityTestAsync;
import yzq.com.androidtest.activity_broadcast.ActivityTestBroadcast;
import yzq.com.androidtest.activity_luanch_mode.ActivityTestA;
import yzq.com.androidtest.activity_recycler_view.ActivityRecyclerView;
import yzq.com.androidtest.activity_service.ActivityTestService;
import yzq.com.androidtest.activity_shared_preference.ActivityTestSharedPreference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Button> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons.add((Button) findViewById(R.id.button_test_launch));
        buttons.add((Button) findViewById(R.id.button_test_intent));
        buttons.add((Button) findViewById(R.id.button_test_service));
        buttons.add((Button) findViewById(R.id.button_test_broadcast));
        buttons.add((Button) findViewById(R.id.button_test_async));
        buttons.add((Button) findViewById(R.id.button_test_shared_preference));
        buttons.add((Button) findViewById(R.id.button_recycler_view));
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_test_launch:
                Intent intent = new Intent(MainActivity.this, ActivityTestA.class);
                startActivity(intent);
                break;
            case R.id.button_test_intent:
                Uri uri = Uri.parse("tel:123123");
                Intent implicit_intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(implicit_intent);
                break;
            case R.id.button_test_service:
                Intent serviceIntent = new Intent(MainActivity.this, ActivityTestService.class);
                startActivity(serviceIntent);
                break;
            case R.id.button_test_broadcast:
                Intent broadcastIntent = new Intent(MainActivity.this, ActivityTestBroadcast.class);
                startActivity(broadcastIntent);
                break;
            case R.id.button_test_async:
                Intent asyncIntent = new Intent(MainActivity.this, ActivityTestAsync.class);
                startActivity(asyncIntent);
                break;
            case R.id.button_test_shared_preference:
                Intent sharedIntent = new Intent(MainActivity.this, ActivityTestSharedPreference.class);
                startActivity(sharedIntent);
                break;
            case R.id.button_recycler_view:
                Intent recyclerIntent = new Intent(MainActivity.this, ActivityRecyclerView.class);
                startActivity(recyclerIntent);
                break;
            default:
                break;
        }
    }
}
