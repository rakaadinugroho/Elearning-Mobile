package com.rakaadinugroho.msuryanusatara.core.report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.rakaadinugroho.msuryanusatara.R;
import com.rakaadinugroho.msuryanusatara.core.examination.ExaminationActivity;
import com.rakaadinugroho.msuryanusatara.models.Examination;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flepsik.github.com.progress_ring.ProgressRingView;

public class ReportActivity extends AppCompatActivity {
    @BindView(R.id.report_title)
    TextView report_title;
    @BindView(R.id.report_date)
    TextView report_date;
    @BindView(R.id.report_indicator)
    ProgressRingView report_indicator;
    @BindView(R.id.report_total)
    TextView report_total;
    @BindView(R.id.report_correct)
    TextView report_correct;
    @BindView(R.id.report_grade)
    TextView report_grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        this.setTitle("Laporan Pembelajaran");
        initData();
    }

    private void initData() {
        report_title.setText(getIntent().getStringExtra("title"));
        report_date.setText("Minggu, 4 Juni 2017");
        float data  = Float.valueOf(getIntent().getStringExtra("weight").toString().trim())/100;
        report_indicator.setProgress(data);
        report_total.setText("Total : "+ getIntent().getStringExtra("total"));
        report_correct.setText("Benar : "+ getIntent().getStringExtra("correct"));
        report_grade.setText("Bobot : "+getIntent().getStringExtra("weight"));
    }
    @OnClick(R.id.report_tryagai)
    public void moveToExamination(){
        Intent intent   = new Intent(ReportActivity.this, ExaminationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("title", getIntent().getStringExtra("title").trim());
        intent.putExtra("id", getIntent().getStringExtra("id").trim());
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.report_session)
    public void moveToSession(){
        Toast.makeText(this, "Comingsoon", Toast.LENGTH_SHORT).show();
    }
}
