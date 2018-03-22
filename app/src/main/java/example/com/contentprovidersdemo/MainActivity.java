package example.com.contentprovidersdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<String> Contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchContacts();
    }

    private void fetchContacts() {
        Contacts=new ArrayList<>();
        //Authorities for contact App
//        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        Uri uri1= Uri.parse("content://com.mycontentprovider.PROVIDER");

        // What you want to access
//        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String[] projection = null;

        //What do you want to select
        String selection = null;

        String[] selectionArg = null;

        // how you want to sort data
        String SortOrder = "";

        ContentResolver resolver = getContentResolver();

        Cursor cursor = resolver.query(uri1, projection, selection, selectionArg, SortOrder);

        while (cursor.moveToNext()){

//            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String name=cursor.getString(cursor.getColumnIndex("myname"));

            Integer phone_number=cursor.getInt(cursor.getColumnIndex("myage"));

            Log.i("Contact List:","\n"+"Name:"+ name+"\n"+"Age"+phone_number);

            Contacts.add(name+"\n"+phone_number);

            ListView ls=(ListView)findViewById(R.id.listContacts);

            ls.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Contacts));

        }

    }


}
