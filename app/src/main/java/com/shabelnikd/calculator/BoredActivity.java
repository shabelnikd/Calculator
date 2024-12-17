package com.shabelnikd.calculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class BoredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bored_app_activity);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            DecimalFormat df = new DecimalFormat("#.##");
            Double result = extras.getDouble("Result", 0);
            TextView tv = findViewById(R.id.card_text);
            tv.setText(df.format(result));
        }

        Button btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(v -> {
            finishAffinity();
        });

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteText);
        String[] items = {"Type 1", "Type 2", "Type 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        autoCompleteTextView.setOnClickListener(v -> {
            autoCompleteTextView.setHint("");
        });
        autoCompleteTextView.setAdapter(adapter);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bored), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}