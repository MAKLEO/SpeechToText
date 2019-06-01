package manish.khullar.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView t;
    Button bt;
    private final int REQ_CODE_SPEECH_INPUT=1994; //it should be declared seperately and should never be 0 or 1
//if we dont write final, overridden methods wont recognise that variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectxml();
        boolhalkehalke();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void boolhalkehalke() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //perform an action for speech recognition
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //1.LANGUAGE_MODEL_FREE_FORM  Supports multiple languages and maintains a language free form
        //2.
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //Locale.getDefault() Using default system language
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"HI SPEAK SOMETHING");
        //EXTRA_PROMPT passing an additional value to the prompt or recognizer
        try
        {
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);//intent,REQ_CODE_SPEECH_INPUT we pass object of intent and req code in startActivityForResult
        }//**********VVIMP***************  startActivityForResult()<start tag jaisa> method is followed by onActivityResult() <end tag jaise> method
        catch(ActivityNotFoundException a)
        {

        }
    }


    private void connectxml() {
        t=findViewById(R.id.textView);
        bt=findViewById(R.id.button);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case REQ_CODE_SPEECH_INPUT:
            {
                if (resultCode==RESULT_OK&&null!=data)
                {

                        ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        t.setText(result.get(0));
                        //writedatainfile(t.getText().toString())
                    break;
                }
            }
        }
    }
}
