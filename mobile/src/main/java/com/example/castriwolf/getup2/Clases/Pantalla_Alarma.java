package com.example.castriwolf.getup2.Clases;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.castriwolf.getup2.Activitys.Menu_Alarma;
import com.example.castriwolf.getup2.R;

public class Pantalla_Alarma extends AppCompatActivity {


    //Clase no usada actualmente, utilizable en futuras mejoras
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri alarmUri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);
        ringtone.play();

        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(getApplication().NOTIFICATION_SERVICE);
        Intent go = new Intent(this, Menu_Alarma.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                go, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "")
                        .setSmallIcon(R.drawable.cancelar)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.alarma_copy))
                        .setContentTitle("Alarma")
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Despierta"))
                        .setContentText("Mamon")
                        .setPriority(Notification.PRIORITY_MAX);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(10, mBuilder.build());


    }
}
