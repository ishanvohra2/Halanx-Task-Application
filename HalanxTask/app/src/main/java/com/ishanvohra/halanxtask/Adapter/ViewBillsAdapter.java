package com.ishanvohra.halanxtask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ishanvohra.halanxtask.Model.GetBillsResponse;
import com.ishanvohra.halanxtask.Model.Result;
import com.ishanvohra.halanxtask.R;

import java.util.ArrayList;

public class ViewBillsAdapter extends RecyclerView.Adapter<ViewBillsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Result> dataSet;
    private RvListener rvListener;

    public interface RvListener{
        public void deleteBill(Result bill, int pos);
        public void fetchBill(Result bill);
    }

    public void setBills(ArrayList<Result> dataSet){
        this.dataSet = dataSet;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTv, tenantTv, dateTv, priceTv;
        private ImageView imageView;

        public MyViewHolder(View itemView){
            super(itemView);
            categoryTv = itemView.findViewById(R.id.bill_item_category_tv);
            tenantTv = itemView.findViewById(R.id.bill_item_tenant_tv);
            dateTv = itemView.findViewById(R.id.bill_item_date_tv);
            imageView = itemView.findViewById(R.id.bill_item_iv);
            priceTv = itemView.findViewById(R.id.bill_item_money_tv);
        }
    }

    public ViewBillsAdapter(Context context, ArrayList<Result> dataSet, RvListener rvListener){
        this.context = context;
        this.dataSet = dataSet;
        this.rvListener = rvListener;
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
        holder.priceTv.setText("Rs. " + response.getAmount());

        switch (response.getCategory().getId()){
            case 1:
                holder.imageView.setImageDrawable(context.getDrawable(R.drawable.ic_water));
                break;
            case 2:
                holder.imageView.setImageDrawable(context.getDrawable(R.drawable.ic_electricity));
                break;
            case 3:
                holder.imageView.setImageDrawable(context.getDrawable(R.drawable.ic_gas));
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvListener.fetchBill(response);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void deleteItem(int position) {
        rvListener.deleteBill(dataSet.get(position), position);
    }

}
