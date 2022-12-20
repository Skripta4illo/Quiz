package com.example.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.FragmentFirstBinding;
//this code is for one answer from 3 options

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseUserAnswerHandler dbua = new DatabaseUserAnswerHandler(this.getContext());

        DatabaseHandler db = new DatabaseHandler(this.getContext());

        //get quiz id from global variable
        singleToneClass singleToneClass = com.example.quiz.singleToneClass.getInstance();
        long quiz_id = singleToneClass.getQuizId();

        Quiz quiz1 = db.getQuiz(quiz_id);
        String qn = quiz1.getQuizName();
        binding.textviewFirst.setText(qn);

        DatabaseQuestionHandler dbq = new DatabaseQuestionHandler(this.getContext());
        List<Question> queForFrag = dbq.getAllQuestionInQuiz(quiz_id);

        Question ques1 = queForFrag.get(0);
        Question ques2 = queForFrag.get(1);
        Question ques3 = queForFrag.get(2);

        binding.firstAnswer.setText(ques1.getQuestionName());
        binding.SecondAnswer.setText(ques2.getQuestionName());
        binding.ThirdAnswer.setText(ques3.getQuestionName());

        singleToneClassAns singleToneClassAns = com.example.quiz.singleToneClassAns.getInstance();
        singleToneClassAns.setAns("no answer selected");

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //write data to UserAnswer
                int[] ansArray = new int[]{binding.firstAnswer.isChecked()?1:0,
                        binding.SecondAnswer.isChecked()?1:0, binding.ThirdAnswer.isChecked()?1:0};
                UserAnswer userAnswer = new UserAnswer();

                for (int aa = 0; aa<3; aa++){
                    userAnswer.setUserId(singleToneClass.getUid());
                    userAnswer.setQuizId(quiz_id);
                    userAnswer.setQuestionId(queForFrag.get(aa).getIDa());
                    userAnswer.setUserAnswer(ansArray[aa]);
                    dbua.addUserAnswer(userAnswer);
                }

                //check if answer is correct
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
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}