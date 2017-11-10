package com.xwtiger.devrescollect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xwtiger.devrescollect.study.androidapi.AnmitorStudy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private Button btn_start;
    private Button btn_reversal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_reversal = (Button) findViewById(R.id.btn_reversal);

        btn_start.setOnClickListener(this);
        btn_reversal.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                AnmitorStudy.testAnmitor(textView);
                break;
            case R.id.btn_reversal:

                AnmitorStudy.reversalAnmitor(textView,btn_start.getHeight()+10);
                break;
        }
    }


    class TestAnimitor {




    }
}
