package com.taras.secondapplication.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.taras.secondapplication.R;
import com.taras.secondapplication.adapters.PagerAdapter;
import com.taras.secondapplication.fragments.TabListFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TabListFragment.FabCallbacks {

    private DrawerLayout mDrawer;
    private FloatingActionButton mFab;
    private AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        makeFabToolbarNavigation();
        makeTabAndViewPager();
    }

    /**
     * Initialize views in current activity.
     */
    private void initViews() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mAppBarLayout = (AppBarLayout)findViewById(R.id.app_bar);
    }

    /**
     * Initialize and filling TabLayout and ViewPager in current activity.
     */
    private void makeTabAndViewPager() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_in_work));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_complete));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_expects));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), getApplicationContext());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    /**
     * Initialize Toolbar, ActionBarDrawerToggle and NavigationView in current activity.
     */
    private void makeFabToolbarNavigation() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.nav_open, R.string.nav_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_all_appeal_item) {

        } else if (id == R.id.nav_appeal_map_item) {

        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Hide Fab and Tollbar (if Api < 21)  on scroll in ListView.
     */
    @Override
    public void onVisibleFab(boolean visible) {
        if(visible){
            mFab.show();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                mAppBarLayout.setExpanded(true, true);
            }
        }else{
            mFab.hide();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                mAppBarLayout.setExpanded(false, true);
            }
        }
    }
}
