package yzq.com.androidtest.activity_async;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import yzq.com.androidtest.R;

public class ActivityTestAsync extends AppCompatActivity {

    private Button thread, handler, asyncTask;
    private TextView text;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    text.setText("handler done");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_async);

        thread = (Button) findViewById(R.id.button_thread);
        handler = (Button) findViewById(R.id.button_handler);
        asyncTask = (Button) findViewById(R.id.button_async_task);
        text = (TextView) findViewById(R.id.text_result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            text.post(new Runnable() {
                                @Override
                                public void run() {
                                    text.setText("thread done");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            Message msg = new Message();
                            msg.what = 0;
                            mHandler.sendMessage(msg);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        asyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestAsyncTask().execute();
            }
        });
    }

    private class TestAsyncTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "async task done";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            text.setText(s);
        }
    }
}
