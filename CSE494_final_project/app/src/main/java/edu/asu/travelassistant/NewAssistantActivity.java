package edu.asu.travelassistant;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewAssistantActivity extends Activity {

  private static final Calendar CALENDAR = Calendar.getInstance();
  private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
  private static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("hh:mm a", Locale.US);

  private Intent intentAlarm;
  private EditText editTextStartAddress, editTextEndAddress, editTextDate, editTextTime;
  private DatePickerDialog datePickerDialog;
  private TimePickerDialog timePickerDialog;
  private Bundle extras;
  private Button saveButton, backButton;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_assistant);
    datePickerDialog = new DatePickerDialog(this, date,
        CALENDAR.get(Calendar.YEAR), CALENDAR.get(Calendar.MONTH),
        CALENDAR.get(Calendar.DAY_OF_MONTH));
      /* Init EditTexts */
    editTextStartAddress = (EditText) findViewById(R.id.editTextStartAddress);
    editTextEndAddress = (EditText) findViewById(R.id.editTextEndAddress);
    editTextDate = (EditText) findViewById(R.id.editTextDate);
    editTextTime = (EditText) findViewById(R.id.editTextTime);
	  
	    /* Restore previous values if possible */
    Bundle extras = getIntent().getExtras();
    editTextStartAddress.setText("Start address");
    editTextEndAddress.setText("Destination address");
    editTextDate.setText(DATE_FORMATTER.format(Calendar.getInstance().getTime()));
    editTextTime.setText(TIME_FORMATTER.format(Calendar.getInstance().getTime()));

    editTextTime.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        new TimePickerDialog(NewAssistantActivity.this, time,
            CALENDAR.get(Calendar.HOUR), CALENDAR.get(Calendar.MINUTE), false).show();
      }
    });

    editTextDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        new DatePickerDialog(NewAssistantActivity.this, date,
            CALENDAR.get(Calendar.YEAR),
            CALENDAR.get(Calendar.MONTH),
            CALENDAR.get(Calendar.DAY_OF_MONTH)).show();
      }
    });
  }

//  public void dateOnClick(View v) {
//    new DatePickerDialog(NewAssistantActivity.this, dateSetListener, CALENDAR.get(Calendar.YEAR), CALENDAR.get(Calendar.MONTH),
//        CALENDAR.get(Calendar.DAY_OF_MONTH)).show();
//  }
//
  TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      CALENDAR.set(Calendar.HOUR_OF_DAY, hourOfDay);
      CALENDAR.set(Calendar.MINUTE, minute);
      editTextTime.setText(TIME_FORMATTER.format(CALENDAR.getTime()));
    }
  };

  DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      CALENDAR.set(Calendar.YEAR, year);
      CALENDAR.set(Calendar.MONTH, monthOfYear);
      CALENDAR.set(Calendar.DAY_OF_MONTH, dayOfMonth);
      editTextDate.setText(DATE_FORMATTER.format(CALENDAR.getTime()));
    }
  };
}
