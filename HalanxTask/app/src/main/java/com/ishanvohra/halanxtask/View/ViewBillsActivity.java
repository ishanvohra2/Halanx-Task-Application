package com.ishanvohra.halanxtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.ishanvohra.halanxtask.Adapter.ViewBillsAdapter;
import com.ishanvohra.halanxtask.Model.GetBillsResponse;
import com.ishanvohra.halanxtask.Model.Result;
import com.ishanvohra.halanxtask.R;
import com.ishanvohra.halanxtask.ViewModel.GetBillsViewModel;

import java.util.ArrayList;

public class ViewBillsActivity extends AppCompatActivity {

    private ViewBillsAdapter adapter;
    private static String TAG = "ViewBillsActivity";

    private ConstraintLayout bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bills);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewBillsActivity.this, LoginActivity.class));
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.view_bills_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String username = pref.getString("username","");
        String password = pref.getString("password", "");

        GetBillsViewModel viewModel = new ViewModelProvider(ViewBillsActivity.this).get(GetBillsViewModel.class);
        viewModel.init();

        adapter = new ViewBillsAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        if(!username.isEmpty()){
            viewModel.getBills(username, password).observe(ViewBillsActivity.this, new Observer<GetBillsResponse>() {
                @Override
                public void onChanged(GetBillsResponse getBillsResponse) {
                    if(getBillsResponse != null){
                        adapter.setBills((ArrayList<Result>) getBillsResponse.getResults());
                        adapter.notifyDataSetChanged();
                        Log.d(TAG, "Bills loaded into RV " + getBillsResponse.getCount());
                    }
                }
            });
        }

        bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        TextView viewPastBills = bottomSheet.findViewById(R.id.bottom_sheet_past_bills_tv);
        TextView createNewBill = bottomSheet.findViewById(R.id.bottom_sheet_create_tv);
        TextView deleteBills = bottomSheet.findViewById(R.id.design_bottom_sheet_delete_tv);
    }
}