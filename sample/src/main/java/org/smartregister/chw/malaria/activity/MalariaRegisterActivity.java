package org.smartregister.chw.malaria.activity;

import android.view.MenuItem;

public class MalariaRegisterActivity extends BaseMalariaRegisterActivity {

    @Override
    protected void registerBottomNavigation() {
        super.registerBottomNavigation();

        if(bottomNavigationView != null){
            MenuItem clients = bottomNavigationView.getMenu().findItem(org.smartregister.R.id.action_clients);
            if (clients != null) {
                clients.setTitle("");
            }

            bottomNavigationView.getMenu().removeItem(org.smartregister.R.id.action_search);
            bottomNavigationView.getMenu().removeItem(org.smartregister.R.id.action_library);
        }
    }
}