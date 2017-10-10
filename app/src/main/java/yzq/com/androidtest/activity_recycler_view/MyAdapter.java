package yzq.com.androidtest.activity_recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import yzq.com.androidtest.R;

/**
 * Created by YZQ on 2017/10/10.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Item> mItems;
    private int p;

    MyAdapter(List<Item> items) {
        mItems = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        p = position;
        Item item = mItems.get(position);

        ViewGroup.LayoutParams lp = holder.getText().getLayoutParams();
        lp.height = item.getHeight();

        holder.getText().setLayoutParams(lp);
        holder.getText().setText(String.valueOf(item.getNum()));
        holder.getText().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                removeItem(Integer.valueOf(((TextView)v).getText().toString()));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItem(int num, int height) {
        int s= mItems.size();
        Item item = new Item();
        item.setNum(num);
        item.setHeight(height);
        mItems.add(s, item);
        notifyItemInserted(s);
    }

    public void removeItem(int num) {
        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).getNum() == num) {
                mItems.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }
}
