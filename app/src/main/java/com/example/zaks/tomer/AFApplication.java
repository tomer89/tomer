package com.example.zaks.tomer;

import android.app.Application;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;

import java.util.Map;

/**
 * Created by ZAKS on 28/08/2018.
 */

//found on the sample app of appsflyer

public class AFApplication extends Application {

    private static final String DEV_KEY = "LTomU4gWLEWfJRackwArY9";


    @Override
    public void onCreate(){
        super.onCreate();


        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {


            //All functions inside the constructor are from the AppsFlyer git (didn't know the AppsFlyerConversionListener interface)
            //Although these functions are from the appsflyer sample app I do understand them

            /* Returns the attribution data. Note - the same conversion data is returned every time per install */
            @Override
            public void onInstallConversionDataLoaded(Map<String, String> conversionData) {
                for (String attrName : conversionData.keySet()) {
                    Log.d(AppsFlyerLib.LOG_TAG, "attribute: " + attrName + " = " + conversionData.get(attrName));
                }
                setInstallData(conversionData);
            }

            @Override
            public void onInstallConversionFailure(String errorMessage) {
                Log.d(AppsFlyerLib.LOG_TAG, "error getting conversion data: " + errorMessage);
            }

            /* Called only when a Deep Link is opened */
            @Override
            public void onAppOpenAttribution(Map<String, String> conversionData) {
                for (String attrName : conversionData.keySet()) {
                    Log.d(AppsFlyerLib.LOG_TAG, "attribute: " + attrName + " = " + conversionData.get(attrName));
                }
            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d(AppsFlyerLib.LOG_TAG, "error onAttributionFailure : " + errorMessage);
            }
        };


        /* This API enables AppsFlyer to detect installations, sessions, and updates. */

        AppsFlyerLib.getInstance().init(DEV_KEY , conversionListener , getApplicationContext());
        AppsFlyerLib.getInstance().startTracking(this, DEV_KEY);


        /* Set to true to see the debug logs. Comment out or set to false to stop the function */

        AppsFlyerLib.getInstance().setDebugLog(true);

    }



    //This is also from the AppsFlyer this is for the "onInstallConversionDataLoaded" func.
    //Although these functions are from the appsflyer sample app, I do understand them.

    /* IGNORE - USED TO DISPLAY INSTALL DATA */
    public static String InstallConversionData =  "";
    public static int sessionCount = 0;
    public static void setInstallData(Map<String, String> conversionData){
        if(sessionCount == 0){
            final String install_type = "Install Type: " + conversionData.get("af_status") + "\n";
            final String media_source = "Media Source: " + conversionData.get("media_source") + "\n";
            final String install_time = "Install Time(GMT): " + conversionData.get("install_time") + "\n";
            final String click_time = "Click Time(GMT): " + conversionData.get("click_time") + "\n";
            final String is_first_launch = "Is First Launch: " + conversionData.get("is_first_launch") + "\n";
            InstallConversionData += install_type + media_source + install_time + click_time + is_first_launch;
            sessionCount++;
        }

    }


}


