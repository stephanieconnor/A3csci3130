package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests CRUD
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class ContactTest {

    private MyApplicationData appState;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

    /**
     * tests to see if user can create new contact
     */

    @Test
    public void createContactTest(){

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://a4csci3130.firebaseio.com");
        DatabaseReference ref = database.getReference("businessContact");

        BusinessContact bcActual = new BusinessContact("000000001", "Joe", "Distributor", "123 Lakeview", "NS");
        final BusinessContact bcTest;

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bcTest = dataSnapshot.child();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        assertEquals(bcActual, bcTest);
    }

    /**
     * tests to see if user can read a business contact
     */

    @Test
    public void getContactTest(){

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://a4csci3130.firebaseio.com");
        DatabaseReference ref = database.getReference("businessContact");

        BusinessContact bc = new BusinessContact("000000001", "Joe", "Distributor", "123 Lakeview", "NS");


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bc = dataSnapshot.child();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        assertNotNull(bc);
    }

    /**
     * tests to see if user can update the database
     */

    @Test
    public void updateContactTest(){

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://a4csci3130.firebaseio.com");
        DatabaseReference ref = database.getReference("businessContact");

        BusinessContact bc = new BusinessContact("000000001", "Joe", "Distributor", "123 Lakeview", "NS");

        appState.firebaseReference.child(bc.name).setValue("Huey");

        assertEquals(bc.name, "Huey");
    }

    /**
     * tests to see if contact can be deleted
     */

    @Test
    public void eraseContactTest(){

        BusinessContact bc = new BusinessContact("000000001", "Joe", "Distributor", "123 Lakeview", "NS");
        appState.firebaseReference.child(bc.name).removeValue(bc);

        assertNull(bc);

    }


}
