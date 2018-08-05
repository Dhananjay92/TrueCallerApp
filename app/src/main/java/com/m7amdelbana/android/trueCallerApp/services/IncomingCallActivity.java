package com.m7amdelbana.android.trueCallerApp.services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.widget.TextView;

import com.m7amdelbana.android.trueCallerApp.R;
import com.m7amdelbana.android.trueCallerApp.sqlDatabase.DatabaseHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public class IncomingCallActivity extends Activity {

    @BindView(R.id.incoming_call_textView)
    TextView phoneTextView;
    @BindView(R.id.incoming_contactName_textView)
    TextView nameTextView;

    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        setContentView(R.layout.activity_incomming_call);
        ButterKnife.bind(this);

        dbHandler = new DatabaseHandler(this);

        String number = getIntent().getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        phoneTextView = findViewById(R.id.incoming_call_textView);
        nameTextView = findViewById(R.id.incoming_contactName_textView);
        phoneTextView.setText(number);

        String name = dbHandler.getNameByPhone(number);

        if (!name.equals("0")) {
            nameTextView.setText(name);
        } else {
            nameTextView.setText(R.string.anonymous);
        }
    }
}
