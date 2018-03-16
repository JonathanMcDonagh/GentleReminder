package ie.app.gentlereminder;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {


    DatabaseHelper myDB;
    Button btnAdd,btnView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);




        //When the add button is selected it takes the string from the editText box
        //  and adds the data to the database but only if it is not empty, its displayed in the list view
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(editText.length()!= 0){
                    AddData(newEntry);
                    editText.setText("");
                }else{
                    //If the editText box is empty a toast will appear
                    Toast.makeText(MainActivity.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Allows the user to switch to the other activity
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(intent);

            }
        });


    }

    // leaves your back stack as it is, just puts your task (all activities) in background. Same as if user pressed Home button.
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    //method to add data to the database
    public void AddData(String newEntry) {

        boolean insertData;
        insertData = myDB.addData(newEntry);

        if(insertData){
            Toast.makeText(this, "Added to list!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong...", Toast.LENGTH_LONG).show();
        }
    }





}
