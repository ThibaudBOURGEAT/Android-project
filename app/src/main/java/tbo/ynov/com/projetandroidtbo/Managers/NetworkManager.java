package tbo.ynov.com.projetandroidtbo.Managers;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tbo.ynov.com.projetandroidtbo.Interfaces.IVolleyCallback;
import tbo.ynov.com.projetandroidtbo.Models.School;
import tbo.ynov.com.projetandroidtbo.Models.User;

import static android.content.Context.MODE_PRIVATE;

public class NetworkManager {

    public static void authentication(final User user, Context context, final IVolleyCallback callback){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://schoolz-api.herokuapp.com/api/v1/users/sign_in";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            callback.onSuccessResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onErrorResponse(error);
                    }
        }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", user.getLogin());
                params.put("password", user.getPassword());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public static void createSchool(final School school, final Context context, final IVolleyCallback callback){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://schoolz-api.herokuapp.com/api/v1/schools/";
        SharedPreferences sharedPreferences = null;
        final SharedPreferences finalSharedPreferences = sharedPreferences;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            callback.onSuccessResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onErrorResponse(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  headers = new HashMap<>();
                SharedPreferences prefs = context.getSharedPreferences("login", MODE_PRIVATE);
                headers.put("AUTHORIZATION", "123");
                headers.put("Content-Type", "application/json");
                return headers;
            }
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", school.getName());
                params.put("address", school.getAddress());
                params.put("zip_code", school.getZip_code());
                params.put("city", school.getCity());
                params.put("opening_hours", school.getOpennings());
                params.put("phone", school.getPhone());
                params.put("email", school.getEmail());
                params.put("latitude", school.getLatitude());
                params.put("longtitude", school.getLongitude());
                params.put("students_count", school.getNbStudent());
                params.put("status", school.getStatus());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public static List<School> SchoolsList;

    public void getAllSchool (final Activity activity, final IVolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(activity);

        StringRequest postRequest = new StringRequest(Request.Method.GET, "https://schoolz-api.herokuapp.com/api/v1/schools",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            SchoolsList = new ArrayList<>();
                            JSONObject jsonResponse = new JSONObject(response);
                            convertSchoolJsonArrayToSchoolList(jsonResponse.getJSONArray(("schools")));
                            callback.onSuccessResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity, "La récupération des écolese échoué", Toast.LENGTH_LONG).show();
                        callback.onErrorResponse(error);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String>  params = new HashMap<String, String>();
                SharedPreferences prefs = activity.getSharedPreferences("login", MODE_PRIVATE);
                params.put("AUTHORIZATION", "123");
                return params;
            }
        };
        queue.add(postRequest);
    }

    public void getPosSchool (final Activity activity, final IVolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(activity);

        StringRequest postRequest = new StringRequest(Request.Method.GET, "https://schoolz-api.herokuapp.com/api/v1/schools",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            SchoolsList = new ArrayList<>();
                            JSONObject jsonResponse = new JSONObject(response);
                            convertSchoolJsonArrayToSchoolList(jsonResponse.getJSONArray(("schools")));
                            callback.onSuccessResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity, "La récupération des écolese échoué", Toast.LENGTH_LONG).show();
                        callback.onErrorResponse(error);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String>  params = new HashMap<String, String>();
                SharedPreferences prefs = activity.getSharedPreferences("login", MODE_PRIVATE);
                params.put("AUTHORIZATION", "123");
                return params;
            }
        };
        queue.add(postRequest);
    }

    private void convertSchoolJsonArrayToSchoolList(JSONArray jsonArray) {

        for (int i = 0 ; i < jsonArray.length() ; i++)  {

            try {
                JSONObject school = (JSONObject) jsonArray.get(i);

                this.SchoolsList.add(new School(
                        school.getInt("id"),
                        school.getString("name"),
                        school.getString("address"),
                        school.getString("zip_code"),
                        school.getString("city"),
                        school.getString("opening_hours"),
                        school.getString("phone"),
                        school.getString("email"),
                        school.getString("latitude"),
                        school.getString("longitude"),
                        school.getString("students_count"),
                        school.getString("status")
                ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
