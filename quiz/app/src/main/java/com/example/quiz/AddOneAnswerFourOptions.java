package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.AddOneAnswerFourOptionBinding;
import com.example.quiz.databinding.AddOneAnswerThreeOptionBinding;
//this code is for adding one answer from 4 options

public class AddOneAnswerFourOptions extends Fragment {
    private AddOneAnswerFourOptionBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = AddOneAnswerFourOptionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //record to database
                DatabaseHandler db = new DatabaseHandler(getContext());
                Quiz newQuiz = new Quiz();
                newQuiz.setQuizType("One Answer");
                newQuiz.setQuizName(binding.quizName.getText().toString());
                newQuiz.setAnsCount(4);
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

                Question ques4 = new Question();
                ques4.setQuizID(nqId);
                ques4.setQuestionName(binding.question4.getText().toString());
                ques4.setQuestionRight(Integer.parseInt(binding.answer4.getText().toString()));
                dbq.addQuestion(ques4);

                //check if the only answer is true

                NavHostFragment.findNavController(AddOneAnswerFourOptions.this)
                        .navigate(R.id.action_AddOneAnswerFourOption_to_SecondFragment);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}