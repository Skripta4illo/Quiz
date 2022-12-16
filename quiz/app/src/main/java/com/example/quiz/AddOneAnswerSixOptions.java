package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.AddOneAnswerFiveOptionBinding;
import com.example.quiz.databinding.AddOneAnswerSixOptionBinding;
//this code is for adding one answer from 4 options

public class AddOneAnswerSixOptions extends Fragment {
    private AddOneAnswerSixOptionBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = AddOneAnswerSixOptionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean fillCorrect = false;
                singleToneClassAns singleToneClassAns = com.example.quiz.singleToneClassAns.getInstance();
                String qt = singleToneClassAns.getAns();

                try {
                    if (qt == "One Answer")
                        if ((Integer.parseInt(binding.answer1.getText().toString()) + Integer.parseInt(binding.answer2.getText().toString()) +
                                Integer.parseInt(binding.answer3.getText().toString()) + Integer.parseInt(binding.answer4.getText().toString())
                                + Integer.parseInt(binding.answer5.getText().toString()) + Integer.parseInt(binding.answer6.getText().toString()) == 1) &&
                                (binding.answer1.getText().toString().equals("0") || binding.answer1.getText().toString().equals("1")) &&
                                (binding.answer2.getText().toString().equals("0") || binding.answer2.getText().toString().equals("1")) &&
                                (binding.answer3.getText().toString().equals("0") || binding.answer3.getText().toString().equals("1")) &&
                                (binding.answer4.getText().toString().equals("0") || binding.answer4.getText().toString().equals("1")) &&
                                (binding.answer5.getText().toString().equals("0") || binding.answer5.getText().toString().equals("1")) &&
                                (binding.answer6.getText().toString().equals("0") || binding.answer6.getText().toString().equals("1")))
                            fillCorrect = true;
                        else
                            Toast.makeText(getActivity(), "Only one answer should be correct and answers should be only 0 or 1", Toast.LENGTH_SHORT).show();

                    if (qt == "Few Answers")
                        if ((binding.answer1.getText().toString().equals("0") || binding.answer1.getText().toString().equals("1")) &&
                                (binding.answer2.getText().toString().equals("0") || binding.answer2.getText().toString().equals("1")) &&
                                (binding.answer3.getText().toString().equals("0") || binding.answer3.getText().toString().equals("1")) &&
                                (binding.answer4.getText().toString().equals("0") || binding.answer4.getText().toString().equals("1")) &&
                                (binding.answer5.getText().toString().equals("0") || binding.answer5.getText().toString().equals("1")) &&
                                (binding.answer6.getText().toString().equals("0") || binding.answer6.getText().toString().equals("1")))
                            fillCorrect = true;
                        else
                            Toast.makeText(getActivity(), "Only one answer should be correct and answers should be only 0 or 1", Toast.LENGTH_SHORT).show();

                    if (qt == "Prioritize")
                        if ((Integer.parseInt(binding.answer1.getText().toString()) > 0 && Integer.parseInt(binding.answer1.getText().toString()) < 7) &&
                                (Integer.parseInt(binding.answer2.getText().toString()) > 0 && Integer.parseInt(binding.answer2.getText().toString()) < 7) &&
                                (Integer.parseInt(binding.answer3.getText().toString()) > 0 && Integer.parseInt(binding.answer3.getText().toString()) < 7) &&
                                (Integer.parseInt(binding.answer4.getText().toString()) > 0 && Integer.parseInt(binding.answer4.getText().toString()) < 7) &&
                                (Integer.parseInt(binding.answer5.getText().toString()) > 0 && Integer.parseInt(binding.answer5.getText().toString()) < 7) &&
                                (Integer.parseInt(binding.answer6.getText().toString()) > 0 && Integer.parseInt(binding.answer6.getText().toString()) < 7) &&
                                (Integer.parseInt(binding.answer1.getText().toString()) != Integer.parseInt(binding.answer2.getText().toString())) &&
                                (Integer.parseInt(binding.answer1.getText().toString()) != Integer.parseInt(binding.answer3.getText().toString())) &&
                                (Integer.parseInt(binding.answer1.getText().toString()) != Integer.parseInt(binding.answer4.getText().toString())) &&
                                (Integer.parseInt(binding.answer1.getText().toString()) != Integer.parseInt(binding.answer5.getText().toString())) &&
                                (Integer.parseInt(binding.answer1.getText().toString()) != Integer.parseInt(binding.answer6.getText().toString())) &&
                                (Integer.parseInt(binding.answer3.getText().toString()) != Integer.parseInt(binding.answer2.getText().toString())) &&
                                (Integer.parseInt(binding.answer4.getText().toString()) != Integer.parseInt(binding.answer2.getText().toString())) &&
                                (Integer.parseInt(binding.answer5.getText().toString()) != Integer.parseInt(binding.answer2.getText().toString())) &&
                                (Integer.parseInt(binding.answer6.getText().toString()) != Integer.parseInt(binding.answer2.getText().toString())) &&
                                (Integer.parseInt(binding.answer4.getText().toString()) != Integer.parseInt(binding.answer3.getText().toString())) &&
                                (Integer.parseInt(binding.answer5.getText().toString()) != Integer.parseInt(binding.answer3.getText().toString())) &&
                                (Integer.parseInt(binding.answer6.getText().toString()) != Integer.parseInt(binding.answer3.getText().toString())) &&
                                (Integer.parseInt(binding.answer4.getText().toString()) != Integer.parseInt(binding.answer5.getText().toString())) &&
                                (Integer.parseInt(binding.answer4.getText().toString()) != Integer.parseInt(binding.answer6.getText().toString())) &&
                                (Integer.parseInt(binding.answer6.getText().toString()) != Integer.parseInt(binding.answer5.getText().toString())))
                            fillCorrect = true;
                        else
                            Toast.makeText(getActivity(), "Only one answer should be correct and answers should be between 1 and 6 and should be unique", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Enter correct answers", Toast.LENGTH_SHORT).show();
                }
                if (fillCorrect) {
                    //record to database
                    DatabaseHandler db = new DatabaseHandler(getContext());
                    Quiz newQuiz = new Quiz();
                    newQuiz.setQuizType(qt);
                    newQuiz.setQuizName(binding.quizName.getText().toString());
                    newQuiz.setAnsCount(6);
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

                    Question ques5 = new Question();
                    ques5.setQuizID(nqId);
                    ques5.setQuestionName(binding.question5.getText().toString());
                    ques5.setQuestionRight(Integer.parseInt(binding.answer5.getText().toString()));
                    dbq.addQuestion(ques5);

                    Question ques6 = new Question();
                    ques6.setQuizID(nqId);
                    ques6.setQuestionName(binding.question6.getText().toString());
                    ques6.setQuestionRight(Integer.parseInt(binding.answer6.getText().toString()));
                    dbq.addQuestion(ques6);


                    NavHostFragment.findNavController(AddOneAnswerSixOptions.this)
                            .navigate(R.id.action_AddOneAnswerSixOption_to_SecondFragment);
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