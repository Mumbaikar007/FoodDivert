package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register_page extends AppCompatActivity {

    EditText username;
    EditText password;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        firebaseAuth=FirebaseAuth.getInstance();
        username=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);

        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(this,Donor_or_Receiver.class));

        }


    }

    public void onRegister(View v)
    {
        String user=username.getText().toString();
        String pass=password.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isComplete())
                        {
                            Toast.makeText(Register_page.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            Intent in1=new Intent(Register_page.this,Donor_or_Receiver.class);

                            startActivity(in1);
                        }
                        else {
                            Toast.makeText(Register_page.this,"Not Registered Successfully",Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }
    public  void onAlredyRegister(View v)
    {

        Intent in=new Intent(this,AlredyRegister.class);
        startActivity(in);

    }



}
