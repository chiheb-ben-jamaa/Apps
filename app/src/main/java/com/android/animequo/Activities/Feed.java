package com.android.animequo.Activities;


import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.android.animequo.Fragments.About_fragment;
import com.android.animequo.Fragments.Bookmark_fragment;
import com.android.animequo.Fragments.Category_fragment;
import com.android.animequo.Fragments.Trending_fragment;
import com.android.animequo.Logs.Sign_in;
import com.android.animequo.R;
import com.android.animequo.Utils.SectionsPagerAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class Feed extends AppCompatActivity implements
        Category_fragment.OnFragmentInteractionListener,Trending_fragment.OnFragmentInteractionListener,
        Bookmark_fragment.OnFragmentInteractionListener,About_fragment.OnFragmentInteractionListener , Category_fragment.OnSendDataHost {


    private static final String LOG = "Feed Activity";
    com.airbnb.lottie.LottieAnimationView menu_animate, search_animate,check_icon_animation,copied_animation;
    private TextView mini_logo,death_tag;
    public RelativeLayout bottom_Nav_bar,layer_category_tag,layer_search,coiped_layer;
    boolean visible,visible1;
    private EditText search_input;
    android.support.v7.widget.Toolbar toolsbar;
    private int select_nbr;
    private boolean pressed=false;

    Animation animte,animte1,animte_down;

    private TabLayout mTabLayout;
    public ViewPager mViewPager;
    public SectionsPagerAdapter mSectionsPageAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        menu_animate = (com.airbnb.lottie.LottieAnimationView) findViewById(R.id.menu_animate);
        search_animate = (com.airbnb.lottie.LottieAnimationView) findViewById(R.id.search_animate);
        check_icon_animation=(com.airbnb.lottie.LottieAnimationView)findViewById(R.id.check_icon_animation);
        copied_animation=(com.airbnb.lottie.LottieAnimationView)findViewById(R.id.copied_animation);


        //add the animaton for the slide_up animation with manging the visviblete (Visible):
        animte= AnimationUtils.loadAnimation(this,R.anim.fade_animation);
        animte1=AnimationUtils.loadAnimation(this,R.anim.bottom_fade_animation);
        //add the animaton for the slide_down animation with manging the visviblete (Invisible):
        animte_down=AnimationUtils.loadAnimation(this,R.anim.slide_down);





        search_input=(EditText)findViewById(R.id.search_input);
        mini_logo=(TextView)findViewById(R.id.mini_logo);

        //relative layout for the Tablayout:
        bottom_Nav_bar=(RelativeLayout)findViewById(R.id.bottom_Nav_bar);
        layer_search=(RelativeLayout)findViewById(R.id.layer_search);

        //relative layout for the category bottom  pop:
        layer_category_tag = (RelativeLayout) findViewById(R.id.layer_category_tag);
        death_tag=(TextView)findViewById(R.id.death_tag);
        coiped_layer=(RelativeLayout)findViewById(R.id.coiped_layer);
        toolsbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);


        mViewPager = (ViewPager) findViewById(R.id.viewpager_container);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);


        bottom_Nav_bar.startAnimation(animte);
        layer_category_tag.startAnimation(animte);



        init_tablayout();
        set_animation_toolbar();
        set_animation_category_layer();



    }


    @Override
    public void set_animation_copied_layer(boolean clicked) {
        if (clicked == true) {
            if (coiped_layer.getVisibility() == View.INVISIBLE) {
                if ((bottom_Nav_bar.getVisibility() == View.VISIBLE) || (layer_category_tag.getVisibility() == View.VISIBLE) && (layer_search.getVisibility() == View.VISIBLE)) {

                    //TODO: Apply the down animation :
                    bottom_Nav_bar.setVisibility(View.INVISIBLE);
                    layer_search.setVisibility(View.INVISIBLE);
                    layer_category_tag.setVisibility(View.INVISIBLE);
                    //TODO : Add the animation for the Relative layer :
                    coiped_layer.setAnimation(animte1);
                    coiped_layer.setVisibility(View.VISIBLE);
                    copied_animation.playAnimation();
                    TimerCloseLayer_copied();


                }
                else{
                    //TODO : Add the animation for the Relative layer :
                    coiped_layer.setAnimation(animte1);
                    coiped_layer.setVisibility(View.VISIBLE);
                    copied_animation.playAnimation();
                    TimerCloseLayer_copied();

                }
            }
            else {
                //TODO : Add the animation for the Relative layer :
                coiped_layer.startAnimation(animte_down);
                coiped_layer.setVisibility(View.INVISIBLE);
            }

        }
        //else Maybe missing there :



    }



    private void TimerCloseLayer_copied() {
        //close the layer pop after 5 Seconds:
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {

                    //TODO: add the fade out animation :
                    coiped_layer.setVisibility(View.INVISIBLE);
                    copied_animation.startAnimation(animte_down);
                    bottom_Nav_bar.startAnimation(animte);
                    bottom_Nav_bar.setVisibility(View.VISIBLE);


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        },5000);



    }



    private void set_animation_category_layer() {
        death_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:add the changing drawable icon logic here :

                if  (pressed){
                    //this for change the icon image from  the defoulat image into read image icon in on click evnet:
                    v.setBackgroundResource(R.drawable.ic_death_tag_fill);
                    select_nbr++;
                    pressed=true;

                }
                else
                    {
                        v.setBackgroundResource(R.drawable.ic_death_tag);
                }


                pressed = !pressed;



            }

        });
    }

    private void set_animation_toolbar() {


        menu_animate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                //Animate View Group with fade animtion:
                //TransitionManager.beginDelayedTransition(bottom_Nav_bar);

                if  (bottom_Nav_bar.getVisibility()==View.INVISIBLE){
                    //bottom_Nav_bar.setVisibility(View.VISIBLE);
                    bottom_Nav_bar.setVisibility(View.VISIBLE);
                    bottom_Nav_bar.startAnimation(animte);


                    layer_category_tag.setVisibility(View.INVISIBLE);
                    layer_search.setVisibility(View.INVISIBLE);
                }else
                    {
                        bottom_Nav_bar.setVisibility(View.INVISIBLE);
                        bottom_Nav_bar.startAnimation(animte_down);
                    }



                // Play the Animation:
                menu_animate.playAnimation();


            }
        });



        search_animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play the Animation
                search_animate.playAnimation();
                //TODO: Animate the saerch layer :
                init_search_input();


            }
        });

        check_icon_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //init the parms of the animation :
                check_icon_animation.setBackground(null);
                check_icon_animation.playAnimation();
                init_layer_category();


            }
        });

    }

    private void init_layer_category() {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {

                    //TODO: add the fade bootom animation :
                    //TODO : change the Visiblete animation into xml animation :
                    layer_category_tag.setVisibility(View.INVISIBLE);
                    layer_category_tag.startAnimation(animte_down);
                    bottom_Nav_bar.setVisibility(View.VISIBLE);
                    bottom_Nav_bar.startAnimation(animte);
                    //TODO: send the Filter params into the API :


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        },4000);

    }



    private void init_search_input() {
        //TODO add fade animation :*
        if (layer_search.getVisibility() == View.INVISIBLE)
        {
            if ((bottom_Nav_bar.getVisibility() == View.VISIBLE) || (layer_category_tag.getVisibility() == View.VISIBLE)) {
                bottom_Nav_bar.setVisibility(View.INVISIBLE);
                layer_category_tag.setVisibility(View.INVISIBLE);
                layer_search.startAnimation(animte1);
                layer_search.setVisibility(View.VISIBLE);

            } else {
                layer_search.startAnimation(animte1);
                layer_search.setVisibility(View.VISIBLE);

            }
        }
        else {
            layer_search.setVisibility(View.INVISIBLE);
            layer_search.startAnimation(animte_down);
            bottom_Nav_bar.setVisibility(View.VISIBLE);
            bottom_Nav_bar.startAnimation(animte);
        }



    }

    private void init_tablayout() {

        mSectionsPageAdapter=new SectionsPagerAdapter(getSupportFragmentManager());


        ImageView imgView= new ImageView(Feed.this);
        imgView.setImageResource(R.drawable.anim_tab_trending);
        imgView.setPadding(5,5,5,5);
        mTabLayout.getTabAt(0).setCustomView(imgView);




        ImageView imgView1= new ImageView(Feed.this);
        imgView1.setImageResource(R.drawable.anim_tab_category);
        imgView1.setPadding(5,5,5,5);
        mTabLayout.getTabAt(1).setCustomView(imgView1);




        ImageView imgView2= new ImageView(Feed.this);
        imgView2.setImageResource(R.drawable.anim_tab_bookmark);
        imgView2.setPadding(5,5,5,5);
        mTabLayout.getTabAt(2).setCustomView(imgView2);



        ImageView imgView3= new ImageView(Feed.this);
        imgView3.setImageResource(R.drawable.anim_tab_about);
        imgView3.setPadding(5,5,5,5);
        mTabLayout.getTabAt(3).setCustomView(imgView3);


        //open the tab layout with Feed Tab in defoult  fragment in opening :
        //mTabLayout.getTabAt(1).select();






        LinearLayout tabStrip = (LinearLayout) mTabLayout.getChildAt(0);
        tabStrip.getChildAt(1).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {




                visible1 = !visible1;
                if (visible1){

                    bottom_Nav_bar.startAnimation(animte_down);
                    bottom_Nav_bar.setVisibility(View.INVISIBLE);

                    layer_category_tag.startAnimation(animte1);
                    layer_category_tag.setVisibility(View.VISIBLE);


                } else {
                    bottom_Nav_bar.startAnimation(animte);
                    bottom_Nav_bar.setVisibility(View.VISIBLE);

                    layer_category_tag.startAnimation(animte_down);
                    layer_category_tag.setVisibility(View.INVISIBLE);

                }

                return false;
            }
        });





    }

    private void setupViewPager(ViewPager mViewPager) {

        SectionsPagerAdapter Adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        //Adding Thes Fragments to the TabLayout:
        //there is an extra fragment it is Feed_fragment from the tutoriels:
        Adapter.addFragment(new Trending_fragment(),"");
        Adapter.addFragment(new Category_fragment(),"");
        Adapter.addFragment(new Bookmark_fragment(),"");
        Adapter.addFragment(new About_fragment(),"");
        mViewPager.setAdapter(Adapter);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




}