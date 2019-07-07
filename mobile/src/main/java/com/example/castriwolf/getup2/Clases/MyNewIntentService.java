package com.example.castriwolf.getup2.Clases;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import com.example.castriwolf.getup2.Activitys.StepCounter;

/**
 * Created by cristinavilas on 5/17/18.
 */

public class MyNewIntentService extends IntentService {

    //Servicio de nuestra alarma
    private static final int NOTIFICATION_ID = 3;
    private Notification notificationCompat;
    private static NotificationManager managerCompat;
    static boolean sonar = true;
    static MediaPlayer player;
    Vibrator vibrator;
    SharedPreferences pref;
    boolean vibrar;



    public MyNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //comprueba el valor de la vibracion en preferencias para hacer vibrar o no

         pref= getSharedPreferences("Mispreferencias", MODE_PRIVATE);
         vibrar=pref.getBoolean("vibracion",false);

         //patron de vibracion del movil

        long[] pattern = { 0, 2000, 5000, 2000, 5000, 2000, 5000, 2000, 5000, 2000, 5000};

        boolean sonar = true;
        Uri alarmUri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
         vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        //hacemos sonar la musica en loop
         player = MediaPlayer.create(this, alarmUri);
        player.setLooping(true);
        player.start();

        //Envio a clase cuentapasos
        Intent go = new Intent(getApplicationContext(), StepCounter.class);
        go.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(go);

        //if vibrar es true, mientras suena hace que vibra
        if(vibrar)
        {
            while(player.isPlaying()) {
                vibrator.vibrate(pattern,0);        }
        }



    }

    //metodo que nos para la musica al llegar a la cantidad de pasos exigida
    public static void cancelar() {
        player.stop();

    }

    public static void posponer() {
        player.stop();


    }
}
