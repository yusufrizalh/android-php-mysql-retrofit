package id.inixindo.androidjavaretrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import id.inixindo.androidjavaretrofit.models.ModelOnBoard;
import id.inixindo.androidjavaretrofit.R;

public class AdapterOnBoard extends PagerAdapter {
    private Context context;
    ArrayList<ModelOnBoard> modelOnBoards = new ArrayList<>();

    public AdapterOnBoard(Context context, ArrayList<ModelOnBoard> modelOnBoards) {
        this.context = context;
        this.modelOnBoards = modelOnBoards;
    }

    @Override
    public int getCount() {
        return modelOnBoards.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.onboard_item, container, false);

        ModelOnBoard modelOnBoard = modelOnBoards.get(position);

        ImageView imageView = itemView.findViewById(R.id.iv_onboard);
        imageView.setBackgroundResource(modelOnBoard.getImageID());
        TextView textViewTitle = itemView.findViewById(R.id.tv_header);
        textViewTitle.setText(modelOnBoard.getTitle());
        TextView textViewContent = itemView.findViewById(R.id.tv_desc);
        textViewContent.setText(modelOnBoard.getDescription());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
