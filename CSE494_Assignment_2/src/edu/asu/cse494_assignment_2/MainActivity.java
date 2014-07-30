package edu.asu.cse494_assignment_2;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivity extends Activity implements SensorEventListener {

	public enum Gender {
		MALE, FEMALE, OTHER
	}

	private Button btnStop, btnStart;
	private RelativeLayout relativeGraphLayout;
	private GraphView graphView;
	private String[] horlabels, verlabels;
	private SQLiteDatabase db;
	private Runnable recDataThread;
	private final float[] emptyData = { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 0.0f };
	float x[] = new float[128];
	float y[] = new float[128];
	float z[] = new float[128];
	boolean isRecordingData = false;
	int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get views
		btnStop = (Button) findViewById(R.id.btnClear);
		btnStart = (Button) findViewById(R.id.btnData);
		relativeGraphLayout = (RelativeLayout) findViewById(R.id.graphLayout);

		// Graph
		for (int i = 0; i < 10; i++)
			emptyData[i] = 0.0f;
		horlabels = new String[] { "x-axis" };
		verlabels = new String[] { "y-axis" };
		graphView = new GraphView(this, emptyData, "Graph View", horlabels,
				verlabels, GraphView.LINE);
		graphView.setId(6);
		graphView.setLayoutParams(new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		relativeGraphLayout.addView(graphView);
		
		// Create new database and start recording data when start is clicked
		btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				recDataThread.run();
			}
		});

		/* When clicked, clear the graph of all data */
		btnStop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				graphView.setData(emptyData);
				if (isRecordingData == true) {
					/* Stop recDataThread */
				}
				isRecordingData = false;
			}
		});
	}

	public void record() {
		recDataThread = new Runnable() {
			public void run() {
				isRecordingData = true;
				do {
					/* get accelerometer data at 1 hz */
					/* Insert data into database */
				} while (isRecordingData == true);
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		return id == R.id.action_settings || super.onOptionsItemSelected(item);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		index++;
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			x[index] = event.values[0];
			y[index] = event.values[1];
			z[index] = event.values[2];
		}
		if (index >= 127) index = 0;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

}
