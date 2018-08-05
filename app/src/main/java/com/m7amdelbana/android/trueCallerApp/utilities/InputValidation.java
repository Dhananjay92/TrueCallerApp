package com.m7amdelbana.android.trueCallerApp.utilities;

import android.support.design.widget.TextInputLayout;

public class InputValidation {

    public boolean isFormValidation(TextInputLayout nameTIL, TextInputLayout companyTIL, TextInputLayout phoneTIL, TextInputLayout notesTIL) {

        String name = nameTIL.getEditText().getText().toString().trim();
        String company = companyTIL.getEditText().getText().toString().trim();
        String phone = phoneTIL.getEditText().getText().toString().trim();
        String notes = notesTIL.getEditText().getText().toString().trim();

        if (name.isEmpty() || company.isEmpty() || phone.isEmpty() || notes.isEmpty()) {

            if (name.isEmpty()) {
                nameTIL.setError("Enter Name!");
            }
            if (company.isEmpty()) {
                companyTIL.setError("Enter Company Name!");
            }
            if (phone.isEmpty()) {
                phoneTIL.setError("Enter Phone!");
            }
            if (notes.isEmpty()) {
                notesTIL.setError("Enter Notes!");
            }
            return false;
        }
        return true;
    }
}
