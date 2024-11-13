package com.example.listingsmovies.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.listingsmovies.adapter.HomeFragmentAdapter;
import com.example.listingsmovies.databinding.FragmentHomeBinding;
import com.example.listingsmovies.model.MoviesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DatabaseReference mDatabase;
    private List<MoviesModel> itemMovies, itemMovies2,itemMovies3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        itemMovies = new ArrayList<>();
        itemMovies2 = new ArrayList<>();
        itemMovies3 = new ArrayList<>();

        loadMovies();
        loadMovies2();
        loadMovies3();

        return root;
    }

    private void loadMovies(){
        mDatabase = FirebaseDatabase.getInstance().getReference("Peliculas/peliculaInfantil");
        Query moviesQuery = mDatabase.orderByChild("genero").equalTo("Animacion");

        moviesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                itemMovies.clear();

                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    MoviesModel item = snapshot.getValue(MoviesModel.class);
                    itemMovies.add(item);
                }

                HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(itemMovies, getContext());

                binding.recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                binding.recyclerViewMovie.setAdapter(homeFragmentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),   "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void loadMovies2(){
        mDatabase = FirebaseDatabase.getInstance().getReference("Peliculas/peliculaComedia");
        Query moviesQuery = mDatabase.orderByChild("genero").equalTo("comedia");

        moviesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                itemMovies2.clear();

                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    MoviesModel item = snapshot.getValue(MoviesModel.class);
                    itemMovies2.add(item);
                }

                HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(itemMovies2, getContext());

                binding.recyclerViewMovie2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                binding.recyclerViewMovie2.setAdapter(homeFragmentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),   "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMovies3(){
        mDatabase = FirebaseDatabase.getInstance().getReference("Peliculas/peliculasApp");
        Query moviesQuery = mDatabase.orderByChild("genero").equalTo("Accion");

        moviesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                itemMovies3.clear();

                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    MoviesModel item = snapshot.getValue(MoviesModel.class);
                    itemMovies3.add(item);
                }

                HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(itemMovies2, getContext());

                binding.recyclerViewMovie3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                binding.recyclerViewMovie3.setAdapter(homeFragmentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),   "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}