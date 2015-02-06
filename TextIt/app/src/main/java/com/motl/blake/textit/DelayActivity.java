package com.motl.blake.textit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Blake on 7/13/2014.
 */
public class DelayActivity extends Activity {
    private EditText editTextTimeValue;
    private RadioButton enableOne;
    private EditText editTextRangeLower;
    private EditText editTextRangeUpper;
    private RadioButton enableRange;
    private String phoneNumber;
    private String message;
    private String numberToSend;
    private String timeDelay;
    private boolean range;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay);
        initView();
        boolean bundleFull = getValuesFromBundle(getIntent().getExtras());
        if(!bundleFull){
            Toast.makeText(this,"Error Loading Values!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(timeDelay != null){
            if(range){
                String[] timeArrayStorage = timeDelay.split("-");
                if(timeArrayStorage.length == 2) {
                    editTextRangeLower.setText(timeArrayStorage[0]);
                    editTextRangeUpper.setText(timeArrayStorage[1]);
                }
            }
            else{
                editTextTimeValue.setText(timeDelay);
            }
        }
    }

    private void initView()
    {
        editTextTimeValue = (EditText)findViewById(R.id.editTextTimeValue);
        enableOne = (RadioButton)findViewById(R.id.enableOne);
        editTextRangeLower = (EditText)findViewById(R.id.editTextRangeLower);
        editTextRangeUpper = (EditText)findViewById(R.id.editTextRangeUpper);
        enableRange = (RadioButton)findViewById(R.id.enableRange);
    }

    private boolean getValuesFromBundle(Bundle extras){
        if(extras != null) {
            if(extras.containsKey(Constants.phoneNumberBundleKey)) {
                phoneNumber = extras.getString(Constants.phoneNumberBundleKey);
            }
            else{
                return false;
            }
            if(extras.containsKey(Constants.messageBundleKey)){
                message = extras.getString(Constants.messageBundleKey);
            }
            else{
                return false;
            }
            if(extras.containsKey(Constants.numberToSendBundleKey)){
                numberToSend = extras.getString(Constants.numberToSendBundleKey);
            }
            else{
                return false;
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    public void preview(View v){
        Intent nextActivity = new Intent(this,PreviewActivity.class);
        nextActivity.putExtra(Constants.phoneNumberBundleKey,phoneNumber);
        nextActivity.putExtra(Constants.messageBundleKey,message);
        nextActivity.putExtra(Constants.numberToSendBundleKey,numberToSend);
        nextActivity.putExtra(Constants.timeDelayBundleKey,timeDelay);
    }

    public void one(View v){
        range = false;
        enableRange.setChecked(false);
        editTextTimeValue.setEnabled(true);
        editTextRangeUpper.setEnabled(false);
        editTextRangeLower.setEnabled(false);
    }

    public void range(View v){
        range = true;
        enableOne.setChecked(false);
        editTextTimeValue.setEnabled(false);
        editTextRangeUpper.setEnabled(true);
        editTextRangeLower.setEnabled(true);
    }

}
