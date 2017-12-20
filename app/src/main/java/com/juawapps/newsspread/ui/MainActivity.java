package com.juawapps.newsspread.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.juawapps.newsspread.R;
import com.juawapps.newsspread.categories.NewsCategoriesManager;
import com.juawapps.newsspread.categories.NewsCategory;
import com.juawapps.newsspread.ui.news.NewsFragment;

import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {


    private final NewsCategoriesManager mCategoriesManager = new NewsCategoriesManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        final List<NewsCategory> mNewsCategories;

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            mNewsCategories = mCategoriesManager.getCategories();
        }

        @Override
        public Fragment getItem(int position) {

            if (position > mNewsCategories.size()) {
                position = mNewsCategories.size() - 1;
                Timber.w("Out of index access to category view pager! Tried to get %d out of %d categories", position, mNewsCategories.size());
            }

            return NewsFragment.newInstance(mNewsCategories.get(position));
        }

        @Override
        public int getCount() {
            return mNewsCategories.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(mNewsCategories.get(position).getLabel());
        }
    }
}
