package tbo.ynov.com.projetandroidtbo.Services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import tbo.ynov.com.projetandroidtbo.Interfaces.IVolleyCallback;
import tbo.ynov.com.projetandroidtbo.MainActivity;
import tbo.ynov.com.projetandroidtbo.Managers.NetworkManager;
import tbo.ynov.com.projetandroidtbo.Models.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Trax6 on 05/06/2018.
 */

public class AuthenticationService{

    private WeakReference<MainActivity> activity;
    private User user;

    public AuthenticationService(MainActivity act, String login, String password){
        this.activity = new WeakReference<MainActivity>(act);
        this.user = new User(login, password);
    }

    public void authenticate(){
        NetworkManager.authentication(this.user, this.activity.get(), new IVolleyCallback() {
            @Override
            public void onSuccessResponse(String result){
                JSONObject response = null;
                try {
                    response = new JSONObject(result);
                    SharedPreferences prefs = activity.get().getBaseContext().getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("token", response.getString("auth_token"));
                    editor.apply();
                    activity.get().showMenuActivity();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.get(), "Login/Mot de passe éroné.", Toast.LENGTH_LONG).show();
            }
        });
    }

}
