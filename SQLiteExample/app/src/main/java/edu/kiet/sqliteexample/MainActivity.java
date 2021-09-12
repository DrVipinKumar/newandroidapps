package edu.kiet.sqliteexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText rollno, name, course;
    Button insert, update, display, delete;
    MySQLiteDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollno=findViewById(R.id.txtRollNo);
        name=findViewById(R.id.txtName);
        course=findViewById(R.id.txtCourse);
        insert=findViewById(R.id.btnInsert);
        display=findViewById(R.id.btnDisplay);
        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);
        mydb=new MySQLiteDatabase(getApplicationContext());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int roll_no;
                if(!rollno.getText().toString().isEmpty())
                {
                    roll_no=Integer.parseInt(rollno.getText().toString());
                    long check=mydb.deleteData(roll_no);
                    if(check>=1)
                    {
                        displayMsg("Information","Data is deleted");
                    }else
                    {
                        displayMsg("Error","Data is not delete");
                    }
                }else
                {
                    displayMsg("Error","Enter roll no to delete");
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int roll_no;
                String sname, scourse;
                if(!rollno.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !course.getText().toString().isEmpty()) {
                    roll_no=Integer.parseInt(rollno.getText().toString());
                    sname=name.getText().toString();
                    scourse=course.getText().toString();
                    long check = mydb.updateData(roll_no,sname,scourse);
                    if(check>=1)
                    {
                        displayMsg("Information","Data is updated");
                    }else
                    {
                        displayMsg("Error","Data not updated");
                    }
                }else
                {
                    displayMsg("Error","Insert Information First");
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data=mydb.getDisplay();
                StringBuilder mydata=new StringBuilder();
                if(data.getCount()>0)
                {
                    while(data.moveToNext())
                    {
                        mydata.append("Roll No=>"+data.getInt(0)+"\t");
                        mydata.append("Name=>"+data.getString(1)+"\t");
                        mydata.append("Course=>"+data.getString(2)+"\n\n");
                    }
                    displayMsg("Your Data",mydata.toString());

                }
                else
                {
                    displayMsg("Error","Data not found");
                }
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int roll_no;
                String sname,scourse;
                long check;
                if(!rollno.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !course.getText().toString().isEmpty()) {
                    roll_no = Integer.parseInt(rollno.getText().toString());
                    sname=name.getText().toString();
                    scourse=course.getText().toString();
                    check=mydb.insertData(roll_no,sname,scourse);
                    if(check==-1)
                    {
                        displayMsg("Error","Data not Inserted");
                    }
                    else
                    {
                        displayMsg("Information","Data is Inserted");
                    }
                }
                else
                {
                    displayMsg("Error","Enter information frist");
                }
            }
        });

    }
    void displayMsg(String title, String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(true);
        AlertDialog alert=builder.create();
        alert.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent i =new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}