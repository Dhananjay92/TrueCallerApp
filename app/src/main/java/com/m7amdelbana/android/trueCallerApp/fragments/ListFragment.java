package com.m7amdelbana.android.trueCallerApp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m7amdelbana.android.trueCallerApp.R;
import com.m7amdelbana.android.trueCallerApp.adapter.ContactAdapter;
import com.m7amdelbana.android.trueCallerApp.models.Contact;
import com.m7amdelbana.android.trueCallerApp.sqlDatabase.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListFragment extends Fragment {

    @BindView(R.id.contacts_recyclerView)
    RecyclerView contactsRecyclerView;
    Unbinder unbinder;

    private List<Contact> contacts;
    private ContactAdapter contactAdapter;
    DatabaseHandler dbHandler;

    public ListFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        dbHandler = new DatabaseHandler(getContext());

        contacts = new ArrayList<>();
        contacts = dbHandler.getAllContacts();

        contactsRecyclerView.setHasFixedSize(true);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        contactAdapter = new ContactAdapter(getContext(), contacts);
        contactsRecyclerView.setAdapter(contactAdapter);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
