package com.william.loginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class GuardiaActivity extends AppCompatActivity {

    private Button mButtonSignOut;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardia);
        mAuth = FirebaseAuth.getInstance();
        mButtonSignOut = (Button) findViewById(R.id.btnSignOut);
        mButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(GuardiaActivity.this, MainActivity.class ));
                finish();
            }
        });
    }
}