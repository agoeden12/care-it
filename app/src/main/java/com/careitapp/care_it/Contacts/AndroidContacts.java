package com.careitapp.care_it.Contacts;

import android.widget.ListView;

import java.io.Serializable;

public class AndroidContacts implements Serializable{

    private String contactName = "";
    private String phoneNumber = "";
    //private ListView list_view_contacts;

    public AndroidContacts() {

    }

    public AndroidContacts(String contactName, String phoneNumber) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        //this.list_view_contacts = list_view_contacts;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public ListView getList_view_contacts() {
//        return list_view_contacts;
//    }
//
//    public void setList_view_contacts(ListView list_view_contacts) {
//        this.list_view_contacts = list_view_contacts;
//    }

    @Override
    public String toString() {
        return contactName + "\n"  + phoneNumber ;
    }

}
