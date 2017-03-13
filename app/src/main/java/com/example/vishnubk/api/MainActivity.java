package com.example.vishnubk.api;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactsAdapter contactsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final List<Contacts>contact=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        contactsAdapter=new ContactsAdapter(this,contact);
        recyclerView.setAdapter(contactsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        APIRequestBuilder requestBuilder = new APIRequestBuilder(MainActivity.this);

        new APIClient().execute(this, requestBuilder, new APIClient.APIResultCallback() {
            @Override
            public void onFinish(JSONObject object) {
                    try {
                        //JSONObject data = object.getJSONObject(String.valueOf(object));
                        JSONArray contacts = object.getJSONArray("contacts");
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject obj = contacts.getJSONObject(i);
                            Contacts data=new Contacts();
                            data.name = obj.getString("name");
                            data.email = obj.getString("email");
                            data.address = obj.getString("address");
                            data.gender = obj.getString("gender");
                            contact.add(data);

                        }
                       contactsAdapter.notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }

            @Override
            public void onNoInternet() {
                Toast.makeText(MainActivity.this, "no internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
