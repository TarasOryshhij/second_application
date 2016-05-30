package com.taras.secondapplication.activities.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taras.secondapplication.R;
import com.taras.secondapplication.models.FacebookModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    @BindView(R.id.iv_profile_icon)
    ImageView mIvProfileIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    private ProfilePresenterImpl mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        initPresenter();
        makeToolbarAndArrow();
        loadDataFromDb();
    }

    /**
     * Init presenter for current activity.
     */
    @Override
    public void initPresenter() {
        mPresenter = new ProfilePresenterImpl(this);
    }

    /**
     * Close current activity when back pressed.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    /**
     * Initialize toolbar and arrow in current activity.
     */
    @Override
    public void makeToolbarAndArrow() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.nav_profile);
        }
    }

    /**
     * Create FacebookModel from database.
     */
    @Override
    public void loadDataFromDb() {
        mPresenter.loadData();
    }

    /**
     * Set values from FacebookModel to views.
     */
    @Override
    public void setDataOnView(FacebookModel facebookModel) {
        if(facebookModel != null) {
            Picasso.with(getApplicationContext())
                    .load(facebookModel.getPicture().getData().getUrl())
                    .fit()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mIvProfileIcon);

            mTvName.setText(facebookModel.getName());
            mTvId.setText(facebookModel.getId());
            mTvEmail.setText(facebookModel.getEmail());
        }else{
            mTvName.setText(R.string.prf_empty);
            mTvId.setText(R.string.prf_empty);
            mTvEmail.setText(R.string.prf_empty);
        }
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
