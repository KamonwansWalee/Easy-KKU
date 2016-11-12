package kku.singsuanmon.kamonwans.easykku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by kamonwans on 11/12/2016.
 */

public class MyAlert {
    //Explicit

    private Context context;
    private int anInt;
    private String titleString, messageString;

    public MyAlert(Context context, int anInt, String titleString, String messageString) {  // สร้าง contructer คือ ให้กด  alt+ insert
        this.context = context;
        this.anInt = anInt;
        this.titleString = titleString;
        this.messageString = messageString;
    }

    public void myDialog() {  //การทำ pop up

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);  //ให้ปุ่ม undo ใช้งานไม่ได้ชั่วคราว
        builder.setIcon(anInt);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();


    }// My Dialog

}// Main Class
