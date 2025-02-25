package com.example.bmicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FoodActivity extends AppCompatActivity {
    TextView bmiResultText, foodSuggestionText;
    Button home, food, exercise;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        // Initialize UI Components
        bmiResultText = findViewById(R.id.bmiResultText);
        foodSuggestionText = findViewById(R.id.foodSuggestionText);
        home = findViewById(R.id.homeButton);
        food = findViewById(R.id.foodButton);
        exercise = findViewById(R.id.exerciseButton);

        // Get BMI from Intent
        float bmi = getIntent().getFloatExtra("BMI_VALUE", 0.0f);

        // Display BMI result
        bmiResultText.setText("আপনার BMI: " + String.format("%.2f", bmi));

        // Display Food Suggestions
        foodSuggestionText.setText(getFoodSuggestion(bmi));

        // ===================== Bottom Navigation =========================

        // Home Button (Navigates back to MainActivity)
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(FoodActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        // Food Button (Stay on the same screen)
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foood = new Intent(FoodActivity.this, FoodActivity.class);
                foood.putExtra("BMI_VALUE", bmi);
                startActivity(foood);
            }
        });

        // Exercise Button (Navigate to ExerciseActivity)
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exerciseIntent = new Intent(FoodActivity.this, ExerciseActivity.class);
                exerciseIntent.putExtra("BMI_VALUE", bmi);
                startActivity(exerciseIntent);
            }
        });
    }

    // Food Suggestions Based on BMI
    private String getFoodSuggestion(float bmi) {
        if (bmi < 18.5) {
            return "⚠️ আপনি কম ওজনের (Underweight)।\n\n" +
                    "আপনার দৈনিক ক্যালোরি গ্রহণ: ২৩০০-৩০০০ ক্যালোরি\n\n" +
                    "🥗 **আপনার খাবারে কী থাকতে হবে?**\n" +
                    "• বেশি ক্যালোরিযুক্ত খাবার: চাল, রুটি, আলু, মিষ্টি আলু\n" +
                    "• প্রোটিন সমৃদ্ধ খাবার: ডিম, মাংস, মাছ, ডাল, বাদাম\n" +
                    "• দুগ্ধজাত খাবার: দুধ, দই, পনির\n" +
                    "• স্বাস্থ্যকর চর্বি: অ্যাভোকাডো, অলিভ অয়েল, ঘি\n\n" +
                    "💡 **পরামর্শ:** দিনে ৫-৬ বার খান, প্রোটিন শেক ও বাদাম খেতে পারেন।";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "✅ আপনার ওজন স্বাভাবিক (Normal Weight)।\n\n" +
                    "আপনার দৈনিক ক্যালোরি গ্রহণ: ২০০০-২৫০০ ক্যালোরি\n\n" +
                    "🥗 **আপনার খাবারে কী থাকতে হবে?**\n" +
                    "• পরিমিত পরিমাণে কার্বোহাইড্রেট: ভাত, আটার রুটি, ওটস\n" +
                    "• প্রোটিন: মাছ, মুরগি, ডাল, ছোলা\n" +
                    "• শাকসবজি ও ফল: গাজর, পালং শাক, আপেল, কলা\n" +
                    "• স্বাস্থ্যকর চর্বি: বাদাম, অলিভ অয়েল\n\n" +
                    "💡 **পরামর্শ:** ব্যালান্সড ডায়েট মেনে চলুন, অতিরিক্ত ফাস্ট ফুড এড়িয়ে চলুন।";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "⚠️ আপনি অতিরিক্ত ওজনের (Overweight)।\n\n" +
                    "আপনার দৈনিক ক্যালোরি গ্রহণ: ১৫০০-২০০০ ক্যালোরি\n\n" +
                    "🥗 **আপনার খাবারে কী থাকতে হবে?**\n" +
                    "• কম ক্যালোরিযুক্ত খাবার: সবুজ শাকসবজি, গাজর, টমেটো\n" +
                    "• প্রোটিন: মুরগি, মাছ, ছোলা, ডাল\n" +
                    "• কম চর্বিযুক্ত দুগ্ধজাত খাবার: দুধ, দই\n" +
                    "• কম কার্বোহাইড্রেট: ব্রাউন রাইস, আটার রুটি\n\n" +
                    "💡 **পরামর্শ:** ফাস্ট ফুড, অতিরিক্ত ভাজাপোড়া এড়িয়ে চলুন, নিয়মিত হাঁটুন।";
        } else {
            return "⚠️ আপনি স্থূলতার সমস্যায় (Obese)।\n\n" +
                    "আপনার দৈনিক ক্যালোরি গ্রহণ: ১২০০-১৫০০ ক্যালোরি\n\n" +
                    "🥗 **আপনার খাবারে কী থাকতে হবে?**\n" +
                    "• উচ্চ ফাইবার খাবার: শাকসবজি, পালং শাক, কাঁচা গাজর\n" +
                    "• প্রোটিন: মাছ, ডাল, মুরগি (ভাজা নয়)\n" +
                    "• কম চর্বিযুক্ত দুগ্ধজাত খাবার: স্কিম মিল্ক, টক দই\n" +
                    "• স্বাস্থ্যকর কার্বোহাইড্রেট: ব্রাউন রাইস, ওটস\n\n" +
                    "💡 **পরামর্শ:** প্রতিদিন ৩০ মিনিট হাঁটুন, ফাস্ট ফুড ও মিষ্টি খাওয়া কমান।";
        }
    }
}
