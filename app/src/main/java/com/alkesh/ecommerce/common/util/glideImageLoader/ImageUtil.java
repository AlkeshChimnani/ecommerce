package com.alkesh.ecommerce.common.util.glideImageLoader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.alkesh.ecommerce.R;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


public class ImageUtil {

    public static void loadImage(Context context, ImageView imageView, String imageURL) {
        CircularProgressDrawable cd = new CircularProgressDrawable(context);
        cd.setStrokeWidth(5f);
        cd.setCenterRadius(30f);
        cd.start();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(cd)
                .error(R.drawable.ic_error_image);
        GlideApp.with(context).load(imageURL).apply(options).into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, Integer image) {
        CircularProgressDrawable cd = new CircularProgressDrawable(context);
        cd.setStrokeWidth(5f);
        cd.setCenterRadius(30f);
        cd.start();

        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(cd)
                .error(R.drawable.ic_error_image);
        GlideApp.with(context).load(image).apply(options).into(imageView);
    }

    public static void loadGif(Context context, ImageView imageView, Integer image) {
        GlideApp.with(context).asGif().load(image).into(imageView);
    }

    public static void loadRawGif(Context context, ImageView imageView, Integer image) {
        GlideApp.with(context).asGif().load(image).into(imageView);
    }

    public static void loadRes(Context context, ImageView imageView, Integer image) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .error(R.drawable.ic_error_image);
        GlideApp.with(context).load(image).apply(options).into(imageView);

    }

    public static void loadRes(Context context, ImageView imageView, Bitmap image, Integer width, Integer height) {
        //Picasso.get().load(image).resize(width, height).into(imageView);
        imageView.setImageBitmap(Bitmap.createScaledBitmap(image, width, height, false));
    }

    public static void loadBitmap(ImageView imageView, Bitmap bitmap) {
        GlideApp.with(imageView.getContext()).load(bitmap).fitCenter().into(imageView);
    }

    public static String encodeToString(Bitmap bm) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String binaryString = Base64.encodeToString(byteArray, Base64.NO_WRAP);
        return binaryString;

    }

    public static Bitmap decodeToBitmap(String binary) {
        try {
            byte[] decodedString = Base64.decode(binary, Base64.DEFAULT);

            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedByte;
        } catch (Exception exp) {
            return null;
        }
    }

    public static void loadBase64(String binary, ImageView imageView) {
        byte[] decodedString = Base64.decode(binary, Base64.DEFAULT);
        CircularProgressDrawable cd = new CircularProgressDrawable(imageView.getContext());
        cd.setStrokeWidth(5f);
        cd.setCenterRadius(30f);
        cd.start();

        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(cd)
                .error(R.drawable.ic_error_image);
        GlideApp.with(imageView.getContext()).load(decodedString).apply(options).into(imageView);

    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(Resources.getSystem(), x);
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public static Bitmap getOriginalBitmapFromFile(String photoPath, Bitmap bitmap) throws Exception {
        ExifInterface ei = new ExifInterface(photoPath);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap rotatedBitmap = null;
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(bitmap, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(bitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(bitmap, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = bitmap;
        }
        return rotatedBitmap;
    }

    public static Bitmap getBitmapFromView(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        view.draw(canvas);
        return bitmap;
    }

    public static Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

}