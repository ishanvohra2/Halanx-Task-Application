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

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.ishanvohra.halanxtask.Adapter.SwipeToDeleteCallback;
import com.ishanvohra.halanxtask.Adapter.ViewBillsAdapter;
import com.ishanvohra.halanxtask.Model.GetBillResponse;
import com.ishanvohra.halanxtask.Model.GetBillsResponse;
import com.ishanvohra.halanxtask.Model.MarkBillAsPaidBody;
import com.ishanvohra.halanxtask.Model.MarkBillAsPaidResponse;
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

    private ConstraintLayout bottomSheet, fetchBillSheet;
    private BottomSheetBehavior bottomSheetBehavior, fetchBillSheetBehaviour;

    private GetBillsViewModel viewModel;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bills);

        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator);
        shimmerFrameLayout = findViewById(R.id.shimmer);

        fetchBillSheet = findViewById(R.id.fetch_bill_bottom_sheet);
        fetchBillSheetBehaviour = BottomSheetBehavior.from(fetchBillSheet);

        Toolbar toolbar = findViewById(R.id.toolbar);

        RecyclerView recyclerView = findViewById(R.id.view_bills_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        username = "o" + pref.getString("username","");
        password = pref.getString("password", "");

        viewModel = new ViewModelProvider(ViewBillsActivity.this).get(GetBillsViewModel.class);
        viewModel.init(username, password);

        adapter = new ViewBillsAdapter(this, new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        if(!username.isEmpty()){
            viewModel.init(username, password);
            fetchAllBills();
        }

        bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        TextView viewPastBills = bottomSheet.findViewById(R.id.bottom_sheet_past_bills_tv);
        TextView createNewBill = bottomSheet.findViewById(R.id.bottom_sheet_create_tv);
        TextView deleteBills = bottomSheet.findViewById(R.id.design_bottom_sheet_delete_tv);
        TextView logOut = bottomSheet.findViewById(R.id.design_bottom_sheet_logout_tv);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("key");
                editor.remove("username");
                editor.remove("password");
                editor.apply();

                startActivity(new Intent(ViewBillsActivity.this,LoginActivity.class));
                finish();
            }
        });

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
                finish();
            }
        });
    }

    @Override
    public void deleteBill(Result bill, int pos) {
        MaterialAlertDialogBuilder builder =new MaterialAlertDialogBuilder(this);
        builder.setTitle("Delete bill");
        builder.setMessage("Are you sure you want to delete the bill?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DeleteBillViewModel viewModel = new ViewModelProvider(ViewBillsActivity.this).get(DeleteBillViewModel.class);
                viewModel.init();
                viewModel.deleteBill(username, password, bill.getId()).observe(ViewBillsActivity.this,
                        new Observer<ResponseBody>() {
                    @Override
                    public void onChanged(ResponseBody responseBody) {
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), "Bill deleted successfully", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        fetchAllBills();
                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fetchAllBills();
            }
        });
        builder.show();
    }

    @Override
    public void fetchBill(Result bill) {
        TextView tenantNameTv, tenantPhoneTv, addressTv, categoryTv, dueDateTv, amountTv;
        Button payBillBtn;

        payBillBtn = fetchBillSheet.findViewById(R.id.fetch_bill_pay_btn);
        tenantNameTv = fetchBillSheet.findViewById(R.id.fetch_bill_tenant_tv);
        tenantPhoneTv = fetchBillSheet.findViewById(R.id.fetch_bill_tenant_phone_tv);
        categoryTv = fetchBillSheet.findViewById(R.id.fetch_bill_category_tv);
        dueDateTv = fetchBillSheet.findViewById(R.id.fetch_bill_date_tv);
        amountTv = fetchBillSheet.findViewById(R.id.fetch_bill_money_tv);
        addressTv = fetchBillSheet.findViewById(R.id.fetch_bill_address_tv);
        Toolbar toolbar = fetchBillSheet.findViewById(R.id.toolbar);

        viewModel.getBill(bill.getId()).observe(this, new Observer<GetBillResponse>() {
            @Override
            public void onChanged(GetBillResponse getBillResponse) {
                if(getBillResponse != null){
                    fetchBillSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);

                    tenantNameTv.setText(getBillResponse.getTenant().getUser().getFirstName());
                    tenantPhoneTv.setText(getBillResponse.getTenant().getPhoneNo());
                    categoryTv.setText(getBillResponse.getCategory().getName());
                    dueDateTv.setText("Due Date : " + getBillResponse.getDueDate());
                    amountTv.setText("Amount To Be Paid. : " + getBillResponse.getAmount());
                    addressTv.setText(getBillResponse.getHouse().getAddress().getCompleteAddress());
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tenantNameTv.setText(getString(R.string.tenant_name));
                tenantPhoneTv.setText("9595959595");
                categoryTv.setText(getString(R.string.category));
                dueDateTv.setText("Due Date : ");
                amountTv.setText("Amount To Be Paid. : ");
                fetchBillSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        if(bill.getPaid())
            payBillBtn.setVisibility(View.GONE);

        payBillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MarkBillAsPaidBody body = new MarkBillAsPaidBody();
                body.setPaid(true);
                viewModel.markBillAsPaid(bill.getId(), body).observe(ViewBillsActivity.this, new Observer<MarkBillAsPaidResponse>() {
                    @Override
                    public void onChanged(MarkBillAsPaidResponse markBillAsPaidResponse) {
                        if(markBillAsPaidResponse != null){
                            Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), "Bill marked as paid successfully", Snackbar.LENGTH_LONG);
                            snackbar.show();
                            payBillBtn.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    private void fetchAllBills(){
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        viewModel.getBills().observe(ViewBillsActivity.this, new Observer<GetBillsResponse>() {
            @Override
            public void onChanged(GetBillsResponse getBillsResponse) {
                if(getBillsResponse != null){
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    adapter.setBills((ArrayList<Result>) getBillsResponse.getResults());
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "Bills loaded into RV " + getBillsResponse.getCount());
                }
            }
        });
    }
}