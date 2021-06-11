package com.example.inputconstraints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.inputconstraints.databinding.ActivityInputBinding;

public class InputActivity extends AppCompatActivity {
    ActivityInputBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize binding
        b = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("Input Activity");


        //Send data back
        sendInputBack();

    }

    /**
     * Sending data to InputConstraints Activity
     */
    private void sendInputBack() {
        //click event on SendBack Button
        b.btnSendback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = b.data.getText().toString().trim();
                //check data is not empty
                if (data.isEmpty()) {
                    b.data.setError("Please enter data");
                    return;
                }
                //check data  validation
                if (!data.matches(checkConstraints())) {
                    b.data.setError("Invalid");
                    return;
                }
                //Return data to InputConstraints Activity
                Intent intent = new Intent(InputActivity.this, InputConstraintsActivity.class);
                intent.putExtra(Constants.INPUT_DATA, data);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    /**
     * To check data Validation
     *
     * @return String of regex
     */
    private String checkConstraints() {

        Bundle bundle = getIntent().getExtras();

        StringBuilder regex = new StringBuilder();

        regex.append("^([");


        //check data contain Uppercase letters
        if (Boolean.parseBoolean(bundle.getString(Constants.UPPER_CASE, "false"))) {
            regex.append("A-Z");
        }

        //check data contain Lowercase letters
        if (Boolean.parseBoolean(bundle.getString(Constants.LOWER_CASE, "false"))) {
            regex.append("a-z");

        }
        //check data contain Numbers
        if (Boolean.parseBoolean(bundle.getString(Constants.DIGITS, "false"))) {
            regex.append("0-9");

        }

        //check data contain Operators
        if (Boolean.parseBoolean(bundle.getString(Constants.OPERATORS, "false"))) {
            regex.append("+-/*%");

        }
        //check data contain symbols
        if (Boolean.parseBoolean(bundle.getString(Constants.SYMBOLS, "false"))) {
            regex.append("@#\\\\^{}\\]\"\"^()?`~!;:''.,|\\[");
        }
        regex.append("])+");

        return regex.toString();

    }
}