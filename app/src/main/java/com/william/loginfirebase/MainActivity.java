package com.william.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextName;
    private EditText mEditTextCont;
    private EditText mEditTextMail;
    private Button mButtonRegister;

    //Variables de los datos que se van a registrar
    private String name = "";
    private String mail = "";
    private String cont = "";

    //Firebase
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextCont = (EditText) findViewById(R.id.editTextCont);
        mEditTextMail = (EditText) findViewById(R.id.editTextMail);
        mButtonRegister = (Button) findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mEditTextName.getText().toString();
                mail = mEditTextMail.getText().toString();
                cont = mEditTextCont.getText().toString();

                if (!name.isEmpty() && !mail.isEmpty() && !cont.isEmpty()) {
                    if (cont.length() >= 6) {

                    } else {
                        Toast.makeText(MainActivity.this, "La clave debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                    registerUser();
                } else {
                    Toast.makeText(MainActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
        private void registerUser(){
        mAuth.createUserWithEmailAndPassword(mail, cont).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("Name", name);
                    map.put("Email", mail);
                    map.put("Password", cont);

                    String id = mAuth.getCurrentUser().getUid();


                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull  Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                                finish();

                            }else{
                                Toast.makeText(MainActivity.this, "No se crearon los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(MainActivity.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
                
            }
        });

        }
    }
