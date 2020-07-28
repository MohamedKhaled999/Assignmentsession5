package com.example.assignmentsession5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactClickListener, View.OnClickListener, TextWatcher {
    private List<Contact> contacts;
    private ContactsAdapter contactsAdapter;
    private ContactsDbHelper contactsDbHelper;
private Button button;
private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.search) ;
        editText=(EditText) findViewById(R.id.searchtext);
        button.setOnClickListener(this);
        editText.addTextChangedListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyler);
        contacts = new ArrayList<>();
        contactsDbHelper = new ContactsDbHelper(this);
        contactsAdapter = new ContactsAdapter(MainActivity.this, contacts, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(contactsAdapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contact:
                Intent intent = new Intent(this, NewContactActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                finish();
                return true;
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        Cursor cursor = contactsDbHelper.showContacts();
        while (!cursor.isAfterLast()) {
            contacts.add(new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();



        }
        contactsAdapter.notifyDataSetChanged();

    }


    @Override
    public void contactOnClickListener(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getPhone()));
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.search){
            showcontacts();

        }
    }

    public void showcontacts(){
        String key=editText.getText().toString();
if(!key.isEmpty()){
        contacts.clear();
        Cursor cursor = contactsDbHelper.showContacts();
        while (!cursor.isAfterLast()) {
            if(cursor.getString(1).contains(key)){
            contacts.add(new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));}
            cursor.moveToNext();



        }
        contactsAdapter.notifyDataSetChanged();}
else{
    onResume();
}
    }




    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        showcontacts();

    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}

