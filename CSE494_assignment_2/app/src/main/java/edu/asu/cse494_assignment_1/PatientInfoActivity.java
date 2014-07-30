package edu.asu.cse494_assignment_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class PatientInfoActivity extends Activity {

    private static Button btnSave;
    private static EditText editTextName, editTextIDNumber, editTextAge;
    private static RadioGroup radioGroupPtSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        initUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        radioGroupPtSex = (RadioGroup) findViewById(R.id.radioGroupPtSex);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextIDNumber = (EditText) findViewById(R.id.editTextIDNumber);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMainActivity();
            }
        });
    }

    private void callMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ptName", editTextName.getText().toString());
        intent.putExtra("ptAge", editTextAge.getText().toString());
        intent.putExtra("ptId", editTextIDNumber.getText().toString());
        int radioButtonID = radioGroupPtSex.getCheckedRadioButtonId();
        View radioButton = radioGroupPtSex.findViewById(radioButtonID);
        int idx = radioGroupPtSex.indexOfChild(radioButton);
        if (idx == 0)
            intent.putExtra("ptSex", "Male");
        else if (idx == 1)
            intent.putExtra("ptSex", "Female");
        else
            intent.putExtra("ptSex", "None");
        startActivity(intent);
    }
}
