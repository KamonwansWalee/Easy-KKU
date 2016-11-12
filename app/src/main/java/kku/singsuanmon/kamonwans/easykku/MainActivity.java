package kku.singsuanmon.kamonwans.easykku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // การประกาศตัวแปล Explicit
    private Button signInButton, singUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //การทำ Bind Widget
        signInButton = (Button) findViewById(R.id.button);
        singUpButton = (Button) findViewById(R.id.button2);

        //การทำ Sign Up Controller
        singUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });


    } // Main Method
}   //Main Class
