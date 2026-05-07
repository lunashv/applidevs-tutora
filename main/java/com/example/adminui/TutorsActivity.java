package com.example.adminui;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TutorsActivity extends AppCompatActivity {

    private TextView backButton;
    private LinearLayout tutorListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutors);

        backButton = findViewById(R.id.backButton);
        tutorListContainer = findViewById(R.id.tutorListContainer);

        showPendingDatabaseState();
        backButton.setOnClickListener(v -> finish());
    }

    private void showPendingDatabaseState() {
        tutorListContainer.removeAllViews();
        tutorListContainer.addView(createMessageRow("Tutor list will appear here after database integration."));
    }

    private void showTutorRows(List<TutorModel> tutors) {
        tutorListContainer.removeAllViews();
        for (TutorModel tutor : tutors) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.addView(createCell(tutor.getName(), 1.4f));
            row.addView(createCell(String.valueOf(tutor.getStudentCount()), 1f));
            row.addView(createCell(String.valueOf(tutor.getRating()), 1f));
            tutorListContainer.addView(row);
        }
    }

    private TextView createCell(String text, float weight) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, dpToPx(56), weight));
        textView.setBackgroundResource(R.drawable.bg_table_cell);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(dpToPx(8), dpToPx(8), dpToPx(8), dpToPx(8));
        textView.setText(text);
        textView.setTextColor(0xFF333333);
        textView.setTextSize(16);
        return textView;
    }

    private TextView createMessageRow(String text) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setPadding(dpToPx(12), dpToPx(12), dpToPx(12), dpToPx(12));
        textView.setText(text);
        textView.setTextColor(0xFF333333);
        return textView;
    }

    private int dpToPx(int dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }
}
