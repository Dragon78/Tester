package capstone_clive_odane_ashle.tester;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import capstone_clive_odane_ashle.tester.OptiCAL_OCR.TessOCR;

public class CameraActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE= 1;
    private String ReadText;
    private Bitmap FinalImage;
    private ImageView CapturedImage;
    private Button Capture;
    private Button OCR_READ;
    private TessOCR ImageTranslator; // IMPLEMENTS OCR FUNCTIONALITY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        CapturedImage=(ImageView)findViewById(R.id.imageView) ;
        OCR_READ=(Button)findViewById(R.id.button5);
        Capture=(Button)findViewById(R.id.button4) ;
        if (!hasCamera()){Capture.setEnabled(false);}

        try{
            Intent intent = getIntent();
            //Bitmap bitmap = (Bitmap)intent.getData();
            Bitmap bitmap = (Bitmap) intent.getParcelableExtra("Bitmap");
            CapturedImage.setImageBitmap(bitmap);}
        catch(Exception e ){/* Do Nothing*/}
    }
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
    public void LaunchCamera(View view){
        Intent I= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(I,REQUEST_IMAGE_CAPTURE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode== REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap Image = (Bitmap)extras.get("data");
            CapturedImage.setImageBitmap(Image);
            FinalImage= (Bitmap) Image;
        }
    }

    public void goto_OCRMode(View View){
        //Pass Image into OCR: Create OCR Using Tesseract API
        try {
            ImageTranslator = new TessOCR(FinalImage); 
            ReadText= ImageTranslator.process();
            ImageTranslator.EndOCRProcess();
            ReadText="It Worked \n";// Temporary
        } catch(Exception OcrException1){
            ReadText="ERROR 1:OCR READ ERROR \n";
        }
        Intent I = new Intent(this,TextEditorActivity.class);
        // Pass Text into new Activity (Confirmation & Editing)
        GlobalText.Append(ReadText);
        startActivity(I);
    }
}