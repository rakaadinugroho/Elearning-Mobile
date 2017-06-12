package com.rakaadinugroho.msuryanusatara.core.quiz;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rakaadinugroho.msuryanusatara.R;
import com.rakaadinugroho.msuryanusatara.adapter.NumberAdapter;
import com.rakaadinugroho.msuryanusatara.core.report.ReportActivity;
import com.rakaadinugroho.msuryanusatara.models.Examination;
import com.rakaadinugroho.msuryanusatara.models.Hasil;
import com.rakaadinugroho.msuryanusatara.models.RequestAnswer;
import com.rakaadinugroho.msuryanusatara.models.RequestReport;
import com.rakaadinugroho.msuryanusatara.models.ResponsePackage;
import com.rakaadinugroho.msuryanusatara.models.ResponseReport;
import com.rakaadinugroho.msuryanusatara.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizActivity extends AppCompatActivity implements QuizView {
    private static final String TAG = QuizActivity.class.getSimpleName();
    private List<Examination> examinations;
    private List<Hasil> hasils;
    private ProgressDialog progressDialog;

    private QuizPresenter quizPresenter;
    private int currentNumber;
    private int totalQuestion;
    @BindView(R.id.quiz_number_indicator) RecyclerView quiz_number_indicator;
    @BindView(R.id.quiz_description) TextView quiz_description;
    @BindView(R.id.quiz_loading) ProgressBar quiz_loading;
    @BindView(R.id.quiz_back) AppCompatButton quiz_back;
    @BindView(R.id.quiz_passed) AppCompatButton quiz_passed;
    @BindView(R.id.quiz_next) AppCompatButton quiz_next;
    @BindView(R.id.quiz_answer) RadioGroup quiz_answer;

    @BindView(R.id.quiz_answer_a) RadioButton quiz_a;
    @BindView(R.id.quiz_answer_b) RadioButton quiz_b;
    @BindView(R.id.quiz_answer_c) RadioButton quiz_c;
    @BindView(R.id.quiz_answer_d) RadioButton quiz_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initComponent();
    }

    private void initComponent() {
        quizPresenter   = new QuizPresenter(this);
        //requestAnswers  = new ArrayList<>();
        hasils          = new ArrayList<>();
        progressDialog  = new ProgressDialog(this);

        quiz_number_indicator.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        quiz_number_indicator.addOnItemTouchListener(numberSelectItem());
        quiz_number_indicator.setItemAnimator(new DefaultItemAnimator());
        // Load Data;
        quizPresenter.loadExamination(getIntent().getStringExtra("id"));
    }
    private String getAnswerLabel(String answer_data){
        String hasil = null;
        answer_data = answer_data.trim();
        if (answer_data.equalsIgnoreCase(examinations.get(currentNumber).getAnswer().getA().toString().trim()))
            hasil = "A";
        else if (answer_data.equalsIgnoreCase(examinations.get(currentNumber).getAnswer().getB().toString().trim()))
            hasil = "B";
        else if (answer_data.equalsIgnoreCase(examinations.get(currentNumber).getAnswer().getC().toString().trim()))
            hasil = "C";
        else if (answer_data.equalsIgnoreCase(examinations.get(currentNumber).getAnswer().getC().toString().trim()))
            hasil = "D";
        else
            hasil = "W";
        return hasil;
    }
    private boolean validateChecked(){
        int selected    = quiz_answer.getCheckedRadioButtonId();
        if (selected == -1){
            return false;
        }else{
            return true;
        }
    }
    private void saveTempQuiz(){
        Hasil hasil = new Hasil();

        int selected    = quiz_answer.getCheckedRadioButtonId();
        RadioButton answer_data = (RadioButton) findViewById(selected);
        String answer_label = getAnswerLabel(answer_data.getText().toString());
        try {
            hasils.get(currentNumber);
            hasil.setJawab(answer_label);
            hasil.setSoalId(examinations.get(currentNumber).getQuestionId().toString());
            hasils.set(currentNumber, hasil);
        }catch (IndexOutOfBoundsException e){
            hasil.setJawab(answer_label);
            hasil.setSoalId(examinations.get(currentNumber).getQuestionId().toString().trim());
            hasils.add(hasil);
        }
        Log.d(TAG, "saveTempQuiz: "+ answer_label);
    }
    private RecyclerItemClickListener numberSelectItem(){
        return new RecyclerItemClickListener(this, quiz_number_indicator, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                currentNumber = position;
                quizPresenter.changeQuiz(currentNumber, examinations);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }
    @OnClick(R.id.quiz_back)
    public void quizBack(){
        if (!validateChecked()){
            Toast.makeText(this, "Please select one of the Answer", Toast.LENGTH_SHORT).show();
            return;
        }
        saveTempQuiz();
        currentNumber = currentNumber==0?0:currentNumber-1;
        quizPresenter.changeQuiz(currentNumber, examinations);
    }
    @OnClick(R.id.quiz_passed)
    public void quizPassed(){
        if (!validateChecked()){
            Toast.makeText(this, "Please select one of the Answer", Toast.LENGTH_SHORT).show();
            return;
        }
        saveTempQuiz();
        createConfirmation();
    }
    @OnClick(R.id.quiz_next)
    public void quizNext(){
        if (!validateChecked()){
            Toast.makeText(this, "Please select one of the Answer", Toast.LENGTH_SHORT).show();
            return;
        }
        saveTempQuiz();
        currentNumber   = currentNumber==(totalQuestion-1)?currentNumber:currentNumber+1;
        quizPresenter.changeQuiz(currentNumber, examinations);
    }
    @Override
    public void loadExamination(ResponsePackage responsePackage) {
        examinations    = responsePackage.getExamination();

        totalQuestion   = responsePackage.getExamination().size();
        List<Integer> numIndicator;
        numIndicator    = new ArrayList<>();
        for (int i=0; i<totalQuestion; i++){
            numIndicator.add(i);
        }
        currentNumber   = 0;
        // Set Soal by Current Number
        quizPresenter.changeQuiz(currentNumber, examinations);
        // Set Number Indicator
        quiz_number_indicator.setAdapter(new NumberAdapter(numIndicator,
                getApplicationContext(),
                R.layout.item_number_quiz));
    }

    @Override
    public void loadExaminationError(String messages) {
        Toast.makeText(this, messages, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        quiz_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        quiz_loading.setVisibility(View.GONE);
    }

    @Override
    public void changeQuiz(Examination examination) {
        // Set Data
        quiz_description.setText(Html.fromHtml(examination.getQuestion().toString()));
        quiz_a.setText(Html.fromHtml(examination.getAnswer().getA().toString()));
        quiz_b.setText(Html.fromHtml(examination.getAnswer().getB().toString()));
        quiz_c.setText(Html.fromHtml(examination.getAnswer().getC().toString()));
        quiz_d.setText(Html.fromHtml(examination.getAnswer().getD().toString()));
        // Check Request Answer
        try {
            hasils.get(currentNumber);
            // Checked
            String myAnswer = hasils.get(currentNumber).getJawab().toString().trim();
            Log.d(TAG, "Jawaban :"+myAnswer);
            Log.d(TAG, "Jawaban :"+hasils.get(currentNumber).getSoalId().toString().trim());

            if ("A".equalsIgnoreCase(myAnswer))
                quiz_a.setChecked(true);
            else if ("B".equalsIgnoreCase(myAnswer))
                quiz_b.setChecked(true);
            else if ("C".equalsIgnoreCase(myAnswer))
                quiz_c.setChecked(true);
            else if ("D".equalsIgnoreCase(myAnswer))
                quiz_d.setChecked(true);

        }catch (IndexOutOfBoundsException e){
            quiz_answer.clearCheck();
        }
    }

    @Override
    public void changeQuizError(String messages) {
        Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void submitExamination(ResponseReport responseReport) {
        Log.d(TAG, "submitExamination: "+responseReport.getExamreport().getCorrect().toString());
        Gson gson   = new Gson();
        Log.d(TAG, "submitExamination: "+ gson.toJson(responseReport).toString());
        if (responseReport.getStatus()){
            Intent intent   = new Intent(QuizActivity.this, ReportActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            /* Set Data */
            intent.putExtra("title", getIntent().getStringExtra("title").toString().trim());
            intent.putExtra("id", getIntent().getStringExtra("id").toString().trim());
            intent.putExtra("correct", responseReport.getExamreport().getCorrect().toString().trim());
            intent.putExtra("grade", responseReport.getExamreport().getGrade().toString().trim());
            intent.putExtra("weight", responseReport.getExamreport().getGradeWeight().toString().trim());
            intent.putExtra("total", responseReport.getExamreport().getTotalExam().toString().trim());
            /* Set Data */
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void submitExaminationError(String messages) {
        Toast.makeText(this, "Error : "+messages, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void submitShowLoading() {
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Pleasewait...");

        progressDialog.show();
    }

    @Override
    public void submitHideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //onBackPressed();
                createConfirmation();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        createConfirmation();
    }

    private void createConfirmation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Apakah Anda Yakin Mengakhiri Ujian Ini ?");

        builder.setPositiveButton("Yess", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                quizPresenter.submitExamination(getIntent().getStringExtra("id"), hasils);
            }
        });

        builder.setNegativeButton("No!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
}
