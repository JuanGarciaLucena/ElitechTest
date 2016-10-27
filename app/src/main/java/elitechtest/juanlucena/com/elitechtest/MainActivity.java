package elitechtest.juanlucena.com.elitechtest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import Async.JSONGetter;
import utils.JSONUtil;

public class MainActivity extends AppCompatActivity {

    private ImageView mainImageView;
    private Handler handler;
    private List<String> urlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONGetter jsonGetter = new JSONGetter(MainActivity.this);
        mainImageView = (ImageView)findViewById(R.id.mainImageView);
        try {
            JSONObject jsonObject = jsonGetter.execute().get();
            JSONArray jsonArray = jsonObject.getJSONArray("pictures_data");
            //jsonArray = JSONUtil.sortJSONArray(jsonArray);

            urlList = JSONUtil.parseURL(jsonArray, MainActivity.this);
            Picasso.with(MainActivity.this)
                    .load(urlList.get(0))
                    .into(mainImageView);

            handler = new Handler();
            Runnable runnable = new Runnable() {
                int i = 1;

                public void run() {
                    Picasso.with(MainActivity.this)
                            .load(urlList.get(i))
                            .into(mainImageView);
                    i++;
                    if (i > urlList.size() - 1) {
                        i = 0;
                    }
                    handler.postDelayed(this, 10000);
                }
            };
            handler.postDelayed(runnable, 10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
