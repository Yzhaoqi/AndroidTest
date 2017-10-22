package yzq.com.androidtest.activity_view_action;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import yzq.com.androidtest.R;

import static java.lang.Thread.sleep;

public class ActivityTestViewAction extends AppCompatActivity {

    private static final String TAG = "ActivityTestViewAction";

    private ImageView viewAction;
    private Button button;
    private float mLastX, mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_action);

        viewAction = (ImageView) findViewById(R.id.action_image_view);
        button = (Button) findViewById(R.id.button_view_move);
        mLastX = viewAction.getX();
        mLastY = viewAction.getY();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewAction.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float deltaX = x - mLastX;
                        float deltaY = y - mLastY;
                        float translationX = v.getTranslationX() + deltaX;
                        float translationY = v.getTranslationY() + deltaY;
                        v.setTranslationX(translationX);
                        v.setTranslationY(translationY);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        break;
                }
                mLastX = x;
                mLastY = y;
                return true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float randomX = (float) (Math.random()*200-100);
                float randomY = (float) (Math.random()*200-100);
                AnimatorSet set = new AnimatorSet();
                set.playSequentially(
                        ObjectAnimator.ofFloat(viewAction, "translationX", viewAction.getTranslationX(), viewAction.getTranslationX() + randomX),
                        ObjectAnimator.ofFloat(viewAction, "translationY", viewAction.getTranslationY(), viewAction.getTranslationY() + randomY)
                );
                set.setDuration(1000).start();
            }
        });
    }
}
