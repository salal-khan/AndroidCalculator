package com.example.web.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et_result, et_newNumber;
    TextView tv_showOperator;

    //Value Cul Variables
    private Double operand1 = null;
    private String pendingOperator = "=";
    private final String stateOperand1 = "operand1State";
    private final String statependingOperator = "pendingOperatorState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_newNumber = (EditText) findViewById(R.id.et_newNumber);
        et_result = (EditText) findViewById(R.id.et_result);
        tv_showOperator = (TextView) findViewById(R.id.tv_operation);
        Button btn_one = (Button) findViewById(R.id.btn_one);
        Button btn_two = (Button) findViewById(R.id.btn_two);
        Button btn_three = (Button) findViewById(R.id.btn_three);
        Button btn_four = (Button) findViewById(R.id.btn_four);
        Button btn_five = (Button) findViewById(R.id.btn_five);
        Button btn_six = (Button) findViewById(R.id.btn_six);
        Button btn_seven = (Button) findViewById(R.id.btn_seven);
        Button btn_eight = (Button) findViewById(R.id.btn_eight);
        Button btn_nine = (Button) findViewById(R.id.btn_nine);
        Button btn_zero = (Button) findViewById(R.id.btn_zero);
        Button btn_dot = (Button) findViewById(R.id.btn_dot);

        Button btn_plus = (Button) findViewById(R.id.btn_plus);
        Button btn_minus = (Button) findViewById(R.id.btn_minus);
        Button btn_multiply = (Button) findViewById(R.id.btn_mulitply);
        Button btn_divid = (Button) findViewById(R.id.btn_divid);
        Button btn_equals = (Button) findViewById(R.id.btn_equals);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button0 = (Button) view;
                et_newNumber.append(button0.getText().toString());
            }
        };

        btn_one.setOnClickListener(listener);
        btn_two.setOnClickListener(listener);
        btn_three.setOnClickListener(listener);
        btn_four.setOnClickListener(listener);
        btn_five.setOnClickListener(listener);
        btn_six.setOnClickListener(listener);
        btn_seven.setOnClickListener(listener);
        btn_eight.setOnClickListener(listener);
        btn_nine.setOnClickListener(listener);
        btn_zero.setOnClickListener(listener);
        btn_dot.setOnClickListener(listener);

        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button button = (Button) view;
                String operator = button.getText().toString();
                String value = et_newNumber.getText().toString();
                try {
                    Double doublevale = Double.valueOf(value);
                    performOperator(doublevale, operator);
                } catch (NumberFormatException e) {
                    et_newNumber.setText("");
                }


                pendingOperator = operator;
                tv_showOperator.setText(pendingOperator);
            }
        };

        btn_equals.setOnClickListener(operationListener);
        btn_plus.setOnClickListener(operationListener);
        btn_minus.setOnClickListener(operationListener);
        btn_divid.setOnClickListener(operationListener);
        btn_multiply.setOnClickListener(operationListener);

    }

    private void performOperator(Double value, String operator) {
        if (null == operand1) {
            operand1 = value;
        } else {
            if (pendingOperator.equals("=")) {
                pendingOperator = operator;
            }
            switch (pendingOperator) {
                case "=":
                    operand1 = value;
                    break;
                case "+":
                    operand1 += value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
            }
        }
        et_result.setText(operand1.toString());
        et_newNumber.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(statependingOperator, pendingOperator);
        if (operand1 != null) {
            outState.putDouble(stateOperand1, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        operand1 = savedInstanceState.getDouble(stateOperand1);
        pendingOperator = savedInstanceState.getString(statependingOperator);
        tv_showOperator.setText(pendingOperator.toString());
        super.onRestoreInstanceState(savedInstanceState);
    }
}
