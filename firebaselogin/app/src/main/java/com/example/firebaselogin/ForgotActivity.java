package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForgotActivity extends AppCompatActivity {
private Button search,update;
private EditText name,pass;
private TextView uname;
private String id;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        search = findViewById(R.id.btn_search);
        update = findViewById(R.id.btn_update);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Login");
        name = findViewById(R.id.e_name);
        pass = findViewById(R.id.u_pass);
        uname =findViewById(R.id.up_name);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = databaseReference.orderByChild("userName").startAt(name.getText().toString()).endAt(name.getText().toString() + "\uf8ff");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot artistSnapshot: dataSnapshot.getChildren()){
                            User da= artistSnapshot.getValue(User.class);
                            id = da.getId();
                        }
                        uname.setText(name.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(id);
                startActivity(new Intent(ForgotActivity.this,MainActivity.class));
            }
        });

    }

    private void updateData(String id1) {
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Login").child(id1);
        User user = new User(id1,name.getText().toString(),pass.getText().toString());
        dr.setValue(user);
       // use when u want to delete data
        //dr.removeValue();
        Toast.makeText(ForgotActivity.this,"Price Updated Successfully",Toast.LENGTH_SHORT).show();
    }
}
