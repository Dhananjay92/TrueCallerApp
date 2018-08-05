package com.m7amdelbana.android.trueCallerApp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.m7amdelbana.android.trueCallerApp.R;
import com.m7amdelbana.android.trueCallerApp.activities.MainActivity;
import com.m7amdelbana.android.trueCallerApp.models.Contact;
import com.m7amdelbana.android.trueCallerApp.sqlDatabase.DatabaseHandler;
import com.m7amdelbana.android.trueCallerApp.utilities.InputValidation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FormFragment extends Fragment {

    @BindView(R.id.form_fullName_text_input_layout)
    TextInputLayout nameTIL;
    @BindView(R.id.form_company_text_input_layout)
    TextInputLayout companyTIL;
    @BindView(R.id.form_phone_text_input_layout)
    TextInputLayout phoneTIL;
    @BindView(R.id.form_notes_text_input_layout)
    TextInputLayout notesTIL;
    @BindView(R.id.form_sign_up_button)
    Button signUpButton;
    Unbinder unbinder;

    DatabaseHandler dbHandler;

    public FormFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        unbinder = ButterKnife.bind(this, view);

        dbHandler = new DatabaseHandler(getContext());

        return view;
    }

    private void saveInDatabase() {

        String name = nameTIL.getEditText().getText().toString().trim();
        String company = companyTIL.getEditText().getText().toString().trim();
        String phone = phoneTIL.getEditText().getText().toString().trim();
        String notes = notesTIL.getEditText().getText().toString().trim();

        Contact contact = new Contact(name, company, phone, notes);
        dbHandler.addContact(contact);

        Toast.makeText(getContext(), "Contact is added.", Toast.LENGTH_SHORT).show();

        getActivity().finish();
        startActivity(getActivity().getIntent());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.form_sign_up_button)
    public void onViewClicked() {

        InputValidation validation = new InputValidation();

        if (validation.isFormValidation(nameTIL, companyTIL, phoneTIL, notesTIL)) {

            saveInDatabase();
        }
    }
}
