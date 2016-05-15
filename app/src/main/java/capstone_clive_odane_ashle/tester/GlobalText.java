package capstone_clive_odane_ashle.tester;

import android.graphics.Bitmap;

/**
 * Created by Clive on 07/04/2016.
 */
public class GlobalText {
    public static int ivar1,ivar2;
    public static String svar1="",svar2="";
    public static Bitmap Imageholder;

    public static void Append(String Text){
        svar2= Text;
        svar1= svar1+svar2;
    }
    public static void ClearAll(){
        svar1= "";
        svar2= "";
    }
    public static String getText(){
        return  svar1;
    }
    public static String getRecentText(){
        return svar2;

    }

    public static void setImage(Bitmap Image){
        Imageholder=Image;
    }
    public static Bitmap getImage(){return Imageholder;}
}
