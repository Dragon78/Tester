package capstone_clive_odane_ashle.tester;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button NewImage, LoadImage, HelpButton;
    private static int RESULT_LOAD_IMAGE = 1;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewImage = (Button) findViewById(R.id.button);
        HelpButton = (Button) findViewById(R.id.button2);
        LoadImage = (Button) findViewById(R.id.button3);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    public void goto_CameraMode(View View) {
        Intent I = new Intent(this, CameraActivity.class);
        startActivity(I);
    }

    public void goto_MyImages(View View) {
        Intent I = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //startActivityForResult(I, REQUEST_IMAGE_CAPTURE);

        startActivityForResult(I, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage = data.getData();
        InputStream inputstream;
        try {
            inputstream = getContentResolver().openInputStream(selectedImage);
            Bitmap bmp = BitmapFactory.decodeStream(inputstream);
            Intent I = new Intent(this, CameraActivity.class);
            I.putExtra("Bitmap", bmp);
                startActivity(I);

            } catch (FileNotFoundException e) {
            GlobalText.Append("Load ImageFailed (File Access Error)");
            // Show a message to user to let them know image is unavailable
            Intent I = new Intent(this, CameraActivity.class);
            startActivity(I);
        }catch(SecurityException e2){
            GlobalText.Append("Load ImageFailed (Security Permission Denied)");
            Intent I = new Intent(this, CameraActivity.class);
            startActivity(I);
        }
    }



    public void goto_Help(View View) {
        Intent I = new Intent(this, HelpActivity.class);
        startActivity(I);
    }

}