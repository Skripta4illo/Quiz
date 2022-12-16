package com.example.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        //get quiz id
        DatabaseHandler db = new DatabaseHandler(this.getContext());
        super.onViewCreated(view, savedInstanceState);
        singleToneClassAns singleToneClassAns = com.example.quiz.singleToneClassAns.getInstance();
        binding.textviewSecond.setText(singleToneClassAns.getAns());
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {

                                                     NavHostFragment.findNavController(SecondFragment.this)
                                                             .navigate(R.id.action_SecondFragment_to_AddQuestionOption);
                                                 }
                                             });
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get quiz id
                int randomNum = (int) (Math.random() * db.getQuizCount()) + 1;
                //randomNum = 16; //test string

                //set quiz id to global variable
                singleToneClass singleToneClass = com.example.quiz.singleToneClass.getInstance();
                singleToneClass.setData(randomNum);

                //get quiz
                Quiz quiz1 = db.getQuiz(randomNum);

                //get quiz answer count
                int ac = quiz1.getAnsCount();

                //get quiz type
                String qt = quiz1.getQuizType();

                if (qt.equals("One Answer")) {
                    if (ac == 3)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_FirstFragment);
                    if (ac == 4)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_OneQuestionFourAnswers);
                    if (ac == 5)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_OneQuestionFiveAnswers);
                    if (ac == 6)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_OneQuestionSixAnswers);
                    if (ac == 7)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_OneQuestionSevenAnswers);
                }
                if (qt.equals("Few Answers")){
                    if (ac == 4)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_FewAnswersFourOptions);
                    if (ac == 5)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_FewAnswersFiveOptions);
                    if (ac == 6)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_FewAnswersSixOptions);
                    if (ac == 7)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_FewAnswersSevenOptions);
                }
                if (qt.equals("Prioritize")){
                    if (ac == 4)
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_PrioritizeFour);
                    if (ac == 5)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_PrioritizeFive);
                    if (ac == 6)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_PrioritizeSix);
                    if (ac == 7)
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_PrioritizeSeven);
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