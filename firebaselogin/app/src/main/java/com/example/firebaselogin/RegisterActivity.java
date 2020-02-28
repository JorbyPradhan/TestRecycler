package com.example.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
private Button Register;
private TextInputEditText username,pass,confirmpass;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Register = findViewById(R.id.btn_register);
        username = findViewById(R.id.edt_registerMail);
        pass = findViewById(R.id.edt_reg_pass);
        confirmpass = findViewById(R.id.edt_con_pass);
        myRef = FirebaseDatabase.getInstance().getReference("Login");
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString())){
                    username.setError("Fill Data");
                    return;
                }
                if(TextUtils.isEmpty(pass.getText().toString())){
                    pass.setError("Fill Password");
                    return;
                }
                if(pass.getText().toString().trim().equals(confirmpass.getText().toString().trim())){
                    String id =myRef.push().getKey();
                    User data = new User(id,username.getText().toString().trim(),pass.getText().toString().trim());
                    myRef.child(id).setValue(data);
                    Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    confirmpass.setError("Password not match");
                    return;
                }
            }
        });
    }
}
