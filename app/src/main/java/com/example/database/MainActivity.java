package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText id,nam,fnam,adres,educatio,phon,gende;
    Button sub,vie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DatabaseHelper(this);

        id=(EditText) findViewById(R.id.id);
        nam=(EditText) findViewById(R.id.name);
        fnam=(EditText) findViewById(R.id.fname);
        adres=(EditText) findViewById(R.id.adress);
        educatio=(EditText) findViewById(R.id.education);
        phon=(EditText) findViewById(R.id.phone);
        gende=(EditText) findViewById(R.id.gender);
sub=(Button) findViewById(R.id.sub);

        vie=(Button) findViewById(R.id.view);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametext=nam.getText().toString();
                String idtext=id.getText().toString();
                String fathernametext=fnam.getText().toString();
                String adresstext=adres.getText().toString();
                String gendertext=gende.getText().toString();
                String educationtext=educatio.getText().toString();
                String phonetext=phon.getText().toString();

                boolean checkinserteddata=db.insertdata(nametext,idtext,fathernametext,adresstext,gendertext,educationtext,phonetext);
              if(checkinserteddata==true)
                  Toast.makeText(MainActivity.this,"new entry inserted",Toast.LENGTH_SHORT).show();
              else
                  Toast.makeText(MainActivity.this,"new entry not inserted",Toast.LENGTH_SHORT).show();

            }

        });
vie.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Cursor res=db.getData();
        if(res.getCount()==0)
        {
            Toast.makeText(MainActivity.this,"no Entry exists",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Name :"+res.getString(1)+"\n");
            buffer.append("Registration number :"+res.getString(0)+"\n");
            buffer.append("Father name :"+res.getString(2)+"\n");
            buffer.append("Address :"+res.getString(3)+"\n");
            buffer.append("Gender :"+res.getString(4)+"\n");
            buffer.append("Education :"+res.getString(5)+"\n");
            buffer.append("Phone number :"+res.getString(6)+"\n\n");

        }
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Entries");
        builder.setMessage(buffer.toString());
        builder.show();
    }
});


    }
}