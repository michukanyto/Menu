package com.appsmontreal.menu2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.Sound;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int textViewWidgets[] = {R.id.textViewBm,R.id.textViewBt,R.id.textViewBw,R.id.textViewBth,R.id.textViewBf,
            R.id.textViewLm,R.id.textViewLt,R.id.textViewLw,R.id.textViewLth,R.id.textViewLf,
            R.id.textViewSm,R.id.textViewSt,R.id.textViewSw,R.id.textViewSth,R.id.textViewSt};

    TextView[] textViews = new TextView[25];
    TextView textViewClicked;
    Intent myIntent;
    String strReturnData;
    Sound sound;
    Button buttonExit;
    Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize(){
        for (int x = 0; x < textViewWidgets.length; x++){
            textViews[x] = (TextView) findViewById(textViewWidgets[x]);
            textViews[x].setOnClickListener(this);
            sound = new Sound(this);
            buttonExit = (Button)findViewById(R.id.buttonExit);
            buttonExit.setOnClickListener(this);
            buttonSend = (Button)findViewById(R.id.buttonSend);
            buttonSend.setOnClickListener(this);
        }
    }

//    public void clickExit(){
//        buttonExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sound.soundGoExit();
//                finish();
//            }
//        });
//    }
//
//    public void clickSend(){
//        buttonSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sound.soundGoForward();
//            }
//        });
//    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonExit:
                sound.soundGoExit();
                finish();
                break;

            case R.id.buttonSend:
                sound.soundGoForward();
                
                break;

            default:
                sound.soundGoForward();
                textViewClicked = (TextView) v;
                myIntent = new Intent(this,ChangeMenuActivity.class);
                myIntent.putExtra("diet",(String) textViewClicked.getText().toString());
                startActivityForResult(myIntent,1);
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            strReturnData = (String)data.getSerializableExtra("valueReturn");
            textViewClicked.setText(strReturnData);
            Toast.makeText(this,"Diet Changed",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"NO modification!",Toast.LENGTH_LONG).show();
        }
    }
}
