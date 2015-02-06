package com.themotlcode.textit;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;

import java.lang.reflect.Array;

public class StartActivity extends ActionBarActivity {
    private EditText editPhoneNumber;
    private TextView validIndicator;
    private EditText editMessage;
    private EditText editNumberToSend;
    private String phoneNumber;
    private String message;
    private String numberToSend;
    private Button next;
    private Activity activity = this;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private ObservableScrollView mainScrollView;
    private ListView drawerItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initViews();
        //setupPhoneNumberTextListener();
        //next.setEnabled(false);
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
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setStatusBarBackgroundColor(Color.WHITE);
        toolbar = (Toolbar) findViewById(R.id.includeToolbar);
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(drawerToggle);
        drawer.setStatusBarBackgroundColor(getResources().getColor(R.color.primary));
        mainScrollView = (ObservableScrollView)findViewById(R.id.scrollView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToScrollView(mainScrollView);
        drawerItems = (ListView)findViewById(R.id.left_drawer);
        ArrayAdapter<String> navDrawerListAdapter =  new ArrayAdapter<>(this,R.layout.list_item);
        drawerItems.setAdapter(navDrawerListAdapter);
        navDrawerListAdapter.addAll(getResources().getStringArray(R.array.drawer_items));
        drawerItems.setOnItemClickListener(new DrawerItemClickListener());
        //editPhoneNumber = (EditText)findViewById(R.id.editTextPhoneNumber);
        //validIndicator = (TextView)findViewById(R.id.textValidIndicator);
        //editMessage = (EditText)findViewById(R.id.editMessage);
        //editNumberToSend = (EditText)findViewById(R.id.editTextNumberToSend);
        //next = (Button)findViewById(R.id.buttonNextStart);
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
        //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
               // activity, transitionView, DetailActivity.EXTRA_IMAGE);
        //ActivityCompat.startActivity(activity, new Intent(activity, DetailActivity.class),
                //options.toBundle());
        startActivity(nextActivity);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.text_it,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            drawer.openDrawer(Gravity.START|Gravity.LEFT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(Gravity.START|Gravity.LEFT)){
            drawer.closeDrawers();
            return;
        }
        super.onBackPressed();
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
        }
    }
}

