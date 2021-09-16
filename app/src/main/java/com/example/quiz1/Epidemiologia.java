package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;

public class Epidemiologia extends AppCompatActivity {
    private Button Continuar2;
    private CheckBox CCP, Ninguno1, Viaje, Viaje2, Trabajador;
    private String nombre, id;
    private int Valor;
    private boolean resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epidemiologia);

        id = getIntent().getExtras().getString("id");
        nombre = getIntent().getExtras().getString("nombre");

        Continuar2 = findViewById(R.id.Continuar2);
        CCP = findViewById(R.id.CCP);
        Ninguno1 = findViewById(R.id.Ninguno1);
        Viaje = findViewById(R.id.Viaje);
        Viaje2 = findViewById(R.id.Viaje2);
        Trabajador = findViewById(R.id.Trabajador);



        isCheckboxClicked();

        Continuar2.setOnClickListener(
                (v) -> {
                    resultado(CCP);
                    resultado(Viaje2);
                    resultado(Viaje);
                    resultado(Trabajador);

                    Log.e(">>>", "" + Valor);

                    Intent i = new Intent(this, com.example.quiz1.Sintomas.class);
                    i.putExtra("resultado", Valor);
                    i.putExtra("nombre", nombre);
                    i.putExtra("id", id);
                    startActivity(i);
                    finish();
                }
        );
    }

    public void isCheckboxClicked() {
        Continuar2.setEnabled(false);
        Continuar2.setBackgroundResource(R.drawable.boton2);
        new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (CCP.isChecked() || Ninguno1.isChecked() || Viaje.isChecked() || Viaje2.isChecked() || Trabajador.isChecked()) {
                            runOnUiThread(
                                    () -> {
                                        Continuar2.setEnabled(true);
                                        Continuar2.setBackgroundResource(R.drawable.boton);

                                    }
                            );
                        } else {
                            runOnUiThread(
                                    () -> {
                                        Continuar2.setEnabled(false);
                                        Continuar2.setBackgroundResource(R.drawable.boton2);
                                    }
                            );
                        }
                    }
                }
        ).start();

    }

    public void resultado(CheckBox check) {
        resultado = false;
        if (check.isChecked()) {
            Valor = Valor + 3;
            resultado = true;
        } else if (resultado) {
            Valor = Valor - 3;
            resultado = false;
        }
    }
}