package tam.pa.galeriislam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import tam.pa.galeriislam.DetailActivity;
import tam.pa.galeriislam.Model.DataListNabi;
import tam.pa.galeriislam.R;

public class ListNabiAdapter extends PagerAdapter {
    private Context context;
    private List<DataListNabi> dataListNabi;

    public ListNabiAdapter(Context context, List<DataListNabi> dataListNabi) {
        this.context = context;
        this.dataListNabi = dataListNabi;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.lc_list_nabi, null);
        ImageView img_nabi = v.findViewById(R.id.img_nabi);
        TextView tv_name = v.findViewById(R.id.tv_name);
        TextView tv_city = v.findViewById(R.id.tv_city);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataListNabi.get(position).getGambar())
                .error(R.drawable.isa)
                .into(img_nabi);
        img_nabi.setImageResource(R.drawable.isa);
        tv_name.setText(dataListNabi.get(position).getNama());
        tv_city.setText(dataListNabi.get(position).getTempat());
        container.addView(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("nama", dataListNabi.get(position).getNama());
                intent.putExtra("usia", dataListNabi.get(position).getUsia());
                intent.putExtra("tempat", dataListNabi.get(position).getTempat());
                intent.putExtra("deskripsi", dataListNabi.get(position).getDescripsi());
                intent.putExtra("gambar", dataListNabi.get(position).getGambar());
                context.startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public int getCount() {
        return dataListNabi.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
