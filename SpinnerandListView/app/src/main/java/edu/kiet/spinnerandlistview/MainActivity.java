package edu.kiet.spinnerandlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> data;
    //Spinner spinner;
    ListView listview;
    ArrayAdapter<String> adapter;
   // String data2[]={"C languages","C++ languages","Java languages","Python languages","Kotlin languages"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // spinner=findViewById(R.id.spinner);
        listview=findViewById(R.id.listview);
        data=new ArrayList<>();
        data.add("C languages");
        data.add("C++ languages");
        data.add("Java languages");
        data.add("Python languages");
        data.add("Kotlin languages");
        adapter =new ArrayAdapter<>(getApplicationContext(), R.layout.mylistviewlayout,R.id.textView,data);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),adapter.getItem(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });
        /*
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),adapter.getItem(position).toString(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }
}