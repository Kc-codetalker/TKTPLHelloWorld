package id.ac.ui.cs.mobileprogramming.kace.helloworld;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class QuestionDetail extends Fragment {


    public QuestionDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_detail, container, false);
        this.setAnswerButtonOnClick(view);
        this.setQuestionTextView(view);
        return view;
    }

    private void setAnswerButtonOnClick(final View view) {
        Button button = view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("Button Pressed", "This is ANSWER button.");

                EditText editText = view.findViewById(R.id.editText2);
                String message = editText.getText().toString();

                Toast.makeText(getActivity(), "You answered: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setQuestionTextView(View view) {
        QuestionViewModel model = ((MainActivity) getActivity()).model;
        String question = model.getQuestionDetail((MainActivity) getActivity());

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(question);
    }
}
