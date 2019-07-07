package com.example.castriwolf.getup2.Activitys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.castriwolf.getup2.Clases.MyAlarmReceiver;
import com.example.castriwolf.getup2.Clases.MyNewIntentService;
import com.example.castriwolf.getup2.R;

import java.util.Calendar;

public class PararAlarma extends AppCompatActivity{


    //clase que será usada en futuras mejoras, no usada actualmente

    private Button cancelar;
    private Button posponer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parar_alarma);

        cancelar = findViewById(R.id.CancelarAlarma);
        posponer = findViewById(R.id.posponer);
        cancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              MyNewIntentService.cancelar();

            }
        });

        posponer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
                Calendar time = Calendar.getInstance();
                time.setTimeInMillis(System.currentTimeMillis());
                time.add(Calendar.SECOND, 10);
                alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),
                        1000 * 60 * 10, pendingIntent);

                Toast.makeText(getApplicationContext(), "Alarma Creada", Toast.LENGTH_SHORT).show();
                MyNewIntentService.cancelar();

            }
        });

    }


}
