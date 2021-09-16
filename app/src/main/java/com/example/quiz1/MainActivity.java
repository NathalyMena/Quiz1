package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button Registrar;
    private TextView Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Registrar = findViewById(R.id.Registrar);
        Registro = findViewById(R.id.Registro);

        listaUsuarios();

        Registrar.setOnClickListener(
                (v)->{
                    Intent i = new Intent(this, Registro.class);
                    startActivity(i);
                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        listaUsuarios();
    }

    public void listaUsuarios(){
        SharedPreferences preferences = getSharedPreferences("encuesta", MODE_PRIVATE);
        String usuarios = preferences.getString("nombre+calificacion", "No hay encuestados");
        Registro.setText(usuarios);
    }


}