package com.example.bmicalculator;

import android.content.Intent;
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
    EditText weight, height_ft, height_inc;
    Button calculate, home, food, exercise;
    double bmi; // Declaring the BMI variable properly

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // ======================= Initialization ==========================
        displayBMI = findViewById(R.id.bmiDisplay);
        height_ft = findViewById(R.id.teHeight_ft);
        height_inc = findViewById(R.id.teHeight_inc);
        calculate = findViewById(R.id.calculateButton);
        home = findViewById(R.id.homeButton);
        food = findViewById(R.id.foodButton);
        exercise = findViewById(R.id.exerciseButton);
        weight = findViewById(R.id.teWeight);

        // =========================== BMI Calculation ==========================
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float weight1 = Float.parseFloat(weight.getText().toString());
                    float height1 = Float.parseFloat(height_ft.getText().toString());
                    float height2 = Float.parseFloat(height_inc.getText().toString());

                    double height_m = (height1 * 0.3048 + height2 * 0.0254);
                    bmi = weight1 / (height_m * height_m); // Correctly storing BMI

                    String bmiCategory;
                    if (bmi < 18.5) {
                        bmiCategory = "কম ওজন";
                    } else if (bmi < 25) {
                        bmiCategory = "স্বাভাবিক ওজন";
                    } else if (bmi < 30) {
                        bmiCategory = "বেশি ওজন";
                    } else if (bmi >= 30 && bmi < 39.9) {
                        bmiCategory = "স্থূল";
                    } else {
                        bmiCategory = "অতিরিক্ত স্থূল";
                    }

                    String resultText = String.format(
                            "আপনার বিএমআই হলঃ %.1f\n %s",
                            bmi,
                            bmiCategory
                    );

                    displayBMI.setText(resultText);
                } catch (NumberFormatException e) {
                    displayBMI.setText("দয়া করে সঠিক সংখ্যা ইনপুট দিন");
                } catch (Exception e) {
                    displayBMI.setText("গণনা ত্রুটি");
                }
            }
        });

        // ===================== Bottom Navigation =========================

        // Home Button - No need to restart MainActivity
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(MainActivity.this, MainActivity.class);
                startActivity(home);
            }
        });

        // Food Suggestion Page
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                intent.putExtra("BMI_VALUE", (float) bmi); // Pass the calculated BMI
                startActivity(intent);
            }
        });
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                intent.putExtra("BMI_VALUE", (float) bmi); // Pass the calculated BMI
                startActivity(intent);
            }
        });

        // Handling Insets for UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
