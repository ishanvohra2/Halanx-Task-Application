package com.ishanvohra.halanxtask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ishanvohra.halanxtask.Model.Category;
import com.ishanvohra.halanxtask.R;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Category> categories;
    private LayoutInflater inflater;

    public CategoryAdapter(Context context, ArrayList<Category> categories){
        this.context = context;
        this.categories = categories;
        inflater = LayoutInflater.from(context);
    }

    public void setCategories(ArrayList<Category> categories){
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.category_item, null);
        TextView textView = view.findViewById(R.id.category_tv);
        textView.setText(categories.get(i).getName());

        return view;
    }
}
