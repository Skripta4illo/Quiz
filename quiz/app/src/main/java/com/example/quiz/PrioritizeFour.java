package com.example.quiz;

import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
// Java code to display the screen size
// import java.lang.Object;
// import java.awt.*;

import androidx.navigation.fragment.NavHostFragment;

import com.example.quiz.databinding.PrioritizeFourBinding;

import java.util.List;

//this code is for prioritizing
public class PrioritizeFour extends Fragment {
    private PrioritizeFourBinding binding;

    //declare ui views
    private RelativeLayout relativeLayout;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    private int xDelta;
    private int yDelta;
    private int ansCount = 0;
    private int ans1 = 0;
    private int ans2 = 0;
    private int ans3 = 0;
    private int ans4 = 0;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = PrioritizeFourBinding.inflate(inflater, container, false);

        //init ui views
        relativeLayout = binding.relative;
        textView1 = binding.textAns1;
        textView2 = binding.textAns2;
        textView3 = binding.textAns3;
        textView4 = binding.textAns4;

        //setup layout params
        RelativeLayout.LayoutParams layoutParamsta1 = new RelativeLayout.LayoutParams(800, 600);
        textView1.setLayoutParams(layoutParamsta1);
        //setup touch listener
        textView1.setOnTouchListener(new CustomTouchListener());

        //setup layout params
        RelativeLayout.LayoutParams layoutParamsta2 = new RelativeLayout.LayoutParams(800, 600);
        textView2.setLayoutParams(layoutParamsta2);
        //setup touch listener
        textView2.setOnTouchListener(new CustomTouchListener());

        //setup layout params
        RelativeLayout.LayoutParams layoutParamsta3 = new RelativeLayout.LayoutParams(800, 600);
        textView3.setLayoutParams(layoutParamsta3);
        //setup touch listener
        textView3.setOnTouchListener(new CustomTouchListener());

        //setup layout params
        RelativeLayout.LayoutParams layoutParamsta4 = new RelativeLayout.LayoutParams(800, 600);
        textView4.setLayoutParams(layoutParamsta4);
        //setup touch listener
        textView4.setOnTouchListener(new CustomTouchListener());

        // getScreenSize() returns the size
        // of the screen in pixels
        //Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        //int width = (int)size.getWidth();

        int width = 600;
        int height = 800;

        int randomNumX1 = (int) (Math.random() * width) + 1;
        int randomNumY1 = (int) (Math.random() * height - 200) + 201;

        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) this.textView1.getLayoutParams();
        layoutParams1.width = 150;
        layoutParams1.height = 100;
        layoutParams1.leftMargin = randomNumX1 - xDelta;
        layoutParams1.topMargin = randomNumY1 - yDelta;
        layoutParams1.rightMargin = 0;
        layoutParams1.bottomMargin = 0;
        textView1.setLayoutParams(layoutParams1);

        int randomNumX2 = (int) (Math.random() * width) + 1;
        int randomNumY2 = (int) (Math.random() * height - 200) + 201;

        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.textView2.getLayoutParams();
        layoutParams2.width = 150;
        layoutParams2.height = 100;
        layoutParams2.leftMargin = randomNumX2 - xDelta;
        layoutParams2.topMargin = randomNumY2 - yDelta;
        layoutParams2.rightMargin = 0;
        layoutParams2.bottomMargin = 0;
        textView2.setLayoutParams(layoutParams2);


        int randomNumX3 = (int) (Math.random() * width) + 1;
        int randomNumY3 = (int) (Math.random() * height - 200) + 201;

        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.textView3.getLayoutParams();
        layoutParams3.width = 150;
        layoutParams3.height = 100;
        layoutParams3.leftMargin = randomNumX3 - xDelta;
        layoutParams3.topMargin = randomNumY3 - yDelta;
        layoutParams3.rightMargin = 0;
        layoutParams3.bottomMargin = 0;
        textView3.setLayoutParams(layoutParams3);

        int randomNumX4 = (int) (Math.random() * width) + 1;
        int randomNumY4 = (int) (Math.random() * height - 200) + 201;

        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.textView4.getLayoutParams();
        layoutParams4.width = 150;
        layoutParams4.height = 100;
        layoutParams4.leftMargin = randomNumX4 - xDelta;
        layoutParams4.topMargin = randomNumY4 - yDelta;
        layoutParams4.rightMargin = 0;
        layoutParams4.bottomMargin = 0;
        textView4.setLayoutParams(layoutParams4);

        return binding.getRoot();
    }


    private class CustomTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();

            RelativeLayout.LayoutParams layoutParams;

            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();

                    xDelta = X - lParams.leftMargin;
                    yDelta = Y - lParams.topMargin;

                    break;

                case MotionEvent.ACTION_UP:

                    layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    if (layoutParams.topMargin < 200)
                    {
                        String fullViewName = getResources().getResourceName(v.getId());
                        String viewName = fullViewName.substring(fullViewName.lastIndexOf("/") + 1);
                        //Toast.makeText(PrioritizeFour.this.getContext(), viewName, Toast.LENGTH_SHORT).show();
                        layoutParams.topMargin = 10;
                        layoutParams.leftMargin = ansCount * 170;
                        v.setLayoutParams(layoutParams);
                        ansCount++;
                        if (viewName.equals("textAns1")) ans1 = ansCount;
                        if (viewName.equals("textAns2")) ans2 = ansCount;
                        if (viewName.equals("textAns3")) ans3 = ansCount;
                        if (viewName.equals("textAns4")) ans4 = ansCount;
                        //Toast.makeText(PrioritizeFour.this.getContext(), viewName + " - " + ansCount, Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(PrioritizeFour.this.getContext(), "Image is on new Location!", Toast.LENGTH_SHORT).show();
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

        binding.textAns1.setText(ques1.getQuestionName());
        binding.textAns2.setText(ques2.getQuestionName());
        binding.textAns3.setText(ques3.getQuestionName());
        binding.textAns4.setText(ques4.getQuestionName());

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
                        ques4.getQuestionRight() == ans4)
                    singleToneClassAns.setAns(ra);
                else
                    singleToneClassAns.setAns(wa);

//                singleToneClassAns.setAns(ques1.getQuestionRight() + " " + ans1 + " / " +
//                            ques2.getQuestionRight() + " " + ans2 + " / " +
//                            ques3.getQuestionRight() + " " + ans3 + " / " +
//                            ques4.getQuestionRight() + " " + ans4);

               NavHostFragment.findNavController(PrioritizeFour.this)
                        .navigate(R.id.action_PrioritizeFour_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}