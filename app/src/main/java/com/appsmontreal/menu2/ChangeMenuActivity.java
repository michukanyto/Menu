package com.appsmontreal.menu2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeMenuActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    Button buttonOk;
    Intent intentReturn;
    String strData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_menu);
        initialize();
    }

    public void initialize(){
        editText = (EditText)findViewById(R.id.editTextDescription);
        strData = (String)getIntent().getExtras().getSerializable("diet");
        editText.setText(strData);
        buttonOk = (Button)findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(this);
        intentReturn = new Intent(this,MainActivity.class);
    }

    public void returnResult(View v){

        if (!editText.getText().toString().equals(strData)) {
            Log.i("editTex values", editText.getText().toString());
            Log.i("Variable values", strData);
            intentReturn.putExtra("valueReturn", editText.getText().toString());
            setResult(RESULT_OK, intentReturn);
        } else {
            setResult(RESULT_CANCELED, intentReturn);
        }

        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonOk:
                returnResult(v);
                break;
        }


    }
}
