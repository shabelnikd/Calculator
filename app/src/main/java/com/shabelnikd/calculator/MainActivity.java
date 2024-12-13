package com.shabelnikd.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private double a, b;
    private boolean isOperationClick;
    private int currentOperation;
    private boolean isDotted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.result);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onNumberClick(View view) {
        DecimalFormat df = new DecimalFormat("#.##");
        String text = ((MaterialButton) view).getText().toString();
        try {
            if (view.getId() == R.id.btn_ac) {
                textView.setText("0");
                a = 0;
                b = 0;
                isDotted = false;
            } else if (view.getId() == R.id.btn_delete) {
                textView.setText(getFormattedTextView().substring(0, getFormattedTextView().length() -1));

            } else if (view.getId() == R.id.btn_plus_minus) {
                double num = Double.parseDouble(getFormattedTextView());
                textView.setText(df.format(num * -1));
            } else if (textView.getText().toString().equals("0") || isOperationClick) {
                textView.setText(text);
            } else if (view.getId() == R.id.btn_float) {
                if (!isDotted) {
                    textView.append(".");
                    isDotted = true;
                }
            } else {
                textView.append(text);
            }
        } catch (NumberFormatException e) {
            textView.setText(e.toString());
        }

        isOperationClick = false;

    }

    public void onOperationClick(View view) {
        DecimalFormat df = new DecimalFormat("#.##");
        try {
            if (view.getId() == R.id.btn_equal) {
                b = Double.parseDouble(getFormattedTextView());
                a = calc(a, b, currentOperation);
                textView.setText(df.format(a));
                currentOperation = 0;
                isDotted = false;
            } else if (view.getId() == R.id.btn_percent){
                b = Double.parseDouble(getFormattedTextView());
                textView.setText(df.format(a));
                currentOperation = 0;
                isDotted = false;
            } else {
                a = Double.parseDouble(getFormattedTextView());
                currentOperation = view.getId();
            }
        } catch (NumberFormatException e) {
            textView.setText(e.toString());
        }

        isOperationClick = true;

    }

    private double calc(double a, double b, int action) {
        if (action == R.id.btn_minus) {
            return a - b;
        } else if (action == R.id.btn_plus) {
            return a + b;
        } else if (action == R.id.btn_divide) {
            if (b == 0) {
                return 0;
            }
            return a / b;
        } else if (action == R.id.btn_multiply) {
            return a * b;
        }
        return a;
    }
    private double calcPercent(int action){
        return 0;
    }

    private String getFormattedTextView(){
        return textView.getText().toString().replace(',', '.');
    }
}