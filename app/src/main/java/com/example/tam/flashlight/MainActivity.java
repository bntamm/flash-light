package com.example.tam.flashlight;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnFlash;
    private android.hardware.Camera camera;
    private android.hardware.Camera.Parameters parameters;
    private boolean Check = true;
    private boolean isflash = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFlash = (ImageButton) findViewById(R.id.buttonFlashOff);

        //Kiểm tra device có hỗ trợ đèn flash hay ko
        Check = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if(Check == false){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.create();
            builder.setTitle("Error!");
            builder.setMessage("Flash light is not available on this device!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                }
            });

            builder.show();
        }

        btnFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isflash == true){
                    OnFlash();
                }else{
                    OffFlash();
                }
            }});

  }





    //Kiểm tra xem camera đã bật hay chưa
    private void getCamera(){
        if(camera == null && parameters == null){
            camera = android.hardware.Camera.open();
            parameters = camera.getParameters();
        }}

    //Bật Flash
    private void OnFlash(){
        if(isflash == true){
            parameters = camera.getParameters();
            parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.startPreview();
            isflash = false;
            TrangThaiHinhFlash();
        }}


    //Tắt Flash
    private void OffFlash(){
        if(isflash == false) {
            parameters = camera.getParameters();
            parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.stopPreview();
            isflash = true;
            TrangThaiHinhFlash();
        }}

    //Set trạng thái hình ảnh on off cho imagebutton
    private void TrangThaiHinhFlash(){
        if(isflash == true){
            btnFlash.setImageResource(R.drawable.on);
        }else{
            btnFlash.setImageResource(R.drawable.off);
        }}


}


