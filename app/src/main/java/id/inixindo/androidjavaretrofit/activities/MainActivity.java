package id.inixindo.androidjavaretrofit.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.inixindo.androidjavaretrofit.R;
import id.inixindo.androidjavaretrofit.adapters.AdapterRiders;
import id.inixindo.androidjavaretrofit.apis.ApiRequestData;
import id.inixindo.androidjavaretrofit.apis.RetrofitServer;
import id.inixindo.androidjavaretrofit.models.ModelResponse;
import id.inixindo.androidjavaretrofit.models.ModelRiders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewRiders;
    private FloatingActionButton fabAddRider;
    private ProgressBar progressBarRider;

    private RecyclerView.Adapter adapterRider;
    private RecyclerView.LayoutManager layoutManagerRider;
    private List<ModelRiders> listRiders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // actionbar
        Toolbar toolbarMainActivity = findViewById(R.id.toolbarMainActivity);
        toolbarMainActivity.setTitle("Riders List");
        toolbarMainActivity.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbarMainActivity);

        // swipe to refresh
        SwipeRefreshLayout swipeRefreshRider = findViewById(R.id.swipeRefreshRider);
        swipeRefreshRider.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshRider.setRefreshing(false);
                        getAllRiders();
                    }
                }, 3000);
            }
        });

        recyclerViewRiders = findViewById(R.id.recyclerViewRiders);
        fabAddRider = findViewById(R.id.fabAddRider);
        progressBarRider = findViewById(R.id.progressBarRider);

        layoutManagerRider = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewRiders.setLayoutManager(layoutManagerRider);

        fabAddRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddRiderActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllRiders();
    }

    public void getAllRiders() {
        progressBarRider.setVisibility(View.VISIBLE);

        ApiRequestData apiRequestData = RetrofitServer.connectRetrofit().create(ApiRequestData.class);
        Call<ModelResponse> responseCall = apiRequestData.getAllData();
        responseCall.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                listRiders = response.body().getData();

                adapterRider = new AdapterRiders(MainActivity.this, listRiders);
                recyclerViewRiders.setAdapter(adapterRider);
                adapterRider.notifyDataSetChanged();

                progressBarRider.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed connect to server!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}