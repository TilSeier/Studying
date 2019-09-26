package com.tilseier.studying.screens.files;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tilseier.studying.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FilesActivity extends AppCompatActivity {

    private static final String INTERNAL_STORAGE_FILE_NAME = "internal_storage.txt";
    private static final String EXTERNAL_STORAGE_FILE_NAME = "external_storage.txt";
    private static final String EXTERNAL_STORAGE_DIR = "StudyingBeforeJob";

    private static final String TAG = "FilesActivity";
    private static final int STORAGE_REQUEST_CODE = 100;
    private static final int LOAD_STORAGE_REQUEST_CODE = 101;

    EditText etMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        etMessage = findViewById(R.id.et_message);
    }

    public void onBtnSaveToFileClick(View view){
        String text = etMessage.getText().toString();

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(INTERNAL_STORAGE_FILE_NAME, MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());

            etMessage.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + INTERNAL_STORAGE_FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void onBtnLoadFromFileClick(View view){

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = openFileInput(INTERNAL_STORAGE_FILE_NAME);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String text;

            while ((text = bufferedReader.readLine()) != null){
                stringBuffer.append(text).append("\n");
            }

            etMessage.setText(stringBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void onBtnSaveToFileESClick(View view){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, STORAGE_REQUEST_CODE);
                return;
            }
        }

        saveToFileES();

    }

    public void onBtnLoadFromFileESClick(View view){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, LOAD_STORAGE_REQUEST_CODE);
                return;
            }
        }

        loadFromFileES();

    }

    void loadFromFileES(){
        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/" + EXTERNAL_STORAGE_DIR);
        File file = new File(dir, EXTERNAL_STORAGE_FILE_NAME);
        String message;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while ((message = bufferedReader.readLine()) != null){
                stringBuffer.append(message).append("\n");
            }
            etMessage.setText(stringBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void saveToFileES(){
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {

            File root = Environment.getExternalStorageDirectory();

            File dir = new File(root.getAbsolutePath() + "/" + EXTERNAL_STORAGE_DIR);

            if (!dir.exists()){
//                dir.mkdir();
                Toast.makeText(this, "Dir created: " + dir.mkdir(), Toast.LENGTH_LONG).show();
            }
            File file = new File(dir, EXTERNAL_STORAGE_FILE_NAME);
            String message = etMessage.getText().toString();

            FileOutputStream fileOutputStream = null;

            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(message.getBytes());
                etMessage.getText().clear();
                Toast.makeText(this, "Message Saved To " + file.getPath(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            Toast.makeText(this, "SD card Not Found!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_REQUEST_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
                //resume tasks needing this permission
                saveToFileES();
            }
        } else if (requestCode == LOAD_STORAGE_REQUEST_CODE){
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
                //resume tasks needing this permission
                loadFromFileES();
            }
        }
    }
}