package eg.intercom.hrss.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;

import eg.intercom.hrss.R;
import eg.intercom.hrss.activities.add.NewVacationActivity;

/**
 * Created by Emad.Essam on 10/26/2016.
 */

public class VacationCategoryActivity extends Activity {
TextView annual;
    String annl="Annual leave request";
    String pli="Pilgrimage leave request";
    String deat="Death leave request";
    String mil="Military leave request";
    String mar="Marriage leave request";
    String sick="Emergency leave request";
    String stu="Study leave request";
    LinearLayout plig;
    LinearLayout death;
    LinearLayout military;
    LinearLayout marriage;
    LinearLayout sickness;
    LinearLayout study;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacation_category);
        annual = (TextView) findViewById(R.id.annual);
        plig = (LinearLayout) findViewById(R.id.plig_lay);
        death = (LinearLayout) findViewById(R.id.death_lay);
        military = (LinearLayout) findViewById(R.id.mili_lay);
        marriage = (LinearLayout) findViewById(R.id.mar_lay);
        sickness = (LinearLayout) findViewById(R.id.sick_lay);
        study = (LinearLayout) findViewById(R.id.stu_lay);
        final Intent i = new Intent(VacationCategoryActivity.this, NewVacationActivity.class);
        annual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(i);
            }
        });
        plig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("myann",pli);

                startActivity(i);            }
        });
        death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("myann",deat);
                startActivity(i);
            }
        });
        military.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("myann",mil);
                startActivity(i);
            }
        });
        marriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("myann",mar);
                startActivity(i);
            }
        });
        sickness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("myann",sick);

                startActivity(i);
            }
        });
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("myann",stu);
                startActivity(i);
            }
        });

    }
}
