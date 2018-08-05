package com.m7amdelbana.android.trueCallerApp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.m7amdelbana.android.trueCallerApp.R;
import com.m7amdelbana.android.trueCallerApp.activities.EditActivity;
import com.m7amdelbana.android.trueCallerApp.models.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> contacts;
    private Context context;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.contacts = contacts;
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Contact contact = contacts.get(position);

        holder.nameTextView.setText(contact.getName());
        String phone = "Phone: " + contact.getPhone();
        holder.phoneTextView.setText(phone);
        String company = "Company: " + contact.getCompany();
        holder.companyTextView.setText(company);
        String notes = "Notes: " + contact.getNotes();
        holder.notesTextView.setText(notes);
        holder.editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditActivity.class);
                i.putExtra("PHONE", contact.getPhone());
                i.putExtra("ID", contact.getID() + "");
                getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView phoneTextView;
        private TextView companyTextView;
        private TextView notesTextView;
        private ImageButton editImageButton;

        ViewHolder(View view) {
            super(view);

            nameTextView = view.findViewById(R.id.contact_name_textView);
            phoneTextView = view.findViewById(R.id.contact_phone_textView);
            companyTextView = view.findViewById(R.id.contact_company_textView);
            notesTextView = view.findViewById(R.id.contact_notes_textView);
            editImageButton = view.findViewById(R.id.contact_edit_imageButton);
        }
    }
}
