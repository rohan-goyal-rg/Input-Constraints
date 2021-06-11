package com.example.inputconstraints;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.inputconstraints.databinding.ActivityInputConstraintsBinding;

public class InputConstraintsActivity extends AppCompatActivity {
    private static final int REQUEST_INPUT = 12;
    ActivityInputConstraintsBinding b;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize binding
        b = ActivityInputConstraintsBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("InputConstraints Activity");

        //Send constraint
        sendConstraints();
    }

    /**
     * Send constraints to InputActivity
     */
    private void sendConstraints() {
        //click event listener on TakeInput Button
        b.btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //To put the constraints in bundle
                putConstraints();

                //Check bundle
                if (bundle.isEmpty()){
                    Toast.makeText(InputConstraintsActivity.this, "Please select a constraint", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Send Constraints to InputActivity
                Intent intent=new Intent(InputConstraintsActivity.this,InputActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,REQUEST_INPUT);
            }
        });

    }

    /**
     * which constraint user checked
     * And put the constraint in bundle
     */
    private void putConstraints() {
        //Initialize bundle
        bundle = new Bundle();

        //To check user select upperCase constraint
        if (b.checkUppercase.isChecked()) {
            bundle.putString(Constants.UPPER_CASE, "true");
        }

        //To check user select lowerCase constraint
        if (b.checkLowercase.isChecked()) {
            bundle.putString(Constants.LOWER_CASE, "true");
            Log.d("Abhi", "checkConstraints: ");
        }

        //To check user select digit constraint
        if (b.checkDigits.isChecked()) {
            bundle.putString(Constants.DIGITS, "true");
        }

        //To check user select operators constraint
        if (b.checkOperators.isChecked()) {
            bundle.putString(Constants.OPERATORS, "true");
        }
        //To check user select symbol constraint
        if (b.checkSymbols.isChecked()) {
            bundle.putString(Constants.SYMBOLS, "true");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //To Check data is send by InputActivity
        if(requestCode==REQUEST_INPUT&&resultCode==RESULT_OK){
            b.Input.setText("Input is :- "+data.getStringExtra(Constants.INPUT_DATA));
            b.Input.setVisibility(View.VISIBLE);
        }
    }
}