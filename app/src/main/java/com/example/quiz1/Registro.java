package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private EditText Nombre, ID;
    private Button Continuar1;
    private String nombre, id;
    private boolean numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        Nombre = findViewById(R.id.Nombre);
        ID = findViewById(R.id.ID);
        Continuar1 = findViewById(R.id.Continuar1);

        Continuar1.setOnClickListener(
                (v) -> {
                    nombre = Nombre.getText().toString();
                    id = ID.getText().toString();
                    validacion();
                    if (numero) {
                        Intent i = new Intent(this, com.example.quiz1.Epidemiologia.class);
                        i.putExtra("nombre", nombre);
                        i.putExtra("id", id);
                        startActivity(i);
                        finish();
                    }
                }
        );
    }

    protected void validacion() {
        numero = true;
        if (nombre == null || nombre.isEmpty() || id == null || id.isEmpty()) {
            Toast.makeText(this, "Hay campos vacíos", Toast.LENGTH_LONG).show();
            numero = false;
        } else {
            for (int i = 0; i < nombre.length(); i++) {
                if (Character.isDigit(nombre.charAt(i))) {
                    Toast.makeText(this, "Tienes números en el nombre. Revíselo.", Toast.LENGTH_LONG).show();
                    nombre = "";
                    numero = false;
                } else {
                    numero = true;
                }
            }
        }
        String usuarios = getSharedPreferences("encuesta", MODE_PRIVATE).getString("encuestados", "");
        if (usuarios.contains(id)) {
            Toast.makeText(this, "Esta persona ya fue registrada", Toast.LENGTH_LONG).show();
            numero = false;
        }
    }
}