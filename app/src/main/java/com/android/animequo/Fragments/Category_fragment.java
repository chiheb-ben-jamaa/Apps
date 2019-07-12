package com.android.animequo.Fragments;

import android.animation.Animator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.animequo.Activities.Feed;
import com.android.animequo.R;

public class Category_fragment extends Fragment {
    public static final String TAG="Category_Fragment";
  public interface OnSendDataHost{
        void set_animation_copied_layer(boolean clicked);
    }
    public OnSendDataHost mOnSendDataHost;



  //TODO: Init the Lottie Animation :
    com.airbnb.lottie.LottieAnimationView like_item_lottie1,like_item_lottie,loading_card_view2,loading_card_view1;
    public boolean clicked=false;
    TextView text_item;
    RelativeLayout content_card2,content_card1;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_category_fragment , container,false);

        //decalre the var :
        like_item_lottie=(com.airbnb.lottie.LottieAnimationView)view.findViewById(R.id.like_item_lottie);
        like_item_lottie1=(com.airbnb.lottie.LottieAnimationView)view.findViewById(R.id.like_item_lottie1);
        loading_card_view2=(com.airbnb.lottie.LottieAnimationView)view.findViewById(R.id.loading_card_view2);
        loading_card_view1=(com.airbnb.lottie.LottieAnimationView)view.findViewById(R.id.loading_card_view1);

        content_card2=(RelativeLayout)view.findViewById(R.id.content_card2);
        content_card1=(RelativeLayout)view.findViewById(R.id.content_card1);



        final CardView item=(CardView)view.findViewById(R.id.card_view);
        text_item=(TextView)view.findViewById(R.id.text_item);
        item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                clicked=!clicked;
                mOnSendDataHost.set_animation_copied_layer(clicked);

                //TODO: copy the Text :
                CopyToClipboard(text_item.getText().toString());
                return false;
            }
        });


        //init animation:
        init_like_animation();
        return  view ;

    }

    private void init_like_animation() {
        like_item_lottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //init the defoult icon animation :
                like_item_lottie.setBackground(null);
                like_item_lottie.setScaleX(30);
                like_item_lottie.setScaleY(30);
                like_item_lottie.playAnimation();

            }
        });

        like_item_lottie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //init the defoult icon animation :
                like_item_lottie1.setBackground(null);
                like_item_lottie1.playAnimation();

            }
        });
    }

    private void init_recyucleur_animation(){

        loading_card_view1.playAnimation();
        loading_card_view1.setVisibility(View.VISIBLE);
       loading_card_view1.addAnimatorListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animator) {
               //TODO: set the animation visible :


           }

           @Override
           public void onAnimationEnd(Animator animator) {
               //TODO: set the animation visible :
                loading_card_view1.setVisibility(View.INVISIBLE);

           }

           @Override
           public void onAnimationCancel(Animator animator) {

           }

           @Override
           public void onAnimationRepeat(Animator animator) {

           }
       });
    }
    public void CopyToClipboard(String copyText) {

        ClipboardManager clipboard = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("url", copyText);
        clipboard.setPrimaryClip(clip);
        //Toast toast = Toast.makeText(getContext(), "Link is copied", Toast.LENGTH_SHORT);
        //toast.show();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnSendDataHost=(OnSendDataHost)getActivity();
        }catch (ClassCastException e){
            Log.d(TAG, "onAttach: ");
        }
    }
}
