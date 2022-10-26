package com.example.i_text;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.ContentValues.TAG;
import static android.os.Build.VERSION.SDK_INT;
import static com.example.i_text.R.drawable.father;
import static com.example.i_text.R.drawable.phone;
import static com.itextpdf.kernel.colors.ColorConstants.BLACK;
import static com.itextpdf.kernel.pdf.PdfName.BaseFont;
import static com.itextpdf.kernel.pdf.PdfName.Center;
import static com.itextpdf.kernel.pdf.PdfName.Circle;
import static com.itextpdf.kernel.pdf.PdfName.ColorSpace;
import static com.itextpdf.kernel.pdf.PdfName.Colors;
import static com.itextpdf.kernel.pdf.PdfName.DefaultCMYK;
import static com.itextpdf.kernel.pdf.PdfName.Resources;
import static com.itextpdf.kernel.pdf.PdfName.Stream;
import static com.itextpdf.layout.property.HorizontalAlignment.CENTER;
import static com.itextpdf.layout.property.HorizontalAlignment.LEFT;
import static com.itextpdf.layout.property.HorizontalAlignment.RIGHT;
import static com.sun.xml.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

import static org.abego.treelayout.Configuration.Location.Left;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ColorStateListInflaterCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.Settings;
import android.sax.Element;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.dx.util.ListIntSet;
import com.android.ide.common.vectordrawable.Svg2Vector;
import com.google.android.material.button.MaterialButton;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.CalRgb;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.colors.PatternColor;
import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.BackgroundImage;
import com.itextpdf.layout.property.BaseDirection;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.svg.SvgConstants;
import com.itextpdf.svg.converter.SvgConverter;
import com.itextpdf.svg.processors.impl.SvgConverterProperties;
import com.itextpdf.svg.processors.impl.SvgProcessorResult;

