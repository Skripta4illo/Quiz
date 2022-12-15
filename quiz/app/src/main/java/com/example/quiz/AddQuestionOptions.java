package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.AddQuestionOptionsBinding;
//this code is for adding one answer from 3 options

public class AddQuestionOptions extends Fragment {
    private AddQuestionOptionsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = AddQuestionOptionsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check question type
                //check if the only answer is true

                if (!binding.ans4.isChecked() && !binding.ans5.isChecked() && !binding.ans6.isChecked() && !binding.ans7.isChecked() && binding.firstAnswer.isChecked())
                    NavHostFragment.findNavController(AddQuestionOptions.this)
                        .navigate(R.id.action_AddQuestionOption_to_AddOneAnswerThreeOption);
                if (binding.ans4.isChecked() && binding.firstAnswer.isChecked())
                    NavHostFragment.findNavController(AddQuestionOptions.this)
                            .navigate(R.id.action_AddQuestionOption_to_AddOneAnswerFourOption);
                if (binding.ans5.isChecked() && binding.firstAnswer.isChecked())
                    NavHostFragment.findNavController(AddQuestionOptions.this)
                            .navigate(R.id.action_AddQuestionOption_to_AddOneAnswerFiveOption);
                if (binding.ans6.isChecked() && binding.firstAnswer.isChecked())
                    NavHostFragment.findNavController(AddQuestionOptions.this)
                            .navigate(R.id.action_AddQuestionOption_to_AddOneAnswerSixOption);
                if (binding.ans7.isChecked() && binding.firstAnswer.isChecked())
                    NavHostFragment.findNavController(AddQuestionOptions.this)
                            .navigate(R.id.action_AddQuestionOption_to_AddOneAnswerSevenOption);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}