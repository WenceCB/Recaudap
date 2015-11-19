package com.example.wence.recaudap;

/**
 * Created by Wence on 14/11/15.
 */
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


public class PdfManager {
    private static Context mContext;
    private static final String APP_FOLDER_NAME = "com.example.wence.recaudap";
    private static final String Recaudacion = "Recaudacion";
    private static Font catFont;
    private static Font subFont ;
    private static Font smallBold ;
    private static Font smallFont ;
    private static Font italicFont ;
    private static Font italicFontBold ;
    private static Date fecha_Actual;
    private static String nCliente,local,total,empresa;
    public String FILENAME;

    //Declaramos nuestra fuente base que se encuentra en la carpeta "assets/fonts" folder
    //Usaremos arialuni.ttf que permite imprimir en nuestro PDF caracteres Unicode Cirílicos (Ruso, etc)
    private static BaseFont unicode;


    private static File fontFile = new File("assets/fonts/arialuni.ttf");

    //Constructor set fonts and get context
    public PdfManager(Context context) throws IOException, DocumentException {
        mContext = context;
        //Creamos los distintos estilos para nuestro tipo de fuente.
        unicode = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        catFont = new Font(unicode, 22,Font.BOLD, BaseColor.BLACK);
        subFont = new Font(unicode, 16,Font.BOLD, BaseColor.BLACK);
        smallBold = new Font(unicode, 12,Font.BOLD, BaseColor.BLACK);
        smallFont = new Font(unicode, 12,Font.NORMAL, BaseColor.BLACK);
        italicFont = new Font(unicode, 12,Font.ITALIC, BaseColor.BLACK);
        italicFontBold = new Font(unicode, 12,Font.ITALIC|Font.BOLD, BaseColor.BLACK);
    }

