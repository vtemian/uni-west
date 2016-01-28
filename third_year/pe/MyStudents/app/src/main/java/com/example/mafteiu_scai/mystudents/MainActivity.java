package com.example.mafteiu_scai.mystudents;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

    EditText editEnrollno, editName, editMarks, editPhone;
    Button btnAdd, btnDelete, btnModify, btnSearch, btnViewAll, btnNext, btnPrev, btnFirst, btnLast;
    SQLiteDatabase db;
    int position;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        editEnrollno = (EditText) findViewById(R.id.editEnrollno);
        editName = (EditText) findViewById(R.id.editName);
        editMarks = (EditText) findViewById(R.id.editMarks);
        editPhone = (EditText) findViewById(R.id.editPhone);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnModify = (Button) findViewById(R.id.btnModify);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnViewAll = (Button) findViewById(R.id.btnViewAll);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnLast = (Button) findViewById(R.id.btnLast);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnFirst.setOnClickListener(this);
        btnLast.setOnClickListener(this);

        db = openOrCreateDatabase("MADStudentsDB1", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(enrollno VARCHAR,name VARCHAR,marks VARCHAR, phone VARCHAR);");

        Cursor c = db.rawQuery("SELECT * FROM student", null);
        if (c.getCount() == 0) {
            showMessage("Error", "No students found in database");
            clearText();
        }
        else {
            position=0;
            c.moveToPosition(position);
            editEnrollno.setText(c.getString(0));
            editName.setText(c.getString(1));
            editMarks.setText(c.getString(2));
        }
    }

    public void onClick(View view) {
        if (view == btnAdd) {
            if (editEnrollno.getText().toString().trim().length() == 0 ||
                    editName.getText().toString().trim().length() == 0 ||
                    editName.getText().toString().trim().length() == 0 ||
                    editMarks.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter all student data");
                return;
            }

            db.execSQL("INSERT INTO student VALUES('" + editEnrollno.getText() + "','" + editName.getText() +
                    "','" + editMarks.getText() + "', '" + editPhone + "');");
            showMessage("Success", "Record added");
            clearText();
            position=0;
        }
        if (view == btnDelete) {
            if (editEnrollno.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Student Enroll Number");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student WHERE enrollno='" + editEnrollno.getText() + "'", null);
            if (c.moveToFirst()) {
                db.execSQL("DELETE FROM student WHERE enrollno='" + editEnrollno.getText() + "'");
                showMessage("Success", "Record Deleted");
            } else {
                showMessage("Error", "Invalid Enroll Number");
            }
            //you must put the previsious record
           // clearText();
            position--;
        }
        if (view == btnModify) {
            if (editEnrollno.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Enroll Number");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student WHERE enrollno='" + editEnrollno.getText() + "'", null);
            if (c.moveToFirst()) {
                db.execSQL("UPDATE student SET name='" + editName.getText() + "',marks='" + editMarks.getText() + "', phone='" + editPhone.getText() +
                        "' WHERE enrollno='" + editEnrollno.getText() + "'");
                showMessage("Success", "Student Data Modified");
            } else {
                showMessage("Error", "Invalid Enroll Number");
            }
            //clearText();
        }
        if (view == btnSearch) {
            if (editEnrollno.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Student enroll number");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student WHERE enrollno='" + editEnrollno.getText() + "'", null);
            if (c.moveToFirst()) {
                editName.setText(c.getString(1));
                editMarks.setText(c.getString(2));
            } else {
                showMessage("Error", "Invalid Enroll Number");
                clearText();
            }
            position = c.getPosition();
        }
        if (view == btnViewAll) {
            Cursor c = db.rawQuery("SELECT * FROM student", null);
            if (c.getCount() == 0) {
                showMessage("Error", "No students found in database");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("Enroll Number: " + c.getString(0) + "\n");
                buffer.append("Name: " + c.getString(1) + "\n");
                buffer.append("Mark: " + c.getString(2) + "\n\n");
            }
            showMessage("MAD Student Data", buffer.toString());
        }
        if (view == btnNext) {

            Cursor c = db.rawQuery("SELECT * FROM student", null);
            position++;
            if (c.moveToPosition(position)) {
                editEnrollno.setText(c.getString(0));
                editName.setText(c.getString(1));
                editMarks.setText(c.getString(2));
            }
            else
                showMessage("Error", "End of database");
            return;
        }

        if (view == btnPrev) {

            Cursor c = db.rawQuery("SELECT * FROM student", null);
            position--;
            if (c.moveToPosition(position)) {
                editEnrollno.setText(c.getString(0));
                editName.setText(c.getString(1));
                editMarks.setText(c.getString(2));
            }
            else
                showMessage("Error", "Begin of database");
            return;
        }
        if (view == btnFirst) {

            Cursor c = db.rawQuery("SELECT * FROM student", null);
            position=0;
            if (c.moveToPosition(position)) {
                editEnrollno.setText(c.getString(0));
                editName.setText(c.getString(1));
                editMarks.setText(c.getString(2));
            }
            else
                showMessage("Error", "Begin of database");
            return;
        }
        if (view == btnLast) {

            Cursor c = db.rawQuery("SELECT * FROM student", null);
            position=c.getCount()-1;
            if (c.moveToPosition(position)) {
                editEnrollno.setText(c.getString(0));
                editName.setText(c.getString(1));
                editMarks.setText(c.getString(2));
            }
            else
                showMessage("Error", "End of database");
            return;
        }
    }

    public void showMessage(String title, String message) {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        editEnrollno.setText("");
        editName.setText("");
        editMarks.setText("");
        editPhone.setText("");
        editEnrollno.requestFocus();
    }
}
