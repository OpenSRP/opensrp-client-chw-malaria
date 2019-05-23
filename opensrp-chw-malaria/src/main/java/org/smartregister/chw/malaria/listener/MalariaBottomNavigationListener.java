package org.smartregister.chw.malaria.listener;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.Toast;


import org.smartregister.chw.malaria.activity.BaseMalariaRegisterActivity;
import org.smartregister.chw.malaria.util.Constants;
import org.smartregister.listener.BottomNavigationListener;
import org.smartregister.malaria.R;
import org.smartregister.view.activity.BaseRegisterActivity;

public class MalariaBottomNavigationListener extends BottomNavigationListener {
    private Activity context;

    public MalariaBottomNavigationListener(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        super.onNavigationItemSelected(item);

        BaseRegisterActivity baseRegisterActivity = (BaseRegisterActivity) context;

        if (item.getItemId() == R.id.action_family) {
            baseRegisterActivity.switchToBaseFragment();
        } else if (item.getItemId() == R.id.action_scan_qr) {
            baseRegisterActivity.startQrCodeScanner();
        } else if (item.getItemId() == R.id.action_register) {
            baseRegisterActivity.startFormActivity(Constants.FORMS.MALARIA_REGISTRATION, null,null);
            Toast.makeText(context, "Hey", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
