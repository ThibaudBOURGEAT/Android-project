package tbo.ynov.com.projetandroidtbo.Services;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import tbo.ynov.com.projetandroidtbo.Adapters.RecyclerAdapter;
import tbo.ynov.com.projetandroidtbo.CreateActivity;
import tbo.ynov.com.projetandroidtbo.Interfaces.IVolleyCallback;
import tbo.ynov.com.projetandroidtbo.ListActivity;
import tbo.ynov.com.projetandroidtbo.MainActivity;
import tbo.ynov.com.projetandroidtbo.Managers.NetworkManager;
import tbo.ynov.com.projetandroidtbo.MapsActivity;
import tbo.ynov.com.projetandroidtbo.Models.School;
import tbo.ynov.com.projetandroidtbo.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Trax6 on 07/06/2018.
 */

public class SchoolService {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private NetworkManager networkManager = new NetworkManager();

    public void add(School school, final CreateActivity activity){
        NetworkManager.createSchool(school, activity,new IVolleyCallback() {
            @Override
            public void onSuccessResponse(String result){
                Toast.makeText(activity, "Nouvelle école créer avec succès.", Toast.LENGTH_LONG).show();
                activity.showMenuActivity();
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "Veuillez remplir tous les champs correctement.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showAllSchool(final ListActivity activity){
        networkManager.getAllSchool(activity, new IVolleyCallback() {
            @Override
            public void onSuccessResponse(String result) throws JSONException {
                recyclerView = activity.findViewById(R.id.school_recycle_view);
                final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity.getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                adapter = new RecyclerAdapter(networkManager.SchoolsList , activity);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "Impossible de récuperer les écoles.", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void showAllPos(final MapsActivity activity){
        networkManager.getPosSchool(activity, new IVolleyCallback() {
            @Override
            public void onSuccessResponse(String result) throws JSONException {
                activity.getSchools(networkManager.SchoolsList);
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "Impossible de récuperer les écoles.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
