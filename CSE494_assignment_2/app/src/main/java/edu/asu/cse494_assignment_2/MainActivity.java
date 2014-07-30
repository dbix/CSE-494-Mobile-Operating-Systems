package edu.asu.cse494_assignment_2;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static Button btnStop, btnStart, btnInfo;
    private static TextView txtViewName, txtViewAge, txtViewId, txtViewSex;
    private static RelativeLayout relativeGraphLayout;
    private static Random randomGen = new Random();
    private static float randomFloats[];
    private static GraphView graphView;
    private static String ptName, ptAge, ptId, ptSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGraphView();
        initButtons();
        initTextViews();
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

    private void callPatientInfoActivity () {
        Intent intent = new Intent(this, PatientInfoActivity.class);
        startActivity(intent);
    }

    private void initGraphView() {
        /* Data */
        randomFloats = new float[20];
        for (int i = 0; i < 20; i++) {
            randomFloats[i] = i;
        }
        String[] horlabels = {
                "0", "2", "4", "6", "8", "10", "12", "14", "16", "18", "20"
        };
        String[] verlabels;
        verlabels = new String[] { "100", "90", "80", "70", "60", "50", "40", "30", "20", "10", "0" };

        graphView = new GraphView(this, randomFloats,
                "Graph View", horlabels, verlabels, GraphView.BAR);
        graphView.setId(6);
        graphView.setLayoutParams(new LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

        relativeGraphLayout = (RelativeLayout) findViewById(R.id.graphLayout);
        relativeGraphLayout.addView(graphView);
    }

    private void initButtons() {
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnInfo = (Button) findViewById(R.id.btnInfo);

        // Start
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
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

		// Stop and clear
        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 20; i++) {
                    randomFloats[i] = 0.0f;
                }
                graphView.setValues(randomFloats);
                graphView.invalidate();
            }
        });

		// Patient info
        btnInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callPatientInfoActivity();
            }
        });
    }

    private void initTextViews() {
        txtViewName = (TextView) findViewById(R.id.txtViewNameField);
        txtViewAge = (TextView) findViewById(R.id.txtViewAgeField);
        txtViewId = (TextView) findViewById(R.id.txtViewIDField);
        txtViewSex = (TextView) findViewById(R.id.txtViewSexField);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ptName = extras.getString("ptName");
            ptAge = extras.getString("ptAge");
            ptId = extras.getString("ptId");
            ptSex = extras.getString("ptSex");
        } else {
            ptName = "None";
            ptAge = "None";
            ptId = "None";
            ptSex = "None";
        }
        txtViewName.setText(ptName);
        txtViewAge.setText(ptAge);
        txtViewId.setText(ptId);
        txtViewSex.setText(ptSex);
    }

    public boolean allEqual(Object key, Object... objs) {
        for(Object o : objs) if(!o.equals(key)) return false;
        return true;
    }
}
