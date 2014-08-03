package edu.asu.travelassistant;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlarmManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity {

  private static Button buttonCreateNewAssistant, buttonTest;
  private static EditText etStartDestination, etEndDestination, etTimeToReach;
  private static String startDestination, endDestination, timeToReach;
  private static AlarmManager alarmManager;
  private static LocationManager locationManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button buttonCreateNewAssistant = (Button) findViewById(R.id.buttonCreateNewAssistant);
    buttonCreateNewAssistant.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        callNewAssistantIntent();
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  protected void callNewAssistantIntent() {
    Intent intent = new Intent(this, NewAssistantActivity.class);
    startActivity(intent);
  }

}
