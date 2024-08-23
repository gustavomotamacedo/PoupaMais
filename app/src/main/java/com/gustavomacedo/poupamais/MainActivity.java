package com.gustavomacedo.poupamais;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edtValorInicial;
    private EditText edtAplicacao;
    private EditText edtTempo;
    private EditText edtTaxa;
    private Button btnCalcular;
    private TextView txtResultado;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtValorInicial = findViewById(R.id.edtValorInicial);
        edtAplicacao = findViewById(R.id.edtAplicacao);
        edtTempo = findViewById(R.id.edtTempo);
        edtTaxa = findViewById(R.id.edtTaxa);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular.setOnClickListener(v -> {
            String strValorInicial = String.format(Locale.US, edtValorInicial.getText().toString());
            String strAplicacao = String.format(Locale.US, edtAplicacao.getText().toString());
            String strTempo = String.format(Locale.US, edtTempo.getText().toString());
            String strTaxa = String.format(Locale.US, edtTaxa.getText().toString());
            // Fórmula dos juros compostos M = R * ((C *((1+i)^t)-1)/i)
            double valorInicial = Double.parseDouble(strValorInicial);
            double aplicacao = Double.parseDouble(strAplicacao);
            double taxa = Double.parseDouble(strTaxa);
            int tempo = Integer.parseInt(strTempo);

            taxa = taxa/100;
            double montante = valorInicial;

            for (int i = 1; i <= tempo; i++) {
                montante = (montante + aplicacao) * (1+taxa);
            }

            String strMontante = String.format(Locale.US, "%.2f", montante);
            txtResultado.setText("Montante: R$ " + strMontante);
        });

    }
}