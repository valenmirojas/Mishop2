package com.example.tiendaderopa3;



import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class Entradax extends AppCompatActivity {

    Button girl;
    Button men;
    Button acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entradax);

        girl=(Button)findViewById(R.id.btn_mujeres);

        girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Entradax.this,Mujeres.class);
                startActivity(i);
            }
        });

        men=(Button)findViewById(R.id.btn_hombres);

        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Entradax.this,Hombres.class);
                startActivity(i);
            }
        });

        acc=(Button)findViewById(R.id.btn_acc);

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Entradax.this,Accesorios.class);
                startActivity(i);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("https://images.unsplash.com/photo-1525507119028-ed4c629a60a3?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        arrayList.add("https://plus.unsplash.com/premium_photo-1675186049409-f9f8f60ebb5e?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        arrayList.add("https://images.unsplash.com/photo-1562157873-818bc0726f68?q=80&w=2127&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        arrayList.add("https://plus.unsplash.com/premium_photo-1709033404514-c3953af680b4?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");

        ImageAdapter adapter = new ImageAdapter(Entradax.this, arrayList);
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onCliick(ImageView imageView, String url) {

                startActivity(new Intent(Entradax.this, ImageViewActivity.class).putExtra("image",url),
                        ActivityOptions.makeSceneTransitionAnimation(Entradax.this, imageView,"image").toBundle());
            }
        });
        recyclerView.setAdapter(adapter);
    }
}