package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView displayBMI;
    EditText weight, height_ft, height_inc ;
    Button calculate ;
    double bmi ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        =======================Initialization===================================
        displayBMI=findViewById(R.id.bmiDisplay);
        height_ft=findViewById(R.id.teHeight_ft);
        height_inc=findViewById(R.id.teHeight_inc);
        calculate=findViewById(R.id.calculateButton);
        weight=findViewById(R.id.teWeight) ;

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weight1=Float.parseFloat(weight.getText().toString());
                float height1=Float.parseFloat(height_ft.getText().toString());
                float height2=Float.parseFloat(height_inc.getText().toString());
                double height_m=(height1*0.3048+height2*0.0254) ;

                bmi= weight1/(height_m*height_m) ;

                displayBMI.setText("আপনার বিএমাই হলঃ "+bmi);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}