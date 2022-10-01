package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView outputN, inputN;

    MaterialButton ac, titik, dibagi, dikali, ditambah, dikurangi, lihat_hasil;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputN = findViewById(R.id.outputN);
        inputN = findViewById(R.id.inputN);

        assignId(ac,R.id.ac);
        assignId(titik,R.id.titik);
        assignId(dibagi,R.id.dibagi);
        assignId(dikali,R.id.dikali);
        assignId(ditambah,R.id.ditambah);
        assignId(dikurangi,R.id.dikurangi);
        assignId(lihat_hasil,R.id.lihat_hasil);

        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);

    }


    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String equalsText = button.getText().toString();
        String dataN = inputN.getText().toString();

        if(equalsText.equals("AC")){
            inputN.setText("");
            outputN.setText("0");
        }

        if(equalsText.equals("=")){
            inputN.setText(outputN.getText());
        }

        dataN = dataN+equalsText;

        inputN.setText(dataN);

        String hasil = getResult(dataN);

        if(!hasil.equals("Err")){
            outputN.setText("=" + hasil);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}