package com.m7amdelbana.android.trueCallerApp.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.m7amdelbana.android.trueCallerApp.R;
import com.m7amdelbana.android.trueCallerApp.models.Contact;
import com.m7amdelbana.android.trueCallerApp.sqlDatabase.DatabaseHandler;
import com.m7amdelbana.android.trueCallerApp.utilities.InputValidation;

import java.util.IdentityHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditActivity extends AppCompatActivity {

    @BindView(R.id.edit_fullName_text_input_layout)
    TextInputLayout nameTIL;
    @BindView(R.id.edit_company_text_input_layout)
    TextInputLayout companyTIL;
    @BindView(R.id.edit_phone_text_input_layout)
    TextInputLayout phoneTIL;
    @BindView(R.id.edit_notes_text_input_layout)
    TextInputLayout notesTIL;
    @BindView(R.id.edit_button)
    Button editButton;
    @BindView(R.id.delete_button)
    Button deleteButton;

    DatabaseHandler dbHandler;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        dbHandler = new DatabaseHandler(this);

        getFromDatabase(getIntent().getStringExtra("PHONE"));

        ID = Integer.parseInt(getIntent().getStringExtra("ID"));
    }

    private void getFromDatabase(String phone) {

        Contact contact = dbHandler.getContactByPhone(phone);

        nameTIL.getEditText().setText(contact.getName());
        companyTIL.getEditText().setText(contact.getCompany());
        phoneTIL.getEditText().setText(phone);
        notesTIL.getEditText().setText(contact.getNotes());

    }

    @OnClick({R.id.edit_button, R.id.delete_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_button:

                InputValidation validation = new InputValidation();

                if (validation.isFormValidation(nameTIL, companyTIL, phoneTIL, notesTIL)) {

                    String name = nameTIL.getEditText().getText().toString().trim();
                    String company = companyTIL.getEditText().getText().toString().trim();
                    String phone = phoneTIL.getEditText().getText().toString().trim();
                    String notes = notesTIL.getEditText().getText().toString().trim();

                    Contact contact = new Contact(ID, name, company, phone, notes);

                    if (dbHandler.updateItem(contact)) {
                        Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.delete_button:

                dbHandler.deleteContactByID(ID);
                Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
