package Async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;

import utils.JSONUtil;

/**
 * Created by juanlucena on 27/10/16.
 */
public class JSONGetter extends AsyncTask<String, String, JSONObject> {

    private static final String LOGIN_URL = "http://54.174.80.251/data_models/jumbotron/data_for_scene?scene_id=86";
    private Context context;

    JSONUtil jsonUtil = new JSONUtil();

    public JSONGetter(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... args) {

        try {

            HashMap<String, String> params = new HashMap<>();
            JSONObject json = jsonUtil.makeHttpRequest(LOGIN_URL, "GET", params);

            if (json != null) {
                Log.d("JSON result", json.toString());
                return json;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
