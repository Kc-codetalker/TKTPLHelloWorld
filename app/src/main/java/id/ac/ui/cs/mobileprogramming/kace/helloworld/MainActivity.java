package id.ac.ui.cs.mobileprogramming.kace.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WifiManager wifiManager;
    BroadcastReceiver wifiScanReceiver;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("LOG Masuk", "Sudah masuk ke onCreate");
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is a snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getWifiNames().observe(this, names -> {
            ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
            ListView listView = findViewById(R.id.wifiList);
            listView.setAdapter(adapter);
        });

        setupWifiScanning(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user taps the Send button */
    public void onEnterButtonPressed(View view) {
        Log.d("Button Pressed", "This is ENTER button.");

        // Capture the layout's EditText and get its text
        EditText editText = (EditText) findViewById(R.id.editText2);
        String message = editText.getText().toString();

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText("Hello " + message + "!");
    }

    private void setupWifiScanning(Context ctx) {
        wifiManager = (WifiManager)
                ctx.getSystemService(Context.WIFI_SERVICE);

        wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                boolean success = intent.getBooleanExtra(
                        WifiManager.EXTRA_RESULTS_UPDATED, false);
                if (success) {
                    Log.d("WIFI_SCAN_RESULT", "Receiver success");
                    scanSuccess();
                } else {
                    // scan failure handling
                    Log.d("WIFI_SCAN_RESULT", "Receiver fail");
                    scanFailure();
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        ctx.registerReceiver(wifiScanReceiver, intentFilter);

        boolean success = wifiManager.startScan();
        if (!success) {
            // scan failure handling
            Log.d("WIFI_SCAN_RESULT", "Start scan fail");
            scanFailure();
        }
    }

    private void scanSuccess() {
        List<ScanResult> results = wifiManager.getScanResults();
        Log.d("WIFI_SCAN_RESULT", "Scan results size: " + results.size());
        setWifiNames(results);
    }

    private void scanFailure() {
        // handle failure: new scan did NOT succeed
        // consider using old scan results: these are the OLD results!
        List<ScanResult> results = wifiManager.getScanResults();
        Log.d("WIFI_SCAN_RESULT", "Scan results size: " + results.size());
        setWifiNames(results);
    }

    private void setWifiNames(List<ScanResult> results) {
        List<String> names = new ArrayList<>();
        for(ScanResult res : results) {
            names.add(res.SSID);
            Log.d("WIFI_SCAN_RESULT", "Wi-Fi name: " + res.SSID);
        }
        viewModel.setWifiNames(names);
        Log.d("WIFI_SCAN_RESULT", "View Model list size: " + viewModel.getWifiNames().getValue().size());
    }
}
