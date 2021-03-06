package com.example.yeohf.loginsystem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateMailPassActivity extends AppCompatActivity {
    private static final String TAG= "Update Mail Pass";
    private Button updateEmail;
    private Button updatePass;
    private EditText newPassword;
    private EditText newMail;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mail_pass);

        updateEmail=(Button)findViewById(R.id.btnUpdateEmail);
        updatePass= (Button)findViewById(R.id.btnUpdatePass);
        newPassword=(EditText)findViewById(R.id.etNewpass);
        newMail=(EditText)findViewById(R.id.etNewmail);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();



        updateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernewmail= newMail.getText().toString();
                Log.d(TAG,"Usernewmail:"+usernewmail);
                firebaseUser.updateEmail(usernewmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UpdateMailPassActivity.this, "Email Changed!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(UpdateMailPassActivity.this, "Emailchange Failed!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        updatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernewpass= newPassword.getText().toString();
                Log.d(TAG,"Usernewpass:"+usernewpass);
                firebaseUser.updatePassword(usernewpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UpdateMailPassActivity.this, "Password Changed!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(UpdateMailPassActivity.this, "Passwordchange Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });




    }
}
