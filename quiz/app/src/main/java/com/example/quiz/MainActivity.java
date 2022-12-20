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

        singleToneClassAns singleToneClassAns = com.example.quiz.singleToneClassAns.getInstance();
        singleToneClassAns.setAns("Welcome ");

        DatabaseHandler db = new DatabaseHandler(this);
        DatabaseQuestionHandler dbq = new DatabaseQuestionHandler(this);

        //check if databases exist
        if (db.getQuizCount() == 0) {

//        List<Quiz> allQuiz = db.getAllQuizes();
//        for (Quiz dq : allQuiz)
//        {
//            db.deleteQuiz(dq);
//        }

            // Inserting Quizes
            db.addQuiz(new Quiz(1, "What is creator name?", 3, "One Answer"));
            db.addQuiz(new Quiz(2, "What is creator favorite dish?", 3, "One Answer"));
            db.addQuiz(new Quiz(3, "Who is creator beloved wife?", 3, "One Answer"));
            db.addQuiz(new Quiz(4, "Where is the ocean?", 4, "One Answer"));
            db.addQuiz(new Quiz(5, "The largest continent", 5, "One Answer"));
            db.addQuiz(new Quiz(6, "Who will come for the new year?", 6, "One Answer"));
            db.addQuiz(new Quiz(7, "The largest island", 7, "One Answer"));
            db.addQuiz(new Quiz(8, "Choose all correct statements", 4, "Few Answers"));
            db.addQuiz(new Quiz(9, "Choose all correct statements 5", 5, "Few Answers"));
            db.addQuiz(new Quiz(10, "Choose all correct statements 6", 6, "Few Answers"));
            db.addQuiz(new Quiz(11, "Choose all correct statements 7", 7, "Few Answers"));
            db.addQuiz(new Quiz(12, "Prioritize words", 4, "Prioritize"));
            db.addQuiz(new Quiz(13, "Prioritize words", 5, "Prioritize"));
            db.addQuiz(new Quiz(14, "Prioritize words", 6, "Prioritize"));
            db.addQuiz(new Quiz(15, "Prioritize words", 7, "Prioritize"));


            //deleting all existing quiz
//        List<Question> allQuestion = dbq.getAllQuestion();
//        for (Question dqs : allQuestion)
//        {
//            dbq.deleteQuestion(dqs);
//        }

            // Inserting Question
            dbq.addQuestion(new Question(1, 1, "Iurii", 0));
            dbq.addQuestion(new Question(2, 1, "Ivan", 1));
            dbq.addQuestion(new Question(3, 1, "Alexey", 0));

            dbq.addQuestion(new Question(4, 2, "Mimosa", 1));
            dbq.addQuestion(new Question(5, 2, "Herring under a fur coat", 0));
            dbq.addQuestion(new Question(6, 2, "Olivier", 0));

            dbq.addQuestion(new Question(7, 3, "Abel", 0));
            dbq.addQuestion(new Question(8, 3, "Sherry", 0));
            dbq.addQuestion(new Question(9, 3, "Liudmila", 1));

            dbq.addQuestion(new Question(10, 4, "East", 1));
            dbq.addQuestion(new Question(11, 4, "West", 0));
            dbq.addQuestion(new Question(12, 4, "North", 0));
            dbq.addQuestion(new Question(13, 4, "South", 0));

            dbq.addQuestion(new Question(14, 5, "Europe", 0));
            dbq.addQuestion(new Question(15, 5, "Asia", 1));
            dbq.addQuestion(new Question(16, 5, "South America", 0));
            dbq.addQuestion(new Question(17, 5, "North America", 0));
            dbq.addQuestion(new Question(18, 5, "Australia", 0));

            dbq.addQuestion(new Question(19, 6, "Satana", 0));
            dbq.addQuestion(new Question(20, 6, "Putin", 0));
            dbq.addQuestion(new Question(21, 6, "Ded Moroz", 0));
            dbq.addQuestion(new Question(22, 6, "St. Claus", 1));
            dbq.addQuestion(new Question(23, 6, "Uncle Benz", 0));
            dbq.addQuestion(new Question(24, 6, "Mario", 0));

            dbq.addQuestion(new Question(25, 7, "Negros", 0));
            dbq.addQuestion(new Question(26, 7, "Crete", 0));
            dbq.addQuestion(new Question(27, 7, "Madagascar", 0));
            dbq.addQuestion(new Question(28, 7, "Zelenen'kiy", 0));
            dbq.addQuestion(new Question(29, 7, "Phangan", 0));
            dbq.addQuestion(new Question(30, 7, "Grenlandia", 0));
            dbq.addQuestion(new Question(31, 7, "Australia", 1));

            dbq.addQuestion(new Question(32, 8, "Putin is devil", 0));
            dbq.addQuestion(new Question(33, 8, "There are very hot in Africa", 1));
            dbq.addQuestion(new Question(34, 8, "SOLID id possible", 1));
            dbq.addQuestion(new Question(35, 8, "People are immortal", 0));

            dbq.addQuestion(new Question(36, 9, "Expression is true", 0));
            dbq.addQuestion(new Question(37, 9, "Afternoon is 0pm", 0));
            dbq.addQuestion(new Question(38, 9, "Nothing matters", 0));
            dbq.addQuestion(new Question(39, 9, "Earth is an ellipsoid", 1));
            dbq.addQuestion(new Question(40, 9, "Gravity is a force of mutual attraction between two objects that both have mass or energy", 1));

            dbq.addQuestion(new Question(41, 10, "Safety first", 1));
            dbq.addQuestion(new Question(42, 10, "No money no honey", 0));
            dbq.addQuestion(new Question(43, 10, "All you need is love", 0));
            dbq.addQuestion(new Question(44, 10, "Whale sharks are safe", 1));
            dbq.addQuestion(new Question(45, 10, "Mars is located beyond the asteroid belt", 0));
            dbq.addQuestion(new Question(46, 10, "In the heat, you need to drink a lot", 1));

            dbq.addQuestion(new Question(47, 11, "English is the most popular in the world", 0));
            dbq.addQuestion(new Question(48, 11, "We don't know anything about the wars in South America", 1));
            dbq.addQuestion(new Question(49, 11, "Venezuela is a very poor country", 1));
            dbq.addQuestion(new Question(50, 11, "In Brazil, many people are engaged in capoeira", 1));
            dbq.addQuestion(new Question(51, 11, "Soap operas are mostly from Latin America", 0));
            dbq.addQuestion(new Question(52, 11, "Argentina is a federal constitutional republic and representative democracy", 1));
            dbq.addQuestion(new Question(53, 11, "10% of the world's population lives in the Southern Hemisphere", 1));

            dbq.addQuestion(new Question(54, 12, "name", 2));
            dbq.addQuestion(new Question(55, 12, "Ivan", 4));
            dbq.addQuestion(new Question(56, 12, "My", 1));
            dbq.addQuestion(new Question(57, 12, "is", 3));

            dbq.addQuestion(new Question(58, 13, "get", 3));
            dbq.addQuestion(new Question(59, 13, "this", 4));
            dbq.addQuestion(new Question(60, 13, "can", 2));
            dbq.addQuestion(new Question(61, 13, "job", 5));
            dbq.addQuestion(new Question(62, 13, "You", 1));

            dbq.addQuestion(new Question(63, 14, "Something", 2));
            dbq.addQuestion(new Question(64, 14, "Get", 1));
            dbq.addQuestion(new Question(65, 14, "out", 3));
            dbq.addQuestion(new Question(66, 14, "your", 5));
            dbq.addQuestion(new Question(67, 14, "of", 4));
            dbq.addQuestion(new Question(68, 14, "system", 6));

            dbq.addQuestion(new Question(69, 15, "Bite", 1));
            dbq.addQuestion(new Question(70, 15, "off", 2));
            dbq.addQuestion(new Question(71, 15, "more", 3));
            dbq.addQuestion(new Question(72, 15, "than", 4));
            dbq.addQuestion(new Question(73, 15, "you", 5));
            dbq.addQuestion(new Question(74, 15, "can", 6));
            dbq.addQuestion(new Question(75, 15, "chew", 7));
        }
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

        int id = item.getItemId();

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