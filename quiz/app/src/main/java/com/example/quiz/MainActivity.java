package com.example.quiz;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quiz.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseHandler db = new DatabaseHandler(this);

        //deleting all existing quiz
        List<Quiz> allQuiz = db.getAllQuizes();
        for (Quiz dq : allQuiz)
        {
            db.deleteQuiz(dq);
        }

        // Inserting Quizes
        db.addQuiz(new Quiz(1,"What is my name?", 3,  "One Answer"));
        db.addQuiz(new Quiz(2,"What is my favorite dish?", 3,  "One Answer"));
        db.addQuiz(new Quiz(3,"Who is my beloved wife?", 3,  "One Answer"));

        //db.addQuiz(new Quiz("The second quiz", 4,  "Few Answers"));
        //db.addQuiz(new Quiz("The third quiz", 7,  "Try to prioritize"));

        DatabaseQuestionHandler dbq = new DatabaseQuestionHandler(this);

        //deleting all existing quiz
        List<Question> allQuestion = dbq.getAllQuestion();
        for (Question dqs : allQuestion)
        {
            dbq.deleteQuestion(dqs);
        }
        // Inserting Question
        dbq.addQuestion(new Question(1,1,"Iurii",0));
        dbq.addQuestion(new Question(2,1,"Ivan",1));
        dbq.addQuestion(new Question(3,1,"Alexey",0));

        dbq.addQuestion(new Question(4,2,"Mimosa",1));
        dbq.addQuestion(new Question(5,2,"Herring under a fur coat",0));
        dbq.addQuestion(new Question(6,2,"Olivier",0));

        dbq.addQuestion(new Question(7,3,"Abel",0));
        dbq.addQuestion(new Question(8,3,"Sherry",0));
        dbq.addQuestion(new Question(9,3,"Liudmila",1));

        singleToneClass singleToneClass = com.example.quiz.singleToneClass.getInstance();
        singleToneClass.setData(0); //for counter
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}