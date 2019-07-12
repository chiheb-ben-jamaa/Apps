package com.android.todolist.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.todolist.R;

import Dialog.DialogTimer_Frgament;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Dashboard extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    TextView add_tasks,close_fragment,choose_time;
    TextView personal,work,meeting,study,shopping;
    EditText tasks_description;

    RelativeLayout conatiner_add_tasks;
    Animation animte_open,animte_exite,animte_rotate;
    Boolean pressed=false;
    Boolean selected_category=false;
    String input_time,input_description,input_category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //dec animation :
        animte_open= AnimationUtils.loadAnimation(this,R.anim.open_animation);
        animte_exite= AnimationUtils.loadAnimation(this,R.anim.exite_animation);
        //animte_rotate= AnimationUtils.loadAnimation(this,R.anim.rotate_clock);



        //dec the tag list :
        personal=(TextView)findViewById(R.id.personal);
        work=(TextView)findViewById(R.id.work);
        meeting=(TextView)findViewById(R.id.meeting);
        study=(TextView)findViewById(R.id.study);
        shopping=(TextView)findViewById(R.id.shopping);

        //dec layer:
        conatiner_add_tasks=(RelativeLayout)findViewById(R.id.conatiner_add_tasks);
        choose_time=(TextView)findViewById(R.id.choose_time);

        //dec the inputs:
        tasks_description=(EditText)findViewById(R.id.tasks_description);



        //dec:
        add_tasks=(TextView)findViewById(R.id.add_tasks);
        add_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add_tasks.startAnimation(animte_rotate);
                conatiner_add_tasks.startAnimation(animte_open);
                conatiner_add_tasks.setVisibility(View.VISIBLE);

            }
        });


        //dec action_compmonets:
        close_fragment=(TextView)findViewById(R.id.close_fragment);
        close_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conatiner_add_tasks.startAnimation(animte_exite);
                conatiner_add_tasks.setVisibility(View.INVISIBLE);
            }
        });


        //dec the selct Time:
        choose_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //show the Time clock :
                DialogFragment timePicker = new DialogTimer_Frgament();
                timePicker.show(getSupportFragmentManager(), "time picker");

            }
        });

        //TODO: get the data from the ViewGroup and send it into the API :
        init();

    }

    private void init() {
        getdata();

    }

    private void getdata() {
        input_description=tasks_description.getText().toString();
        input_time=choose_time.getText().toString();
        //input_category=getCategory();

    }




    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        choose_time.setText("Today : Hour "+ i +":" + i1);

    }





    public void onClickTextview(View view) {

        switch (view.getId()) {

            case R.id.personal: {


                    if (pressed) {

                        personal.setBackgroundResource(R.drawable.ic_personal_select);
                        pressed = true;
                        selected_category = true;


                    } else {
                        personal.setBackgroundResource(R.drawable.ic_personal);
                    }


                    pressed = !pressed;

                break;
            }

            case R.id.work:{



                    if (pressed) {
                        work.setBackgroundResource(R.drawable.ic_work_select);
                        pressed = true;
                        selected_category = true;

                    } else {
                        work.setBackgroundResource(R.drawable.ic_work);

                    }

                    pressed = !pressed;

                    break;
                }





            case R.id.meeting:{


                    if (pressed) {
                        meeting.setBackgroundResource(R.drawable.ic_meeting_select);
                        pressed = true;
                        selected_category = true;

                    } else {
                        meeting.setBackgroundResource(R.drawable.ic_meeting);

                    }


                    pressed = !pressed;

                break;
            }




            case R.id.study:{


                    if (pressed) {
                        study.setBackgroundResource(R.drawable.ic_study_select);
                        pressed = true;
                        selected_category = true;

                    } else {
                        study.setBackgroundResource(R.drawable.ic_study);

                    }


                    pressed = !pressed;

                break;
            }




            case R.id.shopping:{


                    if (pressed) {
                        shopping.setBackgroundResource(R.drawable.ic_shopping_select);
                        pressed = true;
                        selected_category = true;

                    } else {
                        shopping.setBackgroundResource(R.drawable.ic_shopping);
                        selected_category = false;

                    }


                    pressed = !pressed;

                break;
            }


    }


    }
}
