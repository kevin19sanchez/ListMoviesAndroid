package com.example.listingsmovies.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listingsmovies.MainActivity;
import com.example.listingsmovies.databinding.ActivityLoginBinding;
import com.example.listingsmovies.login.user.Usuario;
import com.example.listingsmovies.register.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        thenActivity();
        nextActivity();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Usuario usuario = new Usuario(LoginActivity.this);

        if (usuario.getLogged() != ""){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void thenActivity(){
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.edtLoginEmail.getText().toString();
                String pass = binding.edtLoginPass.getText().toString();

//                String emailPrueba = "cristianoalvarez@gmail.com";
//                String passPrueba = "123456";

                Usuario usuario = new Usuario(LoginActivity.this);

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Escribe tus credenciales", Toast.LENGTH_SHORT).show();
                }
//                }else if (!email.equals(emailPrueba) || !pass.equals(passPrueba)) {
//                    Toast.makeText(LoginActivity.this, "Correo o contraseña incorrecta", Toast.LENGTH_SHORT).show();
//                }
                else{
                    usuario.setLogged("logged");
//                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    signIn(email, pass);

                }
            }
        });
    }

    private void signIn(String email, String pass){
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "Autenticacion fallida" + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void nextActivity(){
        binding.txtActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}