package com.example.listingsmovies.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listingsmovies.adapter.HomeFragmentAdapter;
import com.example.listingsmovies.databinding.FragmentDashboardBinding;
import com.example.listingsmovies.model.MoviesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DatabaseReference moviesRef;
    private List<MoviesModel> moviesModels;
    String llave = "Pelicula";
    //private String searchView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        moviesModels = new ArrayList<>();

        //searchMovie1();//String movie

        binding.btnSearchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieSearch();
            }
        });

        return root;
    }

    private void movieSearch(){
        String busquedaMovie = binding.searchInput.getText().toString().trim();

        if (busquedaMovie.isEmpty()){
            Toast.makeText(getContext(), "Escribe texto", Toast.LENGTH_SHORT).show();
        }else{
            Query filterQuery = FirebaseDatabase.getInstance().getReference("Peliculas/peliculasApp")
                    .orderByChild("titulo")
                    .equalTo(busquedaMovie);

            filterQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    moviesModels.clear();

                    if(datasnapshot.exists()){
                        for (DataSnapshot snapshot: datasnapshot.getChildren()){
                            MoviesModel item = snapshot.getValue(MoviesModel.class);

                            moviesModels.add(item);
                        }
                        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(moviesModels, getContext());
                        binding.recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        binding.recyclerViewSearch.setAdapter(homeFragmentAdapter);
                    }
                    movieSearch2(busquedaMovie);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "No existe conexion", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private void movieSearch2(String filterMovie){
        Query filterQuery2 = FirebaseDatabase.getInstance().getReference("Peliculas/peliculaComedia")
                .orderByChild("titulo").equalTo(filterMovie);

        filterQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                if(datasnapshot.exists()){

                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        MoviesModel item = snapshot.getValue(MoviesModel.class);
                        moviesModels.add(item);
                    }
                    HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(moviesModels, getContext());
                    binding.recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    binding.recyclerViewSearch.setAdapter(homeFragmentAdapter);
                }else{
                    searchMovie3(filterMovie);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "No existe la pelicula", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchMovie3(String filterMovie2) {
        Query filterQuery3 = FirebaseDatabase.getInstance().getReference("Peliculas/peliculaInfantil")
                .orderByChild("titulo").equalTo(filterMovie2);

        filterQuery3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){

                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        MoviesModel item = snapshot.getValue(MoviesModel.class);
                        moviesModels.add(item);
                    }
                    HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(moviesModels, getContext());
                    binding.recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    binding.recyclerViewSearch.setAdapter(homeFragmentAdapter);
                }else{
                    moviesModels.clear();
                    Toast.makeText(getContext(), "No existe la pelicula", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "No existe conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }


}