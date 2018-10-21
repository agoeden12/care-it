package com.careitapp.care_it.Contacts;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.careitapp.care_it.R;

import java.util.List;

public class Adapter_for_contacts extends ArrayAdapter<AndroidContacts> {
    private Context mContext;
    private List<AndroidContacts> listOfContacts;


    public Adapter_for_contacts(Context mContext, List<AndroidContacts> mContact) {
        super(mContext,0, mContact);
        this.mContext = mContext;
        this.listOfContacts = mContact;
    }

    public int getCount() {
        return listOfContacts.size();
    }


    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_contact, parent);

        }

        TextView textview_contact_Name= convertView.findViewById(R.id.contact_name);
        TextView textview_contact_TelefonNr= convertView.findViewById(R.id.phone_number);

        textview_contact_Name.setText(listOfContacts.get(position).getContactName());
        textview_contact_TelefonNr.setText(listOfContacts.get(position).getPhoneNumber());

        convertView.setTag(listOfContacts.get(position).getContactName());
        return convertView;

    }
}
