package com.philipstudio.themes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.tabs.TabLayout;
import com.philipstudio.themes.adapter.DiscoverAdapter;
import com.philipstudio.themes.adapter.SliderAdapter;
import com.philipstudio.themes.model.Image;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    RecyclerView rVListImageDiscover;
    ViewPager vPSlider;
    TabLayout tabLayoutIndicator;
    Spinner spinnerCategory;
    Timer timer;

    ArrayList<Image> imageList;
    String[] categoryArray = {"Anime", "Scenery", "Beautiful Girl", "Image One", "Image Two", "Category",
            "Category One", "Category Two"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        imageList = new ArrayList<>();

        setUpViewPagerSlider(imageList);

        setUpRecyclerView(imageList);

        setUpSpinnerCategory();
        tabLayoutIndicator.setupWithViewPager(vPSlider, true);

        timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
    }

    private void setUpSpinnerCategory(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoryArray);
        spinnerCategory.setAdapter(arrayAdapter);
    }

    private void setUpRecyclerView(ArrayList<Image> arrayList){
        rVListImageDiscover.setHasFixedSize(true);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        rVListImageDiscover.setLayoutManager(layoutManager);

        DiscoverAdapter adapter = new DiscoverAdapter(arrayList, this);
        rVListImageDiscover.setAdapter(adapter);
    }

    private void setUpViewPagerSlider(ArrayList<Image> arrayList){
        SliderAdapter adapter = new SliderAdapter(arrayList, this);
        vPSlider.setAdapter(adapter);
    }

    private void initView(){
        vPSlider = findViewById(R.id.view_pager_slider);
        rVListImageDiscover = findViewById(R.id.recyclerview_list_image_discover);
        tabLayoutIndicator = findViewById(R.id.tab_layout_indicator);
        spinnerCategory = findViewById(R.id.spinner_category);
    }

    public class SliderTimer extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (vPSlider.getCurrentItem() < imageList.size() - 1){
                        vPSlider.setCurrentItem(vPSlider.getCurrentItem() + 1);
                    }
                    else{
                        vPSlider.setCurrentItem(0);
                    }
                }
            });
        }
    }
}