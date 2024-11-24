package com.example.tiendaderopa3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Usuarios extends AppCompatActivity {
    TextView tvUsuario,tvEmail,tvEdad,tvPassword;
    Button siguiente;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usuarios);

        siguiente=(Button)findViewById(R.id.siguiente1);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Usuarios.this,Entradax.class);
                startActivity(i);
            }
        });




        tvUsuario = (TextView) findViewById(R.id.TextV_nombre);
        tvEmail = (TextView) findViewById(R.id.TextV_email);
        tvEdad = (TextView) findViewById(R.id.TextV_edad);
        tvPassword = (TextView) findViewById(R.id.TextV_password);

        Intent intent = getIntent();
        String email= intent.getStringExtra("email");
        String username= intent.getStringExtra("username");
        int age= intent.getIntExtra("age",-1);
        String password= intent.getStringExtra("password");

        tvUsuario.setText(username);
        tvEmail.setText(email);
        tvPassword.setText(password);
        tvEdad.setText(age+"");

    }
}