import org.apache.http.HttpConnection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    MaterialButton save;
    Image image,image2,image3,image4,image5;

    int PERMISSION_REQUEST_CODE=2296;




    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        save=findViewById(R.id.savebtn);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());



        save.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(MainActivity.this,
                        READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Permission Granted",Toast.LENGTH_SHORT).show();



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
                else {
                    RequestPermission();
                }


            }
        });


    }

    private void RequestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this).setTitle("Permission Needed")
                    .setMessage("Permission Needed for this application.").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();

        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{READ_EXTERNAL_STORAGE}, 6578);
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

    @SuppressLint("ResourceType")
    private void pdffile() throws IOException {



        String pdfPath= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

        File file=new File(pdfPath,"MyCV.pdf");

        OutputStream outputStream=new FileOutputStream(file);

        PdfWriter pdfWriter=new PdfWriter(file);
        PdfDocument pdfDocument=new PdfDocument(pdfWriter);
        Document document=new Document(pdfDocument, PageSize.A4);
        document.setMargins(20,20,20,20);


        // Get images
        imagefromdrawble();
        emailicon();
        homelicon();
        phonelicon();



        Paragraph paragraph=new Paragraph("Laura Parker").setFontSize(40).setBold().setFontColor(BLACK).setHorizontalAlignment(LEFT);
        Paragraph paragraph2=new Paragraph("UI and UX Designer").setFontSize(20).setMarginTop(-15).setFontColor(BLACK).setHorizontalAlignment(LEFT);


        float[] width={50 ,200};

        Table table3= new Table(width);
        table3.setMarginTop(30);
        table3.addCell( new Cell().add(image3).setBorder(Border.NO_BORDER));
        table3.addCell( new Cell().add(new Paragraph("urname@gmail.com")).setBorder(Border.NO_BORDER));

        table3.addCell( new Cell().add(image5).setBorder(Border.NO_BORDER));
        table3.addCell( new Cell().add(new Paragraph("+92 084230480298")).setBorder(Border.NO_BORDER));

        table3.addCell( new Cell().add(image4).setBorder(Border.NO_BORDER));
        table3.addCell( new Cell().add(new Paragraph("Address here , Zip-0123")).setBorder(Border.NO_BORDER));


        Log.d(TAG, "Table ///////////////////: "+table);
//        table.setFixedPosition(1,20,660,table.getWidth());



        Paragraph paragraph4=new Paragraph("Profile".toUpperCase(Locale.ROOT)).setBold().setFontSize(20).setFontColor(ColorConstants.BLUE).setMarginTop(50);

        Paragraph paragraph5=new Paragraph("Lorem Ipsum is simply dummy text of the printing" +
                " and typesetting industry. Lorem Ipsum has been the" +
                " industry's standard dummy text ever since the 1500s, when an" +
                " unknown printer took a galley of type and scrambled it to make" +
                " a type specimen book.").setFontColor(BLACK).setMarginRight(200).setMarginTop(10);


        Paragraph paragraph6=new Paragraph("Experience".toUpperCase(Locale.ROOT)).setFontSize(20).setFontColor(ColorConstants.BLUE).setMarginTop(30).setBold();
        Paragraph paragraph7= new Paragraph("Senior UX Designer - Present \n Company Name/ Location").setFontSize(12).setFontColor(BLACK).setBold().setMarginTop(20);
        Paragraph paragraph8= new Paragraph("Lorem Ipsum is simply dummy text of the printing\n" +
                " and typesetting industry. Lorem Ipsum has been the\n" +
                " industry's standard").setFontColor(BLACK).setMarginRight(200).setMarginTop(10);


        Paragraph paragraph9= new Paragraph("Creative User Interface Designer - 2016\n Company Name/ Location").setFontSize(12).setFontColor(BLACK).setBold().setMarginTop(10);

        Paragraph paragraph10=new Paragraph("Lorem ipsum is simply dummy text of the printing and \n " +
                "typesetting industry . Lorem Ipsum has been the \n industry standard").setMarginRight(200);


        Paragraph paragraph11=new Paragraph("Reference".toUpperCase(Locale.ROOT)).setFontColor(ColorConstants.BLUE).setFontSize(20).setBold().setMarginTop(20);

        float [] coloumwidth2={250,250};
        Table table1= new Table(coloumwidth2);
        table1.setMarginRight(180);
        table1.setMarginTop(10);

        table1.addCell(new Cell().add(new Paragraph("Senior Designer")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Senior UX Designer")).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Jhon Smith").setBold()).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Jhony Ander Smith").setBold()).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Company name here")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Company name here")).setBorder(Border.NO_BORDER));




        Paragraph paragraph12 = new Paragraph("Education".toUpperCase(Locale.ROOT)).setBold().setFontSize(20).setFontColor(ColorConstants.BLUE);
        paragraph12.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        paragraph12.setFixedPosition(1,420,580,200);

        Paragraph paragraph15=new Paragraph("2014-2016");
        paragraph15.setFixedPosition(1,420,550,200  ).setMarginTop(-15);

        Paragraph paragraph16=new Paragraph("Degree / Major Name").setFontColor(BLACK).setBold();
        paragraph16.setFixedPosition(1,420,530,200  );

        Paragraph paragraph17=new Paragraph("University name here");
        paragraph17.setFixedPosition(1,420,510,200  );


        Paragraph paragraph18=new Paragraph("2014-2016");
        paragraph18.setFixedPosition(1,420,460,200);
        Paragraph paragraph19=new Paragraph("Degree / Major Name").setFontColor(BLACK).setBold();
        paragraph19.setFixedPosition(1,420,440,200);

        Paragraph paragraph20=new Paragraph("University name here");
        paragraph20.setFixedPosition(1,420,420,200);


        Paragraph paragraph21=new Paragraph("Language ".toUpperCase(Locale.ROOT)).setBold().setFontSize(20).setFontColor(ColorConstants.BLUE).setMarginTop(50);
        paragraph21.setFixedPosition(1,420,330,200);

        List list= new List();
        list.add("English").setBold().setFontColor(BLACK);
        list.add("French").setBold().setFontColor(BLACK);
        list.add("Spanish").setBold().setFontColor(BLACK);

        list.setFixedPosition(1,420,250,200);



        Paragraph paragraph22=new Paragraph("Skills".toUpperCase(Locale.ROOT)).setBold().setFontSize(20).setFontColor(ColorConstants.BLUE);

        paragraph22.setFixedPosition(1,420,170,200);

        List list1=new List();
        list1.add("Photoshop").setBold().setFontColor(BLACK);
        list1.add("Illustrator").setBold().setFontColor(BLACK);
        list1.add("InDesign").setBold().setFontColor(BLACK);
        list1.add("After Effect").setBold().setFontColor(BLACK);
        list1.add("Premiar pro").setBold().setFontColor(BLACK);

        list1.setFixedPosition(1,420,50,200);



        PdfPage pdfPage = pdfDocument.addNewPage();

        PdfCanvas canvas = new PdfCanvas(pdfPage);

        canvas.roundRectangle(17,635,25,75,10);
        canvas.setStrokeColor(ColorConstants.BLUE);
        canvas.stroke();

        canvas.circle(470,735,80);
        canvas.setStrokeColor(ColorConstants.BLUE);
        canvas.stroke();

        canvas.circle(425,668,10);
        canvas.setStrokeColor(BLACK);
        canvas.fill();


        document.add(paragraph);
        document.add(paragraph2);

        document.add(table3);
