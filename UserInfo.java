package org.colibrisoft.colibri;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class UserInfo extends AppCompatActivity {

    protected EditText user_first_name;
    protected String Name;
    protected EditText User_Name1;
    protected EditText User_Name2;
    protected String FirstName, LastName;
    protected String Full_Number;
    private TextView mNameError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        user_first_name= (EditText) findViewById(R.id.user_first_name);
        mNameError = (TextView) findViewById(R.id.name_error);
    }
    public void phone_num(View v) {
        Intent j = new Intent(this, Phone.class);
        Name = user_first_name.getText().toString();
        User_Name1 = (EditText) findViewById(R.id.user_first_name);
        User_Name2 = (EditText) findViewById(R.id.user_last_name);
        FirstName = User_Name1.getText().toString();
        LastName = User_Name2.getText().toString();
        /*Full_Number = getIntent().getExtras().getString("Phone_Nav");
        j.putExtra("Phone_Nav2",Full_Number);*/


        if (!(FirstName.isEmpty())) {
            j.putExtra("FullName", FirstName + " " + LastName);
            startActivity(j);
            finish();
        } else {

            mNameError.setText("First name is required");
            mNameError.setVisibility (View.VISIBLE);
            mNameError.requestFocus();

        }
    }




}
