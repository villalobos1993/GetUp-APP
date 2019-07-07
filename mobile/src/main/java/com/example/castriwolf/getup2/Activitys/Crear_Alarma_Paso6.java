package com.example.castriwolf.getup2.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.example.castriwolf.getup2.R;

public class Crear_Alarma_Paso6 extends AppCompatActivity {

    //variables
    private NumberPicker picker;
    private ImageView next;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private int hora;
    private int minuto;
    private int horaRecorrido;
    private int minutosRecorrido;
    private String lsalida;
    private String lLlegada;
    private Boolean coche;
    private Boolean bus;
    private Boolean bici;
    private Boolean andar;
    private int Tlevantarse;
    private int Tbano;
    private int Tdesayuno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__alarma__paso6);


        picker = findViewById(R.id.numberpicker4);
        picker.setMaxValue(59);
        picker.setMinValue(0);
        next = findViewById(R.id.next);

        recogerDatos();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent go = new Intent(getApplicationContext(), Resumen.class);
                go.putExtra("Lunes", lunes);
                go.putExtra("Martes", martes);
                go.putExtra("Miercoles", miercoles);
                go.putExtra("Jueves", jueves);
                go.putExtra("Viernes", viernes);
                go.putExtra("Sabado", sabado);
                go.putExtra("Domingo", domingo);

                //Hora llegada trabajo
                go.putExtra("Hora", hora);
                go.putExtra("HMinuto", minuto);
                //Tiempo recorrido Maps
                go.putExtra("HorasRecorrido",horaRecorrido);
                go.putExtra("MinutosRecorridos",minutosRecorrido);
                //Lugar de salida y llegada
                go.putExtra("Lsalida",lsalida);
                go.putExtra("Lllegada",lLlegada);
                //Modo Transporte
                go.putExtra("Coche",coche);
                go.putExtra("Bus",bus);
                go.putExtra("Bici",bici);
                go.putExtra("Andar",andar);
                //Tiempo para levantarse
                go.putExtra("Tlevantarse",Tlevantarse);
                //Tiempo del ducha
                go.putExtra("Tbaño",Tbano);
                //Tiempo del desayuno
                go.putExtra("Tdesayuno",Tdesayuno);
                //Tiempo adiccional
                go.putExtra("Textra",picker.getValue());

                startActivity(go);

            }
        });
    }

    private void recogerDatos(){
        Bundle parametros = getIntent().getExtras();
        //Dias
        lunes = parametros.getBoolean("Lunes");
        martes = parametros.getBoolean("Martes");
        miercoles = parametros.getBoolean("Miercoles");
        jueves = parametros.getBoolean("Jueves");
        viernes = parametros.getBoolean("Viernes");
        sabado = parametros.getBoolean("Sabado");
        domingo = parametros.getBoolean("Domingo");
        //Hora para llegar a tu destino
        hora = parametros.getInt("Hora");
        minuto = parametros.getInt("HMinuto");
        //Tiempo para el recorrido
        horaRecorrido = parametros.getInt("HorasRecorrido");
        minutosRecorrido = parametros.getInt("MinutosRecorridos");
        //Lugar de salida y llegada
        lsalida = parametros.getString("Lsalida");
        lLlegada = parametros.getString("Lllegada");
        //Modo Transporte
        coche = parametros.getBoolean("Coche");
        bus = parametros.getBoolean("Bus");
        bici = parametros.getBoolean("Bici");
        andar = parametros.getBoolean("Andar");
        //Tiempo para levantarte
        Tlevantarse = parametros.getInt("Tlevantarse");
        //Tiempo para el baño
        Tbano = parametros.getInt("Tbaño");
        //Tiempo para el desayuno
        Tdesayuno = parametros.getInt("Tdesayuno");
    }


}
