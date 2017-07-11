package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Creates a new business contact
 */

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, primaryField, addrField, pvField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        primaryField = (EditText) findViewById(R.id.primaryB);
        addrField = (EditText) findViewById(R.id.addr);
        pvField = (EditText) findViewById(R.id.pv);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitInfoButton(v);
            }
        });
    }

    /**
     * OnClick the business information will be submitted to the database
     * @param v
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryField.getText().toString();
        String addr = addrField.getText().toString();
        String pv = pvField.getText().toString();
        BusinessContact person = new BusinessContact(personID, name, primaryBusiness, addr, pv);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
