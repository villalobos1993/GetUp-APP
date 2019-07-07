package com.example.castriwolf.getup2.Activitys;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.castriwolf.getup2.Clases.DirectionsParser;

import com.example.castriwolf.getup2.Clases.TaskRequestDirections;
import com.example.castriwolf.getup2.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private static final int LOCATION_REQUEST = 500;
    final CharSequence[] items = {"Evitar autopista", "Evitar peajes"};
    // arraylist to keep the selected items
    final ArrayList seletedItems = new ArrayList();
    public static GoogleMap mMap;
    private Marker marcador;
    private Marker marcador2;
    private double latsal = 0.0;
    private double lonsal = 0.0;
    private double latdes = 0.0;
    private double londes = 0.0;
    private Typeface weatherFont;
    private ImageView home;
    private ImageView trabajo;
    private PlaceAutocompleteFragment placeautocompletesalida;
    private PlaceAutocompleteFragment placeautocompletedestino;
    private String salida = "";
    private String destino = "";
    private Button calcular;
    private TextView distancia;
    private TextView tiempo;
    private Place punto1;
    private Place punto2;
    private Polyline line = null;
    private ImageView next;
    private ImageView ubi;
    private ImageView coche;
    private ImageView bus;
    private ImageView bici;
    private ImageView andar;
    private ImageView ajuste;
    private Boolean irCoche = false;
    private Boolean irBus = false;
    private Boolean irBici = false;
    private Boolean irAndando = false;
    private Boolean Eautopista = false;
    private Boolean Epeaje = false;
    public static Boolean pintado = false;
    private Bundle parametros;
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
    private CameraUpdate Cllegada;
    private CameraUpdate Csalida;
    private  DirectionsParser directionsParser;
    private StringBuilder stringBuilder;
    private  TaskRequestDirections taskRequestDirections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        traerBundle();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        distancia = findViewById(R.id.km);
        tiempo = findViewById(R.id.tiempo);
        home = findViewById(R.id.imgCasa);
        home.setVisibility(View.VISIBLE);
        next = findViewById(R.id.next);
        next.setVisibility(View.VISIBLE);
        ubi = findViewById(R.id.ubi);
        ubi.setVisibility(View.VISIBLE);
        coche = findViewById(R.id.ivCoche);
        bus = findViewById(R.id.ivBus);
        bici = findViewById(R.id.ivBici);
        andar = findViewById(R.id.ivAndar);
        ajuste = findViewById(R.id.ivAjustes);

        next.setOnClickListener(this);
        ubi.setOnClickListener(this);
        coche.setOnClickListener(this);
        bus.setOnClickListener(this);
        bici.setOnClickListener(this);
        andar.setOnClickListener(this);
        ajuste.setOnClickListener(this);

        //Fragment Place 1
        placeautocompletesalida = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeautocompletesalida.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(Place place) {


                salida = place.getName().toString();
                punto1 = place;
                Toast.makeText(getApplicationContext(), salida, Toast.LENGTH_LONG);
                agregarMarcador(punto1.getLatLng().latitude, punto1.getLatLng().longitude);
            }

            @Override
            public void onError(Status status) {

            }
        });

        //Fragment Place 2
        placeautocompletedestino = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete2);
        placeautocompletedestino.setOnPlaceSelectedListener(new PlaceSelectionListener() {


            @Override
            public void onPlaceSelected(Place place) {

                destino = place.getName().toString();
                punto2 = place;
                agregarMarcador2(punto2.getLatLng().latitude, punto2.getLatLng().longitude);
            }

            @Override
            public void onError(Status status) {

            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Todos estos metodos funcionan para ubicar
     * tu ubicacion en el mapa.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }

    }

    private void agregarMarcador(double lat, double lon) {


        LatLng cordenadas = new LatLng(lat, lon);
        Csalida = CameraUpdateFactory.newLatLngZoom(cordenadas, 16);

        if (marcador != null) {
            marcador.remove();
        }


        marcador = mMap.addMarker(new MarkerOptions().position(cordenadas).title("Salida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.animateCamera(Csalida);


    }

    private void agregarMarcador2(double lat, double lon) {
        LatLng cordenadas = new LatLng(lat, lon);
        Csalida = CameraUpdateFactory.newLatLngZoom(cordenadas, 16);

        if (marcador2 != null) {
            marcador2.remove();
        }


        marcador2 = mMap.addMarker(new MarkerOptions().position(cordenadas).title("Llegada").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.animateCamera(Csalida);


    }

    private void actualizarUbicacion(Location location) {

        if (location != null) {
            latsal = 0;
            lonsal = 0;
            latsal = location.getLatitude();
            lonsal = location.getLongitude();
            agregarMarcador(latsal, lonsal);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "Gps Activado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            showAlert();

        }
    };

    private void miUbicacion() {

        //comprueba si  tenemos acceso a nuestra ubicacion
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            return;
        } else {
            final LocationManager lctManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (lctManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Location location = lctManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                actualizarUbicacion(location);
                lctManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 15000, 00, locListener);
            }


        }
    }


    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
        dialog.setTitle("Enable Location")
                .setMessage("Su ubicación esta desactivada.\npor favor active su ubicación usa esta app")
                .setPositiveButton("Configuración de ubicación", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();


    }


    /**
     * Creas los parametros de la busqueda Json
     *
     * @param place1
     * @param place2
     * @return
     */
    private String getRequestUrl(Place place1, Place place2) {
        //origen
        String str_org = "";
        String mode = "";
        String time = "";
        if (place1 != null) {
            str_org = "origin=" + place1.getLatLng().latitude + "," + place1.getLatLng().longitude;
        } else {
            str_org = "origin=" + latsal + "," + lonsal;
        }
        //destino
        String str_dest = "destination=" + place2.getLatLng().latitude + "," + place2.getLatLng().longitude;
        //sensor
        String sensor = "sensor=false";
        //Modo transporte
        if (irCoche == true) {
            mode = "mode=driving";
        }
        if (irBus == true) {
            mode = "mode=transit";
            /*//Hora bus
            Calendar calendarNow = null;
            int monthDay = 0;
            int year;
            int month = 0;
            int day = 0;
            int horaDia = 0;
            int minutoDia = 0;
            Calendar fechaInicio = null;
            Calendar fechaFin = null;
            long diasMilis = 0;
            long segundos = 0;


            calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));
            year = calendarNow.get(Calendar.YEAR);
            month = calendarNow.get(Calendar.MONTH + 1);
            day = calendarNow.get(Calendar.DAY_OF_MONTH);
            horaDia = calendarNow.get(Calendar.HOUR_OF_DAY);
            minutoDia = calendarNow.get(Calendar.MINUTE);
            fechaInicio = new GregorianCalendar();
            fechaInicio.set(1970, 01, 1);


            if (hora > horaDia) {

                calendarNow.add(Calendar.DAY_OF_MONTH, 1);
                day = calendarNow.get(Calendar.DAY_OF_MONTH);
                fechaFin = new GregorianCalendar();
                fechaFin.set(year, month + 1, day);

            } else {
                fechaFin = new GregorianCalendar();
                fechaFin.set(year, month + 1, day);
            }

            diasMilis = (fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis());
            diasMilis = diasMilis / 1000;
            segundos = ((hora * 60) + minuto) * 60;
            segundos += diasMilis;
*/
            // time = "arrival_time=" + segundos;
            time = "departure_time=now";
        }
        if (irBici == true) {
            mode = "mode=bicycling";
        }
        if (irAndando == true) {
            mode = "mode=walking";
        }

        if (irBus == false) {
            //Trafico
            time = "departure_time=now";
        }
        //Build the full param
        String param = str_org + "&" + str_dest + "&" + sensor + "&" + mode + "&" + time;
        //Output format
        String output = "json";
        //Create url to request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;
        return url;
    }

    /**
     * Busqueda Json
     * Busca la informacion entre el punto de
     * salida y llegada.
     */
    private void recorridoJson() {

        tiempo.setText("");
        distancia.setText("");

        taskRequestDirections = new TaskRequestDirections();
        String url = getRequestUrl(punto1, punto2);
        taskRequestDirections.execute(url);
        stringBuilder = DirectionsParser.traerContenidoStringBuilder(url);

        new CountDownTimer(1500, 1000) {
            @Override
            public void onTick(long l) {

                Toast.makeText(getApplicationContext(), "Calculando...", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFinish() {
                directionsParser = new DirectionsParser();
                //seteamos los textview con el texto del stringbuilder

                tiempo.setText(directionsParser.parsingTiempo(stringBuilder));
                distancia.setText(directionsParser.parsingKM(stringBuilder));


                //si no le da tiempo a la API a traer los resultados ,hacemos recursividad hasta que traiga los resultados
                if (tiempo.getText().equals("") || distancia.getText().equals("")) {
                    recorridoJson();
                }
                recuperarTiempo();

            }
        }.start();


    }


    /**
     * Trae la informacion del Bundle
     * desde otra activity
     */
    private void traerBundle() {

        parametros = getIntent().getExtras();

        lunes = parametros.getBoolean("Lunes");
        martes = parametros.getBoolean("Martes");
        miercoles = parametros.getBoolean("Miercoles");
        jueves = parametros.getBoolean("Jueves");
        viernes = parametros.getBoolean("Viernes");
        sabado = parametros.getBoolean("Sabado");
        domingo = parametros.getBoolean("Domingo");
        hora = parametros.getInt("Hora");
        minuto = parametros.getInt("HMinuto");
    }

    private void recuperarTiempo() {

        //recuperamos el tiempo del JSON
        if (tiempo.getText().toString().contains("hours")) {
            String[] tiempos = tiempo.getText().toString().split(" hours ");
            try {
                horaRecorrido = Integer.parseInt(tiempos[0]);
            } catch (NumberFormatException e) {


            }


            String[] tiempos2 = tiempos[1].toString().split(" mins");

            try {
                minutosRecorrido = Integer.parseInt(tiempos2[0].toString());
            } catch (NumberFormatException e) {


            }
        } else {

            String[] tiempos = tiempo.getText().toString().split(" min");

            try {

                minutosRecorrido = Integer.parseInt(tiempos[0].toString());
            } catch (NumberFormatException e) {

            }
        }
    }

    private void movimientoCamara() {

        //nos mueve la cama segun las direcciones
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(marcador.getPosition());
        builder.include(marcador2.getPosition());
        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.10); // offset from edges of the map 10% of screen

        Csalida = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
        mMap.animateCamera(Csalida);
    }


    @Override
    public void onClick(View v) {
        /**
         * Boton ubi
         * para encontrar tu ubicacion actual.
         */

        if (v.equals(ubi)) {
            new CountDownTimer(4000, 4000) {
                @Override
                public void onTick(long l) {

                    //envio al metodo que nos encuentra la UBI
                    miUbicacion();
                    Toast.makeText(getApplicationContext(), "Buscando tu ubicacion", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFinish() {


                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(latsal, lonsal, 1);
                        //seteamos el autocomplete de salida con la direccion escrita de la latitud y longitud de la salida
                        if (addresses.size() >= 1) {
                            placeautocompletesalida.setText(addresses.get(0).getAddressLine(0));
                            salida = addresses.get(0).getAddressLine(0);
                            latsal = addresses.get(0).getLatitude();
                            lonsal = addresses.get(0).getLongitude();
                        } else {
                            Toast.makeText(getApplicationContext(), "No pudimos encontrar su ubicacion revise su conexión y permisos", Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
        /**
         * Boton de ajustes
         * abre un dialog alert para indicar tipo ajustes de ruta.
         */
        if (v.equals(ajuste)) {

            ajuste.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog dialog = new AlertDialog.Builder(MapsActivity.this)
                            .setTitle("Parametros")
                            .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                    if (isChecked) {
                                        // If the user checked the item, add it to the selected items
                                        seletedItems.add(indexSelected);
                                    } else if (seletedItems.contains(indexSelected)) {
                                        // Else, if the item is already in the array, remove it
                                        seletedItems.remove(Integer.valueOf(indexSelected));
                                    }
                                }
                            }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Your code when user clicked on OK
                                    //  You can write the code  to save the selected item here

                                    for (int i = 0; 0 > seletedItems.size(); i++) {

                                        if (seletedItems.get(i).equals(items)) {

                                        }
                                    }

                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Your code when user clicked on Cancel
                                    Eautopista = false;
                                    Epeaje = false;
                                }
                            }).create();
                    dialog.show();

                }
            });
        }

        if (v.equals(next)) {

            /**
             * Boton Next
             * para seguir al siguiente paso.
             */
            if (irCoche == true || irBus == true || irBici == true || irAndando == true && minutosRecorrido > 0) {
                Intent go = new Intent(getApplicationContext(), Crear_Alarma_Paso3.class);

                //mandamos las variables al next activity
                go.putExtra("Lunes", lunes);
                go.putExtra("Martes", martes);
                go.putExtra("Miercoles", miercoles);
                go.putExtra("Jueves", jueves);
                go.putExtra("Viernes", viernes);
                go.putExtra("Sabado", sabado);
                go.putExtra("Domingo", domingo);
                //Hora de llegada al destino
                go.putExtra("Hora", hora);
                go.putExtra("HMinuto", minuto);
                //Tiempo ruta
                go.putExtra("HorasRecorrido", horaRecorrido);
                go.putExtra("MinutosRecorridos", minutosRecorrido);
                // Lugar de salida y llegada
                go.putExtra("Lsalida", salida);
                go.putExtra("Lllegada", destino);
                //Modo Transporte
                go.putExtra("Coche", irCoche);
                go.putExtra("Bus", irBus);
                go.putExtra("Bici", irBici);
                go.putExtra("Andar", irAndando);


                startActivity(go);
            } else {
                Toast.makeText(getApplicationContext(), "Debes introducir direcciones y calcular ruta.", Toast.LENGTH_LONG).show();
            }
        }

        if (v.equals(coche)) {

            if (irCoche == false && !destino.equals("") && !salida.equals("")) {
                irCoche = true;
                irBus = false;
                irAndando = false;
                irBici = false;

                coche.setImageResource(R.drawable.icons8cocheverde);
                bus.setImageResource(R.drawable.icons8autobusgris);
                bici.setImageResource(R.drawable.icons8bicigris);
                andar.setImageResource(R.drawable.icons8caminargris);

                if (salida.equals("") || destino.equals("")) {

                    Toast.makeText(getApplicationContext(), "Introduce las dos direcciones", Toast.LENGTH_LONG).show();

                } else {

                    recorridoJson();
                    movimientoCamara();

                }
            }
        }

        if (v.equals(bus)) {

            if (irBus == false && !destino.equals("") && !salida.equals("")) {
                irCoche = false;
                irBus = true;
                irAndando = false;
                irBici = false;

                coche.setImageResource(R.drawable.icons8cochegris);
                bus.setImageResource(R.drawable.icons8autobusverde);
                bici.setImageResource(R.drawable.icons8bicigris);
                andar.setImageResource(R.drawable.icons8caminargris);

                if (salida.equals("") || destino.equals("")) {

                    Toast.makeText(getApplicationContext(), "Introduce las dos direcciones", Toast.LENGTH_LONG).show();

                } else {

                    recorridoJson();
                    movimientoCamara();
                }
            }
        }

        if (v.equals(bici)) {

            if (irBici == false && !destino.equals("") && !salida.equals("")) {
                irCoche = false;
                irBus = false;
                irAndando = false;
                irBici = true;

                coche.setImageResource(R.drawable.icons8cochegris);
                bus.setImageResource(R.drawable.icons8autobusgris);
                bici.setImageResource(R.drawable.icons8biciverde);
                andar.setImageResource(R.drawable.icons8caminargris);

                if (salida.equals("") || destino.equals("")) {

                    Toast.makeText(getApplicationContext(), "Introduce las dos direcciones", Toast.LENGTH_LONG).show();

                } else {

                    recorridoJson();
                    movimientoCamara();
                }
            }
        }

        if (v.equals(andar)) {

            if (irAndando == false && !destino.equals("") && !salida.equals("")) {
                irCoche = false;
                irBus = false;
                irAndando = true;
                irBici = false;

                coche.setImageResource(R.drawable.icons8cochegris);
                bus.setImageResource(R.drawable.icons8autobusgris);
                bici.setImageResource(R.drawable.icons8bicigris);
                andar.setImageResource(R.drawable.icons8caminarverde);

                if (salida.equals("") || destino.equals("")) {

                    Toast.makeText(getApplicationContext(), "Introduce las dos direcciones", Toast.LENGTH_LONG).show();

                } else {

                    recorridoJson();
                    movimientoCamara();
                }
            }

        }

    }


}







