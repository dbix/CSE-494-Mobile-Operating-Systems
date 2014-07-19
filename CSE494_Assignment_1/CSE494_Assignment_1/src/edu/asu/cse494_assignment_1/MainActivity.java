package edu.asu.cse494_assignment_1;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private OldestGraphView graphView;
	private Button btnClear, btnData;
	private RelativeLayout relativeGraphLayout;
	private AlertDialog dataPrompt;
	private Random randomGen = new Random();
	private float randomFloats[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/* Data */
		randomFloats = new float[20];
		for (int i = 0; i < 20; i++) {
			randomFloats[i] = (float) i;
		}
		
		/* UI Elements */
		btnClear = (Button) findViewById(R.id.btnClear);
		btnData = (Button) findViewById(R.id.btnData);		
		
		String[] horlabels = {"0", "2", "4", "6", "8", "10", "12", "14", "16", "18", "20"};
		String[] verlabels = {"100", "90", "80", "70", "60", "50", "40", "30", "20", "10", "0"};
		
		final GraphView graphView = new GraphView(this, randomFloats, "Graph View", horlabels, verlabels, OldestGraphView.BAR);
		graphView.setId(6);
		graphView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		relativeGraphLayout = (RelativeLayout) findViewById(R.id.graphLayout);
		relativeGraphLayout.addView(graphView);

		/* Generate new random data when clicked */
		btnData.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String data = "";
				for (int i = 0; i < 20; i++) {
					randomFloats[i] = randomGen.nextFloat() * randomGen.nextInt(100);
					data = data + randomFloats[i] + " ";
				}
				System.out.println(data);
				graphView.invalidate();
			}
		});

		/* When clicked, clear the graph of all data */
		btnClear.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				for (int i = 0; i < 20; i++) {
					randomFloats[i] = 0.0f;
				}
				graphView.setValues(randomFloats);
				graphView.invalidate();
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void showEditDataActivity() {
		// Intent changeActivities = new Intent(this, EditDataActivity.class);
		// startActivity(changeActivities);
	}
}
