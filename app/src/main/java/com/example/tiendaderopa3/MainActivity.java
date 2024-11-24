package com.example.tiendaderopa3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv_registrar;
    Button btn_log;
    EditText et_nombre, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_registrar = findViewById(R.id.tv_reg);
        btn_log = findViewById(R.id.btn_log);
        et_nombre = findViewById(R.id.etUsername12);
        et_password = findViewById(R.id.etUsernameMain);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentReg = new Intent(MainActivity.this, Registrox.class);
                startActivity(intentReg);
            }
        });


        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = et_nombre.getText().toString();
                final String password = et_password.getText().toString();


                if (username.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Por favor ingresa tu nombre de usuario y contraseña.")
                            .setNegativeButton("Aceptar", null)
                            .create().show();
                    return;
                }


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                String email = jsonResponse.getString("email");
                                int age = jsonResponse.getInt("age");


                                Intent intent = new Intent(MainActivity.this, Usuarios.class);
                                intent.putExtra("email", email);
                                intent.putExtra("username", username);
                                intent.putExtra("age", age);
                                intent.putExtra("password", password);
                                startActivity(intent);
                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Error en el login")
                                        .setNegativeButton("Reintentar", null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("Hubo un error al procesar la respuesta del servidor.")
                                    .setNegativeButton("Aceptar", null)
                                    .create().show();
                        }
                    }
                };


                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);


                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}