package yzq.com.androidtest.activity_recycler_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import yzq.com.androidtest.R;

public class ActivityRecyclerView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button addItem;
    private MyAdapter mAdapter;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.layout_recycler_view);
        addItem = (Button) findViewById(R.id.button_add_item);
        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addItem(num++, (int)(100+400*Math.random()));
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new MyAdapter(initItems());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private ArrayList<Item> initItems() {
        ArrayList<Item> items = new ArrayList<>();
        for (num = 0; num < 8; num++) {
            Item item = new Item();
            item.setNum(num);
            item.setHeight((int)(100+400*Math.random()));
            items.add(item);
        }
        return items;
    }
}
