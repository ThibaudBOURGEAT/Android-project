package tbo.ynov.com.projetandroidtbo.Adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tbo.ynov.com.projetandroidtbo.R;

/**
 * Created by Trax6 on 08/06/2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ConstraintLayout background;
    public ImageView thumbImage;
    public TextView address;
    public TextView studentCount;

    public ViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.name_school);
        background = view.findViewById(R.id.back);
        thumbImage = view.findViewById(R.id.hand);
        address = view.findViewById(R.id.address_school);
        studentCount = view.findViewById(R.id.nb_students);
    }
}
