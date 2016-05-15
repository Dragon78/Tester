package capstone_clive_odane_ashle.tester;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextEditorActivity extends AppCompatActivity {
    Button Confirm;
    TextView CLI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_editor);
        Confirm=(Button)findViewById(R.id.button6) ;
        CLI = (TextView)findViewById(R.id.textView3);
        CLI.append(GlobalText.getText());

        //Initialize intrepreter
        // ANTLROptiMath MathInterpeter = new ANTLROptiMath();
    }

    public void callKeyBoard(View View){
        InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.showSoftInput(View, 0);
    }

    public void  confirm (){
        /*
         *Passes Values to Inurpeter and updates screen output
          */
    }

    public void WriteLn(String Text){
        // Writes a new Line in the Text field
        // EditText editor = (EditText)findViewById(R.id.editText);
        // editor.append(Text + "/n");
    }
}
