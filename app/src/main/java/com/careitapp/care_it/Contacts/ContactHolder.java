package com.careitapp.care_it.Contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.careitapp.care_it.R;

import org.w3c.dom.Text;

public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView contactName;
    private final TextView contactNumber;

    private AndroidContacts contact;
    private Context context;

    public ContactHolder(Context context, View itemView) {
        super(itemView);

        this.context = context;

        this.contactName = (TextView) itemView.findViewById(R.id.contact_name);
        this.contactNumber = (TextView) itemView.findViewById(R.id.phone_number);

        itemView.setOnClickListener(this);
    }

    public void bindContact(AndroidContacts contact) {
        this.contact = contact;
        this.contactName.setText(contact.getContactName());
        this.contactNumber.setText(contact.getContactName());
    }

    @Override
    public void onClick(View v) {
        if (this.contact != null) {
            //connect to caretaker page later on
            Toast.makeText(this.context, "Clicked on " + this.contact.getContactName(), Toast.LENGTH_SHORT ).show();
        }
    }
}
