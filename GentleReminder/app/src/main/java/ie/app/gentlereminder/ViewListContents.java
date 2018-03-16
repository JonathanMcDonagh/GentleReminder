package ie.app.gentlereminder;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ViewListContents extends Activity {

    //Variables
    Button addTask;
    Button clearList;
    DatabaseHelper myDB;

    @Override //OnCreateMethod
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlistcontents);


        final ListView listView;
        listView = findViewById(R.id.listView);
        addTask = findViewById(R.id.addTask);
        clearList = findViewById(R.id.clearList);
        myDB = new DatabaseHelper(this);
        //populate an ArrayList<String> from the database and then view it
        //ArrayList to store the list items
        final ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        //If there is no data in the table
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                //Adds the input from the user into a array list and stores it in the database
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }

        //Brings you back to mainActivity
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewListContents.this, MainActivity.class);
                startActivity(intent);

            }
        });

        //Clears the database
        clearList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                myDB.deleteAll();
                listView.setAdapter(null); //Clears list on screen
            }
        });




    }
}