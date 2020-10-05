/*
* Stopwatch reference: https://www.geeksforgeeks.org/how-to-create-a-stopwatch-app-using-android-studio/
*/

package id.ac.ui.cs.mobileprogramming.kace.helloworld;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {

    // Number of seconds displayed
    // on the stopwatch.
    private int seconds = 0;

    // Is the stopwatch running?
    private boolean running;

    // whether the stopwatch was running
    // before the activity was paused.
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if (savedInstanceState != null) {

            // Get the previous state of the stopwatch
            // if the activity has been
            // destroyed and recreated.
            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState)
    {
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
        super.onSaveInstanceState(savedInstanceState); // Di geeksforgeeks gaada ini
    }

    @Override
    protected void onPause()
    {
        Log.d("Masuk onPause", "onPause called");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("Masuk onStop", "onStop() called");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("Masuk onDestroy", "onDestroy() called");
        super.onDestroy();
    }

    public void onClickStart(View view)
    {
        running = true;
    }

    public void onClickStop(View view)
    {
        running = false;
    }

    public void onClickReset(View view)
    {
        running = false;
        seconds = 0;
    }

    // Sets the Number of seconds on the timer.
    // The runTimer() method uses a Handler
    // to increment the seconds and
    // update the text view.
    private void runTimer()
    {

        final TextView timeView
                = (TextView)findViewById(
                R.id.time_view);

        final Handler handler
                = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override

                    public void run()
                    {
                        int hours = seconds / 3600;
                        int minutes = (seconds % 3600) / 60;
                        int secs = seconds % 60;

                        String time
                                = String
                                .format(Locale.getDefault(),
                                        "%d:%02d:%02d", hours,
                                        minutes, secs);

                        timeView.setText(time);

                        // If running is true, increment the
                        // seconds variable.
                        if (running) {
                            seconds++;
                        }

                        // Post the code again
                        // with a delay of 1 second.
                        handler.postDelayed(this, 1000);
                    }
                });
            }
        }).start();

    }

}
