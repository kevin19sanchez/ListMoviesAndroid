package com.example.listingsmovies.ui.blog.addcomment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listingsmovies.R;
import com.example.listingsmovies.databinding.ActivityAddCommentBinding;
import com.example.listingsmovies.model.MoviesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AddCommentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityAddCommentBinding binding;
    private String selectedValue;
    private List<String> moviesModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCommentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        moviesModels = new ArrayList<String>();

        spinnerData();
        //binding.spinnerMovies.setOnItemClickListener(this::onItemSelected);

        binding.btnAddCommetn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedValue = "Toy Story";
                getMoviesId(selectedValue);
            }
        });

        //loadComment();
    }


    private void getMoviesId(String selectedValue) {
        Query query = FirebaseDatabase.getInstance().getReference().child("Peliculas")
                .child("peliculaInfantil")
                .orderByChild("titulo")
                .equalTo(selectedValue);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                //moviesModels.clear();
                if (datasnapshot.exists()){
                    for (DataSnapshot snapshot: datasnapshot.getChildren()){
                        MoviesModel itemMovie = snapshot.getValue(MoviesModel.class);
                        String temp = itemMovie.getId().toString();

                        //moviesModels.add(temp);
                        Toast.makeText(AddCommentActivity.this, " " + temp, Toast.LENGTH_SHORT).show();
                        saveMovie(temp);
                    }
                }else{
                    Toast.makeText(AddCommentActivity.this, "Error en obtener el ID" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddCommentActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveMovie(String valor){
        if (valor.isEmpty()){
            Toast.makeText(this, "Selecciona una pelicula", Toast.LENGTH_SHORT).show();
        }

        String comentario = binding.edtComment.getText().toString().trim();
        if (comentario.isEmpty()){
            Toast.makeText(this, "Por favor ingrese comentario", Toast.LENGTH_SHORT).show();
        }

        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String fechaActual = sdf.format(todayDate);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> newcomment = new HashMap<>();

        newcomment.put("id", valor);
        newcomment.put("comentario", comentario);
        newcomment.put("fechaComentario", fechaActual);

        mDatabase.child("Blog/comentarios").child(selectedValue).setValue(newcomment)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(this, "Guardado exitosamente", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void spinnerData(){
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> movieListAdapter = ArrayAdapter
                .createFromResource(this, R.array.movies,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        movieListAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinnerMovies.setAdapter(movieListAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        selectedValue = adapterView.getItemAtPosition(position).toString();
        getMoviesId(selectedValue);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}