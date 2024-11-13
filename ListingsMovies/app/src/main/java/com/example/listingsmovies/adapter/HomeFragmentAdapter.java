package com.example.listingsmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listingsmovies.databinding.ListItemBinding;
import com.example.listingsmovies.model.MoviesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> {

    private List<MoviesModel> mMovies;
    private Context context;

    public HomeFragmentAdapter(List<MoviesModel> mMovies, Context context) {
        this.mMovies = mMovies;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBinding binding = ListItemBinding.inflate(inflater, parent, false);
        return  new HomeFragmentAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragmentAdapter.ViewHolder holder, int position) {
        //elementos con su contenido
        MoviesModel listMovies = mMovies.get(position);

        Picasso
                .get()
                .load(listMovies.getImagen())
                .into(holder.imageMovie);

        holder.nameMovie.setText(listMovies.getTitulo());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ListItemBinding binding;
        public CardView cardMovie;
        public ImageView imageMovie;
        public TextView nameMovie;


        public ViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            cardMovie = binding.cardItem;
            imageMovie = binding.imgMovie;
            nameMovie = binding.titleMovie;

        }
    }
}
