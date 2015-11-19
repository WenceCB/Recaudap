package com.example.wence.recaudap;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Wence on 14/11/15.
 */
public class InvoiceObject {

    public int id;
    public String BarName;
    public String clienName;
    public String clientAddress;
    public long fecha;
    public String total;
    public String local;
    public String empresa;


    public ArrayList<InvoiceDetails> invoiceDetailsList;
}
