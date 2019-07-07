package com.example.castriwolf.getup2.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.castriwolf.getup2.Clases.MyNewIntentService;
import com.example.castriwolf.getup2.R;

public class StepCounter extends AppCompatActivity implements SensorEventListener {
//variables
    private SensorManager sensor;
    private boolean andando=false;
    private TextView tPasos;
    private int pasos;
    private int totalpasos;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_counter);

        //Recojo los pasos guardados por el usuario en el sharedpreferences
        pref= getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        totalpasos=pref.getInt("pasos",30);
        //inicializamos el sensor manager
        sensor=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        tPasos = findViewById(R.id.pasitos);
        //inicalizamos los pasos a 0
        pasos=0;
        //ponemos el texto en 30 pasos al principio
        tPasos.setText("Te quedan "+String.valueOf(totalpasos)+" pasos para que la alarma se pare,\nGetUp!");


    }

    @Override
    protected void onResume()
    {
        super.onResume();
        andando=true;
        //Inicializamos nuestro sensor y comprabamos que no sea NULL

        Sensor counteSensor=sensor.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(counteSensor!=null)
        {
            sensor.registerListener(this,counteSensor,SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this,"Sensor no encontrado", Toast.LENGTH_LONG).show();
        }

    }

    @Override

    protected  void onPause(){
        super.onPause();
        andando=false;
    }

    @Override
    protected void onStop(){
        super.onStop();

    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        //me cambia el valor de los pasos segun el movimiento del sensor y va comprabando el valor en los condicionales

        if(pasos>=totalpasos){

            //si los pasos llegan a 30 finaliza y deja de sonar

            MyNewIntentService.cancelar();
            event.values[0]=0;
            pasos=0;

            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);



        }else {

            //si los pasos son menor que 30 sigue sonando y va aumentando el valor de los pasos

            //asignamos al event el valor de los pasos
            event.values[0] = pasos;
            //vamos incrementando los pasos segun se vaya moviendo
            pasos += 1;
            //nos indica el numero de pasos restantes
            tPasos.setText("Te quedan " + String.valueOf(totalpasos - (int)event.values[0]) + " pasos para que la alarma se pare,\nGetUp!");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
