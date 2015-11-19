package com.example.wence.recaudap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Recaudar extends ActionBarActivity {

    TextView tvNombre, tvEntrada, tvSalida, tvTotal, tvLocal, tvEmpresa;
    EditText etEntrada, etSalida, etTasas;
    String NombreBar, Entradas, Salidas,Direccion,Bardueno,TotalToString,LocalToString,EmpresaToString;
    int EntradaOld, SalidaOld, EntradaAct, SalidaAct, Tasas;
    double Total,Local,Empresa;
    ImageButton Recauda, GeneraPDF;

    InvoiceObject invoiceObject = new InvoiceObject();
    private String RECAUDACION_FOLDER = "Recaudacion";
    private String FILENAME = "Recaudacion.pdf";
    //Declaramos la clase PdfManager
    private PdfManager pdfManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recaudar);


        Recauda = (ImageButton) findViewById(R.id.bbrecaudar);
        GeneraPDF = (ImageButton) findViewById(R.id.bbgenerapdf);

        Intent i = getIntent();

        NombreBar = i.getStringExtra("NombreBar");
        Entradas = i.getStringExtra("Entrada");
        Salidas = i.getStringExtra("Salida");
        Direccion = i.getStringExtra("Direccion");
        Bardueno = i.getStringExtra("BarDueno");

        tvNombre = (TextView) findViewById(R.id.tvNombreBar);
        tvEntrada = (TextView) findViewById(R.id.tvEntAnt);
        tvSalida = (TextView) findViewById(R.id.tvSalAnt);

        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvLocal = (TextView) findViewById(R.id.tvLocal);
        tvEmpresa = (TextView) findViewById(R.id.tvEmpresa);

        etEntrada = (EditText) findViewById(R.id.etEntAct);
        etSalida = (EditText) findViewById(R.id.etSalAct);
        etTasas = (EditText) findViewById(R.id.etTasas);


        tvNombre.setText(NombreBar);
        tvEntrada.setText(Entradas);
        tvSalida.setText(Salidas);

    }

    public void onRecaudacion(View v) {


        if (etEntrada.getText().toString().equals("") || etSalida.getText().toString().equals("") || etTasas.getText().toString().equals("")) {

            Toast t = Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_LONG);
            t.show();
        } else {

            EntradaOld = Integer.parseInt(Entradas);
            SalidaOld = Integer.parseInt(Salidas);
            EntradaAct = Integer.parseInt(etEntrada.getText().toString());
            SalidaAct = Integer.parseInt(etSalida.getText().toString());
            Tasas = Integer.parseInt(etTasas.getText().toString());

            Total = (EntradaAct - EntradaOld) - (SalidaAct - SalidaOld)*0.20;
            TotalToString = Double.toString(Total);

            Local = (Total/2) - Tasas;
            LocalToString = Double.toString(Local);

            Empresa = Local + Tasas;
            EmpresaToString = Double.toString(Empresa);

            tvTotal.setText(TotalToString);
            tvLocal.setText(LocalToString);
            tvEmpresa.setText(EmpresaToString);
        }


    }

    public void onGenerarPDF(View v) {
        if (etEntrada.getText().toString().equals("") || etSalida.getText().toString().equals("") || etTasas.getText().toString().equals("")) {

            Toast t = Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_LONG);
            t.show();
        } else {
            createInvoiceObject();


            try {
                //Instanciamos la clase PdfManager
                pdfManager = new PdfManager(Recaudar.this);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            assert pdfManager != null;
            pdfManager.createPdfDocument(invoiceObject);
            assert pdfManager != null;
            pdfManager.sendPdfByEmail(RECAUDACION_FOLDER + File.separator + FILENAME, Recaudar.this);

        }
    }

    public void createInvoiceObject(){
        Date fecha = new Date();

        invoiceObject.BarName=NombreBar;
        invoiceObject.clientAddress=Direccion;
        invoiceObject.clienName=Bardueno;
        invoiceObject.fecha= fecha.getTime();
        invoiceObject.total=TotalToString;
        invoiceObject.local=LocalToString;
        invoiceObject.empresa=EmpresaToString;


        InvoiceDetails invoiceDetails1 = new InvoiceDetails();



        InvoiceDetails invoiceDetails2 = new InvoiceDetails();


        invoiceObject.invoiceDetailsList = new ArrayList<InvoiceDetails>();
        invoiceObject.invoiceDetailsList.add(invoiceDetails1);
        invoiceObject.invoiceDetailsList.add(invoiceDetails2);
    }
}
