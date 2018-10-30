/**
 * The first assignment for cyber lesson
 * @author Alexey Titov
 * @date 10.2018
 * @version 1.0
 */
package com.littleredridinghood.firstassignment;
//libraries
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

public class PictureActivity extends AppCompatActivity {
    //global variables
    private ImageView imgCapture;
    private String NameOfUser;
    private Bitmap bp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        Intent intent = getIntent();
        NameOfUser = intent.getStringExtra("name");

        TextView ssid = (TextView) findViewById (R.id.tvPicture);
        ssid.setText("Did "+NameOfUser+" shoot the picture of a clock?");

        imgCapture = (ImageView) findViewById(R.id.capturedImage);
        if(intent.hasExtra("pic")) {
            bp = (Bitmap) intent.getParcelableExtra("pic");
            imgCapture.setImageBitmap(bp);
        }

    }
}
