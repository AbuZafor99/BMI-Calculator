package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView displayBMI;
    EditText weight, height_ft, height_inc;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        displayBMI = findViewById(R.id.bmiDisplay);
        height_ft = findViewById(R.id.teHeight_ft);
        height_inc = findViewById(R.id.teHeight_inc);
        calculate = findViewById(R.id.calculateButton);
        weight = findViewById(R.id.teWeight);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get input values
                    float weightLb = Float.parseFloat(weight.getText().toString());
                    float feet = Float.parseFloat(height_ft.getText().toString());
                    float inches = Float.parseFloat(height_inc.getText().toString());

                    // Convert height to total inches
                    float totalInches = (feet * 12) + inches;

                    // Validate inputs
                    if (weightLb <= 0 || feet < 0 || inches < 0) {
                        displayBMI.setText("Values must be positive");
                        return;
                    }

                    if (totalInches == 0) {
                        displayBMI.setText("Height cannot be zero");
                        return;
                    }

                    // Calculate BMI
                    float bmi = (weightLb * 703) / (totalInches * totalInches);

                    // Display result
                    displayBMI.setText(String.format("BMI: %.1f", bmi));

                } catch (NumberFormatException e) {
                    displayBMI.setText("Invalid input");
                } catch (Exception e) {
                    displayBMI.setText("Error occurred");
                }
            }
        });
    }
}