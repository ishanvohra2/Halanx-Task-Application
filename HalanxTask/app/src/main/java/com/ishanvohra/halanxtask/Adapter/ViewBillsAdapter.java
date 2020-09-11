package com.ishanvohra.halanxtask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ishanvohra.halanxtask.Model.GetBillsResponse;
import com.ishanvohra.halanxtask.Model.Result;
import com.ishanvohra.halanxtask.R;

import java.util.ArrayList;

public class ViewBillsAdapter extends RecyclerView.Adapter<ViewBillsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Result> dataSet;

    public void setBills(ArrayList<Result> dataSet){
        this.dataSet = dataSet;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTv, tenantTv, dateTv;
        private ImageView imageView;

        public MyViewHolder(View itemView){
            super(itemView);
            categoryTv = itemView.findViewById(R.id.bill_item_category_tv);
            tenantTv = itemView.findViewById(R.id.bill_item_tenant_tv);
            dateTv = itemView.findViewById(R.id.bill_item_date_tv);
            imageView = itemView.findViewById(R.id.bill_item_iv);
        }
    }

    public ViewBillsAdapter(Context context, ArrayList<Result> dataSet){
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Result response = dataSet.get(position);

        holder.dateTv.setText(response.getCreatedAt());
        holder.tenantTv.setText(response.getTenant().getUser().getFirstName() + response.getTenant().getUser().getLastName());
        holder.categoryTv.setText(response.getCategory().getName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
