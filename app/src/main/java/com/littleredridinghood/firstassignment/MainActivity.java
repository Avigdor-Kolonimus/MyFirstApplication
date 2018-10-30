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

public class MainActivity extends AppCompatActivity{
    //global variables
    private static final int Image_Capture_Code = 1;
    private Button btCamera;
    private EditText etName;
    private String NameOfUser;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NameOfUser="";
        etName = (EditText) findViewById(R.id.nameMain);
        btCamera = (Button) findViewById(R.id.buttonMain);
        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });
    }
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
}