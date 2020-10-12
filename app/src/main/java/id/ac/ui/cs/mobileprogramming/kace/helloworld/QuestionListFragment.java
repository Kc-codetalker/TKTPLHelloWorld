package id.ac.ui.cs.mobileprogramming.kace.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;


public class QuestionListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private Toast questionToast;

    public QuestionListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.questions, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = getListView().getItemAtPosition(position).toString();

        int resId = this.getQuestionResourceIdFromQuestionName(item);
        Log.d("ResId nih", "" + resId);

        String toastMsg;
        if (resId == 0) {
            toastMsg = item + " is not available.";
            this.displayQuestionToast(toastMsg);
        } else {
            // Save to view model
            ((MainActivity) getActivity()).model.questionResId = resId;

            // Start question detail fragment
            QuestionDetail nextFrag= new QuestionDetail();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }
    }

    private int getQuestionResourceIdFromQuestionName(String questionName) {
        String questionResKey = questionName.replace(' ', '_') + "_detail";
        String packageName = getActivity().getPackageName();
        return getResources().getIdentifier(questionResKey, "string", packageName);
    }

    private void displayQuestionToast(String message) {
        if (this.questionToast != null) {
            this.questionToast.cancel();
        }
        this.questionToast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        this.questionToast.show();
    }

}
