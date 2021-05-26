package com.william.loginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GuardiaActivity extends AppCompatActivity {

    private Button mButtonSignOut;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardia);

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