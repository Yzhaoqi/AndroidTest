package yzq.com.androidtest.activity_shared_preference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import yzq.com.androidtest.R;

public class ActivityTestSharedPreference extends AppCompatActivity {

    private static final String MY_SHARED = "my_shared";

    private Button push, get;
    private EditText key, value, inputKey;
    private TextView textValue;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shared_preference);

        sharedPreferences = getSharedPreferences(MY_SHARED, 0);
        push = (Button) findViewById(R.id.button_push);
        get = (Button) findViewById(R.id.button_get);
        key = (EditText) findViewById(R.id.edit_key);
        value = (EditText) findViewById(R.id.edit_value);
        inputKey = (EditText) findViewById(R.id.edit_input_key);
        textValue = (TextView) findViewById(R.id.text_value);
    }

    @Override
    protected void onStart() {
        super.onStart();
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myKey = key.getText().toString();
                String myValue = value.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(myKey, myValue);
                editor.apply();
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myKey = inputKey.getText().toString();
                textValue.setText(sharedPreferences.getString(myKey, "NOT FOUND"));
            }
        });
    }
}
