package com.example.i_text;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.content.ContentValues.TAG;

import static com.example.i_text.R.drawable.phone;
import static com.itextpdf.kernel.colors.ColorConstants.BLACK;
import static com.itextpdf.kernel.colors.ColorConstants.ORANGE;
import static com.itextpdf.layout.property.HorizontalAlignment.LEFT;
import static com.sun.xml.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
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
import java.util.Locale;

public class CV_Screen extends AppCompatActivity {


    MaterialButton save;
    Image image,image2,image3,image4,image5;


    int PERMISSION_REQUEST_CODE=2296;

    List languages;
    List skills;
    String username;
    String designation;
    String email;
    String phoneNo;
    String address;

    String profile;
    String experiencetitle,experiencetitle1;
    String experiencedescription,experiencedescription1;
    String edu1From_2,edu2From_2,edu3From_2;
    String degree1, degree2, degree3;
    String university1, university2, university3;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_screen);


        save = findViewById(R.id.savebtn);





        username="Khan Rehman";
        designation="Mason";
        email="khan@gmail.com";
        phoneNo="+97894759837249";
        address="Doha Qatar";
        profile="Builds walls, fences, walkways, roads, and other structures using bricks," +
                " stone, concrete blocks, or marble. Carves out and builds structures " +
                "and surfaces with bricks or concrete blocks." +
                " Places brick or concrete blocks on mortar bed. Cuts or saws bricks to" +
                " fit around windows and doors";

        edu1From_2="2018-2020";
        edu2From_2="2016-2018";
        edu3From_2="2012-2016";

        degree1="Master's";
        degree2="Associate Degree";
        degree3="Software Engineering";

        university1="Qurtuba University of Science & Information";
        university2=" Gandhara University";
        university3="Islamia College AND University";



        experiencetitle="Senior Software Engineer and Systems Engineer";
        experiencedescription="Lorem Ipsum is simply dummy text of the printing " +
                "and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s," +
                " when an unknown printer took a galley of type and scrambled " +
                "it to make a type specimen book.";


        experiencetitle1="Senior Software Engineer and Systems Engineer";
        experiencedescription1="Lorem Ipsum is simply dummy text of the printing " +
                "and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s,";


        languages= new List();
        languages.add("english");
        languages.add("french");
        languages.add("spanish");
        languages.add("chinese");
        languages.add("chinese_simplified");
        languages.add("chinese_traditional");
        languages.add("korean");
        languages.add("japanese");
        languages.add("chinese");
        languages.add("chinese_simplified");
        languages.add("chinese_traditional");
        languages.add("korean");
        languages.add("japanese");
        languages.add("english");
        languages.add("french");
        languages.add("spanish");
        languages.add("chinese");
        languages.setFontColor(BLACK).setBold();
        languages.setMarginLeft(20);
        languages.setTextAlignment(TextAlignment.LEFT);



        skills=new List();

        skills.add("java");
        skills.add("android");
        skills.add("ios");
        skills.add("windows");
        skills.add("linux");
        skills.add("mac");
        skills.add("windows_phone");
        skills.add("android_phone");
        skills.add("android");
        skills.add("ios");
        skills.add("windows");
        skills.add("linux");
        skills.add("mac");
        skills.add("android_phone");
        skills.add("android");
        skills.add("ios");
        skills.add("windows");
        skills.add("linux");
        skills.add("mac");
        skills.setFontColor(BLACK).setBold();
        skills.setBorder(Border.NO_BORDER);
        skills.setMarginLeft(20);
        skills.setTextAlignment(TextAlignment.LEFT);



        save=findViewById(R.id.savebtn);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());


        if(ContextCompat.checkSelfPermission(CV_Screen.this,
                READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(CV_Screen.this, "Permission Granted", Toast.LENGTH_SHORT).show();
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

        File file=new File(pdfPath,"CV.pdf");

        OutputStream outputStream=new FileOutputStream(file);

        PdfWriter pdfWriter=new PdfWriter(file);
        PdfDocument pdfDocument=new PdfDocument(pdfWriter);
        Document document=new Document(pdfDocument, PageSize.A4);
        document.setMargins(40,20,20,30);

        // Get images
        imagefromdrawble();
        emailicon();
        homelicon();
        phonelicon();
        // Reference table
        float [] coloumwidth2={250,250};
        Table table1= new Table(coloumwidth2);
        table1.setMarginTop(10);

        table1.addCell(new Cell().add(new Paragraph("Senior Designer")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Senior UX Designer")).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Jhon Smith").setBold()).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Jhony Ander Smith").setBold()).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Company name here")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Company name here")).setBorder(Border.NO_BORDER));


        //Iconc table
        float[] width={50 ,200};

        Table table2= new Table(width);
        table2.setMarginTop(30);
        table2.addCell( new Cell().add(image3).setBorder(Border.NO_BORDER));
        table2.addCell( new Cell().add(new Paragraph(email)).setBorder(Border.NO_BORDER));

        table2.addCell( new Cell().add(image5).setBorder(Border.NO_BORDER));
        table2.addCell( new Cell().add(new Paragraph(phoneNo)).setBorder(Border.NO_BORDER));

        table2.addCell( new Cell().add(image4).setBorder(Border.NO_BORDER));
        table2.addCell( new Cell().add(new Paragraph(address)).setBorder(Border.NO_BORDER));

        // Main table
        float coloumwidth[] ={400,200};

        Table table= new Table(coloumwidth);

        table.addCell(new Cell().add(new Paragraph(username).setBold().setItalic().setFontSize(40).setFontColor(ColorConstants.ORANGE)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(3,1).add(image).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));

        table.addCell(new Cell().add(new Paragraph(designation).setBold().setFontSize(20).setFontColor(BLACK)).setBorder(Border.NO_BORDER)).setMarginTop(-10);
//        document.add(table2);
        table.addCell(new Cell().add(table2).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Profile").setBold()
                .setFontColor(ColorConstants.ORANGE)
                .setFontSize(25)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Education").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(25).setFontColor(ColorConstants.ORANGE)).setBorder(Border.NO_BORDER));


        table.addCell(new Cell(3,1).add(new Paragraph(profile).setTextAlignment(TextAlignment.JUSTIFIED))
                .setMarginRight(20).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(edu1From_2).setMarginLeft(20).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER)).setMarginRight(50);

        table.addCell(new Cell().add(new Paragraph(degree1).setMarginLeft(20).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER).setBold().setFontColor(BLACK));
        table.addCell(new Cell().add(new Paragraph(university1).setMarginLeft(20).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(2,1).add(new Paragraph("Experience").setBold().setFontSize(25).setFontColor(ColorConstants.ORANGE)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(edu2From_2).setMarginLeft(20).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER)).setMarginRight(50);

        table.addCell(new Cell().add(new Paragraph(degree2).setMarginLeft(20).setTextAlignment(TextAlignment.LEFT).setFontColor(BLACK).setBold()).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(experiencetitle).setFontColor(BLACK).setFontSize(12).setBold()).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(new Paragraph(university2).setMarginLeft(20).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(3,1).add(new Paragraph(experiencedescription).setTextAlignment(TextAlignment.JUSTIFIED).setMarginTop(10)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(new Paragraph(edu3From_2).setMarginLeft(20).setMarginTop(15).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER)).setMarginRight(50);
        table.addCell(new Cell().add(new Paragraph(degree3).setMarginLeft(20).setTextAlignment(TextAlignment.LEFT).setFontColor(BLACK).setBold()).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(university3).setMarginLeft(20).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(new Paragraph(experiencetitle1).setFontColor(BLACK).setFontSize(12).setBold()).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Languages").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(25).setFontColor(ColorConstants.ORANGE)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(new Paragraph(experiencedescription1).setTextAlignment(TextAlignment.JUSTIFIED)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(4,1).add(languages).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(new Paragraph(experiencetitle1).setFontColor(BLACK).setFontSize(12).setBold().setMarginTop(15)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(experiencedescription1).setTextAlignment(TextAlignment.JUSTIFIED)).setMarginTop(10).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(new Paragraph(experiencetitle1).setFontColor(BLACK).setFontSize(12).setBold().setMarginTop(15)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(experiencedescription1).setTextAlignment(TextAlignment.JUSTIFIED)).setMarginTop(10).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(new Paragraph("Skills").setTextAlignment(TextAlignment.CENTER).setMarginTop(20).setBold().setFontSize(25).setFontColor(ColorConstants.ORANGE)).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(new Paragraph("Refrences").setBold().setFontSize(25).setFontColor(ColorConstants.ORANGE)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(3,1).add(skills).setBorder(Border.NO_BORDER));

        table.addCell(new Cell(2,1).add(table1).setBorder(Border.NO_BORDER));









        //Drawing canvas

        PdfPage pdfPage = pdfDocument.addNewPage();
        PdfCanvas canvas = new PdfCanvas(pdfPage);

        canvas.roundRectangle(29,615,25,75,10);
        canvas.setStrokeColor(ColorConstants.BLUE);
        canvas.stroke();

        canvas.circle(420,730,90);
        canvas.setStrokeColor(ColorConstants.BLUE);
        canvas.stroke();

        canvas.circle(375,650,10);
        canvas.setStrokeColor(BLACK);
        canvas.fill();



        document.add(table);


        document.close();

        Toast.makeText(this,"File Saved",Toast.LENGTH_LONG).show();

    }




    private void RequestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(CV_Screen.this, READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this).setTitle("Permission Needed")
                    .setMessage("Permission Needed for this application.").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CV_Screen.this, new String[]{READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();

        } else {
            ActivityCompat.requestPermissions(CV_Screen.this, new String[]{READ_EXTERNAL_STORAGE}, 6578);
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
        image.setWidth(160);
        image.setHeight(160);



    }

    private void emailicon(){

        Drawable drawable= getDrawable(R.drawable.email);
        Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();


        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

        image3=new Image(imageData);
        image3.setWidth(15);
        image3.setHeight(15);

    }
    private void homelicon(){

        Drawable drawable= getDrawable(R.drawable.home);
        Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();


        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

        image4=new Image(imageData);
        image4.setWidth(15);
        image4.setHeight(15);



    }
    private void phonelicon(){

        Drawable drawable= getDrawable(phone);
        Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();

        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

        image5=new Image(imageData);
        image5.setWidth(15);
        image5.setHeight(15);


    }





}