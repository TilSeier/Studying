package com.tilseier.studying.screens.ai.emotion_recognizer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.microsoft.projectoxford.face.contract.Emotion;
import com.microsoft.projectoxford.face.contract.Face;
import com.microsoft.projectoxford.face.contract.FaceRectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageHelper {

    private static final String TAG = "ImageHelper";

    public static Bitmap drawFaceRectanglesOnBitmap(Bitmap originalBitmap, Face[] faces) {
        Bitmap bitmap = originalBitmap.copy(Bitmap.Config.RGB_565, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(8);
        if (faces != null) {
            for (Face face : faces) {

                Log.e(TAG, "gender = "+face.faceAttributes.gender);
                int color = face.faceAttributes.gender.equals("male") ? Color.BLUE : Color.MAGENTA;
                paint.setColor(color);

                FaceRectangle faceRectangle = face.faceRectangle;
                canvas.drawRect(
                        faceRectangle.left,
                        faceRectangle.top,
                        faceRectangle.left + faceRectangle.width,
                        faceRectangle.top + faceRectangle.height,
                        paint);

//                int cX = faceRectangle.left + faceRectangle.width;
//                int cY = faceRectangle.top + faceRectangle.height;

                int cX = faceRectangle.left;// + (faceRectangle.width/2);
                int cY = faceRectangle.top;// + (faceRectangle.height/2);

                drawTextOnBitmap(canvas, 60, cX, cY, Color.BLUE, getEmotion(face.faceAttributes.emotion));//cX/2+cX/5 //cY+70

                cX = faceRectangle.left + faceRectangle.width;
                cY = faceRectangle.top;// + (faceRectangle.height/2);

                drawTextOnBitmap(canvas, 60, cX, cY, Color.CYAN, ""+(int)face.faceAttributes.age);//cX/2+cX/5 //cY+70

            }
        }
        return bitmap;
    }

    private static void drawTextOnBitmap(Canvas canvas, int textSize, int cX, int cY, int color, String status) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        paint.setTextSize(textSize);

        canvas.drawText(status, cX, cY, paint);

    }

    private static String getEmotion(Emotion res) {

        List<Double> list = new ArrayList<>();

        list.add(res.anger);
        list.add(res.happiness);
        list.add(res.contempt);
        list.add(res.disgust);
        list.add(res.fear);
        list.add(res.neutral);
        list.add(res.sadness);
        list.add(res.surprise);

        //Sort list
        Collections.sort(list);

        //Get max value from list
        double maxNum = list.get(list.size() - 1);

        if (maxNum == res.anger)
            return "\uD83D\uDE20";
        else if (maxNum == res.happiness)
            return "\uD83D\uDE00";
        else if (maxNum == res.contempt)
            return "\uD83D\uDE12";
        else if (maxNum == res.disgust)
            return "\uD83E\uDD22";
        else if (maxNum == res.fear)
            return "\uD83D\uDE31";
        else if (maxNum == res.neutral)
            return "\uD83D\uDE10";
        else if (maxNum == res.sadness)
            return "\uD83D\uDE22";
        else if (maxNum == res.surprise)
            return "\uD83D\uDE2F";
        else
            return "\uD83D\uDCA9";//Can't detect

    }

}
