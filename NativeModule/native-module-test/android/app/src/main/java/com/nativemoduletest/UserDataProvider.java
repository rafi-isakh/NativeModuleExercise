package com.nativemoduletest;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.nativemoduletest.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage contact
 */
public class UserDataProvider {

    private Context context;

    public UserDataProvider(Context context) {
        this.context = context;
    }

    public List<UserData> searchContactFromPhonebook(String query) {
        String selection = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ? OR " + ContactsContract.CommonDataKinds.Phone.NUMBER + " LIKE ?";
        String[] searchArg = new String[]{"%" + query + "%", "%" + query + "%"};
        return getAllContactFromPhonebook(selection, searchArg);
    }

    /**
     * Get all contacts from phone book
     * @param selection
     * @param selectionArg
     * @return List<InvitableProfile>
     */
    private List<UserData> getAllContactFromPhonebook(String selection, String[] selectionArg) {
        List<UserData> contacts = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, selection, selectionArg,
                "upper("+ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + ") ASC");

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // get important properties
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace("-","").replace(" ", "");
                StringBuilder sb = new StringBuilder(phone);
                if (sb.length() > 0) {
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)) {
                        contacts.add(new UserData(id, name, phone));
                    }
                }
            }
            cursor.close();
        }

        return contacts;
    }
}
