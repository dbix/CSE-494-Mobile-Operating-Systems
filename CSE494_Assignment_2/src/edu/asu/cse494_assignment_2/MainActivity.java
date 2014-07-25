package edu.asu.cse494_assignment_2;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	public enum Gender {
		MALE, FEMALE, OTHER
	}

	private Button btnStop, btnStart;
	private RelativeLayout relativeGraphLayout;
	private GraphView graphView;
	private String[] horlabels, verlabels;
	private SQLiteDatabase db;
	private Runnable recDataThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Instantiate fields
		horlabels = new String[] { "x-axis" };
		verlabels = new String[] { "y-axis" };
		graphView = new GraphView(this, new float[0], "Graph View", horlabels,
				verlabels, GraphView.LINE);
		btnStop = (Button) findViewById(R.id.btnClear);
		btnStart = (Button) findViewById(R.id.btnData);
		relativeGraphLayout = (RelativeLayout) findViewById(R.id.graphLayout);

		// Add graph view to layout
		graphView.setId(6);
		graphView.setLayoutParams(new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		relativeGraphLayout.addView(graphView);

		// Create new database and start recording data when start is clicked
		btnStart.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				db = createDatabase("Seymour Butz", 42069, 69, Gender.FEMALE);
				Runnable r = new Runnable() {
					public void run() {
						for (int i = 0; i < 10; i++) {
							/* Insert accelerometer data */
							db.insert(ACCOUNT_SERVICE, // Dummy parameters
									ACCESSIBILITY_SERVICE, null);
							try {
								this.wait(100);
							} catch (InterruptedException e) {
								System.err.println(
									"\nERROR: wait(100) in btnStart thread");
								e.printStackTrace();
							}
							db.insert(table, nullColumnHack, values);
							graphView.invalidate();
						}
					}
				};
				Thread t = new Thread(r);
				t.start();
			}
		});

		/* When clicked, clear the graph of all data */
		btnStop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i < graphView.ge)
				graphView.invalidate(); // Refresh graph view
			}
		});
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

	private SQLiteDatabase createDatabase(String ptName, int ptID, int ptAge,
			Gender ptGnd) {
		String dbName = ptName + "_" + ptID + "_" + ptAge + "_" + ptGnd;
		db = this.openOrCreateDatabase("myfriendsDB", MODE_PRIVATE, null);
		/* Create database with name dbName */
		return db;
	}

}
