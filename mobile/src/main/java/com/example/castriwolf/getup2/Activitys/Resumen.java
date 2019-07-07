package com.example.castriwolf.getup2.Activitys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.castriwolf.getup2.Activitys.Menu_Alarma;
import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.Clases.MyAlarmReceiver;
import com.example.castriwolf.getup2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Resumen extends AppCompatActivity {


    //variables
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
    private int Tlevantarse;
    private int Tbano;
    private int Tdesayuno;
    private int Totros;
    private Boolean coche;
    private Boolean bus;
    private Boolean bici;
    private Boolean andar;
    private ImageView crear;
    private TextView txtHora;
    private ImageView imgLunes;
    private ImageView imgMartes;
    private ImageView imgMiercoles;
    private ImageView imgJueves;
    private ImageView imgViernes;
    private ImageView imgSabado;
    private ImageView imgDomingo;
    private ImageView imgModo;
    private TextView txtSalida;
    private TextView txtLlegada;
    private TextView txtLevantarse;
    private TextView txtBano;
    private TextView txtDesayuno;
    private TextView txtOtros;
    private TextView txtTotal;
    private TextView txtTrecorrido;
    private double total;
    private int horarestar;
    private int minutosrestar;
    private int horadespertar;
    private int minutosdespertar;
    ArrayList<Integer> diasdelasemana;
    private ImageView cancelar;
    int idalarma;
    private int l,m,x,j,v,s,d; //dias de la semana
    Mihelper db;
    private int minutosTotales;
    private int recorridosEnMinutos;
    SharedPreferences pref;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);


        horarestar = 0;
        minutosrestar = 0;
        crear = findViewById(R.id.guardar);
        txtHora = findViewById(R.id.txtHora);
        txtSalida = findViewById(R.id.txtsalida);
        txtLlegada = findViewById(R.id.txtllegada);
        imgLunes = findViewById(R.id.imgLunes);
        imgMartes = findViewById(R.id.imgMartes);
        imgMiercoles = findViewById(R.id.imgMiercoles);
        imgJueves = findViewById(R.id.imgJueves);
        imgViernes = findViewById(R.id.imgViernes);
        imgSabado = findViewById(R.id.imgSabado);
        imgDomingo = findViewById(R.id.imgDomingo);
        imgModo = findViewById(R.id.imgModo);
        txtLevantarse = findViewById(R.id.txtLevantarse);
        txtDesayuno = findViewById(R.id.txtDesayunar);
        txtBano = findViewById(R.id.txtBaño);
        txtOtros = findViewById(R.id.txtOtros);
        txtTotal = findViewById(R.id.txtResultado);
        txtTrecorrido = findViewById(R.id.txtTrecorrido);
        cancelar=findViewById(R.id.cancelaralarma);
        pref=getSharedPreferences("preferenciasdias", Context.MODE_PRIVATE);
        editor=pref.edit();

        diasdelasemana = new ArrayList<>();
        //Recogemos los datos del Bundle
        recogerDatos();
        //colocamos un cero delante si los minutos es <0
        if (minuto < 10) {
            txtHora.setText(hora + ":0" + minuto);
        } else {
            txtHora.setText(hora + ":" + minuto);
        }
        txtLevantarse.setText(Tlevantarse + " Minutos");
        txtBano.setText(Tbano + " Minutos");
        txtDesayuno.setText(Tdesayuno + " Minutos");
        txtOtros.setText(Totros + " Minutos");
        if (horaRecorrido > 1) {
            txtTrecorrido.setText(horaRecorrido + " Horas " + minutosRecorrido + " Minutos");
            minutosRecorrido += horaRecorrido * 60;
        } else {
            txtTrecorrido.setText(minutosRecorrido + " Minutos");
        }

        total = (Tlevantarse + Tbano + Tdesayuno + Totros + minutosRecorrido);
        txtTotal.setText(total + " Minutos");

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                establecerAlarma();
            }


        });

        comprobarDatos();
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent go=new Intent(getApplicationContext(),Menu_Alarma.class);
                startActivity(go);
                Toast.makeText(getApplicationContext(),"Alarma Cancelada",Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void insertarActividad() {

        //Inserta nuestra actividad en la BBDD

       db=new Mihelper(getApplicationContext());
       db.insertarActividad("tiempolevantarse",Tlevantarse);
       db.insertarActividad("tiempobaño",Tbano);
       db.insertarActividad("tiempodesayuno" ,Tdesayuno);
       db.insertarActividad("tiempootros",Totros);
       recorridosEnMinutos = (horaRecorrido*60)+minutosRecorrido;
       db.insertarActividad("tiemporecorrido",recorridosEnMinutos);
    }

    private void comprobarDatos() {
        //Comprueba el valor de los booleanos de los dias para cambiar la imagen entre roja y ver

        if (lunes == true) {

            imgLunes.setImageResource(R.drawable.icons8lunes40verde);
            l=1;

        }
        if (martes == true) {

            imgMartes.setImageResource(R.drawable.icons8martes40verde);
            m=1;

        }
        if (miercoles == true) {

            imgMiercoles.setImageResource(R.drawable.icons8miercoles40verde);
            x=1;
        }
        if (jueves == true) {

            imgJueves.setImageResource(R.drawable.icons8jueves40verde);
            j=1;

        }
        if (viernes == true) {

            imgViernes.setImageResource(R.drawable.icons8viernes40verde);
            v=1;

        }
        if (sabado == true) {

            imgSabado.setImageResource(R.drawable.icons8sabado40verde);
            s=1;

        }
        if (domingo == true) {

            imgDomingo.setImageResource(R.drawable.icons8domingo40verde);
            d=1;

        }
        if (coche == true) {
            imgModo.setImageResource(R.drawable.icons8cocheverde);
        }
        if (bus == true) {
            imgModo.setImageResource(R.drawable.icons8autobusverde);
        }
        if (bici == true) {
            imgModo.setImageResource(R.drawable.icons8biciverde);
        }
        if (andar == true) {
            imgModo.setImageResource(R.drawable.icons8caminarverde);
        }


    }

    private void recogerDatos() {

        //recoge los datos del Bundle para enviarlos a la siguiente actividad
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
        //Lugares de salida y llegada
        txtSalida.setText(parametros.getString("Lsalida"));
        txtLlegada.setText(parametros.getString("Lllegada"));
        //Tiempo para levantarte
        Tlevantarse = parametros.getInt("Tlevantarse");
        //Tiempo para el baño
        Tbano = parametros.getInt("Tbaño");
        //Tiempo para el desayuno
        Tdesayuno = parametros.getInt("Tdesayuno");
        //tiempo otros
        Totros = parametros.getInt("Textra");
        //Modo Transporte
        coche = parametros.getBoolean("Coche");
        bus = parametros.getBoolean("Bus");
        bici = parametros.getBoolean("Bici");
        andar = parametros.getBoolean("Andar");


    }

    private void establecerAlarma() {

        //envio a diferentes metodos
        formulaCalcularAlarma();

        insertarAlarma();
        alarmanager();

        Intent go = new Intent(getApplicationContext(), Menu_Alarma.class);
        startActivity(go);

    }


    private void formulaCalcularAlarma() {

        //Algoritmo que nos calcula la hora que a la que la alarma tiene que sonar
        double resultado;

        minutosTotales = hora * 60;
        minutosTotales += minuto;
        resultado = minutosTotales - total;

        while (resultado >= 60) {
            resultado -= 60;
            horadespertar += 1;

        }
        if (minutosrestar < 60) {
            minutosdespertar = (int) resultado;
        }
        //lamada a metodo que nos insertará la actividad en nuestra BBDD
        insertarActividad();


    }

    private void alarmanager() {

        /*
        Creacion de la alarma dependiendo de los dias deseados con varios Calendar diferentes
        según los dias que hemos ido guardando y trayendo en el bundle
         */

        Toast.makeText(this, "Alarma Creada a la/s " + horadespertar + ":" + minutosdespertar, Toast.LENGTH_SHORT).show();
        Calendar time1;
        Calendar time2;
        Calendar time3;
        Calendar time4;
        Calendar time5;
        Calendar time6;
        Calendar time7;

        idalarma = db.getIDAlarma();

        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if (lunes) {

            Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
            insertarPending();

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), recuperarIdPending(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            time1= Calendar.getInstance();
            time1.set(Calendar.HOUR_OF_DAY, horadespertar);
            time1.set(Calendar.MINUTE, minutosdespertar);
            time1.set(Calendar.SECOND, 0);
            time1.set(Calendar.MILLISECOND, 0);

            if(Calendar.getInstance().after(time1)){//if its in the past increment
                time1.add(Calendar.DATE,1);
            }


            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time1.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);

        }
        if (martes) {

            Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
            insertarPending();
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), recuperarIdPending(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            time2= Calendar.getInstance();
            time2.set(Calendar.HOUR_OF_DAY, horadespertar);
            time2.set(Calendar.MINUTE, minutosdespertar);
            time2.set(Calendar.SECOND, 0);
            time2.set(Calendar.MILLISECOND, 0);

            if(Calendar.getInstance().after(time2)){//if its in the past increment
                time2.add(Calendar.DATE,1);
            }

            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time2.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);

        }
        if (miercoles) {

            Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
            insertarPending();

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), recuperarIdPending(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

            time3 = Calendar.getInstance();
            time3.set(Calendar.HOUR_OF_DAY, horadespertar);
            time3.set(Calendar.MINUTE, minutosdespertar);
            time3.set(Calendar.SECOND, 0);
            time3.set(Calendar.MILLISECOND, 0);

            if(Calendar.getInstance().after(time3)){//if its in the past increment
                time3.add(Calendar.DATE,1);
            }


            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time3.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);


        }
        if (jueves) {

            Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
            insertarPending();

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), recuperarIdPending(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

             time4 = Calendar.getInstance();
            time4.set(Calendar.HOUR_OF_DAY, horadespertar);
            time4.set(Calendar.MINUTE, minutosdespertar);
            time4.set(Calendar.SECOND, 0);
            time4.set(Calendar.MILLISECOND, 0);

            if(Calendar.getInstance().after(time4)){//if its in the past increment
                time4.add(Calendar.DATE,1);
            }

            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time4.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);


        }
        if (viernes) {
            Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
            insertarPending();

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), recuperarIdPending(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

            time5 = Calendar.getInstance();
            time5.set(Calendar.HOUR_OF_DAY, horadespertar);
            time5.set(Calendar.MINUTE, minutosdespertar);
            time5.set(Calendar.SECOND, 0);
            time5.set(Calendar.MILLISECOND, 0);

            if(Calendar.getInstance().after(time5)){//if its in the past increment
                time5.add(Calendar.DATE,1);
            }




            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time5.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);

        }
        if (sabado) {

            Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
            insertarPending();

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), recuperarIdPending(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

            time6 = Calendar.getInstance();
            time6.set(Calendar.HOUR_OF_DAY, horadespertar);
            time6.set(Calendar.MINUTE, minutosdespertar);
            time6.set(Calendar.SECOND, 0);
            time6.set(Calendar.MILLISECOND, 0);

            if(Calendar.getInstance().after(time6)){//if its in the past increment
                time6.add(Calendar.DATE,1);
            }

            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time6.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);


        }
        if (domingo) {
            Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
            insertarPending();

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), recuperarIdPending(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

            time7 = Calendar.getInstance();
            time7.set(Calendar.HOUR_OF_DAY, horadespertar);
            time7.set(Calendar.MINUTE, minutosdespertar);
            time7.set(Calendar.SECOND, 0);
            time7.set(Calendar.MILLISECOND, 0);

            if(Calendar.getInstance().after(time7)){//if its in the past increment
                time7.add(Calendar.DATE,1);
            }



            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time7.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);


        }
        editor.commit();


    }


    public void insertarAlarma() {

        //Metodo para insertar nuestra alarma en la BBDD

        Mihelper db = new Mihelper(this);

        String lugarSalida = txtSalida.toString();
        String lugarLlegada = txtLlegada.toString();


        //insertamos la alarma en la BBDD
        boolean result = db.insertarAlarma(lugarSalida, lugarLlegada, horadespertar, minutosdespertar, hora, minuto,lunes , martes , miercoles , jueves , viernes , sabado , domingo);
        if (result == true) {

            //si el resultado es true cerramos la bbdd
            db.close();

        } else {
            Toast.makeText(this, "NO se ha introducido en la bd", Toast.LENGTH_SHORT).show();

        }

        db.close();
}

        public void insertarPending() {

        //Funcion para insertar nuestro pendingintent en la BBDD
            Mihelper db = new Mihelper(this);

            db.insertarPending(idalarma);
            db.close();
        }

        private int recuperarIdPending(){

        //Recuperamos el pendind de nuestra BBDD
            Mihelper db = new Mihelper(this);
            int result = db.recuperaridPending();
            db.close();
            return result;
        }

}
