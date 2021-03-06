package com.example.dierbian.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.dierbian.R;


public class MyAdapter extends DelegateAdapter.Adapter {

    private SingleLayoutHelper singleLayoutHelper;

    public MyAdapter(SingleLayoutHelper singleLayoutHelper) {
        this.singleLayoutHelper = singleLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return singleLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, null, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder holder1 = (ItemViewHolder) holder;
        holder1.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder1.btn.setText("别点了，啥也没有");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

      //  private final LinearLayout ll;
        private final Button btn;

        public ItemViewHolder(View view) {
            super(view);
         //   ll = view.findViewById(R.id.item_ll);
            btn = view.findViewById(R.id.btn);
        }
    }
}
