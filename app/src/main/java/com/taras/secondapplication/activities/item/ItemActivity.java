package com.taras.secondapplication.activities.item;

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

import com.taras.secondapplication.R;
import com.taras.secondapplication.adapters.ImageRecyclerAdapter;
import com.taras.secondapplication.models.CardModel;
import com.taras.secondapplication.models.Files;
import com.taras.secondapplication.utils.DateUtils;
import com.taras.secondapplication.utils.ItemOffsetDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener,
        ItemView {

    @BindView(R.id.tv_tittle)
    TextView mTvTitle;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_created)
    TextView mTvCreated;
    @BindView(R.id.tv_created_date)
    TextView mTvCreatedDate;
    @BindView(R.id.tv_registered)
    TextView mTvRegistered;
    @BindView(R.id.tv_reg_date)
    TextView mTvRegDate;
    @BindView(R.id.tv_resolve)
    TextView mTvResolve;
    @BindView(R.id.tv_resolve_date)
    TextView mTvResolveDate;
    @BindView(R.id.tv_responsible)
    TextView mTvResponsible;
    @BindView(R.id.tv_resp_organization)
    TextView mTvRespOrganization;
    @BindView(R.id.tv_note)
    TextView mTvNote;
    @BindView(R.id.rv_filename)
    RecyclerView recyclerView;
    private ItemPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        initPresenter();
        getCardModel();
        initListeners();
    }

    /**
     * Init presenter in current activity.
     */
    @Override
    public void initPresenter() {
        mPresenter = new ItemPresenterImpl(this, getIntent());
    }

    /**
     * Get model for current activity.
     */
    @Override
    public void getCardModel() {
        mPresenter.getCardModel();
    }

    /**
     * Initialize view listeners in current activity.
     */
    @Override
    public void initListeners() {
        mTvTitle.setOnClickListener(this);
        mTvStatus.setOnClickListener(this);
        mTvCreated.setOnClickListener(this);
        mTvCreatedDate.setOnClickListener(this);
        mTvRegistered.setOnClickListener(this);
        mTvRegDate.setOnClickListener(this);
        mTvResolve.setOnClickListener(this);
        mTvResolveDate.setOnClickListener(this);
        mTvResponsible.setOnClickListener(this);
        mTvRespOrganization.setOnClickListener(this);
        mTvNote.setOnClickListener(this);
    }

    /**
     * Get and set values from MainActivity.
     */
    @Override
    public void setValuesFromIntent(CardModel cardModel) {

        String title = cardModel.getTitle();
        String createdDate = DateUtils.parseLongToDayAgo(getApplicationContext(), cardModel.getCreated_date());
        String startDate = DateUtils.parseLongToDayAgo(getApplicationContext(), cardModel.getCreated_date());
        String deadline = DateUtils.parseLongToDayAgo(getApplicationContext(), cardModel.getCreated_date());
        String body = cardModel.getBody();

        String responsible = getString(R.string.tst_empty);
        if(cardModel.getPerformers().size() != 0) {
            responsible = cardModel.getPerformers().get(0).getOrganization();
        }

        mTvTitle.setText(title);
        mTvCreatedDate.setText(createdDate);
        mTvRegDate.setText(startDate);
        mTvResolveDate.setText(deadline);
        mTvNote.setText(body);
        mTvRespOrganization.setText(responsible);
    }

    /**
     * Make status.
     */
    @Override
    public void makeStatus(CardModel cardModel) {
        int state_id = (int) cardModel.getState().getId();

        switch (state_id) {
            case 0:
            case 9:
            case 5:
            case 7:
            case 8:
                mTvStatus.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rectangle_mango));
                mTvStatus.setText(getString(R.string.tab_in_work));
                break;
            case 10:
            case 6:
                mTvStatus.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rectangle_green));
                mTvStatus.setText(getString(R.string.tab_complete));
                break;
            case 1:
            case 3:
            case 4:
                mTvStatus.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rectangle_gray));
                mTvStatus.setText(getString(R.string.itm_consideration));
                break;
        }
    }

    /**
     * Initialize toolbar and arrow in current activity.
     */
    @Override
    public void makeToolbarAndArrow(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            if (title != null) {
                getSupportActionBar().setTitle(title);
            } else {
                getSupportActionBar().setTitle(R.string.tlb_no_ticket_id);
            }
        }
    }

    /**
     * Initialize recyclerView and make adapter for him.
     */
    @Override
    public void makeRecyclerView(RealmList<Files> files) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new ImageRecyclerAdapter(files);
        recyclerView.setAdapter(adapter);

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.activity_horizontal_margin);
        recyclerView.addItemDecoration(itemDecoration);
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
     * OnClickListener for views.
     */
    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), v.getId() + "", Toast.LENGTH_SHORT).show();
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
