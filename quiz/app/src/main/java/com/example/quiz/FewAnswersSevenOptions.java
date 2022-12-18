package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.FewAnswersSevenOptionBinding;

import java.util.List;

//this code is for few answers from 7 options
public class FewAnswersSevenOptions extends Fragment {
    private FewAnswersSevenOptionBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FewAnswersSevenOptionBinding.inflate(inflater, container, false);
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
        Question ques7 = queForFrag.get(6);

        binding.firstAnswer.setText(ques1.getQuestionName());
        binding.SecondAnswer.setText(ques2.getQuestionName());
        binding.ThirdAnswer.setText(ques3.getQuestionName());
        binding.fourthAnswer.setText(ques4.getQuestionName());
        binding.fifthAnswer.setText(ques5.getQuestionName());
        binding.sixthAnswer.setText(ques6.getQuestionName());
        binding.seventhAnswer.setText(ques7.getQuestionName());

        singleToneClassAns singleToneClassAns = com.example.quiz.singleToneClassAns.getInstance();
        singleToneClassAns.setAns("no answer selected");

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //boolean Correct = false;
                String ra = "This is the correct answer!";
                String wa = "This is incorrect answer!";
                if ((binding.firstAnswer.isChecked() ^ ques1.getQuestionRight() == 0) &&
                        (binding.SecondAnswer.isChecked() ^ ques2.getQuestionRight() == 0) &&
                        (binding.ThirdAnswer.isChecked() ^ ques3.getQuestionRight() == 0) &&
                        (binding.fourthAnswer.isChecked() ^ ques4.getQuestionRight() == 0) &&
                        (binding.fifthAnswer.isChecked() ^ ques5.getQuestionRight() == 0) &&
                        (binding.sixthAnswer.isChecked() ^ ques6.getQuestionRight() == 0) &&
                        (binding.seventhAnswer.isChecked() ^ ques7.getQuestionRight() == 0))
                        singleToneClassAns.setAns(ra);
                    else
                        singleToneClassAns.setAns(wa);

                NavHostFragment.findNavController(FewAnswersSevenOptions.this)
                        .navigate(R.id.action_FewAnswersSevenOptions_to_SecondFragment);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}