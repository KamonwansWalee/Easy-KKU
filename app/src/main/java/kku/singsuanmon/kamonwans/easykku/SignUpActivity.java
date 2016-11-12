package kku.singsuanmon.kamonwans.easykku;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;

public class SignUpActivity extends AppCompatActivity {

    //Exclicit
    private EditText nameEditText, phoneEditText, userEditText, passwordEditText;

    private ImageView imageView;
    private Button button;
    private String nameString, phoneString, userString, passwordString, imagePathString, imageNameString;
    private Uri uri;
    private boolean aBoolean = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget

        nameEditText = (EditText) findViewById(R.id.editText);
        phoneEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button3);

        // SignUp Controller
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get value from Edit Text
                nameString = nameEditText.getText().toString().trim(); //ตัดช่องว่างออก
                phoneString = phoneEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                 // Check Space
                if (nameString.equals("") || phoneString.equals("") || userString.equals("") || passwordString.equals("")) {

                    //Have Spac
                    Log.d("12novV1", "Have Spce");
                    MyAlert myAlert = new MyAlert(SignUpActivity.this, R.drawable.doremon48, "มีช่องว่าง", "กรุณากรอกให้ครบทุกช่อง");
                    myAlert.myDialog();
                } else if (aBoolean) {
                    //Non Choose Image
                    MyAlert myAlert = new MyAlert(SignUpActivity.this, R.drawable.nobita48, "ยังไม่เลือกรูป", "กรุณาเลือกรูป");
                    myAlert.myDialog();
                    
                } else {
                    //Choose Image OK
                    UpLoadImageToServer();
                    
                    
                } 

            }//OnClick
        });


        // Image controller
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");  //video/*
                startActivityForResult(Intent.createChooser(intent, "โปรเลือกรแอปูปภภาพ"), 0);

            }//onClick
        });

    } // Main Method

    private void UpLoadImageToServer() {
        //Change Policy
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        try {
            SimpleFTP simpleFTP = new SimpleFTP();
            simpleFTP.connect("ftp.swiftcodingthai.com", 21, "kku@swiftcodingthai.com", "Abc12345");
            simpleFTP.bin();
            simpleFTP.cwd("Image");
            simpleFTP.stor(new File(imagePathString));
            simpleFTP.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }

    } // Uplaod Policy

    @Override
    public void onActivityResult(int requesCode, int resultCode, Intent data) {
        super.onActivityResult(requesCode, resultCode, data);
        if ((requesCode == 0) && (resultCode == RESULT_OK)) {
            Log.d("12novV1", "Result OK");
            aBoolean = false;

            //show image
            uri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);


            } catch (Exception e) {
                e.printStackTrace();
            }

            //Find Path of Image
            imagePathString = myFindPath(uri);
            Log.d("12novV1", "imagePath ==> " + imagePathString);

            //Find Name of Image
            imageNameString = imagePathString.substring(imagePathString.lastIndexOf("/"));
            Log.d("12novV1", "imageName ==>" + imageNameString);
            




        }//if


    }// onActivityResult

    private String myFindPath(Uri uri) {
        String result = null;
        String[] strings = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, strings, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            result = cursor.getString(index);

        }else {
            result = uri.getPath();
        }
        return result;
    }
}  //Main Class
