package com.example.i_text;

import static android.Manifest.permission.BLUETOOTH;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.content.ContentValues.TAG;
import static com.example.i_text.R.drawable.book;
import static com.example.i_text.R.drawable.book_;
import static com.example.i_text.R.drawable.person;
import static com.example.i_text.R.drawable.phone;
import static com.example.i_text.R.drawable.star;
import static com.example.i_text.R.drawable.user;
import static com.example.i_text.R.drawable.user2;
import static com.example.i_text.R.drawable.user_;
import static com.itextpdf.kernel.colors.ColorConstants.BLACK;
import static com.itextpdf.kernel.colors.ColorConstants.BLUE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.tools.r8.code.S;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
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
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

public class CV1 extends AppCompatActivity {


    MaterialButton save;
    Image image,Mail,Phone,Home,User,Book,Star,rate;
    int PERMISSION_REQUEST_CODE=2296;
    String lorem,lorem1;
    String email, phone,address;

    Table skills;

    RatingBar photoshop,illustrator,indesign,Aftereffect,sketch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv1);

        save = findViewById(R.id.savebtn);
        lorem="Lorem Ipsum is simply dummy text of the printing and typesetting industry " +
                "Lorem Ipsum has been the industry's";

        lorem1="Lorem Ipsum is simply dummy text of the printing and typesetting industry " +
                "Lorem Ipsum has been the industry's" +
                " the printing and typesetting industry Lorem Ipsum has been the industry's.";

        email="Rehman@gmail.com";
        phone="923070578290";
        address="Shaheen town peshawar- 25000";

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());


        if(ContextCompat.checkSelfPermission(CV1.this,
                READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(CV1.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
        else {
            RequestPermission();
        }




        save.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {

                try {
                    pdffile();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onClick: +++++++++++++++++++++++++++++++++"+e.getMessage().toString());

                } catch (MalformedURLException e) {

                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        });





    }

    @SuppressLint("ResourceType")
    private void pdffile() throws IOException {
        String pdfPath= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

        File file=new File(pdfPath,"CV1.pdf");

        OutputStream outputStream=new FileOutputStream(file);

        PdfWriter pdfWriter=new PdfWriter(file);
        PdfDocument pdfDocument=new PdfDocument(pdfWriter);
        Document document=new Document(pdfDocument, PageSize.A4);
        document.setMargins(0,0,0,0);

        // my font
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);


        // Get images
        imagefromdrawble();
        emailicon();
        homeicon();
        phoneicon();
        usericon();
        bookicon();
        Ratings();

        float[] width1={50 ,150};

        Table aboutus= new Table(width1);
        aboutus.setMarginLeft(10);
        aboutus.setMarginTop(10);
        aboutus.addCell( new Cell().add(User).setBorder(Border.NO_BORDER));
        aboutus.addCell( new Cell().add(new Paragraph("About Me").setMarginTop(-5)
                .setTextAlignment(TextAlignment.LEFT)).setFont(font).setBorder(Border.NO_BORDER).setFontSize(20).setFontColor(BLUE).setFont(font));

        float[] width2={50 ,150};

        Table Education= new Table(width2);
        Education.setMarginLeft(10);
        Education.setMarginTop(20);
        Education.addCell( new Cell().add(Book).setBorder(Border.NO_BORDER));
        Education.addCell( new Cell().add(new Paragraph("Education").setMarginTop(-5)
                .setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER).setFontSize(20).setFontColor(BLUE).setFont(font));


        //name table
        float[] name={150,150};

        Table Name= new Table(name);
        Name.addCell(new Cell().add(new Paragraph("Areata").setFontSize(35).setBold().setFontColor(BLUE)
                        .setTextAlignment(TextAlignment.LEFT))
                .setBorder(Border.NO_BORDER));
        Name.addCell(new Cell().add(new Paragraph("Base").setMarginLeft(-25).setFontSize(35)
        ).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

        Name.addCell(new Cell().add(new Paragraph("UI Designer").setBold()
                .setMarginTop(-15).setFontSize(20)).setBorder(Border.NO_BORDER));



        //first table

        float coloumwidth[]= {200,350};
        Table table1= new Table(coloumwidth);
//        table1.setBackgroundColor(new DeviceRgb(173,216,230));


        table1.addCell(new Cell().add(image).setBorder(Border.NO_BORDER).setPaddingTop(20));
        table1.addCell(new Cell().add(Name).setPaddingTop(30).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(aboutus).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Experience")
                .setFontSize(20).setFontColor(BLUE).setFont(font)
                .setBold().setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        //icons table
        float[] width={50 ,180};

        Table table2= new Table(width);
        table2.setMarginLeft(10);
        table2.setMarginTop(20);

        table2.addCell( new Cell().add(Mail).setBorder(Border.NO_BORDER));
        table2.addCell( new Cell().add(new Paragraph(email).setPaddingLeft(10).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        table2.addCell( new Cell().add(Phone).setBorder(Border.NO_BORDER));
        table2.addCell( new Cell().add(new Paragraph(phone).setPaddingLeft(10).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        table2.addCell( new Cell().add(Home).setBorder(Border.NO_BORDER));
        table2.addCell( new Cell().add(new Paragraph(address).setPaddingLeft(10).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        // First table / main table

        table1.addCell(new Cell(3,1).add(new Paragraph(lorem).setTextAlignment(TextAlignment.RIGHT).setMarginTop(20)
                .setTextAlignment(TextAlignment.JUSTIFIED).setFontSize(12).setPaddingLeft(60).setMarginRight(20))
                .setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Senior UX Designer - Present").setFontSize(12).setMarginTop(10)
                .setBold().setFontColor(BLACK)).setBorder(Border.NO_BORDER));


        table1.addCell(new Cell().add(new Paragraph("Company Name / Location").setFontSize(12).setMarginTop(10)
        ).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph(lorem1).setMarginRight(50).setMarginTop(10)).setBorder(Border.NO_BORDER).setFontSize(12));


        table1.addCell(new Cell().add(new Paragraph("Contacts")
                .setFontSize(20).setFontColor(BLUE).setFont(font).setMarginTop(20)
                .setBold().setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Creative User Interface Designer - 2016").setFontSize(12).setMarginTop(10)
                .setBold().setFontColor(BLACK)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(2,1).add(table2).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Company Name / Location").setFontSize(12).setMarginTop(10)
        ).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph(lorem1).setMarginRight(50).setMarginTop(10)).setBorder(Border.NO_BORDER).setFontSize(12));

        table1.addCell(new Cell().add(Education).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Profissional Skills").setMarginTop(30)
                .setFontSize(20).setFontColor(BLUE).setFont(font)
                .setBold().setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("2014-2016").setFontSize(12).setPaddingLeft(60).setPaddingTop(20)).setBorder(Border.NO_BORDER));


        photoshop();


        float  pro_skills[]={100,150};

         skills=new Table(pro_skills);
        skills.setMarginTop(0);

        skills.addCell(new Cell().add(new Paragraph("Photoshop").setFontSize(15)
                .setFontColor(BLACK).setBold()).setBorder(Border.NO_BORDER));




        table1.addCell(new Cell(3,1).add(skills)
                .setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Master Degree-Major Name").setFontSize(12).setPaddingLeft(60)
                .setMarginTop(-10).setBold().setFontColor(BLACK)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("University name here")
                .setFontSize(12).setMarginTop(-10).setPaddingLeft(60)).setBorder(Border.NO_BORDER));









//        table1.addCell(new Cell().add(new Paragraph("2014-2016").setFontSize(12)
//                .setPaddingLeft(60).setPaddingTop(10)).setBorder(Border.NO_BORDER));
//        table1.addCell(new Cell().add(new Paragraph("Master Degree-Major Name")
//                .setFontSize(12).setPaddingLeft(60)
//                .setMarginTop(-10).setBold().setFontColor(BLACK)).setBorder(Border.NO_BORDER));
//        table1.addCell(new Cell().add(new Paragraph("University name here")
//                .setFontSize(12).setMarginTop(-10).setPaddingLeft(60)).setBorder(Border.NO_BORDER));
//
//        table1.addCell(new Cell().add(new Paragraph("2014-2016").setFontSize(12).setPaddingLeft(60).setPaddingTop(10)).setBorder(Border.NO_BORDER));
//        table1.addCell(new Cell().add(new Paragraph("Master Degree-Major Name").setFontSize(12).setPaddingLeft(60)
//                .setMarginTop(-10).setBold().setFontColor(BLACK)).setBorder(Border.NO_BORDER));
//        table1.addCell(new Cell().add(new Paragraph("University name here")
//                .setFontSize(12).setMarginTop(-10).setPaddingLeft(60)).setBorder(Border.NO_BORDER));




        //Drawing canvas

        PdfPage pdfPage = pdfDocument.addNewPage();
        PdfCanvas canvas = new PdfCanvas(pdfPage);

//        canvas.roundRectangle(29,500,25,25,10);
//        canvas.setStrokeColor(new DeviceRgb(67,84,90));
//        canvas.setStrokeColor(new DeviceRgb());
//        canvas.fill();
//
//        canvas.circle(420,730,90);
//        canvas.setStrokeColor(ColorConstants.BLUE);
//        canvas.stroke();
//
//        canvas.circle(375,650,10);
//        canvas.setStrokeColor(BLACK);
//        canvas.fill();
//


        document.add(table1);
//        document.add(table3);



        document.close();

        Toast.makeText(this,"File Saved",Toast.LENGTH_LONG).show();

    }

    private void photoshop() {

        int num=60;
        int result=60*5/100;
        for (int i =result; i < 5; i++) {

            if (result<i) {

                Drawable drawable= getDrawable(R.drawable.rate);
                Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();

                ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

                byte[] bitmapdata= outputStream1.toByteArray();

                ImageData imageData= ImageDataFactory.create(bitmapdata);

                rate=new Image(imageData);

                rate.setWidth(20);
                rate.setHeight(20);

                skills.addCell(new Cell().add(Star).setBorder(Border.NO_BORDER)
                        .add(Star).setMarginTop(-10).setPaddingLeft(20)
                        .setBorder(Border.NO_BORDER));


            }else{


                Ratings();

                return;

            }

        }

    }


    private void RequestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(CV1.this, READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this).setTitle("Permission Needed")
                    .setMessage("Permission Needed for this application.").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CV1.this, new String[]{READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();

        } else {
            ActivityCompat.requestPermissions(CV1.this, new String[]{READ_EXTERNAL_STORAGE}, 6578);
        };


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 6578) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"permission granted", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this,"permission not granted", Toast.LENGTH_SHORT).show();}
        }
    }




    private void imagefromdrawble(){

        Drawable drawable= getDrawable(R.drawable.cvprofile);
        Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();


        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

        image=new Image(imageData);
        image.setWidth(100);
        image.setHeight(100);
        image.setHorizontalAlignment(HorizontalAlignment.CENTER);



    }

    private void emailicon(){

        Drawable drawable= getDrawable(R.drawable.mail);
        Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();


        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

        Mail=new Image(imageData);
        Mail.setWidth(20);
        Mail.setHeight(20);

    }
    private void homeicon(){

        Drawable drawable= getDrawable(R.drawable.home);
        Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();


        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

        Home=new Image(imageData);
        Home.setWidth(20);
        Home.setHeight(20);



    }
    private void phoneicon(){

        Drawable drawable= getDrawable(R.drawable.phone);
        Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();

        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

        Phone=new Image(imageData);
        Phone.setWidth(20);
        Phone.setHeight(20);


    }
    private void usericon(){

        Drawable drawable= getDrawable(user_);
        Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();

        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

        User=new Image(imageData);
        User.setWidth(20);
        User.setHeight(20);


    }
    private void bookicon(){

        Drawable drawable= getDrawable(book);
        Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();

        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

        Book=new Image(imageData);
        Book.setWidth(20);
        Book.setHeight(20);


    }

    private void Ratings(){

        Drawable drawable= getDrawable(star);
        Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();

        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

        Star=new Image(imageData);
        Star.setWidth(20);
        Star.setHeight(20);


    }

}