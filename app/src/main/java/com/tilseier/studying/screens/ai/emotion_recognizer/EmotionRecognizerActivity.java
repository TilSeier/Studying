package com.tilseier.studying.screens.ai.emotion_recognizer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.microsoft.projectoxford.face.FaceServiceClient;
import com.microsoft.projectoxford.face.FaceServiceRestClient;
import com.microsoft.projectoxford.face.contract.Face;
import com.tilseier.studying.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EmotionRecognizerActivity extends AppCompatActivity {

    private static final String TAG = "EmotionRecognizer";

    private static final String FACE_ENDPOINT = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0";///detect?
    //returnFaceId=true&returnFaceLandmarks=false&returnFaceAttributes=emotion&recognitionModel=recognition_01&returnRecognitionModel=false&detectionModel=detection_01
    private static final String FACE_SUBSCRIPTION_KEY = "661b25128df744639f8b288a7f985dbc";//"3e80f4258a5d4a89964c4c1d89efa552";

    // <snippet_mainactivity_fields>
    // Add your Face endpoint to your environment variables.
    private final String apiEndpoint = FACE_ENDPOINT;//System.getenv();//"FACE_ENDPOINT"
    // Add your Face subscription key to your environment variables.
    private final String subscriptionKey = FACE_SUBSCRIPTION_KEY;//System.getenv();//"FACE_SUBSCRIPTION_KEY"

    private final FaceServiceClient faceServiceClient =
            new FaceServiceRestClient(apiEndpoint, subscriptionKey);

    private final int PICK_IMAGE = 1;
    private ProgressDialog detectionProgressDialog;

    private Bitmap bitmap;

    private DisposableSingleObserver<Face[]> faceDisposableObserver;
    // </snippet_mainactivity_fields>

    // <snippet_mainactivity_methods>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_recognizer);

        Button btnBrowsePicture = findViewById(R.id.btn_browse_picture);
        Button btnProcessPicture = findViewById(R.id.btn_process_picture);
        btnBrowsePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(
                        intent, "Select Picture"), PICK_IMAGE);
            }
        });
        btnProcessPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bitmap != null)
                    detectAndFrame(bitmap);
                else
                    Toast.makeText(EmotionRecognizerActivity.this, "Please, upload the image", Toast.LENGTH_LONG).show();
            }
        });

        detectionProgressDialog = new ProgressDialog(this);
        detectionProgressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), uri);
                ImageView imageView = findViewById(R.id.imageView1);
                imageView.setImageBitmap(bitmap);

                // Comment out for tutorial
//                detectAndFrame(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // </snippet_mainactivity_methods>
    }

    Single<Face[]> getRxFaceList(final InputStream... params){
        return Single.create(new SingleOnSubscribe<Face[]>() {
            @Override
            public void subscribe(SingleEmitter<Face[]> emitter) throws Exception {

                try {
                    Face[] result = faceServiceClient.detect(
                            params[0],
                            true,         // returnFaceId
                            false,        // returnFaceLandmarks
                            new FaceServiceClient.FaceAttributeType[]{
                                    FaceServiceClient.FaceAttributeType.Emotion,
                                    FaceServiceClient.FaceAttributeType.Gender,
                                    FaceServiceClient.FaceAttributeType.Age}          // returnFaceAttributes:
                                    /* new FaceServiceClient.FaceAttributeType[] {
                                        FaceServiceClient.FaceAttributeType.Age,
                                        FaceServiceClient.FaceAttributeType.Gender }
                                    */
                    );
                    emitter.onSuccess(result);
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        });
    }

    // <snippet_detection_methods>
    // Detect faces by uploading a face image.
    // Frame faces after detection.
    private void detectAndFrame(final Bitmap imageBitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 30, outputStream);
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());

        faceDisposableObserver = getRxFaceList(inputStream)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Face[]>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        Log.e(TAG, "onStart");
                        detectionProgressDialog.show();
                    }
                    @Override
                    public void onSuccess(Face[] faces) {

                        Log.e(TAG, "onSuccess");
                        detectionProgressDialog.dismiss();

                        if (faces == null) {
                            Log.e(TAG, "Detection Finished. Nothing detected");
                            return;
                        }
                        ImageView imageView = findViewById(R.id.imageView1);
                        imageView.setImageBitmap(
                                ImageHelper.drawFaceRectanglesOnBitmap(imageBitmap, faces));
                        imageBitmap.recycle();

                    }
                    @Override
                    public void onError(Throwable e) {
                        detectionProgressDialog.dismiss();
                        showError(String.format("Detection failed: %s", e.getMessage()));
//                        Toast.makeText(EmotionRecognizerActivity.this, String.format("Detection failed: %s", e.getMessage()), Toast.LENGTH_LONG).show();
                    }
                });

//        AsyncTask<InputStream, String, Face[]> detectTask =
//                new AsyncTask<InputStream, String, Face[]>() {
//                    String exceptionMessage = "";
//
//                    @Override
//                    protected Face[] doInBackground(InputStream... params) {
//                        try {
//                            publishProgress("Detecting...");
//                            Face[] result = faceServiceClient.detect(
//                                    params[0],
//                                    true,         // returnFaceId
//                                    false,        // returnFaceLandmarks
//                                    new FaceServiceClient.FaceAttributeType[]{FaceServiceClient.FaceAttributeType.Emotion}          // returnFaceAttributes:
//                                    /* new FaceServiceClient.FaceAttributeType[] {
//                                        FaceServiceClient.FaceAttributeType.Age,
//                                        FaceServiceClient.FaceAttributeType.Gender }
//                                    */
//                            );
//                            if (result == null){
//                                publishProgress(
//                                        "Detection Finished. Nothing detected");
//                                return null;
//                            }
//                            publishProgress(String.format(
//                                    "Detection Finished. %d face(s) detected",
//                                    result.length));
//                            return result;
//                        } catch (Exception e) {
//                            exceptionMessage = String.format(
//                                    "Detection failed: %s", e.getMessage());
//                            return null;
//                        }
//                    }
//
//                    @Override
//                    protected void onPreExecute() {
//                        //TODO: show progress dialog
//                        detectionProgressDialog.show();
//                    }
//                    @Override
//                    protected void onProgressUpdate(String... progress) {
//                        //TODO: update progress
//                        detectionProgressDialog.setMessage(progress[0]);
//                    }
//                    @Override
//                    protected void onPostExecute(Face[] result) {
//                        //TODO: update face frames
//                        detectionProgressDialog.dismiss();
//
//                        if(!exceptionMessage.equals("")){
//                            showError(exceptionMessage);
//                        }
//                        if (result == null) return;
//
//                        ImageView imageView = findViewById(R.id.imageView1);
//                        imageView.setImageBitmap(
//                                ImageHelper.drawFaceRectanglesOnBitmap(imageBitmap, result));
//                        imageBitmap.recycle();
//                    }
//                };
//
//        detectTask.execute(inputStream);
    }

    private void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }})
                .create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (faceDisposableObserver != null)
            faceDisposableObserver.dispose();
    }

    // </snippet_detection_methods>

}
