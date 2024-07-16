package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView solution_tv,result_tv;
    MaterialButton c,divition,negative,multiply,sub,dot,add,modulo,equal,ac;
    MaterialButton num_1,num_2,num_3,num_4,num_5,num_6,num_7,num_8,num_9,num_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solution_tv = findViewById(R.id.solution_tv);
        result_tv = findViewById(R.id.result_tv);

        assignId(ac,R.id.ac);
        assignId(c,R.id.c);
        assignId(negative,R.id.negative);
        assignId(divition,R.id.divition);
        assignId(multiply,R.id.multiply);
        assignId(sub,R.id.sub);
        assignId(dot,R.id.dot);
        assignId(add,R.id.add);
        assignId(modulo,R.id.modulo);
        assignId(equal,R.id.equal);
        assignId(num_0,R.id.num_0);
        assignId(num_1,R.id.num_1);
        assignId(num_2,R.id.num_2);
        assignId(num_3,R.id.num_3);
        assignId(num_4,R.id.num_4);
        assignId(num_5,R.id.num_5);
        assignId(num_6,R.id.num_6);
        assignId(num_7,R.id.num_7);
        assignId(num_8,R.id.num_8);
        assignId(num_9,R.id.num_9);


    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        MaterialButton button =(MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();




        if(buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            result_tv.setText("");
            return;
        }
        if(buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonText.equals("+/-")){
            solution_tv.setText("-"+dataToCalculate);
            return;
        }
        if(buttonText.equals("C")){
            if (dataToCalculate.isEmpty()){
                result_tv.setText("0");
                return;
            }else {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        }
        else if(buttonText.equals("0")){
            int i = dataToCalculate.length();

            if(dataToCalculate.isEmpty()){
                solution_tv.setText("");
                result_tv.setText("0");
            } else if (String.valueOf(dataToCalculate.charAt(i-1)).equals("+")||String.valueOf(dataToCalculate.charAt(i-1)).equals("-")||String.valueOf(dataToCalculate.charAt(i-1)).equals("*")||String.valueOf(dataToCalculate.charAt(i-1)).equals("/")||String.valueOf(dataToCalculate.charAt(i-1)).equals("%")) {
                solution_tv.setText(dataToCalculate);
            } else {
                dataToCalculate = dataToCalculate+buttonText;
            }
        }
        else {
                dataToCalculate = dataToCalculate+buttonText;
        }


        solution_tv.setText(dataToCalculate);

        String finalResult = getresult(dataToCalculate);
        if(!finalResult.equals("Error")){
            result_tv.setText(finalResult);
        }
    }

    String getresult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalresult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalresult.endsWith(".0")){
                finalresult = finalresult.replace(".0","");
            }
            return finalresult;
        }catch (Exception e){
            return "Error";
        }
    }
}