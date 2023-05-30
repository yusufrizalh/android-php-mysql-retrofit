package id.inixindo.androidjavaretrofit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import id.inixindo.androidjavaretrofit.R;
import id.inixindo.androidjavaretrofit.adapters.AdapterOnBoard;
import id.inixindo.androidjavaretrofit.models.ModelOnBoard;

public class OnBoardActivity extends AppCompatActivity {
    private LinearLayout pager_indicator;
    private ViewPager onboard_pager;
    private AdapterOnBoard adapterOnBoard;
    private Button btn_get_started;
    private ImageView[] dots;
    private int dotsCount;
    int previous_pos = 0;
    ArrayList<ModelOnBoard> modelOnBoards = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        initView();
    }

    private void initView() {
        pager_indicator = findViewById(R.id.viewPagerCountDots);
        onboard_pager = findViewById(R.id.pager_introduction);
        btn_get_started = findViewById(R.id.btn_get_started);

        loadOnBoardData();

        adapterOnBoard = new AdapterOnBoard(this, modelOnBoards);
        onboard_pager.setAdapter(adapterOnBoard);
        onboard_pager.setCurrentItem(0);
        onboard_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(OnBoardActivity.this, R.drawable.item_dot_unselected));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(OnBoardActivity.this, R.drawable.item_dot_selected));

                int pos = position + 1;
                if (pos == dotsCount && previous_pos == (dotsCount - 1)) {
                    showAnimations();
                } else if (pos == (dotsCount - 1) && previous_pos == dotsCount) {
                    hideAnimations();
                }
                previous_pos = pos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OnBoardActivity.this, "Welcome aboard!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OnBoardActivity.this, MainActivity.class));
            }
        });

        setUIPageViewController();
    }

    private void setUIPageViewController() {
        dotsCount = adapterOnBoard.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(OnBoardActivity.this, R.drawable.item_dot_unselected));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(8, 0, 8, 0);

            pager_indicator.addView(dots[i], layoutParams);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(OnBoardActivity.this, R.drawable.item_dot_selected));
    }

    public void loadOnBoardData() {
        int[] headers = {R.string.ob_header1, R.string.ob_header2, R.string.ob_header3};
        int[] descriptions = {R.string.ob_desc1, R.string.ob_desc2, R.string.ob_desc3};
        int[] imageIds = {R.drawable.programming_1, R.drawable.programming_2, R.drawable.programming_3};

        for (int i = 0; i < imageIds.length; i++) {
            ModelOnBoard modelOnBoard = new ModelOnBoard();
            modelOnBoard.setImageID(imageIds[i]);
            modelOnBoard.setTitle(getResources().getString(headers[i]));
            modelOnBoard.setDescription(getResources().getString(descriptions[i]));
            modelOnBoards.add(modelOnBoard);
        }
    }

    public void showAnimations() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_up);
        btn_get_started.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                btn_get_started.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btn_get_started.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void hideAnimations() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_down);
        btn_get_started.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btn_get_started.clearAnimation();
                btn_get_started.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
