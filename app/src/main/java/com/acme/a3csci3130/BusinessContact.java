package com.acme.a3csci3130;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class BusinessContact implements Serializable, DatabaseReference.CompletionListener {

    public  String bid;
    public  String name;
    public  String primaryB;
    public  String addr;
    public  String pv;

    public BusinessContact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public BusinessContact(String bid, String name, String primaryB, String addr, String pv){
        this.bid = bid;
        this.name = name;
        this.primaryB = primaryB;
        this.addr = addr;
        this.pv = pv;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("bid", bid);
        result.put("name", name);
        result.put("primary business", primaryB);
        result.put("address", addr);
        result.put("province", pv);

        return result;
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

    }
}
