package com.example.adminui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AdminProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        TextView tvUsername   = findViewById(R.id.tvAdminUsername);
        RelativeLayout layoutRules  = findViewById(R.id.layoutRules);
        RelativeLayout layoutLogout = findViewById(R.id.layoutLogout);

        String adminEmail = getAdminEmail();
        tvUsername.setText(adminEmail);


        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        layoutRules.setOnClickListener(v -> openRulesAndRegulations());
        layoutLogout.setOnClickListener(v -> confirmLogout());
    }

    private String getAdminEmail() {
        return "admin@tutora.edu";
    }

    private void openRulesAndRegulations() {
        Toast.makeText(this, "Opening Rules and Regulations", Toast.LENGTH_SHORT).show();

    }
    private void confirmLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Logout", (dialog, which) -> performLogout())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void performLogout() {
        Toast.makeText(this, "Logged out successfully.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
