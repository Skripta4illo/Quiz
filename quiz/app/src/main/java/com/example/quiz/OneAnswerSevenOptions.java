package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.OneAnswerSevenOptionBinding;
import com.example.quiz.databinding.OneAnswerSixOptionBinding;

import java.util.List;

//this code is for one answer from 6 options
public class OneAnswerSevenOptions extends Fragment {
    private OneAnswerSevenOptionBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = OneAnswerSevenOptionBinding.inflate(inflater, container, false);
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

        binding.firstAnswer.setText(queForFrag.get(0).getQuestionName());
        binding.SecondAnswer.setText(queForFrag.get(1).getQuestionName());
        binding.ThirdAnswer.setText(queForFrag.get(2).getQuestionName());
        binding.fourthAnswer.setText(queForFrag.get(3).getQuestionName());
        binding.fifthAnswer.setText(queForFrag.get(4).getQuestionName());
        binding.sixthAnswer.setText(queForFrag.get(5).getQuestionName());
        binding.seventhAnswer.setText(queForFrag.get(6).getQuestionName());

        singleToneClassAns singleToneClassAns = com.example.quiz.singleToneClassAns.getInstance();
        singleToneClassAns.setAns("no answer selected");

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ra = " - This is the correct answer!";
                String wa = " - This is incorrect answer!";

                //write data to UserAnswer
                int[] ansArray = new int[]{binding.firstAnswer.isChecked()?1:0,
                        binding.SecondAnswer.isChecked()?1:0, binding.ThirdAnswer.isChecked()?1:0,
                        binding.fourthAnswer.isChecked()?1:0, binding.fifthAnswer.isChecked()?1:0,
                        binding.sixthAnswer.isChecked()?1:0, binding.seventhAnswer.isChecked()?1:0};
                UserAnswer userAnswer = new UserAnswer();

                for (int aa = 0; aa<7; aa++){
                    userAnswer.setIDua(dbua.getUserAnswerCount());
                    userAnswer.setUserId(singleToneClass.getUid());
                    userAnswer.setQuizId(quiz_id);
                    userAnswer.setQuestionId(queForFrag.get(aa).getIDa());
                    userAnswer.setUserAnswer(ansArray[aa]);
                    dbua.addUserAnswer(userAnswer);
                    //check if answer is correct
                    if (ansArray[aa] == 1){
                        if (queForFrag.get(aa).getQuestionRight() == 1)
                            singleToneClassAns.setAns(queForFrag.get(aa).getQuestionName() + ra);
                        else
                            singleToneClassAns.setAns(queForFrag.get(aa).getQuestionName() + wa);;
                    }
                }
                NavHostFragment.findNavController(OneAnswerSevenOptions.this)
                        .navigate(R.id.action_OneAnswerSevenOptions_to_SecondFragment);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}