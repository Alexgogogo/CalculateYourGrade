package com.example.alex.coursegradecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher,SeekBar.OnSeekBarChangeListener, View.OnClickListener{


    private static final String DEBUG_TAG="CourseGrades";

    private static final String ASSIGNMENTS="ASSIGNMENTS";
    private static final String PARTICIPATION="PARTICIPTION";
    private static final String PROJECT="PROJECT";
    private static final String QUIZ="QUIZ";
    private static final String EXAM="EXAM";

    EditText assgEditText,partEditText,quizEditText,projectEditText,examEditText;
    double assgMark,partMark,projMark,quizMark,examMark;
    Button reset;
    SeekBar examSeekBar;
    TextView finalMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assgEditText=(EditText)findViewById(R.id.assEditText);
        assgEditText.addTextChangedListener(this);

        partEditText=(EditText)findViewById(R.id.partEditText);
        partEditText.addTextChangedListener(this);
        //quizEditText=(EditText)findViewById(R.id.ParticipationEditText);

        projectEditText=(EditText)findViewById(R.id.ProjectEditText);
        projectEditText.addTextChangedListener(this);

        quizEditText=(EditText)findViewById(R.id.QuizEditText);
        quizEditText.addTextChangedListener(this);

        examEditText=(EditText)findViewById(R.id.ExamEditText);

        finalMark=(TextView)findViewById(R.id.Final);


        examSeekBar=(SeekBar)findViewById(R.id.seek);
        examSeekBar.setOnSeekBarChangeListener(this);

        reset=(Button)findViewById(R.id.ResetAll);
        reset.setOnClickListener(this);

        updateStandard();
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (assgEditText.isFocused()) {
            try {
                assgMark = Double.parseDouble(assgEditText.getText().toString());
                updateStandard();
            } catch (NumberFormatException e) {
                assgMark = 0.0;
            }
        }

        if (partEditText.isFocused()) {
            try {
                partMark = Double.parseDouble(partEditText.getText().toString());
                updateStandard();
            } catch (NumberFormatException e) {
                partMark = 0.0;
            }
        }

        if (projectEditText.isFocused()) {
            try {
                projMark = Double.parseDouble(projectEditText.getText().toString());
                updateStandard();
            } catch (NumberFormatException e) {
                projMark = 0.0;
            }
        }

        if (quizEditText.isFocused()) {
            try {
                quizMark = Double.parseDouble(quizEditText.getText().toString());
                updateStandard();
            } catch (NumberFormatException e) {
                quizMark = 0.0;
            }
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    examMark=seekBar.getProgress();
        Log.i(DEBUG_TAG,"examMark"+examMark);
        updateExam();
        updateStandard();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    private void updateStandard(){
    double finalMarkValue=assgMark*15/100+partMark*15/100+projMark*20/100+quizMark*20/100+examMark*30/100;
    finalMark.setText(String.format("%.02f",finalMarkValue));
    }

    private void updateExam(){
        examEditText.setText(String.format("%.00f",examMark));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putDouble(ASSIGNMENTS,assgMark);
        outState.putDouble(PROJECT,projMark);
        outState.putDouble(QUIZ,quizMark);
        outState.putDouble(PARTICIPATION,partMark);
        outState.putDouble(EXAM,examMark);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);

        double retrievedAssg =inState.getDouble(ASSIGNMENTS);
        double retrievedPart =inState.getDouble(PARTICIPATION);
        double retrievedProj =inState.getDouble(PROJECT);
        double retrievedQuiz =inState.getDouble(QUIZ);
        double retrievedExam =inState.getDouble(EXAM);

        assgMark=retrievedAssg;
        partMark=retrievedPart;
        projMark=retrievedProj;
        quizMark=retrievedQuiz;
        examMark=retrievedExam;

    }

    public void onClick(View view){
        assgMark=0;
        assgEditText.setText("0");
        partMark=0;
        partEditText.setText("0");
        quizMark=0;
        quizEditText.setText("0");
        projMark=0;
        projectEditText.setText("0");
        examSeekBar.setProgress(80);


    }
}
