package com.example.adminui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity {

    private LinearLayout studentsContainer;

    private final List<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        studentsContainer = findViewById(R.id.studentsContainer);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadStudentData();

        renderStudentRows();
    }

    private void loadStudentData() {
        studentList.add(new Student("Ashtine Rodriguez", 7, "Pending"));
        studentList.add(new Student("Jake Gonzales",     8, "Paid"));
        studentList.add(new Student("Jaymie Cruz",      10, "Paid"));
        studentList.add(new Student("Ashton Browny",     7, "Pending"));
        studentList.add(new Student("James Saber",       9, "Paid"));
        studentList.add(new Student("Ben Lucas",        10, "Paid"));
    }

    private void renderStudentRows() {
        studentsContainer.removeAllViews();

        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);

            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            row.setBackgroundColor(i % 2 == 0 ? Color.WHITE : Color.parseColor("#F9F9F9"));
            row.setPadding(0, 4, 0, 4);

            TextView tvName = makeCell(student.getName(), 2f);
            row.addView(tvName);

            TextView tvGrade = makeCell(String.valueOf(student.getGradeLevel()), 1.5f);
            row.addView(tvGrade);

            TextView tvStatus = makeCell(student.getPaymentStatus(), 1.5f);
            tvStatus.setTextColor(student.getPaymentStatus().equals("Paid")
                    ? Color.parseColor("#2E7D32")
                    : Color.parseColor("#C62828"));
            row.addView(tvStatus);

            Button btnSeeHere = new Button(this);
            TableRow.LayoutParams btnParams = new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1.5f);
            btnParams.setMargins(8, 8, 8, 8);
            btnSeeHere.setLayoutParams(btnParams);
            btnSeeHere.setText("See Here");
            btnSeeHere.setTextColor(Color.WHITE);
            btnSeeHere.setBackgroundColor(Color.parseColor("#1A3A8F"));
            btnSeeHere.setTextSize(11f);

            final Student currentStudent = student;
            btnSeeHere.setOnClickListener(v -> openStudentDetail(currentStudent));

            row.addView(btnSeeHere);

            studentsContainer.addView(row);

            addDivider();
        }
    }

    private void openStudentDetail(Student student) {
        Toast.makeText(this,
                "Viewing account info for: " + student.getName(),
                Toast.LENGTH_SHORT).show();

    }

    private TextView makeCell(String text, float weight) {
        TextView tv = new TextView(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, weight);
        tv.setLayoutParams(params);
        tv.setText(text);
        tv.setTextSize(13f);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(8, 12, 8, 12);
        return tv;
    }

    private void addDivider() {
        android.view.View divider = new android.view.View(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        divider.setLayoutParams(params);
        divider.setBackgroundColor(Color.parseColor("#E0E0E0"));
        studentsContainer.addView(divider);
    }
}
