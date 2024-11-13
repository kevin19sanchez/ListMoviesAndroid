package com.example.listingsmovies.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listingsmovies.R;
import com.example.listingsmovies.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    String email;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
    }

    private void addUser(){
        email = binding.edtRegistroEmail.getText().toString();
        pass = binding.edtRegistroPass.getText().toString();

        if (email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(getBaseContext(), "Usuario Registrado", Toast.LENGTH_LONG).show();
        registerUser(email, pass);
    }

    private void registerUser(String email, String pass){
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null){
                        savedUser(user.getUid(), email,pass);
                    }
                }else{
                    Toast.makeText(getBaseContext(), "No se pudo registar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cleanText();
    }

    private void savedUser(String userId, String email, String pass){
        //Intancia hacia la referencia
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> newuser = new HashMap<>();

        newuser.put("email", email);
        newuser.put("pass", pass);
        newuser.put("userId", userId);

        mDatabase.child("users").child(userId).setValue(newuser)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(this, "Guardado exitosamente", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void cleanText(){
        binding.edtRegistroEmail.setText("");
        binding.edtRegistroPass.setText("");
    }
}