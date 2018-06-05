package tbo.ynov.com.projetandroidtbo.Interfaces;

import com.android.volley.VolleyError;

import org.json.JSONException;

/**
 * Created by Trax6 on 05/06/2018.
 */

public interface IVolleyCallback {
    void onSuccessResponse(String result) throws JSONException;
    void onErrorResponse(VolleyError error);
}
