package com.example.wence.recaudap;

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

public class RecaudarRapido extends ActionBarActivity {

    EditText EntAnt,SalAnt,EntAct,SalAct,etTasas;
    TextView tvTotal,tvLocal,tvEmpresa;
    String TotalToString,LocalToString,EmpresaToString;
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
        setContentView(R.layout.activity_recaudar_rapido);

        EntAnt = (EditText) findViewById(R.id.etEntAnt);
        SalAnt = (EditText) findViewById(R.id.etSalAnt);

        EntAct = (EditText) findViewById(R.id.etEntAct);
        SalAct = (EditText) findViewById(R.id.etSalAct);

        etTasas = (EditText) findViewById(R.id.etTasas);

        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvLocal = (TextView) findViewById(R.id.tvLocal);
        tvEmpresa = (TextView) findViewById(R.id.tvEmpresa);


    }

    public void onRecaudacionRapida(View v) {

        if (EntAnt.getText().toString().equals("") || SalAnt.getText().toString().equals("") || EntAct.getText().toString().equals("") || SalAct.getText().toString().equals("") || etTasas.getText().toString().equals("")) {

            Toast t = Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_LONG);
            t.show();
        } else {
            EntradaOld = Integer.parseInt(EntAnt.getText().toString());
            SalidaOld = Integer.parseInt(SalAnt.getText().toString());
            EntradaAct = Integer.parseInt(EntAct.getText().toString());
            SalidaAct = Integer.parseInt(SalAct.getText().toString());
            Tasas = Integer.parseInt(etTasas.getText().toString());

            Total = (EntradaAct - EntradaOld) - (SalidaAct - SalidaOld) * 0.20;
            TotalToString = Double.toString(Total);

            Local = (Total / 2) - Tasas;
            LocalToString = Double.toString(Local);

            Empresa = Local + Tasas;
            EmpresaToString = Double.toString(Empresa);

            tvTotal.setText(TotalToString);
            tvLocal.setText(LocalToString);
            tvEmpresa.setText(EmpresaToString);

        }
    }

    public void onGenerarPDFRR(View v) {
        if (EntAnt.getText().toString().equals("") || SalAnt.getText().toString().equals("") || EntAct.getText().toString().equals("") || SalAct.getText().toString().equals("") || etTasas.getText().toString().equals("")) {

            Toast t = Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_LONG);
            t.show();
        } else {
            createInvoiceObject();

            try {
                //Instanciamos la clase PdfManager
                pdfManager = new PdfManager(RecaudarRapido.this);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            assert pdfManager != null;
            pdfManager.createPdfDocument(invoiceObject);

            assert pdfManager != null;
            pdfManager.sendPdfByEmail(RECAUDACION_FOLDER + File.separator + FILENAME,  RecaudarRapido.this);
        }
    }

    public void createInvoiceObject(){
        Date fecha = new Date();

        invoiceObject.BarName="Recaudación Rápida";
        invoiceObject.clientAddress="";
        invoiceObject.clienName="";
        invoiceObject.fecha= fecha.getTime();


        InvoiceDetails invoiceDetails1 = new InvoiceDetails();

        invoiceDetails1.total=25.0;

        InvoiceDetails invoiceDetails2 = new InvoiceDetails();

        invoiceDetails2.total=52.5;

        invoiceObject.invoiceDetailsList = new ArrayList<InvoiceDetails>();
        invoiceObject.invoiceDetailsList.add(invoiceDetails1);
        invoiceObject.invoiceDetailsList.add(invoiceDetails2);

    }

}
