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

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAth;
   private  EditText userName, Surname, number;
   private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

       userName = findViewById(R.id.name_id);
       Surname = findViewById(R.id.surname_id);
       number = findViewById(R.id.phone_number_id);
       register = findViewById(R.id.sign_btn);

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
               SaveToDataBase();
           }
       });


    }

    private void SaveToDataBase()
    {
        String name = userName.getText().toString();
        String secondName = Surname.getText().toString();
        String phone = number.getText().toString();


        if (name.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter your Name",Toast.LENGTH_SHORT).show();
        }else if (phone.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter your phone number",Toast.LENGTH_SHORT).show();
        }else if (secondName.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter your phone surname",Toast.LENGTH_SHORT).show();
        }
        else
        {
            DatabaseReference User_ref= FirebaseDatabase.getInstance().getReference().child("Users");
            User_ref.orderByChild("phoneNo:").equalTo(phone).addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(DataSnapshot dataSnapshot){
                    if(dataSnapshot.exists())
                    {
                        Toast.makeText(getApplicationContext(),"This User Exist",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this,LogIn_Activity.class);
                        startActivity(intent);

                    }else
                    {
                        Map<String, String> map = new HashMap<>();
                        map.put("UserName:",name);
                        map.put("Surname:",secondName);
                        map.put("phoneNo:",phone);
                        User_ref.push().setValue(map);

                        Intent intent =new Intent(SignUpActivity.this, OTP_Activity.class);
                        intent.putExtra("phone", phone);
                        intent.putExtra("name",name);
                        startActivity(intent);

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
