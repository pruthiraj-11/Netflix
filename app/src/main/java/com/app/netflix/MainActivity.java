package com.app.netflix;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.netflix.Adapters.FilmListAdapter;
import com.app.netflix.Models.ListFilm;
import com.app.netflix.databinding.ActivityMainBinding;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private RecyclerView.Adapter adapterUpcoming, adapterNewMovie;
    private RequestQueue requestQueue;
    private StringRequest stringRequest,stringRequest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.newview3.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.upcomingview2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        sendRequestNew();
        sendRequestUpcoming();
    }
    private void sendRequestNew() {
        requestQueue= Volley.newRequestQueue(this);
        binding.progressBar1.setVisibility(View.VISIBLE);
        stringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson=new Gson();
            binding.progressBar1.setVisibility(View.GONE);
            ListFilm items=gson.fromJson(response, ListFilm.class);
            adapterNewMovie=new FilmListAdapter(items);
            binding.newview3.setAdapter(adapterNewMovie);
        }, error -> binding.progressBar1.setVisibility(View.GONE));
        requestQueue.add(stringRequest);
    }

    private void sendRequestUpcoming() {
        requestQueue= Volley.newRequestQueue(this);
        binding.progressBar2.setVisibility(View.VISIBLE);
        stringRequest2=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response -> {
            Gson gson=new Gson();
            binding.progressBar2.setVisibility(View.GONE);
            ListFilm items=gson.fromJson(response, ListFilm.class);
            adapterUpcoming=new FilmListAdapter(items);
            binding.upcomingview2.setAdapter(adapterUpcoming);
        }, error -> binding.progressBar2.setVisibility(View.GONE));
        requestQueue.add(stringRequest2);
    }
}