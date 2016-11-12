package kku.singsuanmon.kamonwans.easykku;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {

    //Exclicit
    private EditText nameEditText, phoneEditText, userEditText, passwordEditText;

    private ImageView imageView;
    private Button button;
    private String nameString, phoneString, userString, passwordString;
    private Uri uri;


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

    @Override
    public void onActivityResult(int requesCode, int resultCode, Intent data) {
        super.onActivityResult(requesCode, resultCode, data);
        if ((requesCode == 0) && (resultCode == RESULT_OK)) {
            Log.d("12novV1", "Result OK");

            //show image
            uri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }//if


    }// onActivityResult
}  //Main Class
