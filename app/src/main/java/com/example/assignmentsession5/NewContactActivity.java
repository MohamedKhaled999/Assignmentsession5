package com.example.assignmentsession5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewContactActivity extends AppCompatActivity implements View.OnClickListener {
private EditText name,phone;
private Button save;
private ContactsDbHelper contactsDbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        name=(EditText)findViewById(R.id.enter_name);
        phone=(EditText)findViewById(R.id.enter_phone);
        save=(Button)findViewById(R.id.save);
contactsDbHelper=new ContactsDbHelper(this);
save.setOnClickListener(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("new Contact");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.save){
            String NAME=name.getText().toString();
            String PHONE=phone.getText().toString();
            if(!NAME.isEmpty() &&!PHONE.isEmpty()){
            contactsDbHelper.createContact(NAME,PHONE);
finish();}
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
