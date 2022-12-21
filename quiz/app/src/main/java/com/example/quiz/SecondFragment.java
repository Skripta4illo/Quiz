package com.example.quiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        DatabaseHandler db = new DatabaseHandler(this.getContext());
        DatabaseUserHandler dbu = new DatabaseUserHandler(this.getContext());
        DatabaseUserAnswerHandler dbua = new DatabaseUserAnswerHandler(this.getContext());

        String ansc = dbua.checkUserAndQuiz(2, 15)?"answered":"not answered";

        Toast.makeText(getActivity(), ansc, Toast.LENGTH_SHORT).show();
        super.onViewCreated(view, savedInstanceState);
        //get user id
        singleToneClass singleToneClass = com.example.quiz.singleToneClass.getInstance();
        long uid = singleToneClass.getUid();

        //get user name
        User user = dbu.getUser(uid);
        String userName = user.getUserName();

        singleToneClassAns singleToneClassAns = com.example.quiz.singleToneClassAns.getInstance();
        //get quiz id
        binding.textView.setText(singleToneClassAns.getAns() + userName);
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

                //remove all user answer
//                List<UserAnswer> ua = dbua.getAllUserAnswer();
//                for (UserAnswer dua : ua)
//                {
//                    dbua.deleteUserAnswer(dua);
//                }

                //get quiz id
                long randomNum = (long) (Math.random() * db.getQuizCount()) + 1;
                randomNum = 15; //test string

                //set quiz id to global variable
                singleToneClass singleToneClass = com.example.quiz.singleToneClass.getInstance();
                singleToneClass.setQuizId(randomNum);

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