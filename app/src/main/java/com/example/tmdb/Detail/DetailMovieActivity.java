package com.example.tmdb.Detail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.tmdb.Data.Model.MovieDto;
import com.example.tmdb.R;
import com.example.tmdb.Utilities.Util;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.tmdb.Utilities.Constants.RUTA_IMG_MEDIUM;

public class DetailMovieActivity extends AppCompatActivity implements DetailView {

    private MovieDto movie ;
    private ImageView img;
    private  Toolbar toolbar;
    private CollapsingToolbarLayout collap;
    private TextView year,description,count,populary, laguage;

    private LinearLayout linear_lenguaje, linear_category;
    private LinearLayout list_categori;
    //presentador
    private DetailPresenter presenter;
    private FloatingActionButton fab, fab_add, fab_notifi;

    private View view;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Bundle datos = this.getIntent().getExtras();
        movie = datos.getParcelable("ItemDetail");
        presenter = new DetailPresenter(this, new DetailInteractor(this));
        presenter.onResume();
        presenter.onVideo(String.valueOf(movie.getId()));
        presenter.onDetail(this,String.valueOf(movie.getId()));
    }

    public void IstoolbarPrinciopal() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(view -> {
           finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void RenderView() {

        view =  findViewById(R.id.container_view);
        toolbar =  findViewById(R.id.toolbar_detail);
        collap =  findViewById(R.id.collapsing_toolbar);
        year =  findViewById(R.id.textview_detail_date);
        description =  findViewById(R.id.textview_detail_descripcion);
        count =  findViewById(R.id.textView_detail_count);
        populary =  findViewById(R.id.textView_detail_populary);
        img = findViewById(R.id.toolbar_image);
        fab = findViewById(R.id.fab_play);
        fab_add = findViewById(R.id.floatingActionButton_add);
        fab_notifi = findViewById(R.id.floatingActionButton_noti);
        linear_lenguaje = findViewById(R.id.linear_language);
        laguage = findViewById(R.id.textview_detail_language);
        linear_category = findViewById(R.id.linear_category);
        list_categori = findViewById(R.id.list_categori);
    }

    @Override
    public void RenderData(){

        setSupportActionBar(toolbar);
        IstoolbarPrinciopal();

        year.setText(movie.getRelease_data());
        description.setText(movie.getOverview());
        count.setText(""+movie.getVote_count());
        populary.setText(movie.getPopularity());
        collap.setTitle(movie.getTitle());

        Picasso.get()
                .load(RUTA_IMG_MEDIUM+ movie.getPoster_path())
                .into(img);

        fab_add.setOnClickListener(view -> {
            onMessage("Agregar a la lista");
        });

        fab_notifi.setOnClickListener(view -> {
            onMessage("Notificaciones activadas");
        });
    }

    @Override
    public void onListenerFabPlay(String key) {
        Toast.makeText(this,"Onclick fab", Toast.LENGTH_SHORT).show();
        fab.setOnClickListener(view -> {
            String rutavideo = "https://www.youtube.com/watch?v="+key;
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(rutavideo)));
        });
    }

    @Override
    public void onMessage(String msg) {
        Util.OnAlertaSnackbar(view,msg);
    }

    @Override
    public void showCategorys(String[] array) {
        linear_category.setVisibility(View.VISIBLE);
        for (int i = 0; i <array.length ; i++) {
            TextView text = new TextView(this);
            text.setTextColor(getResources().getColor(R.color.color_title));
            text.setTextSize(18);
            text.setText("* "+array[i]);
            list_categori.addView(text);
        }
    }

    @Override
    public void showLaguage(String lan) {
        linear_lenguaje.setVisibility(View.VISIBLE);
        laguage.setText(lan);
    }

    @Override
    public void Showtagline() {

    }


}
