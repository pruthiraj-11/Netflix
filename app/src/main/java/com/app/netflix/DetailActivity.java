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
import com.app.netflix.Adapters.ImageListAdapter;
import com.app.netflix.Models.FilmItem;
import com.app.netflix.databinding.ActivityDetailBinding;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private int idFilm;
    private RecyclerView.Adapter adapterActorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        idFilm=getIntent().getIntExtra("id",0);
        binding.imagesRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.backbtn.setOnClickListener(v -> finish());
        sendRequest();
    }

    private void sendRequest() {
        requestQueue= Volley.newRequestQueue(this);
        binding.detailLoading.setVisibility(View.VISIBLE);
        binding.scrollView2.setVisibility(View.GONE);
        stringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {
            Gson gson=new Gson();
            binding.detailLoading.setVisibility(View.GONE);
            binding.scrollView2.setVisibility(View.VISIBLE);

            FilmItem item=gson.fromJson(response, FilmItem.class);
            Glide.with(DetailActivity.this).load(item.getPoster()).into(binding.posterSmallImg);
            Glide.with(DetailActivity.this).load(item.getPoster()).into(binding.posterBigImg);
            binding.movieName.setText(item.getTitle());
            binding.movieRate.setText(item.getImdbRating());
            binding.movieRuntime.setText(item.getRuntime());
            binding.movieSummary.setText(item.getPlot());
            binding.movieActorsList.setText(item.getActors());

            if(item.getImages()!=null){
                adapterActorList=new ImageListAdapter(item.getImages());
                binding.imagesRecyclerView.setAdapter(adapterActorList);
            }
//            if(item.getGenres()!=null){
//                adapterCategoryList=new GenresEachFilmListAdapter(item.getGenres());
//                binding.genreRecyclerView.setAdapter(adapterCategoryList);
//            }

        }, error -> binding.detailLoading.setVisibility(View.GONE));
        requestQueue.add(stringRequest);
    }
}