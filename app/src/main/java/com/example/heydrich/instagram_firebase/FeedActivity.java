package com.example.heydrich.instagram_firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.SSLContext;

public class FeedActivity extends AppCompatActivity {

    ArrayList<String> userEmailsFromFB;
    ArrayList<String> userImageFromFB;
    ArrayList<String> userCommentFromFB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    PostClass adapter;
    ListView listView;








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.add_post,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_post){
            Intent ıntent = new Intent(getApplicationContext(),UploadActivity.class);
            startActivity(ıntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        userEmailsFromFB = new ArrayList<String>();
        userCommentFromFB = new ArrayList<String>();
        userImageFromFB = new ArrayList<String>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();


        adapter = new PostClass(userEmailsFromFB,userImageFromFB,userCommentFromFB,this);

        listView = findViewById(R.id.listView);

        listView.setAdapter(adapter);
        getDataFromFirebase();

    }
    protected void getDataFromFirebase(){

        DatabaseReference newReference = firebaseDatabase.getReference("Posts");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

/*                 System.out.println("children"+dataSnapshot.getChildren());
                System.out.println("key"+dataSnapshot.getKey());
                System.out.println("value"+dataSnapshot.getValue());

                */

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    HashMap<String,String> hashMap = (HashMap<String, String>) ds.getValue();
                    userEmailsFromFB.add(hashMap.get("userEmail"));
                    userImageFromFB.add(hashMap.get("dowloandURL"));
                    userCommentFromFB.add(hashMap.get("comment"));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
