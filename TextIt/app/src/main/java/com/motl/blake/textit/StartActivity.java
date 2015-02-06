package com.motl.blake.textit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends Activity {
    private EditText editPhoneNumber;
    private TextView validIndicator;
    private EditText editMessage;
    private EditText editNumberToSend;
    private String phoneNumber;
    private String message;
    private String numberToSend;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initViews();
        setupPhoneNumberTextListener();
        next.setEnabled(false);
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(phoneNumber != null){
            editPhoneNumber.setText(phoneNumber);
        }
        if(message != null){
            editMessage.setText(message);
        }
        if(numberToSend != null){
            editNumberToSend.setText(numberToSend);
        }
    }

    private void initViews(){
        editPhoneNumber = (EditText)findViewById(R.id.editTextPhoneNumber);
        validIndicator = (TextView)findViewById(R.id.textValidIndicator);
        editMessage = (EditText)findViewById(R.id.editMessage);
        editNumberToSend = (EditText)findViewById(R.id.editTextNumberToSend);
        next = (Button)findViewById(R.id.buttonNextStart);
    }

    private void setupPhoneNumberTextListener(){
        editPhoneNumber.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (PhoneNumberUtils.isGlobalPhoneNumber(editPhoneNumber.getText().toString())) {
                    validIndicator.setText("✔");
                    next.setEnabled(true);
                } else {
                    validIndicator.setText("✘");
                    next.setEnabled(false);
                }
            }
        });
    }

    public void next(View v)
    {
        Intent nextActivity = new Intent(this, DelayActivity.class);
        phoneNumber = editPhoneNumber.getText().toString();
        message = editPhoneNumber.getText().toString();
        numberToSend = editPhoneNumber.getText().toString();
        nextActivity.putExtra(Constants.phoneNumberBundleKey,phoneNumber);
        nextActivity.putExtra(Constants.messageBundleKey,message);
        nextActivity.putExtra(Constants.numberToSendBundleKey,numberToSend);
        startActivity(nextActivity);
    }
}

