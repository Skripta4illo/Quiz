package com.example.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.ThreadLocalRandom;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.FragmentFirstBinding;

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

        DatabaseHandler db = new DatabaseHandler(this.getContext());

        int randomNum = (int) (Math.random() * db.getQuizCount()) + 1;

        Quiz quiz1 = db.getQuiz(randomNum);
        String qn = quiz1.getQuizName();
        binding.textviewFirst.setText(qn);

        singleToneClassAns singleToneClassAns = com.example.quiz.singleToneClassAns.getInstance();
        singleToneClassAns.setAns("no answer selected");

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.firstAnswer.isChecked()){
                    singleToneClassAns.setAns(binding.firstAnswer.getText().toString());
                }
                if (binding.SecondAnswer.isChecked()){
                    singleToneClassAns.setAns(binding.SecondAnswer.getText().toString());
                }
                if (binding.ThirdAnswer.isChecked()){
                    singleToneClassAns.setAns(binding.ThirdAnswer.getText().toString());
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