//        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph6);
        document.add(paragraph7);
        document.add(paragraph8);
        document.add(paragraph9);
        document.add(paragraph10);
        document.add(paragraph11);
        document.add(table1);

        document.add(paragraph15);
        document.add(paragraph16);
        document.add(paragraph17);
        document.add(paragraph18);
        document.add(paragraph19);
        document.add(paragraph20);

        document.add(paragraph21);
        document.add(list);
        document.add(paragraph22);
        document.add(list1);





        document.add(image);
        document.add(paragraph12);




//        document.add(image5);
        document.close();

        Toast.makeText(this,"File Saved",Toast.LENGTH_LONG).show();

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2296) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // perform action when allow permission success
                    try {
                        pdffile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }





    private void imagefromdrawble(){

        Drawable drawable= getDrawable(R.drawable.cvprofile);
        Bitmap   bitmap=((BitmapDrawable)drawable).getBitmap();


        ByteArrayOutputStream outputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream1);

        byte[] bitmapdata= outputStream1.toByteArray();

        ImageData imageData= ImageDataFactory.create(bitmapdata);

         image=new Image(imageData);
         image.setWidth(140);

         image.setFixedPosition(1,400,665);

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







    private void table(){
        float [] coloumwidth2={150,250,200,150};

        Table table1= new Table(coloumwidth2);

        table1.addCell(new Cell().add(new Paragraph("Name : ").setBold().setFontSize(12).setFontColor(ColorConstants.RED).setBorder(Border.NO_BORDER)));
        table1.addCell(new Cell().add(new Paragraph("ABDURREHMAN").setFontSize(12).setFontColor(ColorConstants.BLUE).setBorder(Border.NO_BORDER)));


        table1.addCell(new Cell().add(new Paragraph("F/Name:").setBold().setFontSize(12).setFontColor(ColorConstants.RED).setBorder(Border.NO_BORDER)).add(image.setHorizontalAlignment(RIGHT).setMarginTop(-15).setHeight(20).setWidth(20)));
        table1.addCell(new Cell().add(new Paragraph("Khan Rehman").setFontSize(12).setFontColor(ColorConstants.BLUE).setBorder(Border.NO_BORDER)));

        table1.addCell(new Cell().add(new Paragraph("Designation : ").setBold().setFontSize(12).setFontColor(ColorConstants.RED).setBorder(Border.NO_BORDER)));
        table1.addCell(new Cell().add(new Paragraph("Software Engineer").setFontSize(12).setFontColor(ColorConstants.BLUE).setBorder(Border.NO_BORDER)));

        table1.addCell(new Cell().add(new Paragraph("Phone No : ").setBold().setFontSize(12).setFontColor(ColorConstants.RED).setBorder(Border.NO_BORDER)));
        table1.addCell(new Cell().add(new Paragraph("03039290740").setFontSize(12).setFontColor(ColorConstants.BLUE).setBorder(Border.NO_BORDER)));

    }

    private void imagefromURL() throws IOException {
        String imageurl="https://cdn-icons-png.flaticon.com/512/5014/5014959.png";

        URL url=new URL(imageurl);

        HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
        InputStream inputStream=httpURLConnection.getInputStream();
        Bitmap bitmap2= BitmapFactory.decodeStream(inputStream);
        ByteArrayOutputStream ops=new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG,100,ops);

        byte[] bmd=ops.toByteArray();
        ImageData imageData2=ImageDataFactory.create(bmd);
         image2=new Image(imageData2);

    }

    private void lineSeparator(){
        LineSeparator lineSeparator = new LineSeparator(new SolidLine());
        lineSeparator.setStrokeColor(new DeviceRgb(0, 0, 68));

    }



}