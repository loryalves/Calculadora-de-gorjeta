package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextValor;
    TextView textViewPorcentagem, textViewGorjeta, textViewTotal;
    SeekBar seekBarPorcentagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValor = findViewById(R.id.editTextValor);
        textViewPorcentagem = findViewById(R.id.textViewPorcentagem);
        textViewGorjeta = findViewById(R.id.textViewGorjeta);
        textViewTotal = findViewById(R.id.textViewTotal);
        seekBarPorcentagem = findViewById(R.id.seekBarPorcentagem);

        //Altera o campo gorjeta ao digitar um valor no campo
        editTextValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularGorjeta();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Atualiza a porcentagem da seekBar
        seekBarPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewPorcentagem.setText(progress + " %");
                calcularGorjeta();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calcularGorjeta(){

        Double valorDigitado = !editTextValor.getText().toString().isEmpty() ? Double.parseDouble(String.valueOf(editTextValor.getText())) : 0.0;

        Double gorjeta = (valorDigitado * this.seekBarPorcentagem.getProgress()) / 100;
        textViewGorjeta.setText("R$ " + gorjeta);

        textViewTotal.setText("R$ " + (gorjeta + valorDigitado));
    }
}