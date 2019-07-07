package com.example.castriwolf.getup2.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.example.castriwolf.getup2.R;

public class Crear_Alarma_Paso3 extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear__alarma__paso3);
        picker= findViewById(R.id.numberpicker);
        picker.setMinValue(0);
        picker.setMaxValue(59);
        next= findViewById(R.id.next);

        //recogemos los datos del bundle
       recogerBundle();





        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mandamos las variables por  el bundle
                Intent go=new Intent(getApplicationContext(),Crear_Alarma_Paso4.class);
                go.putExtra("Lunes",lunes);
                go.putExtra("Martes",martes);
                go.putExtra("Miercoles",miercoles);
                go.putExtra("Jueves",jueves);
                go.putExtra("Viernes",viernes);
                go.putExtra("Sabado",sabado);
                go.putExtra("Domingo",domingo);

                //Hora llegada trabajo
                go.putExtra("Hora", hora);
                go.putExtra("HMinuto", minuto);
                //tiempo recorrido Maps
                go.putExtra("HorasRecorrido",horaRecorrido);
                go.putExtra("MinutosRecorridos",minutosRecorrido);
                //Lugar de salida y llegada
                go.putExtra("Lsalida",lsalida);
                go.putExtra("Lllegada",lLlegada);
                //tiempo para levantarse
                go.putExtra("Tlevantarse",picker.getValue());
                //Modo Transporte
                go.putExtra("Coche",coche);
                go.putExtra("Bus",bus);
                go.putExtra("Bici",bici);
                go.putExtra("Andar",andar);


                startActivity(go);
            }
        });


    }

    private void recogerBundle() {

        Bundle parametros =getIntent().getExtras();
        //Dias
        lunes=parametros.getBoolean("Lunes");
        martes=parametros.getBoolean("Martes");
        miercoles=parametros.getBoolean("Miercoles");
        jueves=parametros.getBoolean("Jueves");
        viernes=parametros.getBoolean("Viernes");
        sabado=parametros.getBoolean("Sabado");
        domingo=parametros.getBoolean("Domingo");
        //Hora de llegada al destino
        hora = parametros.getInt("Hora");
        minuto=parametros.getInt("HMinuto");
        //horas y minutos del recorrido
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

    }
}
