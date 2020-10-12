package id.ac.ui.cs.mobileprogramming.kace.helloworld;

import androidx.lifecycle.ViewModel;

public class QuestionViewModel extends ViewModel {
    int questionResId;

    public String getQuestionDetail(MainActivity activity) {
        return activity.getString(questionResId);
    }
}
