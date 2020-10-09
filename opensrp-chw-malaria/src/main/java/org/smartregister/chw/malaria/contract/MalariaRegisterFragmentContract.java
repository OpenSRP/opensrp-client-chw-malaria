package org.smartregister.chw.malaria.contract;

import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.ViewConfiguration;
import org.smartregister.view.contract.BaseRegisterFragmentContract;

import java.util.Set;

public interface MalariaRegisterFragmentContract {

    interface View extends BaseRegisterFragmentContract.View {

        Presenter presenter();
    }

    interface Presenter extends BaseRegisterFragmentContract.Presenter {

        String getMainCondition();

        String getDefaultSortQuery();

        String getMainTable();

        String getDueFilterCondition();
    }

    interface Model extends BaseRegisterFragmentContract.Model{

        RegisterConfiguration defaultRegisterConfiguration();

        ViewConfiguration getViewConfiguration(String viewConfigurationIdentifier);

        String countSelect(String tableName, String mainCondition);

        String mainSelect(String tableName, String mainCondition);
    }
}
