package com.example.smartconverter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Map<String, Integer> dicionario = new LinkedHashMap<>();



    EditText texto;
    EditText texto1;
    TextView resultado;
    TextView titulo;
    RadioButton rbRomano;
    RadioButton rbDecimal;
    Button converter;
    Button adicionar;
    Button subtrair;
    LinearLayout layout1;
    RadioGroup rg;

    int[] valores = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
    String[] romano = {"I","IV","V","IX","X","XL","L","XC","C","D","CM","M"};
    StringBuilder romanoStr = new StringBuilder();



    @SuppressLint("WrongConstant")
    private void operacoes (RadioButton rdbutton, Button operacao){
        int x=0, y=0, r =0;
        if(rdbutton==rbRomano){
            try {
                x=dicionario.get(texto.getText().toString().toUpperCase());
            }catch(Exception e){
                Toast.makeText(getApplicationContext(), "Coloque um numero romano",200).show();

            }try {
                y=dicionario.get(texto1.getText().toString().toUpperCase());
            }catch(Exception e){
                Toast.makeText(getApplicationContext(), "Coloque um numero romano",200).show();

            }
        }else {
            try {
                x= Integer.parseInt(texto.getText().toString());
            }catch(Exception e){
                Toast.makeText(getApplicationContext(), "Coloque um numero decimal",200).show();

            }try {
                y=Integer.parseInt(texto1.getText().toString());
            }catch(Exception e){
                Toast.makeText(getApplicationContext(), "Coloque um numero decimal",200).show();

            }
        }

        if (operacao==adicionar){
            r=x+y;
        }else{
            r=x-y;
        }
        resultado.clearComposingText();
        resultado.setText("");
        if (rdbutton==rbRomano){
            resultado.setText(decimalParaRomano(r));
        }else {
            resultado.setText(String.valueOf(r));
        }


    }

    public String decimalParaRomano(int decimal){
        romanoStr = new StringBuilder();
        for(int i= valores.length-1;i >= 0 && decimal>0; i--){
            while (decimal >= valores[i]){
                decimal -= valores[i];
                romanoStr.append(romano[i]);
            }
        }
        return romanoStr.toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Desenvolvedores: Nabila Faquir e Delton Guivala
        texto=findViewById(R.id.insercao);
        texto1=findViewById(R.id.insercao1);
        rbRomano=findViewById(R.id.rbRomano);
        rbDecimal=findViewById(R.id.rbDecimal);
        converter=findViewById(R.id.converter);
        adicionar=findViewById(R.id.adicionar);
        subtrair=findViewById(R.id.subtrair);
        resultado=findViewById(R.id.resultado);
        rg=findViewById(R.id.rg);
        titulo=findViewById(R.id.Titulo);
        layout1=findViewById(R.id.layout1);
        //titulo.setTextColor(Color.WHITE);

        rg.check(rbDecimal.getId());

        dicionario.put("I", 1);
        dicionario.put("V", 5);
        dicionario.put("X", 10);
        dicionario.put("L", 50);
        dicionario.put("C", 100);
        dicionario.put("D", 500);
        dicionario.put("M", 1000);

       

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbRomano.isChecked()){
                    operacoes(rbRomano,adicionar);
                }else {
                    operacoes(rbDecimal,adicionar);
                }
            }
        });

        subtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbRomano.isChecked()){
                    operacoes(rbRomano,subtrair);
                }else {
                    operacoes(rbDecimal,subtrair);
                }
            }
        });

        converter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbRomano.isChecked()){ //Romano para Decimal
                    int rm=0;
                    try {
                        rm= dicionario.get(texto.getText().toString().toUpperCase());
                        if (rm!=0){
                            resultado.clearComposingText();
                            resultado.setText("");
                            resultado.setText(String.valueOf(rm));
                        }
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(), "Coloque um numero romano",200).show();
                    }
                }else{
                    try {
                        if (!decimalParaRomano(Integer.parseInt(texto.getText().toString())).equals("")){
                            resultado.setText(decimalParaRomano(Integer.parseInt(texto.getText().toString())));
                        }else{
                            Toast.makeText(getApplicationContext(),"Digite um numero decimal valido",200).show();
                        }
                    }catch (Exception ignored){

                    }

                }
            }
        });


    }


}
