package tam.pa.galeriislam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import tam.pa.galeriislam.Adapter.ListNabiAdapter;
import tam.pa.galeriislam.Model.DataListNabi;
import tam.pa.galeriislam.anim.CubeOutDepthTransformation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private ListNabiAdapter adapter;
    private List<DataListNabi> arrayList = new ArrayList<>();
    RelativeLayout relativeLayout;
    private EditText et_search;
    ImageView img_search;
    private CubeOutDepthTransformation cubeOutDepthTransformation = new CubeOutDepthTransformation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadJSON();
        viewPager.setPageTransformer(true, cubeOutDepthTransformation);
        relativeLayout.setOnClickListener(this);
    }

    private void init() {
        viewPager = findViewById(R.id.viewPager);
        relativeLayout = findViewById(R.id.relative_search);
        et_search = findViewById(R.id.et_search);
        img_search = findViewById(R.id.img_search);
    }

    private void loadJSON() {
        try {
            InputStream stream = getAssets().open("kisahnabi.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String tContents = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONArray obj = new JSONArray(tContents);
                for (int i = 0; i < obj.length(); i++) {
                    JSONObject temp = obj.getJSONObject(i);
                    arrayList.add(new DataListNabi(temp.getString("name"),
                            temp.getString("thn_kelahiran"),
                            temp.getString("usia"),
                            temp.getString("description"),
                            temp.getString("tmp"),
                            temp.getString("image_url")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ListNabiAdapter adapter =new ListNabiAdapter(this, arrayList);
            viewPager.setAdapter(adapter);

        } catch (IOException ignored) {

        }
    }

    @Override
    public void onClick(View v) {

        if (v == relativeLayout){
            img_search.setVisibility(View.GONE);
            relativeLayout.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.btn_search));
            et_search.setVisibility(View.VISIBLE);
        }
    }
}
