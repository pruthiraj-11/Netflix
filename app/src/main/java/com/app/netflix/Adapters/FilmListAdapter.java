package com.app.netflix.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.netflix.DetailActivity;
import com.app.netflix.Models.ListFilm;
import com.app.netflix.R;
import com.bumptech.glide.Glide;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {
    ListFilm listFilm;
    Context context;

    public FilmListAdapter(ListFilm listFilm) {
        this.listFilm = listFilm;
    }

    @NonNull
    @Override
    public FilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        context= parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listFilm.getData().get(position).getTitle());
        holder.score.setText(""+listFilm.getData().get(position).getImdbRating());
        Glide.with(holder.itemView.getContext()).load(listFilm.getData().get(position).getPoster()).into(holder.imageView);

        holder.itemView.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("id",listFilm.getData().get(position).getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listFilm.getData().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title,score;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.pic);
            title=itemView.findViewById(R.id.itemTitle);
            score=itemView.findViewById(R.id.scoreTxt);
        }
    }
}
