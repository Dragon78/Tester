package capstone_clive_odane_ashle.tester.OptiCAL_OCR;

import android.graphics.Bitmap;
import android.os.Environment;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;

import capstone_clive_odane_ashle.tester.GlobalText;
import capstone_clive_odane_ashle.tester.OptiCAL_OCR.ImageEnhancer;

/**
 * Created by Clive on 02/04/2016.
 */
/* THIS CLASS IMPLEMENTS THE OCR FUNCTIONALITY OF THE PROGRAM
     IT DOES THE FOLLOWING:
     1. Takes a raw image and convert it to Gray Scale
     2. Line Detection and Reads Linear and super/sub scripted text
     3. Passes Translated equation in a suitable text format
    */
public class TessOCR {
    private Bitmap Image;
    private String EquationPlainText="";
    private TessBaseAPI Tess;
    private ImageEnhancer Enhancer;
    private String path ="";
    public TessOCR(Bitmap Image){
        this.setImage(Image);
        try{
            Tess = new TessBaseAPI();
            Tess.setDebug(true);
            GlobalText.Append("Tesseract compiled OK \n");// Temporary
        }catch (Exception TesseractError){
            GlobalText.Append("ERROR 1.1: Failed to call Tesseract Constructor\n");
        }
        path=Environment.getExternalStorageDirectory().toString()+"/equ.traineddata";
        File tessdata = new File(path);
        if (!tessdata.exists() || !tessdata.isDirectory()) {
            GlobalText.Append("Trained Data File Not Found in"+ path +"\n");
            throw new IllegalArgumentException("Data path must contain subfolder tessdata!");
        }else{
        //FOR HELP SEE:--> http://stackoverflow.com/questions/16227364/tesseract-help-me-understand-datapath
            String lang ="equ";
            Tess.init(path, lang);
            Tess.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "1234567890");
 }
}

    public void setImage (Bitmap Image){
        /* This Method Constructs TessOCR using TesserBaseApi
        * */
        this.Image=Image;
    }
    public String process (){
        /* This Method Processes the Image using Tesseract and Leptonica Methods
        * */
        try { // Enhance Image
            Enhancer = new ImageEnhancer(this.Image);
            this.Image= Enhancer.Enhance();
            EquationPlainText= Tess.getUTF8Text();  // Translating Image
        }catch (Exception EnhanceException){
            EquationPlainText="Error: Image Enhancement Error 1";
        }
            return EquationPlainText;
    }

    public void EndOCRProcess(){
        /* Terminates and OCR Process (Must Be called in the Function implementing TessOCR)
        * */
        Tess.end();
    }


}
