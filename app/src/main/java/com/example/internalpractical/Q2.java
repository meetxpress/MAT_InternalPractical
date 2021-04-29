package com.example.internalpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class Q2 extends AppCompatActivity {
    EditText q2Id, q2Name, q2Age, q2Sal, q2Dept;
    Button btnInsert, btnDisplay, btnUpdate, btnDelete;
    DbHelper dh;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2);

        q2Id = findViewById(R.id.q2Id);
        q2Name = findViewById(R.id.q2Name);
        q2Age = findViewById(R.id.q2Age);
        q2Sal = findViewById(R.id.q2Sal);
        q2Dept = findViewById(R.id.q2Dept);

        btnInsert = findViewById(R.id.btnInsert);
        btnDisplay = findViewById(R.id.btnDisplay);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

        dh = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dh.getReadableDatabase();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = q2Name.getText().toString();
                String sage = q2Age.getText().toString();
                String ssal = q2Sal.getText().toString();
                String sdept = q2Dept.getText().toString();

                if (sname == "" || sage == "" || ssal == "" || sdept == "") {
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                } else {
                    String i = dh.insert(sname, sage, ssal, sdept) == true ? "Inserted Successfully" : "Something went Wrong";
                    Log.d("msg", i);
                    Toast.makeText(getApplicationContext(), i, Toast.LENGTH_LONG).show();
                    q2Name.setText("");
                    q2Age.setText("");
                    q2Dept.setText("");
                    Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dh = new DbHelper(getApplicationContext());
                Cursor c = dh.displayAll();
                if (c.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer sb = new StringBuffer();
                while (c.moveToNext()) {
                    sb.append("Student No: " + c.getString(0) + "\n");
                    sb.append("Student Name: " + c.getString(1) + "\n");
                    sb.append("Student Age: " + c.getString(2) + "\n");
                    sb.append("Student Address: " + c.getString(3) + "\n\n");
                }
                AlertDialog.Builder adb = new AlertDialog.Builder(Q2.this);
                adb.setCancelable(true)
                        .setTitle("Registered Students")
                        .setMessage(sb.toString())
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                startActivity(new Intent(getApplicationContext(), Q2.class));
                            }
                        })
                        .show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ud_q2Id = Integer.parseInt(q2Id.getText().toString());
                if (flag == 0) {
                    dh = new DbHelper(getApplicationContext());
                    Cursor c = dh.display(ud_q2Id);

                    if (c.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_SHORT).show();
                        q2Name.setText("");
                        q2Age.setText("");
                        q2Dept.setText("");
                    } else {
                        c.moveToNext();
                        q2Name.setText(c.getString(1));
                        q2Age.setText(c.getString(2));
                        q2Sal.setText(c.getString(3));
                        q2Dept.setText(c.getString(4));
                        flag = 1;
                        btnUpdate.setText("Update Now");
                    }
                } else {
                    String ud_q2Name = q2Name.getText().toString();
                    int ud_q2Aage = Integer.parseInt(q2Age.getText().toString());
                    int ud_q2Sal = Integer.parseInt(q2Sal.getText().toString());
                    String ud_q2dept= q2Dept.getText().toString();

                    if (q2Id.getText().toString() == "" || ud_q2Name == "" || ud_q2dept== "") {
                        Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean b = dh.update(ud_q2Id, ud_q2Name, ud_q2Aage, ud_q2Sal, ud_q2dept);
                        if (b == true) {
                            flag = 0;
                            btnUpdate.setText("Fetch Data");
                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                            q2Id.setText("");
                            q2Name.setText("");
                            q2Age.setText("");
                            q2Dept.setText("");
                        }
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ud_stdno = Integer.parseInt(q2Id.getText().toString());
                if (q2Id.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "Enter Id.", Toast.LENGTH_SHORT).show();
                } else {
                    boolean b = dh.delete(ud_stdno);
                    if (b == true) {
                        q2Id.setText("");
                        q2Name.setText("");
                        q2Age.setText("");
                        q2Dept.setText("");
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}