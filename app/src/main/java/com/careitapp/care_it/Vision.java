package com.careitapp.care_it;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Vision extends AppCompatActivity {

    @BindView(R.id.surface_view) SurfaceView cameraView;
    @BindView(R.id.text_view) TextView textView;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vision);

        ButterKnife.bind(this);

        startCamera();
    }

    public void startCamera(){
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Log.w("Vision", "Detector dependencies are not yet available");
        }
        else {
            cameraSource = new CameraSource.Builder(getApplicationContext(),textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280,1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        {
                            ActivityCompat.requestPermissions(Vision.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    cameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                String rxNum;
                String perSession;
                String perDay;
                String totalPills;

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if (items.size()!=0) {
                        textView.post(() -> {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i=0; i<items.size();i++) {
                                TextBlock item = items.valueAt(i);
                                stringBuilder.append(item.getValue());
                                stringBuilder.append("\n");
                            }

                            textView.setText(stringBuilder.toString());
                            String[] values = stringBuilder.toString().split(" ");
                            for (int i=0; i<values.length; i++) {
                                if (values[i].equals("RX")) {
                                    rxNum = values[i+1];
                                }
                                if (values[i].equals("TAKE")) {
                                    if ((values[i]+1).equals("ONE")){
                                        perSession = "1";
                                    }
                                    else {
                                        perSession = values[i+1];
                                    }
                                }
                                if (values[i].equals("TIMES")) {
                                    if (values[i-1].equals("THREE")) {
                                        perDay = "3";
                                    }
                                    else {perDay = values[i-1]; }
                                }
                                if (values[i].equals("QTY")) {
                                    totalPills = values[i+1];
                                }
                            }

                            if ((stringBuilder.toString().contains("TAKE") &&
                                    stringBuilder.toString().contains("TIMES") &&
                                    stringBuilder.toString().contains("QTY") &&
                                    stringBuilder.toString().contains("RX")) ||
                                    (stringBuilder.toString().contains("TAKE") &&
                                            stringBuilder.toString().contains("TIMES") &&
                                            stringBuilder.toString().contains("OTY") &&
                                            stringBuilder.toString().contains("RX"))){
                                cameraSource.stop();
                                Intent intent = new Intent(Vision.this, ManualPill.class);
                                intent.putExtra("perSession", perSession);
                                intent.putExtra("perDay", perDay);
                                intent.putExtra("totalPills", totalPills);
                                startActivity(intent);
                            }
                        });
                    }
                }
            });
        }
    }
}
