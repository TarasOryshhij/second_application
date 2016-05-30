package com.taras.secondapplication.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.taras.secondapplication.Const;
import com.taras.secondapplication.R;
import com.taras.secondapplication.activities.profile.ProfileActivity;
import com.taras.secondapplication.adapters.PagerAdapter;
import com.taras.secondapplication.fragment.TabView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView,
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout) DrawerLayout mDrawer;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.pager) ViewPager mViewPager;
    private MainPresenterImpl mPresenter;
    private PagerAdapter mPagerAdapter;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initFabToolbarNavigation();
        makeTabAndViewPager();
        initPresenter();
        loadSectionTabs();
        initFbCallbackManager();
    }

    /**
     * Initialize action settings.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Initialize CallbackManager for Facebook login.
     */
    @Override
    public void initFbCallbackManager() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(this.mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        mPresenter.writeFbToDatabase(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        Log.d("tag", "onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("tag", "onError");
                    }
                });
    }


    @Override
    public void startProfileActivity() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_profile_item){
            startActivity(new Intent(this, ProfileActivity.class));

        }else if(id == R.id.nav_login_item){
            LoginManager.getInstance().logInWithReadPermissions(this, Collections.singletonList(Const.FbConstant.PUBLIC_PROFILE));
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    public void initPresenter() {
        mPresenter = new MainPresenterImpl(this);
    }

    @Override
    public void loadSectionTabs() {
        mPresenter.loadSectionsTabs();
    }

    /**
     * Initialize Toolbar, ActionBarDrawerToggle and NavigationView in current activity.
     */
    @Override
    public void initFabToolbarNavigation() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.nav_open, R.string.nav_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        mToolbar.setNavigationIcon(R.drawable.ic_menu);
    }

    /**
     * Initialize and filling TabLayout and ViewPager in current activity.
     */
    @Override
    public void makeTabAndViewPager() {
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = (Fragment) mPagerAdapter.instantiateItem(mViewPager, position);
                if (fragment instanceof TabView.OnPageSelectedListener) {
                    ((TabView.OnPageSelectedListener) fragment).onShow();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Initialize pager mPagerAdapter in current activity.
     */
    @Override
    public void initPagerAdapter(List<String> listTitleTabs) {
        for (int i=0; i<listTitleTabs.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(listTitleTabs.get(i)));
        }
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mPagerAdapter = new PagerAdapter
                (getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mPagerAdapter);
    }

    /**
     * Close Realm.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
