package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.PrioritizeFiveBinding;
import com.example.quiz.databinding.PrioritizeFourBinding;

import java.util.List;

//this code is for prioritizing
public class PrioritizeFive extends Fragment {
    private PrioritizeFiveBinding binding;

    //declare ui views
    private RelativeLayout relativeLayout;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;

    private int xDelta;
    private int yDelta;
    private int ansCount = 0;
    private int ans1 = 0;
    private int ans2 = 0;
    private int ans3 = 0;
    private int ans4 = 0;
    private int ans5 = 0;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = PrioritizeFiveBinding.inflate(inflater, container, false);

        //init ui views
        relativeLayout = binding.relative;
        textView1 = binding.textAns1;
        textView2 = binding.textAns2;
        textView3 = binding.textAns3;
        textView4 = binding.textAns4;
        textView5 = binding.textAns5;

        //setup layout params
        RelativeLayout.LayoutParams layoutParamsta1 = new RelativeLayout.LayoutParams(800, 600);
        textView1.setLayoutParams(layoutParamsta1);
        //setup touch listener
        textView1.setOnTouchListener(new CustomTouchListener());

        RelativeLayout.LayoutParams layoutParamsta2 = new RelativeLayout.LayoutParams(800, 600);
        textView2.setLayoutParams(layoutParamsta2);
        //setup touch listener
        textView2.setOnTouchListener(new CustomTouchListener());

        RelativeLayout.LayoutParams layoutParamsta3 = new RelativeLayout.LayoutParams(800, 600);
        textView3.setLayoutParams(layoutParamsta3);
        //setup touch listener
        textView3.setOnTouchListener(new CustomTouchListener());

        RelativeLayout.LayoutParams layoutParamsta4 = new RelativeLayout.LayoutParams(800, 600);
        textView4.setLayoutParams(layoutParamsta4);
        //setup touch listener
        textView4.setOnTouchListener(new CustomTouchListener());

        RelativeLayout.LayoutParams layoutParamsta5 = new RelativeLayout.LayoutParams(800, 600);
        textView5.setLayoutParams(layoutParamsta5);
        //setup touch listener
        textView5.setOnTouchListener(new CustomTouchListener());

