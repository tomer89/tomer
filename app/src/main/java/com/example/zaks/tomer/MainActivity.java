package com.example.zaks.tomer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AppsFlyerLib;
import com.example.zaks.tomer.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, Object> eventValue = new HashMap<String, Object>();
        eventValue.put(AFInAppEventParameterName.LEVEL,1);
        eventValue.put(AFInAppEventParameterName.SCORE,100);
        AppsFlyerLib.getInstance().trackEvent(getApplicationContext(), AFInAppEventType.PURCHASE, eventValue);
    }
}
