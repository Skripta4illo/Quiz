package com.example.quiz;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.AddOneAnswerThreeOptionBinding;
import com.example.quiz.databinding.FragmentFirstBinding;

import java.util.List;
//this code is for adding one answer from 3 options

public class AddOneAnswerThreeOptions extends Fragment {
    private AddOneAnswerThreeOptionBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = AddOneAnswerThreeOptionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the only answer is true
                try {
                    if((Integer.parseInt(binding.answer1.getText().toString()) + Integer.parseInt(binding.answer2.getText().toString()) +
                            Integer.parseInt(binding.answer3.getText().toString()) == 1) &&
                            (binding.answer1.getText().toString().equals("0") || binding.answer1.getText().toString().equals("1")) &&
                            (binding.answer2.getText().toString().equals("0") || binding.answer2.getText().toString().equals("1")) &&
                            (binding.answer3.getText().toString().equals("0") || binding.answer3.getText().toString().equals("1"))){
                        //record to database
                        DatabaseHandler db = new DatabaseHandler(getContext());
                        Quiz newQuiz = new Quiz();
                        newQuiz.setQuizType("One Answer");
                        newQuiz.setQuizName(binding.quizName.getText().toString());
                        newQuiz.setAnsCount(3);
                        db.addQuiz(newQuiz);

                        long nqId = db.getQuizCount();

                        DatabaseQuestionHandler dbq = new DatabaseQuestionHandler(getContext());

                        Question ques1 = new Question();
                        ques1.setQuizID(nqId);
                        ques1.setQuestionName(binding.question1.getText().toString());
                        ques1.setQuestionRight(Integer.parseInt(binding.answer1.getText().toString()));
                        dbq.addQuestion(ques1);

                        Question ques2 = new Question();
                        ques2.setQuizID(nqId);
                        ques2.setQuestionName(binding.question2.getText().toString());
                        ques2.setQuestionRight(Integer.parseInt(binding.answer2.getText().toString()));
                        dbq.addQuestion(ques2);

                        Question ques3 = new Question();
                        ques3.setQuizID(nqId);
                        ques3.setQuestionName(binding.question3.getText().toString());
                        ques3.setQuestionRight(Integer.parseInt(binding.answer3.getText().toString()));
                        dbq.addQuestion(ques3);

                        NavHostFragment.findNavController(AddOneAnswerThreeOptions.this)
                                .navigate(R.id.action_AddOneAnswerThreeOption_to_SecondFragment);
                    }
                    else{
                        Toast.makeText(getActivity(), "Only one answer should be correct and answers should be only 0 or 1", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "Answer could be only 1 or 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}