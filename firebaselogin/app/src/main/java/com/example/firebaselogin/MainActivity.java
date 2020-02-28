package com.example.firebaselogin;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
 private Button Login;
 private TextView forgot,register;
 private TextInputEditText username,password;
 private DatabaseReference reference;
 private String name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = findViewById(R.id.btn_loigin);
        forgot = findViewById(R.id.txt_forgot);
        register = findViewById(R.id.txt_register);
        username = findViewById(R.id.edt_loginMail);
        password = findViewById(R.id.edt_loginpass);
        reference = FirebaseDatabase.getInstance().getReference();
        Login.setOnClickListener(this);
        forgot.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        name = username.getText().toString().trim();
        pass = password.getText().toString().trim();
        switch (id){
            case R.id.btn_loigin :
                Query uname= FirebaseDatabase.getInstance().getReference().child("Login").orderByChild("userName").equalTo(name);
                uname.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() > 0) {
                            Query ps =  FirebaseDatabase.getInstance().getReference().child("Login").orderByChild("password").equalTo(pass);
                            ps.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getChildrenCount()>0){
                                        Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
                                        intent.putExtra("username",name);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                        else   Toast.makeText(MainActivity.this,"userName doesn't Exists",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case R.id.txt_forgot :
                startActivity(new Intent(MainActivity.this,ForgotActivity.class));
                break;
            case R.id.txt_register :
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                break;
        }
    }
}
