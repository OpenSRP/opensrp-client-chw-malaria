package org.smartregister.chw.malaria.model;

import org.json.JSONArray;
import org.smartregister.chw.malaria.MalariaLibrary;
import org.smartregister.chw.malaria.contract.MalariaRegisterFragmentContract;
import org.smartregister.chw.malaria.util.ConfigHelper;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.ViewConfiguration;
import org.smartregister.cursoradapter.SmartRegisterQueryBuilder;
import org.smartregister.domain.Response;
import org.smartregister.view.contract.IField;
import org.smartregister.view.contract.IView;
import org.smartregister.view.contract.IViewConfiguration;

import java.util.List;
import java.util.Set;

public class BaseMalariaRegisterFragmentModel implements MalariaRegisterFragmentContract.Model {

    @Override
    public RegisterConfiguration defaultRegisterConfiguration() {
        return ConfigHelper.defaultRegisterConfiguration(MalariaLibrary.getInstance().context().applicationContext());
    }

    @Override
    public IViewConfiguration getViewConfiguration(String viewConfigurationIdentifier) {
        return ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper().getViewConfiguration(viewConfigurationIdentifier);
    }

    @Override
    public Set<IView> getRegisterActiveColumns(String viewConfigurationIdentifier) {
        return ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper().getRegisterActiveColumns(viewConfigurationIdentifier);
    }

    @Override
    public String countSelect(String tableName, String mainCondition) {
        SmartRegisterQueryBuilder countQueryBuilder = new SmartRegisterQueryBuilder();
        countQueryBuilder.selectInitiateMainTableCounts(tableName);
        return countQueryBuilder.mainCondition(mainCondition);
    }

    @Override
    public String mainSelect(String tableName, String mainCondition) {
        SmartRegisterQueryBuilder queryBUilder = new SmartRegisterQueryBuilder();
        queryBUilder.selectInitiateMainTable(tableName, mainColumns(tableName));
        return queryBUilder.mainCondition(mainCondition);
    }

    @Override
    public String getFilterText(List<IField> list, String s) {
        return null;
    }

    @Override
    public String getSortText(IField iField) {
        return null;
    }

    @Override
    public JSONArray getJsonArray(Response<String> response) {
        return null;
    }

    protected String[] mainColumns(String tableName) {
        String[] columns = new String[]{
                tableName + ".relationalid"
        };
        return columns;
    }

}
