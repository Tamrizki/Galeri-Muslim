package tam.pa.galeriislam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBar;
    private TextView tv_story,
            tv_city,
            tv_name,
            tv_years;
    private String nama, tempat, usia, gambar, deskripsi;
    private RoundRectCornerImageView img;
    private ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        getDataIntent();
        img_back.setOnClickListener(this);
        collapsingToolbarLayout.setTitle(nama.toString());
        collapsingToolbarLayout.setCollapsedTitleTextColor(this.getResources().getColor(R.color.colorYellow));
        collapsingToolbarLayout.setExpandedTitleColor(this.getResources().getColor(R.color.colorTransparant));

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build().load(gambar)
                .error(R.drawable.isa)
                .into(img);
        tv_city.setText(tempat.toUpperCase());
        tv_years.setText(usia+" Tahun");
        tv_name.setText(nama);
        tv_story.setText(deskripsi.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_story.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }
    }

    private void getDataIntent() {
        nama = getIntent().getStringExtra("nama");
        usia = getIntent().getStringExtra("usia");
        gambar = getIntent().getStringExtra("gambar");
        tempat = getIntent().getStringExtra("tempat");
        deskripsi = getIntent().getStringExtra("deskripsi");
    }

    private void init() {
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        appBar = findViewById(R.id.appBar);
        img = findViewById(R.id.img);
        tv_story = findViewById(R.id.tv_story);
        tv_city = findViewById(R.id.tv_city);
        tv_name = findViewById(R.id.tv_name);
        tv_years = findViewById(R.id.tv_years);
        img_back = findViewById(R.id.img_back);
    }

    @Override
    public void onClick(View v) {
        if (v == img_back){
            onBackPressed();
            finish();
        }
    }
}
