package com.example.birdwatching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editBirdname, editZipcode, editPersonname;
    Button buttonSubmit, buttonGoToSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editBirdname = findViewById(R.id.editBirdname);
        editPersonname = findViewById(R.id.editPersonname);
        editZipcode = findViewById(R.id.editZipcode);

        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonGoToSearch = findViewById(R.id.buttonGoToSearch);

        buttonGoToSearch.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);

    }
;

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Birds");

        if (buttonSubmit == view) {
            Toast.makeText(this, "Thank You For Your Submission", Toast.LENGTH_SHORT).show();

            String createbirdname = editBirdname.getText().toString();
            int createzipcode = Integer.parseInt(editZipcode.getText().toString());
            String createpersonname = editPersonname.getText().toString();

            Bird mybird = new Bird(createbirdname, createzipcode, createpersonname);

            myRef.push().setValue(mybird);


        } else if (buttonGoToSearch == view) {
            Intent searchIntent = new Intent(this, Search.class);
            startActivity(searchIntent);
        }

    }
}