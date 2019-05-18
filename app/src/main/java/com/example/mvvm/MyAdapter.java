package com.example.mvvm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private List<MyTable> tableList;

    public MyAdapter(Context context) {

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.recyclerview_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if(tableList != null){
            MyTable current = tableList.get(i);
            myViewHolder.textView.setText(current.getIdentification());
        }else{
            myViewHolder.textView.setText("no item");
        }
    }

    public void setTableList(List<MyTable> tableList) {
        this.tableList = tableList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(tableList != null) {
            return tableList.size();
        }else{
            return 0;
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);

        }
    }
}