    //Generando el documento PDF
    public void createPdfDocument(InvoiceObject invoiceObject) {
        try {

            //Creamos las carpetas en nuestro dispositivo, si existen las eliminamos.
            String fullFileName = createDirectoryAndFileName();

            if(fullFileName.length()>0){
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(fullFileName));

                document.open();

                //Creamos los metadatos del alchivo
                addMetaData(document);
                //Adicionamos el logo de la empresa
                addImage(document);
                //Creamos el título del documento
                addTitlePage(document, invoiceObject);
                //Creamos el total de la factura del documento
                addInvoiceTotal(document, invoiceObject);

                document.close();

                Toast.makeText(mContext, "PDF Creado Correctamente", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createDirectoryAndFileName(){
        Date fecha=new Date();
        long fecha_creacion = fecha.getTime();
        FILENAME = "Recaudacion"+fecha_creacion+".pdf";
        String fullFileName ="";
        //Obtenemos el directorio raiz "/sdcard"
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File pdfDir = new File(extStorageDirectory + File.separator + APP_FOLDER_NAME);

        //Creamos la carpeta
        try {
            if (!pdfDir.exists()) {
                pdfDir.mkdir();
            }
            File pdfSubDir = new File(pdfDir.getPath() + File.separator + Recaudacion);

            if (!pdfSubDir.exists()) {
                pdfSubDir.mkdir();
            }

            fullFileName = Environment.getExternalStorageDirectory() + File.separator + APP_FOLDER_NAME + File.separator + Recaudacion + File.separator + FILENAME;

            File outputFile = new File(fullFileName);

            if (outputFile.exists()) {
                outputFile.delete();
            }
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mContext,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return fullFileName;
    }


    //PDF library add file metadata function

    private static void addMetaData(Document document) {

        document.addTitle("Recaudación Mensual ");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Wence Criado Báez");
        document.addCreator("Wence Apps");
    }

    //Creando el Título y los datos de la Empresa y el Cliente
    private static void addTitlePage(Document document, InvoiceObject invoiceObject)
            throws DocumentException {
        nCliente = invoiceObject.clienName;
        local= invoiceObject.local;
        total=invoiceObject.total;
        empresa=invoiceObject.empresa;
        Paragraph preface = new Paragraph();
        // Adicionamos una línea en blanco
        addEmptyLine(preface, 1);
        // Adicionamos el títulos de la Factura y el número
        preface.add(new Paragraph(mContext.getResources().getString(R.string.invoice_number) + invoiceObject.id, catFont));
        preface.add(new Paragraph(mContext.getResources().getString(R.string.invoice_date) + new Date(), italicFont));



        addEmptyLine(preface, 1);

        //Adicionamos los datos del Cliente
        preface.add(new Paragraph(mContext.getResources().getString(R.string.client_title), smallBold));

        preface.add(new Paragraph(mContext.getResources().getString(R.string.client_name) + " " + invoiceObject.BarName,smallFont));
        preface.add(new Paragraph(invoiceObject.clienName,smallFont));
        preface.add(new Paragraph(invoiceObject.clientAddress, smallFont));

        addEmptyLine(preface, 1);

        //Adicionamos el párrafo creado al documento
        document.add(preface);

        // Si queremos crear una nueva página
        //document.newPage();
    }

    //Creamos el contenido de la factura, las líneas con los artículos.
    private static void addInvoiceContent(Document document, java.util.List<InvoiceDetails> invoiceDetail) throws DocumentException {

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        // Creamos una tabla con los títulos de las columnas

        // Now add all this paragraph with table included to the document
        document.add(paragraph);

    }

    //Creamos el subtotal y el total de la factura.
    private static void addInvoiceTotal(Document document, InvoiceObject invoiceObject) throws DocumentException {

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        // Add a table
        createTotalInvoiceTable(paragraph, invoiceObject);
        // Now add all this to the document
        document.add(paragraph);

    }



    //Procedimiento para crear una lines vacía
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    //Procedimiento para adicionar una imagen al documento PDF
    private static void addImage(Document document) throws IOException, DocumentException {

        Bitmap bitMap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.PNG, 80, stream);


        byte[] bitMapData = stream.toByteArray();
        Image image = Image.getInstance(bitMapData);
        //Posicionamos la imagen el el documento
        image.setAbsolutePosition(400f, 650f);
        document.add(image);
    }





    //Procedimiento para crear los totales y subtotales de la factura en forma de tabla.
    //Misma lógica utilizada para crear los títulos de las columnas de la factura
    private static void createTotalInvoiceTable(Paragraph tableSection, InvoiceObject orderHeaderModel)
            throws DocumentException {

        int TABLE_COLUMNS = 2;
        PdfPTable table = new PdfPTable(TABLE_COLUMNS);

        float[] columnWidths = new float[]{100f, 100f};
        table.setWidths(columnWidths);

        table.setWidthPercentage(100);

        //Adicionamos el título de la celda
        PdfPCell cell = new PdfPCell(new Phrase(mContext.getResources().getString(R.string.invoice_subtotal),smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(total));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);


        //Adicionamos el título de la celda
        cell = new PdfPCell(new Phrase(mContext.getResources().getString(R.string.invoice_tax),smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        //Adicionamos el contenido de la celda con el valor tax
        cell = new PdfPCell(new Phrase(local));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        //Adicionamos el título de la celda
        cell = new PdfPCell(new Phrase(mContext.getResources().getString(R.string.detail_total),smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(empresa));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        tableSection.add(table);

    }



    //Procedimiento para enviar por email el documento PDF generado
    public void sendPdfByEmail(String fileName, Context context){
        String sdCardRoot = Environment.getExternalStorageDirectory().getPath();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RECAUDACIÓN");



        String fullFileName = sdCardRoot + File.separator + APP_FOLDER_NAME + File.separator + Recaudacion+ File.separator + FILENAME;

        Uri uri = Uri.fromFile(new File(fullFileName));
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        emailIntent.setType("application/pdf");

        context.startActivity(Intent.createChooser(emailIntent, "Enviar Recaudación usando :"));

    }

}