package tbo.ynov.com.projetandroidtbo.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tbo.ynov.com.projetandroidtbo.Models.School;
import tbo.ynov.com.projetandroidtbo.R;

/**
 * Created by Trax6 on 08/06/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<School> schoolsList;
    private Activity activity;

    public RecyclerAdapter(List<School> schoolsList, Activity activity) {
        this.schoolsList = schoolsList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_school_list, parent, false);
        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int orange = ContextCompat.getColor(activity, R.color.orangeColor);
        int green = ContextCompat.getColor(activity, R.color.greenColor);
        int red = ContextCompat.getColor(activity, R.color.redColor);

        School school = schoolsList.get(position);

        holder.name.setText(school.getName());
        holder.address.setText(school.getAddress());
        holder.studentCount.setText(new StringBuilder().append(String.valueOf(school.getStudentsCount())).append("élèves").toString());

        if (school.getStudentsCount() < 50) {
            holder.background.setBackground(new ColorDrawable(red));
            holder.thumbImage.setImageResource(R.drawable.koicone);
        } else if (school.getStudentsCount() >= 50 && school.getStudentsCount() < 200) {
            holder.background.setBackground(new ColorDrawable(orange));
            holder.thumbImage.setImageResource(R.drawable.okicone);
        } else {
            holder.background.setBackground(new ColorDrawable(green));
            holder.thumbImage.setImageResource(R.drawable.okicone);
        }
    }

    @Override
    public int getItemCount() {
        return schoolsList.size();
    }
}
