package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class AlredyRegister extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText username1;
    EditText password1;

    FirebaseAuth.AuthStateListener mAuthListner;

    GoogleApiClient mGoogleApiClient;
    SignInButton buttonG;

    private static final String TAG ="NO_USE";

    private static final int RC_SIGN_IN=1;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(mAuthListner);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alredy_register);
        username1=(EditText)findViewById(R.id.editText_alredy_register);
        password1=(EditText)findViewById(R.id.editText2_alredy_registered);
        firebaseAuth=FirebaseAuth.getInstance();


        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(AlredyRegister.this,Donor_or_Receiver.class));
                }
            }
        };

        buttonG = (SignInButton)findViewById(R.id.buttonG);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(AlredyRegister.this , "Connection Failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



        if(firebaseAuth.getCurrentUser()!=null)
        {

            finish();
            startActivity(new Intent(this,Donor_or_Receiver.class));
        }

    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(AlredyRegister.this, "Result REceived" , Toast.LENGTH_SHORT).show();
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Toast.makeText(AlredyRegister.this, "rESULT" + result , Toast.LENGTH_LONG).show();

            if (result.isSuccess()) {

                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
                Toast.makeText(AlredyRegister.this, "success" , Toast.LENGTH_SHORT).show();
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(AlredyRegister.this, "Failed" , Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(AlredyRegister.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });

    }


    public  void onRegister1(View v)
    {
        String user1=username1.getText().toString();
        String pass1=password1.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(user1,pass1)
                .addOnCompleteListener(AlredyRegister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isComplete())
                        {
                            finish();
                            Intent in1=new Intent(AlredyRegister.this,Donor_or_Receiver.class);
                            startActivity(in1);

                        }
                        else {
                            Toast.makeText(AlredyRegister.this,"not done",Toast.LENGTH_SHORT).show();



                        }


                    }
                });




    }

    public  void goalredy(View view)
    {
        startActivity(new Intent(AlredyRegister.this,Register_page.class));
    }





}
