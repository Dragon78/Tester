package capstone_clive_odane_ashle.tester.OptiCAL_OCR;

import android.graphics.Bitmap;

/**
 * Created by Clive on 02/04/2016.
 */
public class ImageEnhancer {
    Bitmap Image;
    public ImageEnhancer(Bitmap Image){
        this.setImage(Image);
    }
    public void setImage(Bitmap Image){
        this.Image=Image;
    }
    public Bitmap Enhance (){
        return this.Image;
    }
}
