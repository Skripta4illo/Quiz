package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.OneAnswerFourOptionBinding;

import java.util.List;

//this code is for one answer from 4 options
public class OneAnswerFourOptions extends Fragment {
    private OneAnswerFourOptionBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = OneAnswerFourOptionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseHandler db = new DatabaseHandler(this.getContext());

        //get quiz id from global variable
        singleToneClass singleToneClass = com.example.quiz.singleToneClass.getInstance();
        int quiz_id = singleToneClass.getData();

        //int randomNum = (int) (Math.random() * db.getQuizCount()) + 1;

        Quiz quiz1 = db.getQuiz(quiz_id);
        String qn = quiz1.getQuizName();
        binding.textviewFirst.setText(qn);

        DatabaseQuestionHandler dbq = new DatabaseQuestionHandler(this.getContext());
        List<Question> queForFrag = dbq.getAllQuestionInQuiz(quiz_id);

        Question ques1 = queForFrag.get(0);
        Question ques2 = queForFrag.get(1);
        Question ques3 = queForFrag.get(2);
        Question ques4 = queForFrag.get(3);

        binding.firstAnswer.setText(ques1.getQuestionName());
        binding.SecondAnswer.setText(ques2.getQuestionName());
        binding.ThirdAnswer.setText(ques3.getQuestionName());
        binding.fourthAnswer.setText(ques4.getQuestionName());

        singleToneClassAns singleToneClassAns = com.example.quiz.singleToneClassAns.getInstance();
        singleToneClassAns.setAns("no answer selected");

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ra = " - This is the correct answer!";
                String wa = " - This is incorrect answer!";
                if (binding.firstAnswer.isChecked()){
                    if (ques1.getQuestionRight() == 1)
                    singleToneClassAns.setAns(binding.firstAnswer.getText().toString() + ra);
                            else
                        singleToneClassAns.setAns(binding.firstAnswer.getText().toString() + wa);;
                }
                if (binding.SecondAnswer.isChecked()){
                    if (ques2.getQuestionRight() == 1)
                    singleToneClassAns.setAns(binding.SecondAnswer.getText().toString() + ra);
                    else
                        singleToneClassAns.setAns(binding.SecondAnswer.getText().toString() + wa);
                }
                if (binding.ThirdAnswer.isChecked()){
                    if (ques3.getQuestionRight() == 1)
                    singleToneClassAns.setAns(binding.ThirdAnswer.getText().toString() + ra);
                    else
                        singleToneClassAns.setAns(binding.ThirdAnswer.getText().toString() + wa);
                }
                //four answer
                if (binding.fourthAnswer.isChecked()){
                    if (ques4.getQuestionRight() == 1)
                        singleToneClassAns.setAns(binding.fourthAnswer.getText().toString() + ra);
                    else
                        singleToneClassAns.setAns(binding.fourthAnswer.getText().toString() + wa);
                }
                NavHostFragment.findNavController(OneAnswerFourOptions.this)
                        .navigate(R.id.action_OneAnswerFourOptions_to_SecondFragment);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}