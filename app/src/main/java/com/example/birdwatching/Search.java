package com.example.birdwatching;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Search extends AppCompatActivity implements View.OnClickListener {

    EditText editZipcode;
    TextView textBird, textPerson;
    Button buttonSearch, buttonReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editZipcode = findViewById(R.id.editZipcode);
        textBird = findViewById(R.id.textBird);
        textPerson = findViewById(R.id.textPerson);
        buttonReport = findViewById(R.id.buttonReport);
        buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(this);
        buttonReport.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Birds");

        if (buttonSearch == view) {

            int findZipcode = Integer.parseInt(editZipcode.getText().toString());
            myRef.orderByChild("zipcode").equalTo(findZipcode).addChildEventListener
                    (new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            String findkey = dataSnapshot.getKey();
                            Bird foundBird = dataSnapshot.getValue(Bird.class);
                            String findbird = foundBird.birdname;
                            Bird foundPerson = dataSnapshot.getValue(Bird.class);
                            String findperson = foundPerson.personname;

                            textBird.setText(findbird);
                            textPerson.setText(findperson);



                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

        } else if (buttonReport == view) {
            Intent createIntent = new Intent(this, MainActivity.class);
            startActivity(createIntent);
        }


    }
}

