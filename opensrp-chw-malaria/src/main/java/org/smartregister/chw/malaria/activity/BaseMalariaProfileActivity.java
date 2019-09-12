package org.smartregister.chw.malaria.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.smartregister.chw.malaria.contract.MalariaProfileContract;
import org.smartregister.chw.malaria.domain.MemberObject;
import org.smartregister.chw.malaria.interactor.BaseMalariaProfileInteractor;
import org.smartregister.chw.malaria.presenter.BaseMalariaProfilePresenter;
import org.smartregister.chw.malaria.util.Constants;
import org.smartregister.chw.malaria.util.Util;
import org.smartregister.domain.AlertStatus;
import org.smartregister.malaria.R;
import org.smartregister.view.activity.BaseProfileActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class BaseMalariaProfileActivity extends BaseProfileActivity implements MalariaProfileContract.View, MalariaProfileContract.InteractorCallBack {
    protected MemberObject MEMBER_OBJECT;
    protected MalariaProfileContract.Presenter profilePresenter;
    protected TextView textViewName;
    protected TextView textViewGender;
    protected TextView textViewLocation;
    protected TextView textViewUniqueID;
    protected TextView textViewRecordMalaria;
    protected View view_last_visit_row;
    protected View view_most_due_overdue_row;
    protected View view_family_row;
    protected RelativeLayout rlLastVisit;
    protected RelativeLayout rlUpcomingServices;
    protected RelativeLayout rlFamilyServicesDue;
    private TextView tvUpComingServices;
    private TextView tvFamilyStatus;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM", Locale.getDefault());
    private ProgressBar progressBar;
    protected CircleImageView imageView;

    private View viewRecordMalaria;

    public static void startProfileActivity(Activity activity, MemberObject memberObject) {
        Intent intent = new Intent(activity, BaseMalariaProfileActivity.class);
        intent.putExtra(Constants.MALARIA_MEMBER_OBJECT.MEMBER_OBJECT, memberObject);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreation() {
        setContentView(R.layout.activity_malaria_profile);
        Toolbar toolbar = findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
            upArrow.setColorFilter(getResources().getColor(R.color.text_blue), PorterDuff.Mode.SRC_ATOP);
            actionBar.setHomeAsUpIndicator(upArrow);
        }

        toolbar.setNavigationOnClickListener(v -> BaseMalariaProfileActivity.this.finish());
        appBarLayout = this.findViewById(R.id.collapsing_toolbar_appbarlayout);
        if (Build.VERSION.SDK_INT >= 21) {
            appBarLayout.setOutlineProvider(null);
        }

        textViewName = findViewById(R.id.textview_name);
        textViewGender = findViewById(R.id.textview_gender);
        textViewLocation = findViewById(R.id.textview_address);
        textViewUniqueID = findViewById(R.id.textview_id);
        viewRecordMalaria = findViewById(R.id.record_visit_malaria);
        view_last_visit_row = findViewById(R.id.view_last_visit_row);
        view_most_due_overdue_row = findViewById(R.id.view_most_due_overdue_row);
        view_family_row = findViewById(R.id.view_family_row);

        tvUpComingServices = findViewById(R.id.textview_name_due);
        tvFamilyStatus = findViewById(R.id.textview_family_has);

        rlLastVisit = findViewById(R.id.rlLastVisit);
        rlUpcomingServices = findViewById(R.id.rlUpcomingServices);
        rlFamilyServicesDue = findViewById(R.id.rlFamilyServicesDue);
        imageView = findViewById(R.id.imageview_profile);
        imageView.setBorderWidth(2);

        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.rlLastVisit).setOnClickListener(this);
        findViewById(R.id.rlUpcomingServices).setOnClickListener(this);
        findViewById(R.id.rlFamilyServicesDue).setOnClickListener(this);

        textViewRecordMalaria = findViewById(R.id.textview_record_malaria);
        textViewRecordMalaria.setOnClickListener(this);

        MEMBER_OBJECT = (MemberObject) getIntent().getSerializableExtra(Constants.MALARIA_MEMBER_OBJECT.MEMBER_OBJECT);

        initializePresenter();

        profilePresenter.fillProfileData(MEMBER_OBJECT);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.title_layout) {
            onBackPressed();
        } else if (id == R.id.rlLastVisit) {
            this.openMedicalHistory();
        } else if (id == R.id.rlUpcomingServices) {
            this.openUpcomingService();
        } else if (id == R.id.rlFamilyServicesDue) {
            this.openFamilyDueServices();
        }
    }

    @Override
    protected void initializePresenter() {
        showProgressBar(true);
        profilePresenter = new BaseMalariaProfilePresenter(this, new BaseMalariaProfileInteractor(), MEMBER_OBJECT);
        fetchProfileData();
        profilePresenter.refreshProfileBottom();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void setProfileViewWithData() {
        int age = new Period(new DateTime(MEMBER_OBJECT.getAge()), new DateTime()).getYears();
        textViewName.setText(String.format("%s %s %s, %d", MEMBER_OBJECT.getFirstName(),
                MEMBER_OBJECT.getMiddleName(), MEMBER_OBJECT.getLastName(), age));
        textViewGender.setText(MEMBER_OBJECT.getGender());
        textViewLocation.setText(MEMBER_OBJECT.getAddress());
        textViewUniqueID.setText(MEMBER_OBJECT.getUniqueId());

        if (MEMBER_OBJECT.getMalariaTestDate() != null) {
            try {
                Date date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(MEMBER_OBJECT.getMalariaTestDate());
                Days days = Days.daysBetween(new LocalDateTime(date), LocalDateTime.now());
                profilePresenter.recordMalariaButton(days.getDays());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(MEMBER_OBJECT.getFamilyHead()) && MEMBER_OBJECT.getFamilyHead().equals(MEMBER_OBJECT.getBaseEntityId())) {
            findViewById(R.id.family_malaria_head).setVisibility(View.VISIBLE);
        }
        if (StringUtils.isNotBlank(MEMBER_OBJECT.getPrimaryCareGiver()) && MEMBER_OBJECT.getPrimaryCareGiver().equals(MEMBER_OBJECT.getBaseEntityId())) {
            findViewById(R.id.primary_malaria_caregiver).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideView() {
        viewRecordMalaria.setVisibility(View.GONE);
    }

    @Override
    public void setDueColor() {
        textViewRecordMalaria.setBackground(getResources().getDrawable(R.drawable.record_btn_selector));
    }

    @Override
    public void setOverDueColor() {
        textViewRecordMalaria.setBackground(getResources().getDrawable(R.drawable.record_btn_selector_overdue));
    }

    @Override
    protected ViewPager setupViewPager(ViewPager viewPager) {
        return null;
    }

    @Override
    protected void fetchProfileData() {
        //fetch profile data
    }

    @Override
    public void showProgressBar(boolean status) {
        progressBar.setVisibility(status ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void refreshMedicalHistory(boolean hasHistory) {
        showProgressBar(false);
        rlLastVisit.setVisibility(hasHistory ? View.VISIBLE : View.GONE);
    }

    @Override
    public void refreshUpComingServicesStatus(String service, AlertStatus status, Date date) {
        showProgressBar(false);
        if (status == AlertStatus.complete)
            return;

        view_most_due_overdue_row.setVisibility(View.VISIBLE);
        rlUpcomingServices.setVisibility(View.VISIBLE);

        if (status == AlertStatus.upcoming) {
            tvUpComingServices.setText(Util.fromHtml(getString(R.string.vaccine_service_upcoming, service, dateFormat.format(date))));
        } else {
            tvUpComingServices.setText(Util.fromHtml(getString(R.string.vaccine_service_due, service, dateFormat.format(date))));
        }
    }

    @Override
    public void refreshFamilyStatus(AlertStatus status) {
        showProgressBar(false);
        view_family_row.setVisibility(View.VISIBLE);
        rlFamilyServicesDue.setVisibility(View.VISIBLE);

        if (status == AlertStatus.complete) {
            tvFamilyStatus.setText(getString(R.string.family_has_nothing_due));
        } else if (status == AlertStatus.normal) {
            tvFamilyStatus.setText(getString(R.string.family_has_services_due));
        } else if (status == AlertStatus.urgent) {
            tvFamilyStatus.setText(Util.fromHtml(getString(R.string.family_has_service_overdue)));
        }
    }

    @Override
    public void openMedicalHistory() {
        //implement
    }

    @Override
    public void openUpcomingService() {
        //implement
    }

    @Override
    public void openFamilyDueServices() {
        //implement
    }

    @Override
    public void setProfileImage(String baseEntityId, String entityType) {
        imageRenderHelper.refreshProfileImage(baseEntityId, imageView, Util.getMemberProfileImageResourceIDentifier(entityType));
    }
}
