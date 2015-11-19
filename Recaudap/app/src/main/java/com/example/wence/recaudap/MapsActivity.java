package com.example.wence.recaudap;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements View.OnClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    TextView tvName,tvDueno,tvM1,tvM2,tvDir,tvRec,tvEnt,tvSal,tvCoor;
    ImageButton IbRecaudar;
    SQLControlador dbcon;
    String barID,barName,barDueno,m1,dir,rec,ent,sal,coor;
    long bar_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();


        dbcon = new SQLControlador(this);
        dbcon.abrirBaseDeDatos();

        tvName = (TextView) findViewById(R.id.bar_id);
        tvDueno = (TextView) findViewById(R.id.barDueno);
        tvM1 = (TextView)findViewById(R.id.m1);
        //tvM2 = (TextView) findViewById(R.id.m2);
        tvDir =(TextView) findViewById(R.id.dir);
        //tvRec = (TextView) findViewById(R.id.rec);
        //tvEnt = (TextView) findViewById(R.id.ent);
        //tvSal = (TextView) findViewById(R.id.sal);
        tvCoor = (TextView) findViewById(R.id.coor);
        IbRecaudar = (ImageButton) findViewById(R.id.IbRecaudar);


        // btnActualizar = (Button) findViewById(R.id.btnActualizar);
        //btnEliminar = (Button) findViewById(R.id.btnEliminar);

        Intent i = getIntent();
         barID = i.getStringExtra("barId");
         barName = i.getStringExtra("barNombre");
         barDueno = i.getStringExtra("barDueño");
         m1 = i.getStringExtra("barm1");
        // m2 = i.getStringExtra("barm2");
         dir = i.getStringExtra("bardir");
         rec = i.getStringExtra("barrec");
         ent = i.getStringExtra("barent");
         sal = i.getStringExtra("barsal");
         coor = i.getStringExtra("barcoor");

        bar_id = Long.parseLong(barID);

        tvName.setText(barName);
        tvDueno.setText(barDueno);
        tvM1.setText(m1);
        //tvM2.setText(m2);
        tvDir.setText(dir);
        //tvRec.setText("RECAUDACIÓN ANTERIOR : "+rec);
        //tvEnt.setText("ENTRADAS ANTERIORES : "+ent);
        //tvSal.setText("SALIDAS ANTERIORES : "+sal);
        tvCoor.setText(coor);


        //btnActualizar.setOnClickListener(this);
        //btnEliminar.setOnClickListener(this);

    }
    public void onRecaudar(View v){

        Intent i = new Intent(this,Recaudar.class);
        i.putExtra("BarID",barID);
        i.putExtra("NombreBar",barName);
        i.putExtra("BarDueno",barDueno);
        i.putExtra("Maquina1",m1);
        i.putExtra("Entrada",ent);
        i.putExtra("Salida",sal);
        i.putExtra("Direccion",dir);
        startActivity(i);


    }


    public void onClick(View v) {
       /* // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnActualizar:
                String memName_upd = et.getText().toString();
                dbcon.actualizarDatos(member_id, memName_upd);
                this.returnHome();
                break;

            case R.id.btnEliminar:
                dbcon.deleteData(member_id);
                this.returnHome();
                break;
        }*/
    }

    public void returnHome() {

        Intent home_intent = new Intent(getApplicationContext(),
                Bar_activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {


        LatLng coor = new LatLng(37.188949, -3.720409);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(coor)
                .zoom(18)
                .tilt(70)
                .build();
        CameraUpdate camup = CameraUpdateFactory.newCameraPosition(camPos);
        mMap.addMarker(new MarkerOptions().position(coor).title("ESTOYAUQUIII"));
        mMap.animateCamera(camup);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

       // mMap.addMarker(new MarkerOptions().position(new LatLng(37.188949, -3.720409)).title("POOOOO"));
    }
}
