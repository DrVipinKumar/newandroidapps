package edu.kiet.contentproviderex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.Manifest.permission.READ_CONTACTS;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    EditText name;
    Button fetch;
    boolean check=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.txtName);
        fetch = findViewById(R.id.btnFetch);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissions()) {
                    if(check==true) {
                        LoaderManager.getInstance(MainActivity.this).initLoader(1, null, MainActivity.this);
                        check=false;
                    }else
                    {
                        LoaderManager.getInstance(MainActivity.this).restartLoader(1,null, MainActivity.this);
                    }
                } else {
                    requestPermission();
                }
            }
        });
    }

    void displayMsg(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    boolean checkPermissions() {
        int check=ContextCompat.checkSelfPermission(getApplicationContext(),READ_CONTACTS);
        return check== PackageManager.PERMISSION_GRANTED;
    }

    void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{READ_CONTACTS},30);
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=?";
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        if (id == 1) {
            return new CursorLoader(MainActivity.this, uri, projection, null,null, null);
        }

        return null;

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        StringBuilder info = new StringBuilder();
        if (data != null && data.getCount() > 0) {
            while (data.moveToNext()) {
                info.append("Name=>" + data.getString(0) + "\t\t Mobile=>" + data.getString(1) + "\n\n");
            }
            displayMsg("Contact Information", info.toString());
        } else {
            displayMsg("Error", "Data not permitted");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

