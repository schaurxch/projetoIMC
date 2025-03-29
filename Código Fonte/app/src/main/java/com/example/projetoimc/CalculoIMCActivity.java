package com.example.projetoimc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculoIMCActivity extends AppCompatActivity {
    private EditText editTextHeight, editTextWeight;
    private Button btnCalculate, btnClear, btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_imcactivity);

        // Ajuste de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referências aos elementos
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        btnClose = findViewById(R.id.btnClose);

        // Lógica do botão CALCULATE
        btnCalculate.setOnClickListener(view -> {
            Calculate();
        });

        // Lógica do botão CLEAR
        btnClear.setOnClickListener(view -> {
            editTextHeight.setText("");
            editTextWeight.setText("");
        });

        // Lógica do botão CLOSE
        btnClose.setOnClickListener(view -> finish());
    }

    public void Calculate() {
        String heightStr = editTextHeight.getText().toString().trim();
        String weightStr = editTextWeight.getText().toString().trim();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(CalculoIMCActivity.this, "Preencha ambos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double height = Double.parseDouble(heightStr);
            double weight = Double.parseDouble(weightStr);

            if (height > 0 && weight > 0) {
                double imc = weight / (height * height);
                Log.i("Teste", "IMC: " + imc);
                Intent intent;

                if (imc < 18.5) {
                    intent = new Intent(this, AbaixoDoPesoActivity.class);
                    Log.i("Teste", "IR PARA TELA ABAIXO DO PESO");

                } else if (imc < 25) {
                    intent = new Intent(this, PesoNormalActivity.class);
                    Log.i("Teste", "IR PARA TELA PESO NORMAL");

                } else if (imc < 30) {
                    intent = new Intent(this, SobrepesoActivity.class);
                    Log.i("Teste", "IR PARA TELA SOBREPESO");

                } else if (imc < 35) {
                    intent = new Intent(this, ObesidadeGrau1Activity.class);
                    Log.i("Teste", "IR PARA TELA OBESIDADE 1");

                } else if (imc < 40) {
                    intent = new Intent(this, ObesidadeGrau2Activity.class);
                    Log.i("Teste", "IR PARA TELA OBESIDADE 2");

                } else {
                    intent = new Intent(this, ObesidadeGrau3Activity.class);
                    Log.i("Teste", "IR PARA TELA OBESIDADE 3");
                }

                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                intent.putExtra("imc", imc);
                startActivity(intent);

                Log.i("Teste", "INTENT " + intent);
            } else {
                Toast.makeText(CalculoIMCActivity.this, "Altura e peso devem ser valores positivos.", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(CalculoIMCActivity.this, "Insira valores válidos.", Toast.LENGTH_SHORT).show();
        }
    }
}