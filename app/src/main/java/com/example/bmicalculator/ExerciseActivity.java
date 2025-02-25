package com.example.bmicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {
    TextView bmiResultText, exerciseSuggestionText;
    Button home, food, exercise;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        // Initialize UI Components
        bmiResultText = findViewById(R.id.bmiResultText);
        exerciseSuggestionText = findViewById(R.id.exerciseSuggestionText);
        home = findViewById(R.id.homeButton);
        food = findViewById(R.id.foodButton);
        exercise = findViewById(R.id.exerciseButton);

        // Get BMI from Intent
        float bmi = getIntent().getFloatExtra("BMI_VALUE", 0.0f);

        // Display BMI result
        bmiResultText.setText("আপনার BMI: " + String.format("%.2f", bmi));

        // Display Exercise Suggestions
        exerciseSuggestionText.setText(getExerciseSuggestion(bmi));

        // ===================== Bottom Navigation =========================

        // Home Button (Navigates back to MainActivity)
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ExerciseActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        // Food Button (Navigate to FoodActivity)
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodIntent = new Intent(ExerciseActivity.this, FoodActivity.class);
                foodIntent.putExtra("BMI_VALUE", bmi);
                startActivity(foodIntent);
            }
        });

        // Exercise Button (Stay on the same screen)
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exercise = new Intent(ExerciseActivity.this, ExerciseActivity.class);
                exercise.putExtra("BMI_VALUE", bmi);
                startActivity(exercise);
            }
        });
    }

    // Exercise Suggestions Based on BMI
    private String getExerciseSuggestion(float bmi) {
        if (bmi < 18.5) {
            return "⚠️ আপনি কম ওজনের (Underweight)।\n\n" +
                    "🏋️ **আপনার জন্য ব্যায়াম:**\n" +
                    "• ওজন বাড়ানোর জন্য স্ট্রেংথ ট্রেনিং (30 মিনিট/দিন)\n" +
                    "• স্কোয়াট, পুশ-আপ, ওজন তোলা\n" +
                    "• প্রতিদিন ১৫-২০ মিনিট যোগব্যায়াম\n\n" +
                    "🔥 **প্রস্তাবিত ক্যালোরি খরচ:** ১৫০-২০০ ক্যালোরি/সেশন\n\n" +
                    "💡 **পরামর্শ:** বেশি শক্তি ব্যয় করবেন না, বরং পেশি গঠন করুন।";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "✅ আপনার ওজন স্বাভাবিক (Normal Weight)।\n\n" +
                    "🏋️ **আপনার জন্য ব্যায়াম:**\n" +
                    "• ব্যালেন্সড কার্ডিও এবং স্ট্রেংথ ট্রেনিং (30-45 মিনিট/দিন)\n" +
                    "• দৌড়ানো, সাইক্লিং, সাঁতার কাটা\n" +
                    "• যোগব্যায়াম বা স্ট্রেচিং\n\n" +
                    "🔥 **প্রস্তাবিত ক্যালোরি খরচ:** ২০০-৩০০ ক্যালোরি/সেশন\n\n" +
                    "💡 **পরামর্শ:** প্রতিদিন হালকা ও মাঝারি ব্যায়াম করুন, ফিটনেস ধরে রাখুন।";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "⚠️ আপনি অতিরিক্ত ওজনের (Overweight)।\n\n" +
                    "🏋️ **আপনার জন্য ব্যায়াম:**\n" +
                    "• ফ্যাট কমানোর জন্য কার্ডিও ব্যায়াম (40-60 মিনিট/দিন)\n" +
                    "• হাঁটা, দৌড়ানো, সাইক্লিং\n" +
                    "• হালকা ওজন উত্তোলন\n\n" +
                    "🔥 **প্রস্তাবিত ক্যালোরি খরচ:** ৩০০-৪০০ ক্যালোরি/সেশন\n\n" +
                    "💡 **পরামর্শ:** প্রতিদিন নিয়মিত ব্যায়াম করুন, বেশি ফাস্ট ফুড খাবেন না।";
        } else {
            return "⚠️ আপনি স্থূলতার সমস্যায় (Obese)।\n\n" +
                    "🏋️ **আপনার জন্য ব্যায়াম:**\n" +
                    "• ধীরে ধীরে কার্ডিও ও স্ট্রেংথ ট্রেনিং শুরু করুন (45-60 মিনিট/দিন)\n" +
                    "• প্রতিদিন ৩০ মিনিট হাঁটুন\n" +
                    "• সাঁতার, স্টেশনারি বাইকিং\n\n" +
                    "🔥 **প্রস্তাবিত ক্যালোরি খরচ:** ৪০০-৫০০ ক্যালোরি/সেশন\n\n" +
                    "💡 **পরামর্শ:** ধীরে ধীরে ওজন কমান, নিয়মিত ব্যায়াম করুন।";
        }
    }
}
