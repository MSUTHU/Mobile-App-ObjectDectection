package com.example.piedpiperd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn_Activity extends AppCompatActivity
{
    private EditText log_number;
    private TextView Forgot_link, create_account_link;
    private Button log_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        log_number = findViewById(R.id.phone_number_id_log);
        Forgot_link = findViewById(R.id.forgot_number_link);
        create_account_link = findViewById(R.id.new_user_link);
        log_btn = findViewById(R.id.log_btn);


        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Authenticate_User();

            }
        });
        Forgot_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LogIn_Activity.this,Forgot_number_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        create_account_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LogIn_Activity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void Authenticate_User()
    {
        String phone = log_number.getText().toString();
        if (phone.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter your phone number",Toast.LENGTH_SHORT).show();
        }else
            {
                DatabaseReference User_ref= FirebaseDatabase.getInstance().getReference().child("Users");
                User_ref.orderByChild("phoneNo:").equalTo(phone).addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        if(dataSnapshot.exists())
                        {
                            Intent intent = new Intent(LogIn_Activity.this,OTP_Activity.class);
                            intent.putExtra("phone",phone);
                            startActivity(intent);

                            SessionManagement session = new SessionManagement(getApplicationContext());
                            session.secondTime();

                        }else
                            {
                                Toast.makeText(getApplicationContext(),"The phone number that you entered is not registered",Toast.LENGTH_SHORT).show();
                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }
                });

            }
    }
}