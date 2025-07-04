package com.abdulrahman.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView resultTextView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.result_view)  ;
    }
    public void onDigitClick(View view){
        Button button = (Button) view;
        resultTextView.append(button.getText());
    }
    String operator = "";
    String result = "";
    public void onOperatorClick(View view){
        Button ClickedOperator = (Button) view;
        if(operator.isEmpty()){
            result = resultTextView.getText().toString();
            operator = ClickedOperator.getText().toString();
            resultTextView.setText(null);
        }else{
            String rhs = resultTextView.getText().toString();
            result = calculate(result , operator , rhs);
            operator = ClickedOperator.getText().toString();
            resultTextView.setText(null);
        }
    }
    private String calculate(String lhs, String operator, String rhs) {
        double n1 = Double.parseDouble(lhs);
        double n2 = Double.parseDouble(rhs);
        double result = 0;
        if (operator.equals("+")){
            result = n1+n2;
        }else if(operator.equals("-")){
            result = n1-n2;
        }else if(operator.equals("×")){
            result = n1*n2;
        }else if(operator.equals("÷")){
            result = n1/n2;
        }
        return ""+result;
    }
    public void onEqualClick(View view){
        String rhs = resultTextView.getText().toString();
        result = calculate(result , operator , rhs);
        resultTextView.setText(result);
        operator = "";
        result = "";
    }
}