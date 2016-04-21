package com.taras.secondapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.taras.secondapplication.Const;
import com.taras.secondapplication.ItemOffsetDecoration;
import com.taras.secondapplication.R;
import com.taras.secondapplication.adapters.ImageRecyclerAdapter;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTvTitle;
    private TextView mTvStatus;
    private TextView mTvCreated;
    private TextView mTvCreatedDate;
    private TextView mTvRegistered;
    private TextView mTvRegDate;
    private TextView mTvResolve;
    private TextView mTvResolveDate;
    private TextView mTvResponsible;
    private TextView mTvRespDate;
    private TextView mTvNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        initViews();
        initListeners();
        makeToolbarAndArrow();
        setValuesFromIntent();
        makeRecyclerView();
    }

    /**
     * Initialize views in current activity.
     */
    private void initViews() {
        mTvTitle = (TextView) findViewById(R.id.tv_tittle);
        mTvStatus = (TextView) findViewById(R.id.tv_status);
        mTvCreated = (TextView) findViewById(R.id.tv_created);
        mTvCreatedDate = (TextView) findViewById(R.id.tv_created_date);
        mTvRegistered = (TextView) findViewById(R.id.tv_registered);
        mTvRegDate = (TextView) findViewById(R.id.tv_reg_date);
        mTvResolve = (TextView) findViewById(R.id.tv_resolve);
        mTvResolveDate = (TextView) findViewById(R.id.tv_resolve_date);
        mTvResponsible = (TextView) findViewById(R.id.tv_responsible);
        mTvRespDate = (TextView) findViewById(R.id.tv_resp_date);
        mTvNote = (TextView) findViewById(R.id.tv_note);
    }

    /**
     * Initialize view listeners in current activity.
     */
    private void initListeners() {
        mTvTitle.setOnClickListener(this);
        mTvStatus.setOnClickListener(this);
        mTvCreated.setOnClickListener(this);
        mTvCreatedDate.setOnClickListener(this);
        mTvRegistered.setOnClickListener(this);
        mTvRegDate.setOnClickListener(this);
        mTvResolve.setOnClickListener(this);
        mTvResolveDate.setOnClickListener(this);
        mTvResponsible.setOnClickListener(this);
        mTvRespDate.setOnClickListener(this);
        mTvNote.setOnClickListener(this);
    }

    /**
     * Get and set values from MainActivity.
     */
    private void setValuesFromIntent() {
        Intent intent = getIntent();

        mTvTitle.setText(intent.getStringExtra(Const.IntentConstant.TITLE));
        mTvCreatedDate.setText(intent.getStringExtra(Const.IntentConstant.DATE));
        makeStatus(intent.getStringExtra(Const.IntentConstant.STATUS));
    }

    /**
     * Make status.
     */
    private void makeStatus(String status) {
        mTvStatus.setText(status);
        if (status.equals(getString(R.string.tab_in_work))){
            mTvStatus.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rectangle_mango));
        }else if(status.equals(getString(R.string.tab_complete))){
            mTvStatus.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rectangle_green));
        }else if(status.equals(getString(R.string.tab_expects))){
            mTvStatus.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rectangle_gray));
        }
    }

    /**
     * Initialize toolbar and arrow in current activity.
     */
    private void makeToolbarAndArrow() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.tlb_title);
        }
    }

    /**
     * Initialize recyclerView and make adapter for him.
     */
    private void makeRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_view);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        String[] myDataset = {getString(R.string.rec_first_image),
                getString(R.string.rec_second_image)};

        RecyclerView.Adapter adapter = new ImageRecyclerAdapter(myDataset);
        recyclerView.setAdapter(adapter);

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.activity_horizontal_margin);
        recyclerView.addItemDecoration(itemDecoration);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), v.getId() + "", Toast.LENGTH_SHORT).show();
    }
}
