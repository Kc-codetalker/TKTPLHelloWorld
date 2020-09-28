package id.ac.ui.cs.mobileprogramming.kace.helloworld;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private static ActivityScenario scenario;
    @Before
    public void setUp() {
        scenario = mActivityRule.getScenario();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertThat(appContext.getPackageName()).isEqualTo("id.ac.ui.cs.mobileprogramming.kace.helloworld");
    }

    @Test
    public void testOnIncrementButtonPressed() {
        scenario.onActivity(new ActivityScenario.ActivityAction() {
            @Override
            public void perform(Activity activity) {
                View incButton = activity.findViewById(R.id.button);
                ((MainActivity) activity).onIncrementButtonPressed(incButton);
                View numberText = activity.findViewById(R.id.textView2);
                String numberStr = ((TextView) numberText).getText().toString();
                assertThat(numberStr).isEqualTo("3");
                assertThat(numberStr).isNotEqualTo("2");
            }
        });
    }

    @Test
    public void testOnDecrementButtonPressed() {
        ActivityScenario scenario = mActivityRule.getScenario();
        scenario.onActivity(new ActivityScenario.ActivityAction() {
            @Override
            public void perform(Activity activity) {
                View incButton = activity.findViewById(R.id.button);
                ((MainActivity) activity).onDecrementButtonPressed(incButton);
                View numberText = activity.findViewById(R.id.textView2);
                String numberStr = ((TextView) numberText).getText().toString();
                assertThat(numberStr).isEqualTo("1");
                assertThat(numberStr).isNotEqualTo("2");
            }
        });
    }
}
