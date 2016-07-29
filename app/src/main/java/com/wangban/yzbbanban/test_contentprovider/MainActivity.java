package com.wangban.yzbbanban.test_contentprovider;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    private List<String> contacts = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ListView contactView;
//    private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactView = (ListView) findViewById(R.id.lv_contacts);
        contacts = setData();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
        contactView.setAdapter(adapter);
    }

    private List<String> setData() {
        Cursor c = null;
        try {
            c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(name + "\n" + number);
//                Log.i("supergirl", "setData: "+name + "\n" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return contacts;

    }
}
