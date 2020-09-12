package com.ishanvohra.halanxtask.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ishanvohra.halanxtask.Model.House;
import com.ishanvohra.halanxtask.Model.HouseResult;
import com.ishanvohra.halanxtask.R;

import java.util.ArrayList;

public class HouseAdapter extends ArrayAdapter<HouseResult> {

    private Context context;
    private ArrayList<HouseResult> houses, tempHouses, suggestions;
    private int resourceId;

    public HouseAdapter(@NonNull Context context, int resourceId, ArrayList<HouseResult> houses){
        super(context, resourceId, houses);
        this.houses = houses;
        this.context = context;
        this.resourceId = resourceId;
        tempHouses = new ArrayList<>();
        suggestions = new ArrayList<>();
    }

    public void setHouses(ArrayList<HouseResult> houses){
        this.houses = houses;
        this.tempHouses = new ArrayList<>(houses);
        suggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(resourceId, parent, false);
            }
            HouseResult houseResult = getItem(position);
            TextView name = view.findViewById(R.id.house_tv);
            name.setText(houseResult.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Nullable
    @Override
    public HouseResult getItem(int position) {
        return houses.get(position);
    }

    @Override
    public int getCount() {
        return houses.size();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return houseFilter;
    }

    private Filter houseFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            HouseResult result = (HouseResult) resultValue;
            return result.getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if(charSequence != null){
                suggestions.clear();
                for(HouseResult result : tempHouses){
                    if(result.getName().toLowerCase().startsWith(charSequence.toString().toLowerCase()))
                        suggestions.add(result);
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            }
            else{
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<HouseResult> tempValues = (ArrayList<HouseResult>) filterResults.values;
            if(filterResults != null && filterResults.count > 0){
                clear();
                for(HouseResult result : tempValues){
                    add(result);
                }
                notifyDataSetChanged();
            }
            else {
                clear();
                notifyDataSetChanged();
            }
        }
    };
}
