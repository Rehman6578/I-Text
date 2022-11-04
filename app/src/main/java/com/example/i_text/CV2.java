package com.example.i_text;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static com.example.i_text.R.drawable.book;
import static com.example.i_text.R.drawable.user_;
import static com.itextpdf.kernel.colors.ColorConstants.BLACK;
import static com.itextpdf.kernel.colors.ColorConstants.BLUE;
import static com.itextpdf.kernel.colors.ColorConstants.LIGHT_GRAY;
import static com.itextpdf.kernel.colors.ColorConstants.ORANGE;
import static com.itextpdf.kernel.colors.ColorConstants.YELLOW;
import static com.itextpdf.kernel.colors.DeviceRgb.WHITE;
import static com.itextpdf.kernel.pdf.PdfName.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

public class CV2 extends AppCompatActivity {

    MaterialButton save;
    Image image, Mail, Phone, Home, User, Book, Star, fillrate;
    int PERMISSION_REQUEST_CODE = 2296;
    String lorem, lorem1;

    Table t1,t2,t3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv2);

        save = findViewById(R.id.savebtn);

        lorem="Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's";


        lorem1="Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, ";

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());


        if(ContextCompat.checkSelfPermission(CV2.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(CV2.this, "Permission Granted", Toast.LENGTH_SHORT).show();

        }
        else {
            RequestPermission();
        }



        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

        try {
            pdffile();
        } catch (IOException e) {
            e.printStackTrace();
        }




            }
        });
    }

    private void pdffile() throws IOException {

        String path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

        File file=new File(path,"CV2.pdf");

        OutputStream os=new FileOutputStream(file);
        PdfWriter pdfWriter = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument, PageSize.A4);
        document.setMargins(0, 0, 0, 0);

        // Get images
        imagefromdrawble();
        emailicon();
        homeicon();
        phoneicon();
        usericon();
        bookicon();

        // my fonts
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);



        PdfPage pdfPage = pdfDocument.addNewPage();
        PdfCanvas canvas = new PdfCanvas(pdfPage);


        // blue large background rectangle
        canvas.rectangle(0,0,200,pdfDocument.getDefaultPageSize().getHeight());
        canvas.setStrokeColor(BLUE);
        canvas.setFillColor(new DeviceRgb(32,42,68));
        canvas.fill();

        // NAme rectangle
        canvas.rectangle(200,690,pdfDocument.getDefaultPageSize().getWidth(),120);
        canvas.setFillColor(new DeviceRgb(211,211,211));
        canvas.fill();

        // about me background color
        canvas.rectangle(200,635,pdfDocument.getDefaultPageSize().getWidth(),25);
        canvas.setFillColor(new DeviceRgb(211,211,211));
        canvas.fill();

        canvas.rectangle(200,635,10,25);
        canvas.setFillColor(ORANGE);
        canvas.fill();

        // Work Experience background color
        canvas.rectangle(200,515,pdfDocument.getDefaultPageSize().getWidth(),25);
        canvas.setFillColor(new DeviceRgb(211,211,211));
        canvas.fill();

        canvas.rectangle(200,515,10,25);
        canvas.setFillColor(ORANGE);
        canvas.fill();



        // light blue background rectangle
        canvas.rectangle(0,635,200,25);
        canvas.setFillColor(new DeviceRgb(0,47,108));
        canvas.fill();

        // contact rectangle
        canvas.rectangle(0,425,200,25);
        canvas.setFillColor(new DeviceRgb(0,47,108));
        canvas.fill();

        canvas.rectangle(0,425,10,25);
        canvas.setFillColor(ORANGE);
        canvas.fill();



        // Yellow backround small rectangles
        canvas.rectangle(200,690,10,120);
        canvas.setFillColor(ORANGE);
        canvas.fill();

        // contact background color


        canvas.rectangle(0,635,10,25);
        canvas.setFillColor(ORANGE);
        canvas.fill();





        /////////////////////////////////////////

        float[] name = {150, 150};

        Table Name = new Table(name);
        Name.setMarginLeft(30);
        Name.setMarginTop(20);
        Name.addCell(new Cell().add(new Paragraph("Keran".toUpperCase(Locale.ROOT)).setFontSize(35).setBold().setFontColor(BLACK    )
                        .setTextAlignment(TextAlignment.LEFT))
                .setBorder(Border.NO_BORDER));
        Name.addCell(new Cell().add(new Paragraph("Richards".toUpperCase(Locale.ROOT)).setMarginLeft(-25)
                .setFontSize(35).setFontColor(BLACK)
        ).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

        Name.addCell(new Cell(1,2).add(new Paragraph("Profissional Title".toUpperCase(Locale.ROOT)).setBold()
                .setFontColor(BLACK) .setMarginTop(-15).setFontSize(15)).setBorder(Border.NO_BORDER));


        // Main Table
        float coloumwidth[] = {200, 350};
        Table table1 = new Table(coloumwidth);

        table1.addCell(new Cell().add(image).setBorder(Border.NO_BORDER).setPaddingTop(20));
        table1.addCell(new Cell().add(Name).setPaddingTop(30).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Education".toUpperCase(Locale.ROOT))
                .setTextAlignment(TextAlignment.LEFT).setMarginTop(60).setMarginLeft(30)
                .setFontSize(14).setFontColor(ColorConstants.WHITE).setBold())
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("About me".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(60).setMarginLeft(30)
                        .setFontSize(14).setFontColor(BLACK).setBold())
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Enter your Major".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(20).setMarginLeft(40)
                        .setFontColor(WHITE).setBold().setFontSize(12))
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(3,1).add(new Paragraph(lorem1).setMarginTop(10).setMarginLeft(40)
                        .setFontColor(BLACK).setFontSize(10)
                .setTextAlignment(TextAlignment.JUSTIFIED)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Name of your University").setMarginTop(-5)
                .setMarginLeft(40).setFontColor(WHITE)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("2005-2006").setMarginTop(-5)
                .setMarginLeft(40).setFontColor(WHITE)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Enter your Major".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(20).setMarginLeft(40)
                        .setFontColor(WHITE).setBold().setFontSize(12))
                .setBorder(Border.NO_BORDER));


        table1.addCell(new Cell().add(new Paragraph("work experience".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(20).setMarginLeft(30)
                        .setFontSize(14).setFontColor(BLACK).setBold())
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(1,2).add(new Paragraph("Name of your University").setMarginTop(-5)
                .setMarginLeft(40).setFontColor(WHITE)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(1,2).add(new Paragraph("2005-2006").setMarginTop(-5)
                .setMarginLeft(40).setFontColor(WHITE)).setBorder(Border.NO_BORDER));


        table1.addCell(new Cell().add(new Paragraph("Contact".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(30).setMarginLeft(30)
                        .setFontSize(14).setFontColor(ColorConstants.WHITE).setBold())
                .setBorder(Border.NO_BORDER));





        //work experience table


        float [] wrkEx={100,300};

         t1 = new Table(wrkEx);
        t1.setMarginLeft(30);

        t1.addCell(new Cell().add(new Paragraph("2012-2014").setBold()
                .setFontSize(10).setFontColor(BLACK)).setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph("Job position here".toUpperCase(Locale.ROOT)
        ).setFontSize(10).setFontColor(BLACK)).setBold().setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph("Company name / California USA")
                        .setTextAlignment(TextAlignment.LEFT).setMarginRight(30))
                .setFontSize(12).setBold().setFontColor(BLACK).setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
        t1.addCell(new Cell().add(new Paragraph(lorem).setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginRight(30).setMarginTop(5))
                .setFontSize(12).setFontColor(BLACK).setBorder(Border.NO_BORDER));

        //////////////////////////////////////////////////////////////////////////

        t1.addCell(new Cell().add(new Paragraph("2012-2014").setMarginTop(10).setBold()
                .setFontSize(10).setFontColor(BLACK)).setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph("Job position here".toUpperCase(Locale.ROOT)
        ).setMarginTop(10).setFontSize(10).setFontColor(BLACK)).setBold().setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph("Company name / California USA")
                        .setTextAlignment(TextAlignment.LEFT).setMarginRight(30))
                .setFontSize(12).setBold().setFontColor(BLACK).setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
        t1.addCell(new Cell().add(new Paragraph(lorem).setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginRight(30).setMarginTop(5))
                .setFontSize(12).setFontColor(BLACK).setBorder(Border.NO_BORDER));

        //////////////////////////////////////////////////////////////////////////////

        t1.addCell(new Cell().add(new Paragraph("2012-2014").setMarginTop(10).setBold()
                .setFontSize(10).setFontColor(BLACK)).setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph("Job position here".toUpperCase(Locale.ROOT)
        ).setMarginTop(10).setFontSize(10).setFontColor(BLACK)).setBold().setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph("Company name / California USA")
                        .setTextAlignment(TextAlignment.LEFT).setMarginRight(30))
                .setFontSize(12).setBold().setFontColor(BLACK).setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));

        t1.addCell(new Cell().add(new Paragraph(lorem).setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginRight(30).setMarginTop(5))
                .setFontSize(12).setFontColor(BLACK).setBorder(Border.NO_BORDER));


        ///////////////////////////**************************/////////////////////////////


        table1.addCell(new Cell(13,1).add(t1).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Address".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(10).setMarginLeft(40)
                        .setFontColor(WHITE).setBold().setFontSize(12))
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Your Street Address").setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(-5).setMarginLeft(40)
                        .setFontColor(WHITE))
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(2,1).add(new Paragraph("SS sterrt city/ Zip code - 456").setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(0).setMarginLeft(40)
                        .setFontColor(WHITE))
                .setBorder(Border.NO_BORDER));


        table1.addCell(new Cell().add(new Paragraph("Phone".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(10).setMarginLeft(40)
                        .setFontColor(WHITE).setBold().setFontSize(12))
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("09876543321".toUpperCase(Locale.ROOT)).setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginLeft(40)
                        .setFontColor(WHITE))
                .setBorder(Border.NO_BORDER));


        table1.addCell(new Cell().add(new Paragraph("Email".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(10).setMarginLeft(40)
                        .setFontColor(WHITE).setBold().setFontSize(12))
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(2,1).add(new Paragraph("flkjaf@gmail.com").setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(0).setMarginLeft(40)
                        .setFontColor(WHITE))
                .setBorder(Border.NO_BORDER));
//

        table1.addCell(new Cell().add(new Paragraph("Website".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(10).setMarginLeft(40)
                        .setFontColor(WHITE).setBold().setFontSize(12))
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(2,1).add(new Paragraph("WEbsite222.com").setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(0).setMarginLeft(40)
                        .setFontColor(WHITE))
                .setBorder(Border.NO_BORDER));


        table1.addCell(new Cell().add(new Paragraph("Reference".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(0).setMarginLeft(30)
                        .setFontSize(14).setFontColor(WHITE).setBold())
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Software Skills".toUpperCase(Locale.ROOT))
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(20).setMarginLeft(30)
                        .setFontSize(14).setFontColor(BLACK).setBold())
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Sara Taylore")
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(0).setMarginLeft(40)
                        .setFontColor(WHITE).setBold().setFontSize(12))
                .setBorder(Border.NO_BORDER));


        float width[]={150,150};
        Table skills = new Table(width);
        skills.setMarginLeft(60);
        skills.setMarginTop(20);

        skills.addCell(new Cell().add(new Paragraph("Adobe Photoshop").setFontSize(10).setFontColor(BLACK)).setBold().setBorder(Border.NO_BORDER));
        skills.addCell(new Cell().add(new Paragraph("Microsoft Word").setFontSize(10).setFontColor(BLACK)).setBold().setBorder(Border.NO_BORDER));

        skills.addCell(new Cell().add(new Paragraph("Adobe Photoshop").setFontSize(10).setFontColor(BLACK)).setBold().setBorder(Border.NO_BORDER));
        skills.addCell(new Cell().add(new Paragraph("Microsoft Word").setFontSize(10).setFontColor(BLACK)).setBold().setBorder(Border.NO_BORDER));

        skills.addCell(new Cell().add(new Paragraph("Adobe Photoshop").setFontSize(10).setFontColor(BLACK)).setBold().setBorder(Border.NO_BORDER));
        skills.addCell(new Cell().add(new Paragraph("Microsoft Word").setFontSize(10).setFontColor(BLACK)).setBold().setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(5,1).add(skills).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Directory Company name ").setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(0).setMarginLeft(40)
                        .setFontColor(WHITE))
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("T: +1232432423").setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(0).setMarginLeft(40)
                        .setFontColor(WHITE))
                .setBorder(Border.NO_BORDER));

        // second reference

        table1.addCell(new Cell().add(new Paragraph("Micke Inderson")
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(0).setMarginLeft(40)
                        .setFontColor(WHITE).setBold().setFontSize(12))
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Directory Company name ").setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(0).setMarginLeft(40)
                        .setFontColor(WHITE))
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("T: +1232432423").setFontSize(10)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(0).setMarginLeft(40)
                        .setFontColor(WHITE))
                .setBorder(Border.NO_BORDER));


        document.add(table1);
        document.close();
        Toast.makeText(CV2.this,"Saved Successfully",Toast.LENGTH_SHORT).show();

    }

    private void RequestPermission () {

        if (ActivityCompat.shouldShowRequestPermissionRationale(CV2.this, READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this).setTitle("Permission Needed")
                    .setMessage("Permission Needed for this application.").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CV2.this, new String[]{READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();

        } else {
            ActivityCompat.requestPermissions(CV2.this, new String[]{READ_EXTERNAL_STORAGE}, 6578);
        }
        ;


    }


    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
                                             @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 6578) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void imagefromdrawble () {

        Drawable drawable = getDrawable(R.drawable.cvprofile);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();


        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        image = new Image(imageData);
        image.setWidth(100);
        image.setHeight(100);
        image.setHorizontalAlignment(HorizontalAlignment.CENTER);


    }

    private void emailicon () {

        Drawable drawable = getDrawable(R.drawable.mail);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();


        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Mail = new Image(imageData);
        Mail.setWidth(20);
        Mail.setHeight(20);

    }
    private void homeicon () {

        Drawable drawable = getDrawable(R.drawable.home);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();


        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Home = new Image(imageData);
        Home.setWidth(20);
        Home.setHeight(20);


    }
    private void phoneicon () {

        Drawable drawable = getDrawable(R.drawable.phone);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Phone = new Image(imageData);
        Phone.setWidth(20);
        Phone.setHeight(20);


    }
    private void usericon () {

        Drawable drawable = getDrawable(user_);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        User = new Image(imageData);
        User.setWidth(20);
        User.setHeight(20);


    }
    private void bookicon () {

        Drawable drawable = getDrawable(book);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Book = new Image(imageData);
        Book.setWidth(20);
        Book.setHeight(20);


    }
}