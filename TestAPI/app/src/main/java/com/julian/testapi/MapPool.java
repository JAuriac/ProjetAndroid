package com.julian.testapi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MapPool extends AppCompatActivity {

    RecyclerView mRecyclerView;
    int[] mPlaceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappool);

        mRecyclerView = findViewById(R.id.recyclerViewMap);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MapPool.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mPlaceList = new int[]{R.drawable.arctic_dream_le, R.drawable.augustine_fall_le, R.drawable.bone_temple_le,
                R.drawable.canyon_of_tribulation, R.drawable.concord_le, R.drawable.divergence_le, R.drawable.dusty_gorge,
                R.drawable.efflorescence_le, R.drawable.emerald_city, R.drawable.eternal_empire, R.drawable.ever_dream,
                R.drawable.golden_wall, R.drawable.heavy_artillery_le, R.drawable.jungle_depths_le,
                R.drawable.mementos_le, R.drawable.multiprocessor_le, R.drawable.nekodrec_le,
                R.drawable.nightshade_sc2, R.drawable.old_estate, R.drawable.purity_and_industry,
                R.drawable.rhoskallian_le, R.drawable.rosebud_le, R.drawable.sentinel_le,
                R.drawable.shipwrecked_le_map, R.drawable.simulacrum, R.drawable.troizinia_le,
                R.drawable.whitewater_line_le, R.drawable.zen};

        CustomAdapterMap CustomAdapterMap = new CustomAdapterMap(MapPool.this, mPlaceList);
        mRecyclerView.setAdapter(CustomAdapterMap);
    }
}