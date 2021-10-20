package com.mrtienthinh.nguyentienthinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etEmail, etDescription;
    private CheckBox cbAgree;
    private Button btnSend, btnShow;
    private Spinner spMain;
    private AppDatabase db;
    private String genderSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etDescription = findViewById(R.id.etDescription);
        cbAgree = findViewById(R.id.cbAgree);
        btnSend = findViewById(R.id.btnSend);
        spMain = findViewById(R.id.spMain);
        btnShow = findViewById(R.id.btnShow);

        List<String> spinnerList = new ArrayList<>();
        spinnerList.add("Gripe");
        spinnerList.add("Female");
        spinnerList.add("Unknown");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerList);
        spMain.setAdapter(adapter);


        db = AppDatabase.getAppDatabase(this);
        db.feedbackDao().deleteAll();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewFeedback();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAll();
            }
        });

        spMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                genderSelected = (String) adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void showAll() {
        List<FeedbackEntity> listFeedbackEntities =  db.feedbackDao().getAllFeedback();
        String result = "";
//        result += listFeedbackEntities.size();
//        result += " feedbacks";

        for (FeedbackEntity fe:listFeedbackEntities) {
            result += fe.name;
            result += ";";
            result += fe.email;
            result += ";";
            result += fe.spinner;
            result += ";";
            result += fe.description;
            result += ";";
            result += fe.isEmail;
            result += "\n";
            result += "\n";
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    private void addNewFeedback() {
        if (etName.getText().toString().equals("")) {
            Toast.makeText(this, "Name not be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etEmail.getText().toString().equals("")) {
            Toast.makeText(this, "Email not be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!etEmail.getText().toString().contains("@")) {
            Toast.makeText(this, "Email not valid!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etDescription.getText().toString().equals("")) {
            Toast.makeText(this, "Description not be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        FeedbackEntity bm = new FeedbackEntity();
        bm.name = etName.getText().toString();
        bm.email = etEmail.getText().toString();
        bm.description = etDescription.getText().toString();
        bm.spinner = genderSelected;
        bm.isEmail = cbAgree.isChecked();
        db.feedbackDao().insertFeedback(bm);
        Toast.makeText(this, "Send feedback successful!", Toast.LENGTH_SHORT).show();
        etName.setText("");
        etEmail.setText("");
        etDescription.setText("");
    }


}