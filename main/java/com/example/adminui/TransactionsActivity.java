package com.example.adminui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionsActivity extends AppCompatActivity {

    private LinearLayout transactionsContainer;

    private final List<Transaction> transactionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        transactionsContainer = findViewById(R.id.transactionsContainer);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        loadTransactionData();

        renderTransactionRows();
    }

    private void loadTransactionData() {
        transactionList.add(new Transaction("Jake Gonzales", "April 21, 2026\n12:32 PM", "Paid"));
        transactionList.add(new Transaction("Jaymie Cruz",   "May 1, 2026\n8:30 AM",    "Paid"));
        transactionList.add(new Transaction("James Saber",   "May 2, 2026\n11:45 AM",   "Paid"));
        transactionList.add(new Transaction("Ben Lucas",     "April 1, 2026\n9:57 AM",  "Paid"));
    }

    private void renderTransactionRows() {
        transactionsContainer.removeAllViews();

        for (int i = 0; i < transactionList.size(); i++) {
            Transaction tx = transactionList.get(i);

            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            row.setBackgroundColor(i % 2 == 0 ? Color.WHITE : Color.parseColor("#F9F9F9"));

            row.addView(makeCell(tx.getName(), 2f));

            row.addView(makeCell(tx.getDatePaid(), 2f));

            TextView tvStatus = makeCell(tx.getPaymentStatus(), 2f);
            tvStatus.setTextColor(Color.parseColor("#2E7D32"));
            row.addView(tvStatus);

            transactionsContainer.addView(row);

            addDivider();
        }
    }

    private TextView makeCell(String text, float weight) {
        TextView tv = new TextView(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, weight);
        tv.setLayoutParams(params);
        tv.setText(text);
        tv.setTextSize(13f);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(8, 14, 8, 14);
        return tv;
    }

    private void addDivider() {
        android.view.View divider = new android.view.View(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        divider.setLayoutParams(params);
        divider.setBackgroundColor(Color.parseColor("#E0E0E0"));
        transactionsContainer.addView(divider);
    }
}
