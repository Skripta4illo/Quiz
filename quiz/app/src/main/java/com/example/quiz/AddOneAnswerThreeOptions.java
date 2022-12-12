package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
                NavHostFragment.findNavController(AddOneAnswerThreeOptions.this)
                        .navigate(R.id.action_AddOneAnswerThreeOption_to_SecondFragment);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}