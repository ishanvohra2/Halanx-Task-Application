package com.ishanvohra.halanxtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.ishanvohra.halanxtask.Adapter.SwipeToDeleteCallback;
import com.ishanvohra.halanxtask.Adapter.ViewBillsAdapter;
import com.ishanvohra.halanxtask.Model.GetBillResponse;
import com.ishanvohra.halanxtask.Model.GetBillsResponse;
import com.ishanvohra.halanxtask.Model.Result;
import com.ishanvohra.halanxtask.R;
import com.ishanvohra.halanxtask.ViewModel.DeleteBillViewModel;
import com.ishanvohra.halanxtask.ViewModel.GetBillsViewModel;

import java.util.ArrayList;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class ViewBillsActivity extends AppCompatActivity implements ViewBillsAdapter.RvListener {

    private ViewBillsAdapter adapter;
    private static String TAG = "ViewBillsActivity";
    private String username, password;

    private ConstraintLayout bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;

    private GetBillsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bills);

        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator);

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
        username = pref.getString("username","");
        password = pref.getString("password", "");

        viewModel = new ViewModelProvider(ViewBillsActivity.this).get(GetBillsViewModel.class);
        viewModel.init(username, password);

        adapter = new ViewBillsAdapter(this, new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        if(!username.isEmpty()){
            viewModel.init(username, password);
            viewModel.getBills().observe(ViewBillsActivity.this, new Observer<GetBillsResponse>() {
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

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        TextView viewPastBills = bottomSheet.findViewById(R.id.bottom_sheet_past_bills_tv);
        TextView createNewBill = bottomSheet.findViewById(R.id.bottom_sheet_create_tv);
        TextView deleteBills = bottomSheet.findViewById(R.id.design_bottom_sheet_delete_tv);

        viewPastBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        deleteBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Swipe any bill to delete it.", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        createNewBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewBillsActivity.this, CreateNewBillActivity.class));
            }
        });
    }

    @Override
    public void deleteBill(Result bill, int pos) {
        DeleteBillViewModel viewModel = new ViewModelProvider(ViewBillsActivity.this).get(DeleteBillViewModel.class);
        viewModel.init();
        viewModel.deleteBill(username, password, bill.getId()).observe(this, new Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody responseBody) {

            }
        });
    }

    @Override
    public void fetchBill(Result bill) {
        Toast.makeText(this, "" + bill.getId(), Toast.LENGTH_SHORT).show();
        viewModel.getBill(bill.getId()).observe(this, new Observer<GetBillResponse>() {
            @Override
            public void onChanged(GetBillResponse getBillResponse) {
                if(getBillResponse != null)
                    Log.d(TAG, "onChanged: " + getBillResponse.getCreatedAt());
            }
        });
    }
}