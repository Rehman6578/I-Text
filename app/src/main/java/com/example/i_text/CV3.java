package com.example.i_text;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static com.example.i_text.R.drawable.book;
import static com.example.i_text.R.drawable.email;
import static com.example.i_text.R.drawable.internet;
import static com.example.i_text.R.drawable.user_;
import static com.example.i_text.R.drawable.youtube;
import static com.itextpdf.kernel.colors.ColorConstants.BLACK;
import static com.itextpdf.kernel.colors.ColorConstants.BLUE;
import static com.itextpdf.kernel.colors.ColorConstants.PINK;
import static com.itextpdf.kernel.colors.ColorConstants.WHITE;
import static com.itextpdf.kernel.colors.ColorConstants.YELLOW;

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
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import org.apache.commons.codec.language.bm.Languages;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;

public class CV3 extends AppCompatActivity {
    MaterialButton save;
    Image image, Mail, Phone, internet, User, Pin, Skill, Education,Experience,instagram,facebook,twitter,Youtube, star;
    int PERMISSION_REQUEST_CODE = 2296;
    String lorem, lorem1;
    LineSeparator lineSeparator;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv3);

        save = findViewById(R.id.savebtn);

        lorem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's ";


        lorem1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s" +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s" +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s";

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
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

       File file= new File(path,"CV3.pdf");

       OutputStream out= new FileOutputStream(file);

        PdfWriter pdfWriter = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument, PageSize.A4);

        document.setLeftMargin(15);
        document.setBottomMargin(20);

        // Get images
        imagefromdrawble();
        emailicon();
        interneticon();
        phoneicon();
        usericon();
        locationPIN();
        education();
        experience();
        skill();
        LineSeparator();
        facebook_icon();
        insta_icon();
        twitter_icon();
        youtube_icon();
        Star();



        // my fonts
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);


        float coloumwidth[] = {150, 400};
        Table table1 = new Table(coloumwidth);


        // about us table

        float[] width1 = {30, 320};

        Table aboutus = new Table(width1);
        aboutus.setMarginLeft(30);
        aboutus.setMarginTop(25);
        aboutus.addCell(new Cell(2,1).add(User).setBorder(Border.NO_BORDER));
        aboutus.addCell(new Cell().add(new Paragraph("About Me".toUpperCase(Locale.ROOT)).setBold().setMarginTop(0)
                .setTextAlignment(TextAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED)).setBorder(Border.NO_BORDER).setFontSize(15).setFontColor(BLACK).setFont(font));

        aboutus.addCell(new Cell().add(lineSeparator).setBorder(Border.NO_BORDER));
        aboutus.addCell(new Cell(4,2).add(new Paragraph(lorem1).setFontColor(BLACK).setTextAlignment(TextAlignment.JUSTIFIED)).setFontSize(10).setBorder(Border.NO_BORDER));



        // Education table

        Table education = new Table(width1);
        education.setMarginLeft(30);
        education.setMarginTop(-25);
        education.addCell(new Cell(2,1).add(Education).setBorder(Border.NO_BORDER));
        education.addCell(new Cell().add(new Paragraph("Education".toUpperCase(Locale.ROOT)).setBold().setMarginTop(0)
                .setTextAlignment(TextAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED)).setBorder(Border.NO_BORDER).setFontSize(15).setFontColor(BLACK).setFont(font));

        education.addCell(new Cell().add(lineSeparator).setBorder(Border.NO_BORDER));


        table1.addCell(new Cell(2,1).add(image).setBorder(Border.NO_BORDER).setPaddingTop(20));
        table1.addCell(new Cell(3,1).add(aboutus).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Henry".toUpperCase(Locale.ROOT)).setTextAlignment(TextAlignment.CENTER).setMarginTop(10).setFontSize(20).setFontColor(BLACK))
                        .add(new Paragraph("Silly".toUpperCase(Locale.ROOT)).setMarginTop(-10).setFontSize(20).setFontColor(BLACK).setBold().setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Web Design & Developer").setFontColor(BLACK).setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(-10).setFontSize(10)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(education).setBorder(Border.NO_BORDER));

        // contact information

        float width2[] = {30,80};
        Table contact = new Table(width2);


        contact.setMarginTop(10);

        contact.addCell(new Cell(1,2).add(new Paragraph("Contact".toUpperCase(Locale.ROOT))
                        .setFontColor(WHITE).setFontSize(12).setBackgroundColor(BLACK).setHeight(25)
                        .setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .setBorder(Border.NO_BORDER));

        contact.addCell(new Cell().add(Pin).setPaddingTop(10).setBorder(Border.NO_BORDER));
        contact.addCell(new Cell().add(new Paragraph("715 Alington Avenue Oak ridge , Tn 35785").setFontColor(BLACK)
                        .setPaddingLeft(10).setTextAlignment(TextAlignment.LEFT).setMarginTop(10))
                .setBorder(Border.NO_BORDER));

        contact.addCell(new Cell().add(Phone).setPaddingTop(10).setBorder(Border.NO_BORDER));
        contact.addCell(new Cell().add(new Paragraph("+2 434-232-543214").setPaddingLeft(10).setFontColor(BLACK)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(10))
                .setBorder(Border.NO_BORDER));

        contact.addCell(new Cell().add(Mail).setPaddingTop(10).setBorder(Border.NO_BORDER));
        contact.addCell(new Cell().add(new Paragraph("henrysilly@gmail.com").setPaddingLeft(10).setFontColor(BLACK)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(10))
                .setBorder(Border.NO_BORDER));

        contact.addCell(new Cell().add(internet).setPaddingTop(10).setBorder(Border.NO_BORDER));
        contact.addCell(new Cell().add(new Paragraph("Henrysilly.com").setPaddingLeft(10).setFontColor(BLACK)
                        .setTextAlignment(TextAlignment.LEFT).setMarginTop(10))
                .setBorder(Border.NO_BORDER));

        // Education details

        float width3[] ={200,200};

        Table Edu_dtl= new Table(width3);

        Edu_dtl.setMarginTop(10);
        Edu_dtl.setMarginLeft(30);

        Edu_dtl.addCell(new Cell().add(new Paragraph(">  School Name 1".toUpperCase(Locale.ROOT))
                .setFontSize(12).setFontColor(BLACK).setBold().setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        Edu_dtl.addCell(new Cell().add(new Paragraph("JUNE 2008 - DEC 2014").setFontSize(10)
                .setFontColor(BLACK).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        Edu_dtl.addCell(new Cell(2,2).add(new Paragraph(lorem)).setFontColor(BLACK).setFontSize(10).setTextAlignment(TextAlignment.JUSTIFIED)
                .setBorder(Border.NO_BORDER));

        Edu_dtl.addCell(new Cell().add(new Paragraph(">  College Name 1".toUpperCase(Locale.ROOT)).setMarginTop(10)
                .setFontSize(12).setFontColor(BLACK).setBold().setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        Edu_dtl.addCell(new Cell().add(new Paragraph("JUNE 2008 - DEC 2014").setFontSize(10).setMarginTop(10)
                .setFontColor(BLACK).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        Edu_dtl.addCell(new Cell(2,2).add(new Paragraph(lorem)).setFontColor(BLACK).setFontSize(10).setTextAlignment(TextAlignment.JUSTIFIED)
                .setBorder(Border.NO_BORDER));

        Edu_dtl.addCell(new Cell().add(new Paragraph(">  College Name 1".toUpperCase(Locale.ROOT)).setMarginTop(10)
                .setFontSize(12).setFontColor(BLACK).setBold().setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        Edu_dtl.addCell(new Cell().add(new Paragraph("JUNE 2008 - DEC 2014").setFontSize(10).setMarginTop(10)
                .setFontColor(BLACK).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        Edu_dtl.addCell(new Cell(2,2).add(new Paragraph(lorem)).setFontColor(BLACK).setFontSize(10).setTextAlignment(TextAlignment.JUSTIFIED)
                .setBorder(Border.NO_BORDER));

        ////////////////////////////+++++++++++++++++++++++++++///////////////////////////////

        // Follow me tabel

        Table follow = new Table(width2);

        follow.addCell(new Cell(1,2).add(new Paragraph("Follow me".toUpperCase(Locale.ROOT))
                        .setFontColor(WHITE).setFontSize(12).setBackgroundColor(BLACK).setHeight(25)
                        .setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .setBorder(Border.NO_BORDER));

        follow.addCell(new Cell().add(facebook).setBorder(Border.NO_BORDER).setPaddingTop(10));
        follow.addCell(new Cell().add(new Paragraph("facebook/username").setFontColor(BLACK)
                        .setPaddingLeft(10).setTextAlignment(TextAlignment.LEFT).setMarginTop(10))
                .setBorder(Border.NO_BORDER));
        follow.addCell(new Cell().add(twitter).setBorder(Border.NO_BORDER));
        follow.addCell(new Cell().add(new Paragraph("twitter/username").setPaddingLeft(10).setFontColor(BLACK)
                        .setTextAlignment(TextAlignment.LEFT))
                .setBorder(Border.NO_BORDER));

        follow.addCell(new Cell().add(instagram).setBorder(Border.NO_BORDER));
        follow.addCell(new Cell().add(new Paragraph("insta/username").setPaddingLeft(10).setFontColor(BLACK)
                        .setTextAlignment(TextAlignment.LEFT))
                .setBorder(Border.NO_BORDER));

        follow.addCell(new Cell().add(Youtube).setBorder(Border.NO_BORDER));
        follow.addCell(new Cell().add(new Paragraph("youtube/username").setPaddingLeft(10).setFontColor(BLACK)
                        .setTextAlignment(TextAlignment.LEFT))
                .setBorder(Border.NO_BORDER));

/////////////////////////////////////////////////////////////////////////////////////////

        // experience table

        Table expr = new Table(width1);
        expr.setMarginLeft(30);

        expr.setMarginTop(10);
        expr.addCell(new Cell(2,1).add(Experience).setBorder(Border.NO_BORDER));
        expr.addCell(new Cell().add(new Paragraph("Experience".toUpperCase(Locale.ROOT)).setBold()
                .setTextAlignment(TextAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED)).setBorder(Border.NO_BORDER).setFontSize(15).setFontColor(BLACK).setFont(font));

        expr.addCell(new Cell().add(lineSeparator).setBorder(Border.NO_BORDER));

        ////////////////////////////////////////////////////////////////////////

        // experience details table

        Table Exp_dtl= new Table(width3);

        Exp_dtl.setMarginTop(10);
        Exp_dtl.setMarginLeft(30);

        Exp_dtl.addCell(new Cell().add(new Paragraph(">  Work Name 1".toUpperCase(Locale.ROOT))
                .setFontSize(12).setFontColor(BLACK).setBold().setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        Exp_dtl.addCell(new Cell().add(new Paragraph("JUNE 2008 - DEC 2014").setFontSize(10)
                .setFontColor(BLACK).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        Exp_dtl.addCell(new Cell(2,2).add(new Paragraph(lorem)).setFontColor(BLACK).setFontSize(10).setTextAlignment(TextAlignment.JUSTIFIED)
                .setBorder(Border.NO_BORDER));

        Exp_dtl.addCell(new Cell().add(new Paragraph(">  Work Name 1".toUpperCase(Locale.ROOT)).setMarginTop(10)
                .setFontSize(12).setFontColor(BLACK).setBold().setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        Exp_dtl.addCell(new Cell().add(new Paragraph("JUNE 2008 - DEC 2014").setFontSize(10).setMarginTop(10)
                .setFontColor(BLACK).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        Exp_dtl.addCell(new Cell(2,2).add(new Paragraph(lorem)).setFontColor(BLACK).setFontSize(10).setTextAlignment(TextAlignment.JUSTIFIED)
                .setBorder(Border.NO_BORDER));

        Exp_dtl.addCell(new Cell().add(new Paragraph(">  Work Name 1".toUpperCase(Locale.ROOT)).setMarginTop(10)
                .setFontSize(12).setFontColor(BLACK).setBold().setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));

        Exp_dtl.addCell(new Cell().add(new Paragraph("JUNE 2008 - DEC 2014").setFontSize(10).setMarginTop(10)
                .setFontColor(BLACK).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        Exp_dtl.addCell(new Cell(2,2).add(new Paragraph(lorem)).setFontSize(10).setFontColor(BLACK).setTextAlignment(TextAlignment.JUSTIFIED)
                .setBorder(Border.NO_BORDER));

        ////////////////////////////////////////////////////////////////////////

        // stars table
        float[] stars = {15, 15, 15, 15, 15};
        Table Stars = new Table(stars);

        Stars.setMarginLeft(15);


        Stars.addCell(new Cell().add(star).setBorder(Border.NO_BORDER));
        Stars.addCell(new Cell().add(star).setBorder(Border.NO_BORDER));
        Stars.addCell(new Cell().add(star).setBorder(Border.NO_BORDER));
        Stars.addCell(new Cell().add(star).setBorder(Border.NO_BORDER));
        Stars.addCell(new Cell().add(star).setBorder(Border.NO_BORDER));


        // languages table

        float languagewidth[] = {30,80};

        Table languages = new Table(width2);

        languages.setMarginTop(10);

        languages.addCell(new Cell(1,2).add(new Paragraph("Language".toUpperCase(Locale.ROOT))
                        .setFontColor(WHITE).setPaddingLeft(10).setFontSize(12).setBackgroundColor(BLACK).setHeight(25)
                        .setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .setBorder(Border.NO_BORDER));

        languages.addCell(new Cell().add(new Paragraph("Arabic").setMarginTop(10)).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        languages.addCell(new Cell().add(Stars).setPaddingTop(10).setHorizontalAlignment(HorizontalAlignment.CENTER).setBorder(Border.NO_BORDER));

        languages.addCell(new Cell().add(new Paragraph("English")).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        languages.addCell(new Cell().add(Stars).setHorizontalAlignment(HorizontalAlignment.CENTER).setBorder(Border.NO_BORDER));

        languages.addCell(new Cell().add(new Paragraph("French")).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        languages.addCell(new Cell().add(Stars).setHorizontalAlignment(HorizontalAlignment.CENTER).setBorder(Border.NO_BORDER));

        languages.addCell(new Cell().add(new Paragraph("Spanich")).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        languages.addCell(new Cell().add(Stars).setHorizontalAlignment(HorizontalAlignment.CENTER).setBorder(Border.NO_BORDER));

/////////////////////////////////////////////////////////////////////////////////

        // skills heading
        Table skill = new Table(width1);
        skill.setMarginLeft(30);

        skill.setMarginTop(10);
        skill.addCell(new Cell(2,1).add(Skill).setBorder(Border.NO_BORDER));
        skill.addCell(new Cell().add(new Paragraph("Skills".toUpperCase(Locale.ROOT)).setBold()
                .setTextAlignment(TextAlignment.LEFT).setTextAlignment(TextAlignment.JUSTIFIED)).setBorder(Border.NO_BORDER).setFontSize(15).setFontColor(BLACK).setFont(font));

        skill.addCell(new Cell().add(lineSeparator).setBorder(Border.NO_BORDER));

        /////////////////////////////////////////////////////////////////////////////////


        // skills table
        float[] skills = {30,100,30,100};
        Table Skills = new Table(skills);

        Skills.setMarginLeft(40);

        Skills.addCell(new Cell().add(new Paragraph("Photoshop")).setTextAlignment(TextAlignment.LEFT).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        Skills.addCell(new Cell().add(Stars).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));

        Skills.addCell(new Cell().add(new Paragraph("Photoshop")).setMarginLeft(15).setTextAlignment(TextAlignment.LEFT).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        Skills.addCell(new Cell().add(Stars).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));

        Skills.addCell(new Cell().add(new Paragraph("HTML")).setTextAlignment(TextAlignment.LEFT).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        Skills.addCell(new Cell().add(Stars).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));

        Skills.addCell(new Cell().add(new Paragraph("HTML")).setMarginLeft(15).setTextAlignment(TextAlignment.LEFT).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        Skills.addCell(new Cell().add(Stars).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));

        Skills.addCell(new Cell().add(new Paragraph("CSS")).setTextAlignment(TextAlignment.LEFT).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        Skills.addCell(new Cell().add(Stars).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));

        Skills.addCell(new Cell().add(new Paragraph("CSS")).setMarginLeft(15).setTextAlignment(TextAlignment.LEFT).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        Skills.addCell(new Cell().add(Stars).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));

        Skills.addCell(new Cell().add(new Paragraph("JAVA SCRIPT")).setTextAlignment(TextAlignment.LEFT).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        Skills.addCell(new Cell().add(Stars).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));

        Skills.addCell(new Cell().add(new Paragraph("JAVA SCRIPT")).setMarginLeft(15).setTextAlignment(TextAlignment.LEFT).setFontColor(BLACK).setFontSize(10).setBold()
                .setBorder(Border.NO_BORDER));
        Skills.addCell(new Cell().add(Stars).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));


////////////////////////////////////////////////////////////////////////////////
        
        // hobies table

        float  hobies[] = {50,50};
        Table Hobbies = new Table(width2);

        Hobbies.setMarginTop(-15);

        Hobbies.addCell(new Cell(1,2).add(new Paragraph("Hobbies ".toUpperCase(Locale.ROOT))
                        .setFontColor(WHITE).setFontSize(12).setBackgroundColor(BLACK).setHeight(25)
                        .setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE))
                .setBorder(Border.NO_BORDER));

        Hobbies.addCell(new Cell().add(new Paragraph("Photography").setMarginTop(10).setTextAlignment(TextAlignment.LEFT)
                .setFontColor(BLACK).setFontSize(10)).setBorder(Border.NO_BORDER));


        Hobbies.addCell(new Cell().add(new Paragraph("Swimming").setMarginTop(10).setPaddingLeft(15).setTextAlignment(TextAlignment.LEFT)
                .setFontColor(BLACK).setFontSize(10)).setBorder(Border.NO_BORDER));


        Hobbies.addCell(new Cell().add(new Paragraph("Bowlings pins").setMarginTop(10).setTextAlignment(TextAlignment.LEFT)
                .setFontColor(BLACK).setFontSize(10)).setBorder(Border.NO_BORDER));


        Hobbies.addCell(new Cell().add(new Paragraph("Music").setMarginTop(10).setPaddingLeft(15).setTextAlignment(TextAlignment.LEFT)
                .setFontColor(BLACK).setFontSize(10)).setBorder(Border.NO_BORDER));



        // Main table


        table1.addCell(new Cell(5,1).add(contact).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell(5,1).add(Edu_dtl).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell(5,1).add(follow).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(expr).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell(5,1).add(Exp_dtl).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell(3,1).add(languages).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(skill).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell(4,1).add(Skills).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell(2,1).add(Hobbies).setBorder(Border.NO_BORDER));




        // canvas
        PdfPage pdfPage = pdfDocument.addNewPage();
        PdfCanvas canvas = new PdfCanvas(pdfPage);

        canvas.rectangle(0,0,180,585);
        canvas.setFillColor(PINK);
        canvas.fill();



        document.add(table1);
        document.close();

        Toast.makeText(this, "CV Created", Toast.LENGTH_SHORT).show();

    }


    private void RequestPermission () {

        if (ActivityCompat.shouldShowRequestPermissionRationale(CV3.this, READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this).setTitle("Permission Needed")
                    .setMessage("Permission Needed for this application.").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CV3.this, new String[]{READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();

        } else {
            ActivityCompat.requestPermissions(CV3.this, new String[]{READ_EXTERNAL_STORAGE}, 6578);
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

    private void LineSeparator(){
         lineSeparator = new LineSeparator(new SolidLine());
        lineSeparator.setStrokeColor(BLACK);

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

        Drawable drawable = getDrawable(R.drawable.email);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();


        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Mail = new Image(imageData);
        Mail.setWidth(15);
        Mail.setHeight(15);

    }
    private void interneticon () {

            Drawable drawable = getDrawable(R.drawable.internet);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();


        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        internet = new Image(imageData);
        internet.setWidth(15);
        internet.setHeight(15);


    }
    private void phoneicon () {

        Drawable drawable = getDrawable(R.drawable.phone);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Phone = new Image(imageData);
        Phone.setWidth(15);
        Phone.setHeight(15);


    }
    private void usericon () {

        Drawable drawable = getDrawable(R.mipmap.usericon);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        User = new Image(imageData);
        User.setWidth(20);
        User.setHeight(20);


    }
    private void  locationPIN () {

            Drawable drawable = getDrawable(R.drawable.pin);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Pin = new Image(imageData);
        Pin.setWidth(15);
        Pin.setHeight(15);


    }
    private void experience () {

        Drawable drawable = getDrawable(R.mipmap.experience);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Experience = new Image(imageData);
        Experience.setWidth(20);
        Experience.setHeight(20);


    }
    private void education () {

        Drawable drawable = getDrawable(R.mipmap.education);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Education = new Image(imageData);
        Education.setWidth(20);
        Education.setHeight(20);


    }
    private void skill () {

        Drawable drawable = getDrawable(R.mipmap.skills);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Skill = new Image(imageData);
        Skill.setWidth(20);
        Skill.setHeight(20);


    }
    private void Star() {

        Drawable drawable = getDrawable(R.drawable.star);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        star = new Image(imageData);
        star.setWidth(12);
        star.setHeight(12);

    }
    private void facebook_icon () {

        Drawable drawable = getDrawable(R.drawable.fcbook);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        facebook = new Image(imageData);
        facebook.setWidth(15);
        facebook.setHeight(15);


    }

    private void twitter_icon () {

        Drawable drawable = getDrawable(R.drawable.twitter);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        twitter = new Image(imageData);
        twitter.setWidth(15);
        twitter.setHeight(15);


    }

    private void insta_icon () {

        Drawable drawable = getDrawable(R.drawable.instagram);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        instagram = new Image(imageData);
        instagram.setWidth(15);
        instagram.setHeight(15);


    }

    private void youtube_icon () {

        Drawable drawable = getDrawable(R.drawable.youtube);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream1);

        byte[] bitmapdata = outputStream1.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapdata);

        Youtube = new Image(imageData);
        Youtube.setWidth(15);
        Youtube.setHeight(15);


    }

}