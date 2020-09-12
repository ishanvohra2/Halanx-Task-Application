package com.ishanvohra.halanxtask.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.ishanvohra.halanxtask.Adapter.CategoryAdapter;
import com.ishanvohra.halanxtask.Adapter.HouseAdapter;
import com.ishanvohra.halanxtask.Adapter.TenantAdapter;
import com.ishanvohra.halanxtask.Model.Category;
import com.ishanvohra.halanxtask.Model.CreateBillBody;
import com.ishanvohra.halanxtask.Model.CreateBillResponse;
import com.ishanvohra.halanxtask.Model.GetHouseResponse;
import com.ishanvohra.halanxtask.Model.GetTenantResponse;
import com.ishanvohra.halanxtask.Model.HouseResult;
import com.ishanvohra.halanxtask.R;
import com.ishanvohra.halanxtask.ViewModel.CreateBillViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateNewBillActivity extends AppCompatActivity {

    private String username, password;
    private int categoryIndex = 1;
    private int houseIndex = 0, tenantIndex = 0;
    private String dateString = "";
    private static String TAG = "CreateNewBillActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_bill);

        AutoCompleteTextView houseTv = findViewById(R.id.create_bill_house_tv);
        AutoCompleteTextView tenantTv = findViewById(R.id.create_bill_tenant_tv);
        TextView dueDateEt = findViewById(R.id.create_bill_due_date_et);
        TextInputEditText amountEt = findViewById(R.id.create_bill_amount_et);

        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        username = pref.getString("username","");
        password = pref.getString("password", "");

        CreateBillViewModel viewModel = new ViewModelProvider(this).get(CreateBillViewModel.class);
        viewModel.init(username, password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateNewBillActivity.this, ViewBillsActivity.class));
                finish();
            }
        });

        //Loading categories for bills
        Spinner categorySpinner = findViewById(R.id.create_bill_category_spinner);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, new ArrayList<>());
        categorySpinner.setAdapter(categoryAdapter);

        viewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryAdapter.setCategories((ArrayList<Category>) categories);
                categoryAdapter.notifyDataSetChanged();
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category category = (Category) categoryAdapter.getItem(i);
                categoryIndex = category.getId();

                Log.d(TAG, "onItemSelected: " + category.getName() + " index : " + category.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //loading up the houses for autocomplete text view
        HouseAdapter houseAdapter = new HouseAdapter(this, R.layout.auto_complete_layout, new ArrayList<>());
        houseTv.setThreshold(1);
        houseTv.setAdapter(houseAdapter);

        viewModel.getHouses().observe(this, new Observer<GetHouseResponse>() {
            @Override
            public void onChanged(GetHouseResponse getHouseResponse) {
                if(getHouseResponse != null)
                    houseAdapter.setHouses((ArrayList<HouseResult>) getHouseResponse.getResults());
            }
        });

        houseTv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HouseResult house = houseAdapter.getItem(i);
                houseIndex = house.getId();
            }
        });

        //loading up active tenants
        TenantAdapter tenantAdapter = new TenantAdapter(this, R.layout.auto_complete_layout, new ArrayList<>());
        tenantTv.setThreshold(1);
        tenantTv.setAdapter(tenantAdapter);

        viewModel.getTenants().observe(this, new Observer<List<GetTenantResponse>>() {
            @Override
            public void onChanged(List<GetTenantResponse> getTenantResponses) {
                if(getTenantResponses != null)
                    tenantAdapter.setTenants((ArrayList<GetTenantResponse>) getTenantResponses);
            }
        });

        tenantTv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tenantIndex = tenantAdapter.getItem(i).getId();
                Toast.makeText(CreateNewBillActivity.this,  tenantTv.getText().toString() + " index : " + tenantIndex, Toast.LENGTH_SHORT).show();
            }
        });

        //Selecting Due Date
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select Due Date");

        MaterialDatePicker picker = builder.build();

        //Date picker
        dueDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.show(getSupportFragmentManager(), picker.toString());
            }
        });

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                dueDateEt.setText(picker.getHeaderText());

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date((Long) selection);
                dateString = format.format(date);
            }
        });

        //Creating the bill
        Button createBtn = findViewById(R.id.create_bill_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(houseIndex == 0){
                    houseTv.setError("Please select the house");
                    return;
                }

                if(dateString.isEmpty()){
                    dueDateEt.setError("Please select the due date");
                    return;
                }

                if(tenantIndex == 0){
                    tenantTv.setError("Please select the tenant");
                    return;
                }

                if(TextUtils.isEmpty(amountEt.getText().toString())){
                    tenantTv.setError("Please enter the amount");
                    return;
                }

                CreateBillBody body = new CreateBillBody();
                body.setAmount(Integer.parseInt(amountEt.getText().toString()));
                body.setCategory(categoryIndex);
                body.setDueDate(dateString);
                body.setHouse(houseIndex);
                body.setTenant(tenantIndex);

                Log.d(TAG, "Amount : "+ body.getAmount());
                Log.d(TAG, "Category: " + body.getCategory());
                Log.d(TAG, "dueDate: " + body.getDueDate());
                Log.d(TAG, "house: " + body.getHouse());
                Log.d(TAG, "tenant: " + body.getTenant());

                viewModel.createBill(body).observe(CreateNewBillActivity.this, new Observer<CreateBillResponse>() {
                    @Override
                    public void onChanged(CreateBillResponse createBillResponse) {
                        if(createBillResponse != null){
                            Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), "Bill created", Snackbar.LENGTH_LONG);
                            snackbar.show();
                            startActivity(new Intent(CreateNewBillActivity.this, ViewBillsActivity.class));
                            finish();
                        }
                    }
                });
            }
        });

    }
}