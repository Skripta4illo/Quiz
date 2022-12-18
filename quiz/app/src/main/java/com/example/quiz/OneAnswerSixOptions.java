package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.OneAnswerFiveOptionBinding;
import com.example.quiz.databinding.OneAnswerSixOptionBinding;

import java.util.List;

//this code is for one answer from 6 options
public class OneAnswerSixOptions extends Fragment {
    private OneAnswerSixOptionBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = OneAnswerSixOptionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseHandler db = new DatabaseHandler(this.getContext());

        //get quiz id from global variable
        singleToneClass singleToneClass = com.example.quiz.singleToneClass.getInstance();
        long quiz_id = singleToneClass.getData();

        Quiz quiz1 = db.getQuiz(quiz_id);
        String qn = quiz1.getQuizName();
        binding.textviewFirst.setText(qn);

        DatabaseQuestionHandler dbq = new DatabaseQuestionHandler(this.getContext());
        List<Question> queForFrag = dbq.getAllQuestionInQuiz(quiz_id);

        Question ques1 = queForFrag.get(0);
        Question ques2 = queForFrag.get(1);
        Question ques3 = queForFrag.get(2);
        Question ques4 = queForFrag.get(3);
        Question ques5 = queForFrag.get(4);
        Question ques6 = queForFrag.get(5);

        binding.firstAnswer.setText(ques1.getQuestionName());
        binding.SecondAnswer.setText(ques2.getQuestionName());
        binding.ThirdAnswer.setText(ques3.getQuestionName());
        binding.fourthAnswer.setText(ques4.getQuestionName());
        binding.fifthAnswer.setText(ques5.getQuestionName());
        binding.sixthAnswer.setText(ques6.getQuestionName());

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
                //fifth answer
                if (binding.fifthAnswer.isChecked()){
                    if (ques5.getQuestionRight() == 1)
                        singleToneClassAns.setAns(binding.fifthAnswer.getText().toString() + ra);
                    else
                        singleToneClassAns.setAns(binding.fifthAnswer.getText().toString() + wa);
                }
                //sixth answer
                if (binding.sixthAnswer.isChecked()){
                    if (ques6.getQuestionRight() == 1)
                        singleToneClassAns.setAns(binding.sixthAnswer.getText().toString() + ra);
                    else
                        singleToneClassAns.setAns(binding.sixthAnswer.getText().toString() + wa);
                }
                NavHostFragment.findNavController(OneAnswerSixOptions.this)
                        .navigate(R.id.action_OneAnswerSixOptions_to_SecondFragment);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}