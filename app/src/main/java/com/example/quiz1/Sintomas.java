package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

public class Sintomas extends AppCompatActivity {
    private Button Continuar3;
    private CheckBox Fiebre, DGarganta, CNasal, Tos, Fatiga, DRespiratoria, Ninguno;
    private int resultado, paciente;
    private boolean Valor;
    private String nombre, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas);

        resultado = getIntent().getExtras().getInt("resultado");
        nombre = getIntent().getExtras().getString("nombre");
        id = getIntent().getExtras().getString("id");

        Continuar3 = findViewById(R.id.Continuar3);
        Fiebre = findViewById(R.id.Fiebre);
        DGarganta = findViewById(R.id.DGarganta);
        CNasal = findViewById(R.id.CNasal);
        Tos = findViewById(R.id.Tos);
        Fatiga = findViewById(R.id.Fatiga);
        DRespiratoria = findViewById(R.id.DRespiratoria);
        Ninguno = findViewById(R.id.Ninguno);


        isCheckboxClicked();

        Continuar3.setEnabled(false);

        Continuar3.setOnClickListener(
                (v) -> {
                    calificacion(Fiebre);
                    calificacion(DGarganta);
                    calificacion(CNasal);
                    calificacion(Tos);
                    calificacion(Fatiga);
                    calificacion(DRespiratoria);

                    SharedPreferences preferences = getSharedPreferences("encuesta", MODE_PRIVATE);
                    int total = resultado + paciente;
                    String calification = String.valueOf(total);
                    String nombreS = preferences.getString("nombre+calificacion", "");
                    String userS = preferences.getString("encuestados", "");
                    String usuario = nombre + ", " + id + ", " + calification + " \n";
                    String nombreCal = nombre + ", " + calification + " \n";
                    preferences.edit().putString("nombre+calificacion", nombreS + nombreCal).apply();
                    preferences.edit().putString("encuestados", userS + usuario).apply();
                    finish();
                }
        );

    }
    private void calificacion(CheckBox check) {
        Valor = false;
        if (check.isChecked() && !Valor) {
            paciente = paciente + 4;
            Valor = true;
        } else if (Valor) {
            paciente = paciente - 4;
            Valor = false;
        }
    }

    private void isCheckboxClicked() {
        new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (Fiebre.isChecked() || DGarganta.isChecked() || CNasal.isChecked() || Tos.isChecked() || Fatiga.isChecked() || DRespiratoria.isChecked() || Ninguno.isChecked()) {
                            runOnUiThread(
                                    () -> {
                                        Continuar3.setEnabled(true);
                                        Continuar3.setBackgroundResource(R.drawable.boton);
                                    }
                            );
                        } else {
                            runOnUiThread(
                                    () -> {
                                        Continuar3.setEnabled(false);
                                        Continuar3.setBackgroundResource(R.drawable.boton2);
                                    }
                            );
                        }
                    }
                }
        ).start();
    }


}