        RndmLoc(textView1, 600,600,150,100,200);
        RndmLoc(textView2, 600,600,150,100,200);
        RndmLoc(textView3, 600,600,150,100,200);
        RndmLoc(textView4, 600,600,150,100,200);
        RndmLoc(textView5, 600,600,150,100,200);
        return binding.getRoot();
    }

    //set answers to random position outside the answer field
    private void RndmLoc(View v, int scrWidth, int scrHeight, int lpWidth, int lpHeight, int pad) {

        int randomNumX1 = (int) (Math.random() * scrWidth) + 1;
        int randomNumY1 = (int) (Math.random() * scrHeight) + 1;

        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) v.getLayoutParams();
        layoutParams1.width = lpWidth;
        layoutParams1.height = lpHeight;
        layoutParams1.leftMargin = randomNumX1 - xDelta;
        layoutParams1.topMargin = randomNumY1 - yDelta + pad;
        layoutParams1.rightMargin = 0;
        layoutParams1.bottomMargin = 0;
        v.setLayoutParams(layoutParams1);
    }

    //move answers left is an answer on answers field has been chosen
    private void MoveLoc(int a){
        if (ans1 > a) {
            ans1--;
            RelativeLayout.LayoutParams tv1lp = (RelativeLayout.LayoutParams) textView1.getLayoutParams();
            tv1lp.leftMargin = tv1lp.leftMargin - 170;
            textView1.setLayoutParams(tv1lp);
        }
        if (ans2 > a) {
            ans2--;
            RelativeLayout.LayoutParams tv2lp = (RelativeLayout.LayoutParams) textView2.getLayoutParams();
            tv2lp.leftMargin = tv2lp.leftMargin - 170;
            textView2.setLayoutParams(tv2lp);
        }
        if (ans3 > a) {
            ans3--;
            RelativeLayout.LayoutParams tv3lp = (RelativeLayout.LayoutParams) textView3.getLayoutParams();
            tv3lp.leftMargin = tv3lp.leftMargin - 170;
            textView3.setLayoutParams(tv3lp);
        }
        if (ans4 > a) {
            ans4--;
            RelativeLayout.LayoutParams tv4lp = (RelativeLayout.LayoutParams) textView4.getLayoutParams();
            tv4lp.leftMargin = tv4lp.leftMargin - 170;
            textView4.setLayoutParams(tv4lp);
        }
        if (ans5 > a) {
            ans5--;
            RelativeLayout.LayoutParams tv5lp = (RelativeLayout.LayoutParams) textView5.getLayoutParams();
            tv5lp.leftMargin = tv5lp.leftMargin - 170;
            textView5.setLayoutParams(tv5lp);
        }

    }

    //listener which allow to drag answer and drop them. If answer is dropped on the answer field it will be last answer
    private class CustomTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();

            String fullViewName = getResources().getResourceName(v.getId());
            String viewName = fullViewName.substring(fullViewName.lastIndexOf("/") + 1);

            RelativeLayout.LayoutParams layoutParams;

            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();

                    xDelta = X - lParams.leftMargin;
                    yDelta = Y - lParams.topMargin;
                    if (lParams.topMargin < 200) {
                        ansCount--;
                        if (viewName.equals("textAns1")) {
                            MoveLoc(ans1);
                            ans1 = 0;
                        }
                        if (viewName.equals("textAns2")) {
                            MoveLoc(ans2);
                            ans2 = 0;
                        }
                        if (viewName.equals("textAns3")) {
                            MoveLoc(ans3);
                            ans3 = 0;
                        }
                        if (viewName.equals("textAns4")) {
                            MoveLoc(ans4);
                            ans4 = 0;
                        }
                        if (viewName.equals("textAns5")) {
                            MoveLoc(ans5);
                            ans5 = 0;
                        }
                    }
                    break;

                case MotionEvent.ACTION_UP:

                    layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    if (layoutParams.topMargin < 200)
                    {
                        layoutParams.topMargin = 10;
                        layoutParams.leftMargin = ansCount * 170;
                        v.setLayoutParams(layoutParams);
                        ansCount++;
                        if (viewName.equals("textAns1")) ans1 = ansCount;
                        if (viewName.equals("textAns2")) ans2 = ansCount;
                        if (viewName.equals("textAns3")) ans3 = ansCount;
                        if (viewName.equals("textAns4")) ans4 = ansCount;
                        if (viewName.equals("textAns5")) ans5 = ansCount;
                    }
                    else Toast.makeText(PrioritizeFive.this.getContext(), "Image is on new Location!", Toast.LENGTH_SHORT).show();
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    layoutParams.leftMargin = X - xDelta;
                    layoutParams.topMargin = Y - yDelta;
                    layoutParams.rightMargin = 0;
                    layoutParams.bottomMargin = 0;
                    v.setLayoutParams(layoutParams);
                    break;

            }
            relativeLayout.invalidate();
            return true;
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseHandler db = new DatabaseHandler(this.getContext());

        //get quiz id from global variable
        singleToneClass singleToneClass = com.example.quiz.singleToneClass.getInstance();
        int quiz_id = singleToneClass.getData();

        DatabaseQuestionHandler dbq = new DatabaseQuestionHandler(this.getContext());
        List<Question> queForFrag = dbq.getAllQuestionInQuiz(quiz_id);

        Question ques1 = queForFrag.get(0);
        Question ques2 = queForFrag.get(1);
        Question ques3 = queForFrag.get(2);
        Question ques4 = queForFrag.get(3);
        Question ques5 = queForFrag.get(4);

        binding.textAns1.setText(ques1.getQuestionName());
        binding.textAns2.setText(ques2.getQuestionName());
        binding.textAns3.setText(ques3.getQuestionName());
        binding.textAns4.setText(ques4.getQuestionName());
        binding.textAns5.setText(ques5.getQuestionName());

        singleToneClassAns singleToneClassAns = com.example.quiz.singleToneClassAns.getInstance();
        singleToneClassAns.setAns("no answer selected");

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ra = "This is the correct answer!";
                String wa = "This is incorrect answer!";
                if (ques1.getQuestionRight() == ans1 &&
                        ques2.getQuestionRight() == ans2 &&
                        ques3.getQuestionRight() == ans3 &&
                        ques4.getQuestionRight() == ans4 &&
                        ques5.getQuestionRight() == ans5)
                    singleToneClassAns.setAns(ra);
                else
                    singleToneClassAns.setAns(wa);
               NavHostFragment.findNavController(PrioritizeFive.this)
                        .navigate(R.id.action_PrioritizeFive_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}