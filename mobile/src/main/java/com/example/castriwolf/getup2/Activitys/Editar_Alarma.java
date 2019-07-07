package com.example.castriwolf.getup2.Activitys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.Clases.Container;
import com.example.castriwolf.getup2.Clases.ListViewInflater;
import com.example.castriwolf.getup2.Clases.MyAlarmReceiver;
import com.example.castriwolf.getup2.Clases.Pending;
import com.example.castriwolf.getup2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Editar_Alarma extends AppCompatActivity {

    TimePicker tp;
    SharedPreferences sharedPreferences;
    Mihelper bd;

    ImageView save;
    private ImageButton toggleButton1;
    private ImageButton toggleButton2;
    private ImageButton toggleButton3;
    private ImageButton toggleButton4;
    private ImageButton toggleButton5;
    private ImageButton toggleButton6;
    private ImageButton toggleButton7;
    boolean lunesverde;
    boolean martesverde;
    boolean miercolesverde;
    boolean juevesverde;
    boolean viernesverde;
    boolean sabadoverde;
    boolean domingoverde;
    int horaalarma;
    int minutosalarma;
    int idalarma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alarma);
        bd = new Mihelper(getApplicationContext());
        tp = findViewById(R.id.timePicker3);
        tp.setIs24HourView(true);
        save=findViewById(R.id.save);
        toggleButton1 = findViewById(R.id.toggleButtonLunes);
        toggleButton2 = findViewById(R.id.toggleButtonMartes);
        toggleButton3 = findViewById(R.id.toggleButtonMiercoles);
        toggleButton4 = findViewById(R.id.toggleButtonJueves);
        toggleButton5 = findViewById(R.id.toggleButtonViernes);
        toggleButton6 = findViewById(R.id.toggleButtonSabado);
        toggleButton7 = findViewById(R.id.toggleButtonDomingo);


        sharedPreferences = getSharedPreferences("Editar" , Context.MODE_PRIVATE);
        tp.setHour(sharedPreferences.getInt("horaalarmaeditar" , 00));
        tp.setMinute(sharedPreferences.getInt("minutosalarmaeditar" , 00));
        horaalarma = sharedPreferences.getInt("horaalarmaeditar" , 0);
        minutosalarma = sharedPreferences.getInt("minutosalarmaeditar" , 0);
        idalarma = sharedPreferences.getInt("idalarmaeditar" , 0);


        ArrayList<Integer> diasalarma = bd.diasAlarma(horaalarma, minutosalarma);


        if (diasalarma.get(0) == 1) {
            toggleButton1.setImageResource(R.drawable.icons8lunes40verde);
            lunesverde=true;
        } else {
            toggleButton1.setImageResource(R.drawable.icons8lunes40);
            lunesverde=false;
        }

        if(diasalarma.get(1)==1){

            toggleButton2.setImageResource(R.drawable.icons8martes40verde);
            martesverde=true;
        }
        else {
            toggleButton2.setImageResource(R.drawable.icons8martes40);
            martesverde=false;
        }

        if(diasalarma.get(2)==1)
        {
            toggleButton3.setImageResource(R.drawable.icons8miercoles40verde);
        miercolesverde=true;}
            else {
        toggleButton3.setImageResource(R.drawable.icons8miercoles40);
        miercolesverde=false;
    }

        if(diasalarma.get(3)==1)
        {
            toggleButton4.setImageResource(R.drawable.icons8jueves40verde);
            juevesverde=true;
        }
        else {
            toggleButton4.setImageResource(R.drawable.icons8jueves40);
            juevesverde=false;
        }
        if(diasalarma.get(4)==1)
        {
            toggleButton5.setImageResource(R.drawable.icons8viernes40verde);
        viernesverde=true;}
        else {
            toggleButton5.setImageResource(R.drawable.icons8viernes40);
        viernesverde=false;}

        if(diasalarma.get(5)==1)
        {
            toggleButton6.setImageResource(R.drawable.icons8sabado40verde);
        sabadoverde=true;}
        else {
            toggleButton6.setImageResource(R.drawable.icons8sabado40);
        sabadoverde=false;}

        if(diasalarma.get(6)==1)
        {
            toggleButton7.setImageResource(R.drawable.icons8domingo40verde);
        domingoverde=true;}
        else {
            toggleButton7.setImageResource(R.drawable.icons8domingo40);
        domingoverde=false;}


        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!lunesverde)
                {
                    toggleButton1.setImageResource(R.drawable.icons8lunes40verde);
                    lunesverde=true;
                } else {
                    toggleButton1.setImageResource(R.drawable.icons8lunes40);
                    lunesverde=false;
                }

            }

        });

        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!lunesverde)
                {
                    toggleButton1.setImageResource(R.drawable.icons8lunes40verde);
                    lunesverde=true;
                } else {
                    toggleButton1.setImageResource(R.drawable.icons8lunes40);
                    lunesverde=false;
                }

            }

        });
        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!martesverde)
                {
                    toggleButton2.setImageResource(R.drawable.icons8martes40verde);
                    martesverde=true;
                } else {
                    toggleButton2.setImageResource(R.drawable.icons8martes40);
                    martesverde=false;
                }

            }

        });
        toggleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!miercolesverde)
                {
                    toggleButton3.setImageResource(R.drawable.icons8miercoles40verde);
                    miercolesverde=true;
                } else {
                    toggleButton3.setImageResource(R.drawable.icons8miercoles40);
                    miercolesverde=false;
                }

            }

        });
        toggleButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!juevesverde)
                {
                    toggleButton4.setImageResource(R.drawable.icons8jueves40verde);
                    juevesverde=true;
                } else {
                    toggleButton4.setImageResource(R.drawable.icons8jueves40);
                    juevesverde=false;
                }

            }

        });
        toggleButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!viernesverde)
                {
                    toggleButton5.setImageResource(R.drawable.icons8viernes40verde);
                    viernesverde=true;
                } else {
                    toggleButton5.setImageResource(R.drawable.icons8viernes40);
                    viernesverde=false;
                }

            }

        });
        toggleButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!sabadoverde)
                {
                    toggleButton6.setImageResource(R.drawable.icons8sabado40verde);
                    sabadoverde=true;
                } else {
                    toggleButton6.setImageResource(R.drawable.icons8sabado40);
                    sabadoverde=false;
                }

            }

        });
        toggleButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!domingoverde)
                {
                    toggleButton7.setImageResource(R.drawable.icons8domingo40verde);
                    domingoverde=true;
                } else {
                    toggleButton7.setImageResource(R.drawable.icons8domingo40);
                    domingoverde=false;
                }

            }

        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for(Pending pi:Container.pendings)
                {
                    if(pi.getIdAlarma()==idalarma)
                    {
                        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
                        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), pi.getIdPending(), intent, 0);
                        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                        alarmManager.cancel(sender);
                    }


                }

                Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
                Mihelper db=new Mihelper(getApplicationContext());
                db.insertarPending(idalarma);
                db.editAlarm(idalarma,tp.getHour(),tp.getMinute(),lunesverde,martesverde,miercolesverde,juevesverde,viernesverde,sabadoverde,domingoverde);
                AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),db.recuperaridPending(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Calendar time=Calendar.getInstance();
                time = Calendar.getInstance();
                time.set(Calendar.HOUR_OF_DAY, tp.getHour());
                time.set(Calendar.MINUTE, tp.getMinute());
                time.set(Calendar.SECOND, 0);
                time.set(Calendar.MILLISECOND, 0);

                if(Calendar.getInstance().after(time)){//if its in the past increment
                    time.add(Calendar.DATE,1);
                }



                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);


                Toast.makeText(getApplicationContext(),"Alarma Editada",Toast.LENGTH_SHORT).show();
                Intent go=new Intent(getApplicationContext(),Menu_Alarma.class);
                startActivity(go);
            }
        });




    }

    @Override
    public void onBackPressed()
    {

        Intent go=new Intent(getApplicationContext(),Menu_Alarma.class);
        startActivity(go);
    }
}
