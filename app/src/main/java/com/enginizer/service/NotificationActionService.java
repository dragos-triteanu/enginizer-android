package com.enginizer.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.enginizer.EnginizerApplication;
import com.enginizer.model.CallDetails;
import com.enginizer.util.CallConstants;

import javax.inject.Inject;

/**
 * Created by drago on 3/24/2017.
 */

public class NotificationActionService extends IntentService {
    private static final String TAG = "NotificationActionService: ";

    @Inject
    CalendarService calendarService;

    public NotificationActionService() {
        super(NotificationActionService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EnginizerApplication.APP.inject(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String action = intent.getAction();
        CallDetails callDetails = (com.enginizer.model.CallDetails) intent.getSerializableExtra(CallConstants.CALL_DETAILS_EXTRA);
        int intExtra = intent.getIntExtra(CallConstants.NOTIFICATION_ID, 0);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if ("snooze".equals(action)) {
            calendarService.addEvent(this, callDetails);
            notificationManager.cancel(intExtra);
        }


    }
}


