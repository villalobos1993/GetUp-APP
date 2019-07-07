package com.example.castriwolf.getup2.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.castriwolf.getup2.R;

public class Pasos_Despertar extends AppCompatActivity {

    ImageView guardar;
    NumberPicker pasos;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Acitivad para guardar el numero de pasos que desamos caminar

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pasos_despertar);
        pref=getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        editor=pref.edit();
        guardar = findViewById(R.id.guardar);
        pasos = findViewById(R.id.numberpickerpasos);
        pasos.setMinValue(10);
        pasos.setMaxValue(50);
        pasos.setValue(pref.getInt("pasos",30));




        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //guardamos nuestros pasos con el editor del sharedpreferences

                editor.putInt("pasos",pasos.getValue());
                editor.commit();
                Toast.makeText(getApplicationContext(),"Pasos Guardados",Toast.LENGTH_SHORT).show();
                Intent go = new Intent(getApplicationContext(), Preferencias_Alarma.class);
                startActivity(go);
            }
        });
    }
}


