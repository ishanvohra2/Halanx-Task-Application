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

import com.ishanvohra.halanxtask.Model.GetTenantResponse;
import com.ishanvohra.halanxtask.R;

import java.util.ArrayList;

public class TenantAdapter extends ArrayAdapter<GetTenantResponse> {

    private Context context;
    private ArrayList<GetTenantResponse> houses, tempHouses, suggestions;
    private int resourceId;

    public TenantAdapter(@NonNull Context context, int resourceId, ArrayList<GetTenantResponse> houses){
        super(context, resourceId, houses);
        this.houses = houses;
        this.context = context;
        this.resourceId = resourceId;
        tempHouses = new ArrayList<>();
        suggestions = new ArrayList<>();
    }

    public void setTenants(ArrayList<GetTenantResponse> houses){
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
            GetTenantResponse response = getItem(position);
            TextView name = view.findViewById(R.id.house_tv);
            name.setText(response.getCustomer().getUser().getFirstName() + " " + response.getCustomer().getUser().getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Nullable
    @Override
    public GetTenantResponse getItem(int position) {
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
            GetTenantResponse result = (GetTenantResponse) resultValue;
            return result.getCustomer().getUser().getFirstName() + " " + result.getCustomer().getUser().getLastName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if(charSequence != null){
                suggestions.clear();
                for(GetTenantResponse result : tempHouses){
                    String name = result.getCustomer().getUser().getFirstName() + " " + result.getCustomer().getUser().getLastName();
                    if(name.toLowerCase().startsWith(charSequence.toString().toLowerCase()))
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
            ArrayList<GetTenantResponse> tempValues = (ArrayList<GetTenantResponse>) filterResults.values;

            if(filterResults != null && filterResults.count > 0){
                clear();
                for(GetTenantResponse result : tempValues){
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
