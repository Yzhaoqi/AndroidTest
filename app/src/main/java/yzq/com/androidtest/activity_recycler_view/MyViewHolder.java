package yzq.com.androidtest.activity_recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import yzq.com.androidtest.R;

/**
 * Created by YZQ on 2017/10/10.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView text;

    public MyViewHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.text_num);
    }

    public TextView getText() {
        return text;
    }
}
