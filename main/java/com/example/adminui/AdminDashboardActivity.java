
package com.example.adminui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {

    private SimplePieChartView subjectPieChart;
    private CardView btnTutors;
    private CardView btnStudents;
    private CardView btnTransactions;
    private TextView tutorCountTextView;
    private TextView studentCountTextView;
    private TextView topTutorNameTextView;
    private TextView topTutorRatingTextView;
    private TextView secondTutorNameTextView;
    private TextView secondTutorRatingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        subjectPieChart = findViewById(R.id.subjectPieChart);
        btnTutors = findViewById(R.id.btnTutors);
        btnStudents = findViewById(R.id.btnStudents);
        btnTransactions = findViewById(R.id.btnTransactions);
        tutorCountTextView = findViewById(R.id.tutorCountTextView);
        studentCountTextView = findViewById(R.id.studentCountTextView);
        topTutorNameTextView = findViewById(R.id.topTutorNameTextView);
        topTutorRatingTextView = findViewById(R.id.topTutorRatingTextView);
        secondTutorNameTextView = findViewById(R.id.secondTutorNameTextView);
        secondTutorRatingTextView = findViewById(R.id.secondTutorRatingTextView);

        setupPieChart();
        showPendingDatabaseState();
        setupNavigation();
    }

    private void setupPieChart() {
        List<SimplePieChartView.PieSlice> entries = new ArrayList<>();
        subjectPieChart.setSlices(entries);
    }

    private void showPendingDatabaseState() {
        tutorCountTextView.setText("Tutors: --");
        studentCountTextView.setText("Students: --");
        topTutorNameTextView.setText("Pending database");
        topTutorRatingTextView.setText("-");
        secondTutorNameTextView.setText("Pending database");
        secondTutorRatingTextView.setText("-");
    }

    private void setupNavigation() {
        btnTutors.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboardActivity.this, TutorsActivity.class))
        );

        btnStudents.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboardActivity.this, StudentsActivity.class))
        );

        btnTransactions.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboardActivity.this, TransactionsActivity.class))
        );

        findViewById(R.id.imgProfile).setOnClickListener(v ->
                startActivity(new Intent(AdminDashboardActivity.this, AdminProfileActivity.class))
        );
    }
}
