package com.appsmontreal.menu2;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.Email;
import model.Sound;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int textViewWidgets[] = {R.id.textViewBm,R.id.textViewLm,R.id.textViewSm,
                             R.id.textViewBt,R.id.textViewLt,R.id.textViewSt,
                             R.id.textViewBw,R.id.textViewLw,R.id.textViewSw,
                             R.id.textViewBth,R.id.textViewLth,R.id.textViewSth,
                             R.id.textViewBf,R.id.textViewLf,R.id.textViewSf};

    TextView[] textViews = new TextView[25];
    TextView textViewClicked;
    Intent myIntent;
    String strReturnData;
    Sound sound;
    Button buttonExit;
    Button buttonSend;
    Email email;


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
        }

        sound = new Sound(this);
        buttonExit = (Button)findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(this);
        buttonSend = (Button)findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonExit:
                sound.soundGoExit();
                finish();
                break;

            case R.id.buttonSend:
                sound.soundGoForward();
//                email = new Email(textViews,this);
//                email.sendEmail();
                sendEmail();
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
            textViewClicked.setTextColor(Color.GREEN);

            Toast.makeText(this,"Diet changed",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"NO modification!",Toast.LENGTH_LONG).show();
        }
    }



    public void sendEmail(){
        Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
        intentEmail.setType("message/rfc822");
        intentEmail.setData(Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_EMAIL  , new String[]{""});
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Diet Schedule");
        intentEmail.putExtra(Intent.EXTRA_TEXT   , "MONDAY\n--------------------------\n\t" + "Breakfast =>\t" + textViews[0].getText().toString() + "\n\tLaunch =>\t" + textViews[1].getText().toString() + "\n\tSnacks =>\t" + textViews[2].getText().toString() + "\n\n" +
                "TUESDAY\n-------------------------\n\t" + "Breakfast =>\t" + textViews[3].getText().toString() + "\n\tLaunch =>\t" + textViews[4].getText().toString() + "\n\tSnacks =>\t" + textViews[5].getText().toString() + "\n\n" +
                "WEDNESDAY\n----------------------\n\t" + "Breakfast =>\t" + textViews[6].getText().toString() + "\n\tLaunch =>\t" + textViews[7].getText().toString() + "\n\tSnacks =>\t" + textViews[8].getText().toString() + "\n\n" +
                "THURSDAY\n-----------------------\n\t" + "Breakfast =>\t" + textViews[9].getText().toString() + "\n\tLaunch =>\t" + textViews[10].getText().toString() + "\n\tSnacks =>\t" + textViews[11].getText().toString() + "\n\n" +
                "FRIDAY\n-------------------------\n\t" + "Breakfast =>\t" + textViews[12].getText().toString() + "\n\tLaunch =>\t" + textViews[13].getText().toString() + "\n\tSnacks =>\t" + textViews[14].getText().toString());

        try {
            startActivity(Intent.createChooser(intentEmail, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Email service was not found in this phone.", Toast.LENGTH_SHORT).show();
        }
    }
}
