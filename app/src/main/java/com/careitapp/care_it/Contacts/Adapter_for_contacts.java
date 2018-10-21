package com.careitapp.care_it.Contacts;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.careitapp.care_it.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_for_contacts extends RecyclerView.Adapter<Adapter_for_contacts.ContactHolder> {
    private List<AndroidContacts> listOfContacts;

    public class ContactHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.contact_name)
        TextView name;
        @BindView(R.id.phone_number)
        TextView phone;

        ContactHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public Adapter_for_contacts(List<AndroidContacts> listOfContacts) {
        this.listOfContacts = listOfContacts;
    }

    @Override
    @NonNull
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_contacts, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        AndroidContacts contact = this.listOfContacts.get(position);
        holder.name.setText(contact.getContactName());
        holder.phone.setText(contact.getPhoneNumber());
    }

    public int getItemCount() {
        return listOfContacts.size();
    }

}
