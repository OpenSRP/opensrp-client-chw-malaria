package org.smartregister.chw.malaria.contract;

import android.content.Context;

public interface BaseMalariaCallDialogContract {

    interface View {
        void setPendingCallRequest(Dialer dialer);
        Context getCurrentContext();
    }

    interface Dialer {
        void callMe();
    }
}
