package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * DetailViewActivity shows a detailed view of the business'
 * details
 */

public class DetailViewActivity extends Activity {

    private EditText nameField, primaryField, addrField, pvField;
    BusinessContact receivedBusinessInfo;
    private MyApplicationData appState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (BusinessContact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        primaryField = (EditText) findViewById(R.id.primaryB);
        addrField = (EditText) findViewById(R.id.addr);
        pvField = (EditText) findViewById(R.id.pv);


        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.name);
            primaryField.setText(receivedBusinessInfo.primaryB);
            addrField.setText(receivedBusinessInfo.addr);
            pvField.setText(receivedBusinessInfo.pv);
        }
    }

    /**
     * Allows the user to update the business details
     * @param v
     */
    public void updateContact(View v){
        //TODO: Update contact functionality

        String personID = appState.firebaseReference.getKey();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryField.getText().toString();
        String addr = addrField.getText().toString();
        String pv = pvField.getText().toString();

        appState.firebaseReference.child(receivedBusinessInfo.bid).setValue(receivedBusinessInfo);
    }

    /**
     * Allows the user to delete the business contact
     * @param v
     */
    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality

        String personID = appState.firebaseReference.getKey();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryField.getText().toString();
        String addr = addrField.getText().toString();
        String pv = pvField.getText().toString();

        appState.firebaseReference.child(receivedBusinessInfo.bid).removeValue(receivedBusinessInfo);
    }
}
