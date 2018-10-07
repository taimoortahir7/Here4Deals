package com.umairjabbar.here4deals.Auth;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.umairjabbar.here4deals.MainActivity;
import com.umairjabbar.here4deals.Signup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserAuth {

    private static final String key = "d4c3d66fd0c38547a3c7a4c6bdc29c36911bc030";

    public static void login(String username, String password, final Dialog dialog, final Context reference) {
        String url = Server.IP + "login";

        final JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        JSONObject data = new JSONObject();
        try {
            jsonArray.put(data.put("username", username));
            jsonArray.put(data.put("password", password));
            jsonObject.put("api-key", key);
            jsonObject.put("data", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(reference, "logged in" + response.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(reference, MainActivity.class);
                reference.startActivity(intent);
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(reference, "error" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("error", jsonObject.toString());
                dialog.dismiss();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public static void signup(String username, String email, String password, final Dialog dialog, final Context reference) {
        String url = Server.IP + "registration";

        final JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        JSONObject data = new JSONObject();
        try {
            jsonArray.put(data.put("username", username));
            jsonArray.put(data.put("email", email));
            jsonArray.put(data.put("password", password));
            jsonObject.put("api-key", key);
            jsonObject.put("data", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(reference, "registered!" + response.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(reference, MainActivity.class);
                reference.startActivity(intent);
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(reference, "object : " + jsonObject.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(reference, "error : " + error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("object : ", jsonObject.toString());
                Log.e("error : ", error.toString());
                dialog.dismiss();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
