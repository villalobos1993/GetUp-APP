package com.example.castriwolf.getup2.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.R;

import java.util.HashMap;
import java.util.Map;

public class GastoTiempo extends AppCompatActivity {

    TextView recorrido;
    TextView despertar;
    TextView baño;
    TextView desayuno;
    TextView otros;
    Mihelper bd;
    int mediarecorrido;
    int mediadespertar;
    int mediabaño;
    int mediadesayuno;
    int mediaotros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_tiempo);

        recorrido=findViewById(R.id.txtrecorrido);
        despertar=findViewById(R.id.txtdespertar);
        baño=findViewById(R.id.txtducha);
        desayuno=findViewById(R.id.txtdesayuno);
        otros=findViewById(R.id.txtotros);
        bd=new Mihelper(getApplicationContext());

        getMediaAcitividades();

        //Escribimos en nuestro layout las medias de cada actividad

        recorrido.setText(String.valueOf(mediarecorrido)+" minutos");
        despertar.setText(String.valueOf(mediadespertar)+" minutos");
        baño.setText(String.valueOf(mediabaño)+" minutos");
        desayuno.setText(String.valueOf(mediadesayuno)+" minutos");
        otros.setText(String.valueOf(mediaotros)+" minutos");



    }

    public void getMediaAcitividades()
    {

        //recuperamos un Hashmap haciendo una consulta a nuestra BBDD de el nombre y el valor de cada actividad donde
        //nos devuelve la media de cada actividad

        HashMap<String,Integer> nombresymedias=bd.getMediaTiempos();

        //si el tamaño del hashmap es 0 asignamos todas las medias a 0
        if(nombresymedias.size()==0)
        {

            mediarecorrido=0;
            mediadesayuno=0;
            mediaotros=0;
            mediadespertar=0;
            mediabaño=0;
        }
        else {

            //Asignamos el valor del hashmap a las variables de la media  de cada key
            for (Map.Entry<String, Integer> aux : nombresymedias.entrySet()) {
                if (aux.getKey().equals("tiemporecorrido")) {
                    mediarecorrido = aux.getValue();
                }
                if (aux.getKey().equals("tiempolevantarse")) {
                    mediadespertar = aux.getValue();
                }
                if (aux.getKey().equals("tiempobaño")) {
                    mediabaño = aux.getValue();
                }
                if (aux.getKey().equals("tiempodesayuno")) {
                    mediadesayuno = aux.getValue();
                }
                if (aux.getKey().equals("tiempootros")) {
                    mediaotros = aux.getValue();
                }

            }
        }

    }
}
