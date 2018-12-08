package org.colibrisoft.colibri;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;


import com.hbb20.CountryCodePicker;



public class Phone extends AppCompatActivity {
    private Button phone_button;
    private CountryCodePicker ccp;
    private EditText phone_num;
    protected String phoneNum;
    private TextView mPhoneError;
    private String FullNameVal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        phone_button=  (Button) findViewById(R.id.phone_button);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        phone_num = (EditText) findViewById(R.id.phone_num);
        mPhoneError = (TextView) findViewById(R.id.invalid_phone);

        FullNameVal = getIntent().getExtras().getString("FullName");


}


    public void verify(View v){
        Intent i = new Intent(this, Verification.class);
        ccp.registerCarrierNumberEditText(phone_num);

        if(ccp.isValidFullNumber()){
            i.putExtra("Value", ccp.getFormattedFullNumber());
            i.putExtra("UserName", FullNameVal);
            startActivity(i);
            finish();
        } else {
                    phone_num.setError("Enter valid phone number");
                    phone_num.requestFocus();

        }
    }
}
