package com.example.castriwolf.getup2.Activitys;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.castriwolf.getup2.R;

public class Crear_Alarma_Paso1 extends AppCompatActivity {

    private ImageButton toggleButton1;
    private ImageButton toggleButton2;
    private ImageButton toggleButton3;
    private ImageButton toggleButton4;
    private ImageButton toggleButton5;
    private ImageButton toggleButton6;
    private ImageButton toggleButton7;
    private ImageView next;

    private Boolean clunes = false;
    private Boolean cmartes = false;
    private Boolean cmiercoles = false;
    private Boolean cjueves = false;
    private Boolean cviernes = false;
    private Boolean csabado = false;
    private Boolean cdomingo = false;

    private TimePicker hora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alarma_paso1);

        //variables de botones
        toggleButton1 = findViewById(R.id.toggleButtonLunes);
        toggleButton2 = findViewById(R.id.toggleButtonMartes);
        toggleButton3 = findViewById(R.id.toggleButtonMiercoles);
        toggleButton4 = findViewById(R.id.toggleButtonJueves);
        toggleButton5 = findViewById(R.id.toggleButtonViernes);
        toggleButton6 = findViewById(R.id.toggleButtonSabado);
        toggleButton7 = findViewById(R.id.toggleButtonDomingo);

        //timepicker para escoger la hora a la que deseamos llegar al destino
        hora = findViewById(R.id.timePicker3);
        hora.setIs24HourView(true);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /**
                 *   Nos comprueba si al menos un día esta seleccionado,de no estarlo nos mandará un aviso
                 *   de estarlo, nos enviará a MapsActivity
                 *
                 *
                 */
                if (clunes == false && cmartes == false && cmiercoles == false && cjueves == false && cviernes == false && csabado == false && cdomingo == false) {

                    Toast.makeText(Crear_Alarma_Paso1.this, "La alarma debe tener minimo un dia asignado", Toast.LENGTH_SHORT).show();

                } else {

                    Intent go = new Intent(getApplicationContext(), MapsActivity.class);
                    //Mandamos los booleanos de los dias al siguiente acitivity
                    go.putExtra("Lunes", clunes);
                    go.putExtra("Martes", cmartes);
                    go.putExtra("Miercoles", cmiercoles);
                    go.putExtra("Jueves", cjueves);
                    go.putExtra("Viernes", cviernes);
                    go.putExtra("Sabado", csabado);
                    go.putExtra("Domingo", cdomingo);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //hora
                        go.putExtra("Hora", hora.getHour());
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //minuto
                        go.putExtra("HMinuto", hora.getMinute());
                    }


                    startActivity(go);

                }
            }
        });

        //cambia el color de los botones entre rojo y verde dependiendo de la elección del usuario

        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (clunes == false) {
                    toggleButton1.setImageResource(R.drawable.icons8lunes40verde);
                    clunes = true;
                } else {
                    toggleButton1.setImageResource(R.drawable.icons8lunes40);
                    clunes = false;
                }

            }
        });
        toggleButton2.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                if (cmartes == false) {
                    toggleButton2.setImageResource(R.drawable.icons8martes40verde);
                    cmartes = true;

                } else {
                    toggleButton2.setImageResource(R.drawable.icons8martes40);
                    cmartes = false;
                }
            }
        });
        toggleButton3.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                if (cmiercoles == false) {
                    toggleButton3.setImageResource(R.drawable.icons8miercoles40verde);
                    cmiercoles = true;
                } else {
                    toggleButton3.setImageResource(R.drawable.icons8miercoles40);
                    cmiercoles = false;

                }

            }
        });
        toggleButton4.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {


                if (cjueves == false) {
                    toggleButton4.setImageResource(R.drawable.icons8jueves40verde);
                    cjueves = true;

                } else {
                    toggleButton4.setImageResource(R.drawable.icons8jueves40);
                    cjueves = false;
                }

            }
        });
        toggleButton5.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                if (cviernes == false) {
                    toggleButton5.setImageResource(R.drawable.icons8viernes40verde);
                    cviernes = true;
                } else {
                    toggleButton5.setImageResource(R.drawable.icons8viernes40);
                    cviernes = false;
                }
            }
        });
        toggleButton6.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                if (csabado == false) {
                    toggleButton6.setImageResource(R.drawable.icons8sabado40verde);
                    csabado = true;
                } else {
                    toggleButton6.setImageResource(R.drawable.icons8sabado40);
                    csabado = false;
                }

            }
        });
        toggleButton7.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                if (cdomingo == false) {
                    toggleButton7.setImageResource(R.drawable.icons8domingo40verde);
                    cdomingo = true;
                } else {
                    toggleButton7.setImageResource(R.drawable.icons8domingo40);
                    cdomingo = false;
                }

            }
        });

    }


}
