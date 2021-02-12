package org.smartregister.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.smartregister.chw.malaria.contract.MalariaRegisterFragmentContract;
import org.smartregister.chw.malaria.presenter.BaseMalariaRegisterFragmentPresenter;
import org.smartregister.chw.malaria.util.Constants;
import org.smartregister.chw.malaria.util.DBConstants;
import org.smartregister.configurableviews.model.View;
import org.smartregister.view.contract.IView;

import java.util.Set;
import java.util.TreeSet;

public class BaseMalariaRegisterFragmentPresenterTest {
    @Mock
    protected MalariaRegisterFragmentContract.View view;

    @Mock
    protected MalariaRegisterFragmentContract.Model model;

    private BaseMalariaRegisterFragmentPresenter baseMalariaRegisterFragmentPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        baseMalariaRegisterFragmentPresenter = new BaseMalariaRegisterFragmentPresenter(view, model, "");
    }

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(baseMalariaRegisterFragmentPresenter);
    }

    @Test
    public void getMainCondition() {
        Assert.assertEquals("", baseMalariaRegisterFragmentPresenter.getMainCondition());
    }

    @Test
    public void getDueFilterCondition() {
        Assert.assertEquals(" (cast( julianday(STRFTIME('%Y-%m-%d', datetime('now'))) -  julianday(IFNULL(SUBSTR(malaria_test_date,7,4)|| '-' || SUBSTR(malaria_test_date,4,2) || '-' || SUBSTR(malaria_test_date,1,2),'')) as integer) between 7 and 14) ", baseMalariaRegisterFragmentPresenter.getDueFilterCondition());
    }

    @Test
    public void getDefaultSortQuery() {
        Assert.assertEquals(Constants.TABLES.MALARIA_CONFIRMATION + "." + DBConstants.KEY.LAST_INTERACTED_WITH + " DESC ", baseMalariaRegisterFragmentPresenter.getDefaultSortQuery());
    }

    @Test
    public void getMainTable() {
        Assert.assertEquals(Constants.TABLES.MALARIA_CONFIRMATION, baseMalariaRegisterFragmentPresenter.getMainTable());
    }

    @Test
    public void initializeQueries() {
        Set<IView> visibleColumns = new TreeSet<>();
        baseMalariaRegisterFragmentPresenter.initializeQueries(null);
        Mockito.doNothing().when(view).initializeQueryParams("ec_malaria_confirmation", null, null);
        Mockito.verify(view).initializeQueryParams("ec_malaria_confirmation", null, null);
        Mockito.verify(view).initializeAdapter(visibleColumns);
        Mockito.verify(view).countExecute();
        Mockito.verify(view).filterandSortInInitializeQueries();
    }

}