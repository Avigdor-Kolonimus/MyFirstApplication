/**
 * The first assignment for cyber lesson
 * @author Alexey Titov
 * @date 10.2018
 * @version 1.0
 */
package com.littleredridinghood.firstassignment;
//libraries
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.Manifest;


public class MainActivity extends AppCompatActivity{
    //global variables
    private static final int Image_Capture_Code = 1;
    private Button btCamera;
    private EditText etName;
    private String NameOfUser;
    private Intent intent;
    private boolean permission=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NameOfUser="";
        etName = (EditText) findViewById(R.id.nameMain);
        btCamera = (Button) findViewById(R.id.buttonMain);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, Image_Capture_Code);
        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permission) {
                    Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cInt, Image_Capture_Code);
                }else{
                    finish();
                    startActivity(getIntent());
                }
            }
        });
    }
    //open next page
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                intent=new Intent("info.fandroid.intent.action.activity_picture");
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                intent.putExtra("pic", bitmap);
                if (etName.getText().toString()!=null)
                    NameOfUser=etName.getText().toString();
                intent.putExtra("name", NameOfUser);
                startActivity(intent);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
    //alert and question about permission
    @Override
    public void onRequestPermissionsResult(int request, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch(request){
            case Image_Capture_Code:
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    permission=true;
                }
                else {
                    AlertDialog.Builder alert1 = new AlertDialog.Builder(this);
                    alert1.setMessage("My friend, you must allow to use the app.");
                    alert1.setCancelable(true);
                    alert1.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = alert1.create();
                    alert.show();
                    permission=false;
                }
        }
    }
}