package id.ac.ui.cs.mobileprogramming.kace.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
        implements BackButtonOverrideDialog.BackButtonOverrideDialogListener {

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
        } else if (id == R.id.action_exit) {
            Log.d("Menu Exit", "Exit is selected");
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user taps the Send button */
    public void onEnterButtonPressed(View view) {
        Log.d("Button Pressed", "This is ENTER button.");

        // Capture the layout's EditText and get its text
        EditText editText = findViewById(R.id.editText2);
        String message = editText.getText().toString();

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText("Hello " + message + "!");
    }

    @Override
    public void onBackPressed() {
        Log.d("Hardware Interact", "onBackPressed Called");
//        Intent setIntent = new Intent(Intent.ACTION_MAIN);
//        setIntent.addCategory(Intent.CATEGORY_HOME);
//        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(setIntent);

        showNoticeToast(null);

        showNoticeDialog();
    }

    public void showNoticeToast(String msg) {
        Context context = getApplicationContext();
        CharSequence text = (msg != null) ? msg: "This is toast! Can't go back.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new BackButtonOverrideDialog();
        dialog.show(getSupportFragmentManager(), "BackButtonOverrideDialogFragment");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        super.onBackPressed();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Log.d("Tidak jadi back", "Yeay tidak jadi back!!");
    }
}
