package com.example.castriwolf.getup2.Activitys;

import android.app.AlarmManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.Preference;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.Clases.Alarma;
import com.example.castriwolf.getup2.Clases.Container;
import com.example.castriwolf.getup2.Clases.ListViewInflater;
import com.example.castriwolf.getup2.Clases.Pending;
import com.example.castriwolf.getup2.R;

import static com.example.castriwolf.getup2.Clases.Container.alarmas;

public class Menu_Alarma extends AppCompatActivity {

    //variables
    private String[] opciones = {"En que gasto el tiempo", "Preferencias", "Sobre nosotros"};
    private DrawerLayout drawerLayout;
    private ImageView anhadiralarma;
    private ImageView menupreferencias;
    private ListView listViewDrawer;
    public static ListView listView;
    private ArrayAdapter<String> adaptadorMenu;
    private boolean datos = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__alarma);
        anhadiralarma = findViewById(R.id.anhadir);
        menupreferencias = findViewById(R.id.menu);
        listViewDrawer = findViewById(R.id.list_view);
        listView = findViewById(R.id.listview);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.removeView(listViewDrawer);
        drawerLayout.addView(listViewDrawer);
        sharedPreferences=getSharedPreferences("Editar", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();


        adaptadorMenu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opciones);
        listViewDrawer.setAdapter(adaptadorMenu);

        //Opciones de nuestro menu
        listViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = null;
                switch (position) {
                    case 0:
                        i = new Intent(getApplicationContext(), GastoTiempo.class);
                        break;
                    case 1:
                        i = new Intent(getApplicationContext(), Preferencias_Alarma.class);
                        break;
                    case 2:
                        i = new Intent(getApplicationContext(), Quienes_Somos.class);
                        break;
                    case 3:


                        break;
                    case 4:
                        break;
                }

                startActivity(i);
                drawerLayout.closeDrawer(listViewDrawer);
            }
        });
        comprobarAlarmas();
        if (datos == true) {

            listView.setAdapter(new ListViewInflater(this, alarmas));

        } else {
            comprobarAlarmas();
        }

        //Nos envia al primer activity de crear alarma si la lista no es ==6
        anhadiralarma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (Container.alarmas.size() >= 6) {
                    Toast.makeText(getApplicationContext(), "Lista de alarmas llena", Toast.LENGTH_SHORT).show();
                } else {
                    Intent go = new Intent(getApplicationContext(), Crear_Alarma_Paso1.class);
                    startActivity(go);
                }
            }
        });
        menupreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(listViewDrawer);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                editor.clear();
                ListViewInflater listViewInflater=new ListViewInflater(getApplicationContext(),Container.alarmas);

                Alarma alarma=(Alarma)listView.getAdapter().getItem(position);

                int idalarma=alarma.getId_alarma();
                int horaalarmaeditar=alarma.getHoraSalida();
                int minutosalarmaeditar=alarma.getMinutoSalida();
                editor.putInt("horaalarmaeditar",horaalarmaeditar);
                editor.putInt("minutosalarmaeditar",minutosalarmaeditar);
                editor.putInt("idalarmaeditar",idalarma);
                editor.commit();
                Intent go=new Intent(getApplicationContext(),Editar_Alarma.class);
                startActivity(go);
            }
        });

    }

  /*  private void rellenarArray() {
        aux.clear();

        for (int i = 0; i < alarmas.size(); i++) {
            if (alarmas.get(i).getMinutoSalida() < 10) {
                String minuto="0"+String.valueOf(alarmas.get(i).getMinutoSalida());
                aux.add(String.valueOf(alarmas.get(i).getHoraSalida()+":"+minuto));
            }else{
                aux.add(String.valueOf(alarmas.get(i).getHoraSalida()+":"+alarmas.get(i).getMinutoSalida()));
            }

        }*/

    //  }

    private void comprobarAlarmas() {

        //recupera todas lar alarmas de nuestra BBDD
        Mihelper mihelper = new Mihelper(getApplicationContext());
        SQLiteDatabase db = mihelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Alarma", null);


//Nos aseguramos de que existe al menos un registro
        Container.alarmas.clear();
        Container.pendings.clear();
        try {

            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    int id_alarma = c.getInt(0);
                    String Lsalida = c.getString(1);
                    String Lllegada = c.getString(2);
                    int hSalida = c.getInt(3);
                    int mSalida = c.getInt(4);
                    int hLlegada = c.getInt(5);
                    int mLlegada = c.getInt(6);
                    int lunes = c.getInt(7);
                    int martes = c.getInt(8);
                    int miercoles = c.getInt(9);
                    int jueves = c.getInt(10);
                    int viernes = c.getInt(11);
                    int sabado = c.getInt(12);
                    int domingo = c.getInt(13);

                    Alarma alarma = new Alarma(id_alarma, Lsalida, Lllegada, hSalida, mSalida, hLlegada, mLlegada, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
                    Container.alarmas.add(alarma);
                    datos = true;
                } while (c.moveToNext());

            }
        } catch (Exception ex) {

        }

        Cursor p = db.rawQuery("SELECT * FROM Pending", null);

        try {
            if (p.moveToFirst()) {
                do {
                    //añadimos todos los pendingintent de la bbdd al container auxiliar

                    int idPending = p.getInt(0);
                    int idAlarma = p.getInt(1);
                    Pending pending = new Pending(idPending,idAlarma);
                    Container.pendings.add(pending);

                } while (p.moveToNext());

            }
        } catch (Exception e) {

        }
        db.close();}

        @Override
        public void onBackPressed () {
            //SI clicamos en el boton de atras nos pregunta si queremos salir
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                    .setMessage("Deseas salir de GetUp!?")
                    .setView(R.layout.custom_layout)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton(android.R.string.no, null).show();
        }
    }