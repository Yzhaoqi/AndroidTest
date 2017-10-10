package yzq.com.androidtest.activity_luanch_mode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import yzq.com.androidtest.R;

public class ActivityTestB extends AppCompatActivity {

    private Button nextA, nextB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_b);

        nextA = (Button) findViewById(R.id.button_jump_b_a);
        nextB = (Button) findViewById(R.id.button_jump_b_b);
        nextA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTestB.this, ActivityTestA.class);
                startActivity(intent);
            }
        });
        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTestB.this, ActivityTestB.class);
                startActivity(intent);
            }
        });
    }
}
