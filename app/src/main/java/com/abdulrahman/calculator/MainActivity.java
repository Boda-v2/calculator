package com.abdulrahman.calculator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView resultTextView;
    TextView expressionTextView;
    String operator = "";
    String result = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.result_view);
        expressionTextView = findViewById(R.id.expression_view);
    }
    public void onDigitClick(View view) {
        Button button = (Button) view;
        resultTextView.append(button.getText());
    }
    public void onOperatorClick(View view) {
        Button clickedOperator = (Button) view;
        if (operator.isEmpty()) {
            result = resultTextView.getText().toString();
            operator = clickedOperator.getText().toString();
            expressionTextView.setText(result + " " + operator);
            resultTextView.setText(null);
        } else {
            String rhs = resultTextView.getText().toString();
            result = calculate(result, operator, rhs);
            operator = clickedOperator.getText().toString();
            expressionTextView.setText(result + " " + operator);
            resultTextView.setText(null);
        }
    }
    private String calculate(String lhs, String operator, String rhs) {
        double n1 = Double.parseDouble(lhs);
        double n2 = Double.parseDouble(rhs);
        double result = 0;
        if (operator.equals("+")) {
            result = n1 + n2;
        } else if (operator.equals("-")) {
            result = n1 - n2;
        } else if (operator.equals("×")) {
            result = n1 * n2;
        } else if (operator.equals("÷")) {
            result = n1 / n2;
        }
        return String.valueOf(result);
    }
    public void onEqualClick(View view) {
        String rhs = resultTextView.getText().toString();
        if (rhs.isEmpty() || operator.isEmpty() || result.isEmpty()) {
            resultTextView.setText("");
            return;
        }
        try {
            result = calculate(result, operator, rhs);
            expressionTextView.setText("");
            resultTextView.setText(result);
            operator = "";
            result = "";
        } catch (Exception e) {
            resultTextView.setText("Error");
            e.printStackTrace();
        }
    }
    public void onClearClick(View view) {
        resultTextView.setText("");
        expressionTextView.setText("");
        operator = "";
        result = "";
    }
    public void onBackspaceClick(View view) {
        String current = resultTextView.getText().toString();
        if (!current.isEmpty()) {
            resultTextView.setText(current.substring(0, current.length() - 1));
        }
    }
    public void onPercentClick(View view) {
        String current = resultTextView.getText().toString();
        if (!current.isEmpty()) {
            try {
                double value = Double.parseDouble(current);
                value = value / 100;
                resultTextView.setText(String.valueOf(value));
            } catch (NumberFormatException e) {
                resultTextView.setText("Error");
            }
        }
    }
    boolean isOpeningBracket = true;
    public void onBracketsClick(View view) {
        if (isOpeningBracket) {
            resultTextView.append("(");
        } else {
            resultTextView.append(")");
        }
        isOpeningBracket = !isOpeningBracket;
    }
}
