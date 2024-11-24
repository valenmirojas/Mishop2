package com.example.tiendaderopa3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registrox extends AppCompatActivity implements View.OnClickListener {

    EditText etnombre, etpassword, etemail, etedad;
    Button btn_registrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrox);


        etnombre = findViewById(R.id.EditT_nombre);
        etemail = findViewById(R.id.EditT_Email);
        etpassword = findViewById(R.id.EditT_password);
        etedad = findViewById(R.id.EditT_edad);


        btn_registrar = findViewById(R.id.btn_Registrar);
        btn_registrar.setOnClickListener(this);  // Aquí se llama al método onClick
    }

    @Override
    public void onClick(View view) {
        final String username = etnombre.getText().toString();
        final String email = etemail.getText().toString();
        final String password = etpassword.getText().toString();
        final String ageText = etedad.getText().toString();


        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || ageText.isEmpty()) {
            showAlert("Error", "Todos los campos son obligatorios.");
            return;
        }


        int age = 0;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            showAlert("Error", "La edad debe ser un número válido.");
            return;
        }


        Response.Listener<String> respoListener = response -> {
            try {

                JSONObject jsonResponse = new JSONObject(response);
                Log.d("", "");
                boolean success = jsonResponse.getBoolean("success");

                if (success) {

                    Intent intent = new Intent(Registrox.this, MainActivity.class);
                    startActivity(intent);
                } else {

                    showAlert("Error en el registro", "Hubo un error al intentar registrar el usuario. Intenta de nuevo.");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                showAlert("Error", "Hubo un problema al procesar la respuesta del servidor." + e.getMessage());
            }
        };
        Response.ErrorListener respoerror = error -> showAlert("Error", "Hubo un problema al procesar: " + error.getMessage());


        RegisterRequest registerRequest = new RegisterRequest(username, email, age, password, respoListener, respoerror);


        RequestQueue queue = Volley.newRequestQueue(Registrox.this);
        queue.add(registerRequest);
    }


    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Registrox.this);
        builder.setTitle(title)
                .setMessage(message)
                .setNegativeButton("Aceptar", null)
                .create()
                .show();
    }
}