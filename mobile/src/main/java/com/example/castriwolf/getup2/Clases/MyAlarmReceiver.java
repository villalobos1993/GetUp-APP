package com.example.castriwolf.getup2.Clases;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by cristinavilas on 5/14/18.
 */

public class MyAlarmReceiver extends BroadcastReceiver {

    //Recibe la llamda de la alarma y nos envia a nuestro servicio cuando el alarmmanager lo llama
    @Override
    public void onReceive(Context context, Intent intent) {

        int nlunes=0;
        int nmartes=0;
        int nmiercoles=0;
        int njueves=0;
        int nviernes=0;
        int nsabado=0;
        int ndomingo=0;
        boolean lunes=false;
        boolean martes=false;
        boolean miercoles=false;
        boolean jueves=false;
        boolean viernes=false;
        boolean sabado=false;
        boolean domingo=false;
        Intent go=new Intent(context,MyNewIntentService.class);

        Mihelper db=new Mihelper(context);
        int hora;
        int minutos;
        Calendar c=Calendar.getInstance();
        hora=c.get(Calendar.HOUR_OF_DAY);
        minutos=c.get(Calendar.MINUTE);

        //recuperamos los dias que suena la alarma, pasandole por parametro la hora y los minutos

        ArrayList<Integer>diassonar=db.diasAlarma(hora,minutos);


        nlunes=diassonar.get(0);
        nmartes=diassonar.get(1);
        nmiercoles=diassonar.get(2);
        njueves=diassonar.get(3);
        nviernes=diassonar.get(4);
        nsabado=diassonar.get(5);
        ndomingo=diassonar.get(6);

        /*
        Comparamos lod dias que tiene que sonar con el dia actual y si coincide lanzamos el servicio
         */
        if(nlunes==1)
        {
            lunes=true;
        }
        if(nmartes==1)
        {
            martes=true;
        }
        if(nmiercoles==1)
        {
            miercoles=true;
        }
        if(njueves==1)
        {
            jueves=true;
        }
        if(nviernes==1)
        {
            viernes=true;
        }
        if(nsabado==1)
        {
            sabado=true;
        }
        if(ndomingo==1)
        {
            domingo=true;
        }





        int dia= c.get(Calendar.DAY_OF_WEEK);





        if(lunes&&dia==2) {

        context.startService(go);
    }
        if(martes&&dia==3) {

            context.startService(go);
        }
        if(miercoles&&dia==4) {

            context.startService(go);
        }
        if(jueves&&dia==5) {

            context.startService(go);
        }
        if(viernes&&dia==6) {

            context.startService(go);
        }
        if(sabado&&dia==7) {

            context.startService(go);
        }
        if(domingo&&dia==1) {

            context.startService(go);
        }


    }
}