package com.aylin.unityads.myads_android_3_0;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;

import com.unity3d.ads.UnityAds;
import com.unity3d.services.UnityServices;
import com.unity3d.services.monetization.IUnityMonetizationListener;
import com.unity3d.services.IUnityServicesListener;
import com.unity3d.services.monetization.UnityMonetization;
import com.unity3d.services.monetization.placementcontent.ads.IShowAdListener;
import com.unity3d.services.monetization.placementcontent.ads.ShowAdPlacementContent;
import com.unity3d.services.monetization.placementcontent.core.PlacementContent;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, IUnityServicesListener, IShowAdListener{

    private Button rewardedAdsButton;
    private String unityGameID = "1606714";
    private String interstitialPlacementId = "video";
    private String placementId = "rewardedVideo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MainActivity thisActivity = this;
        final IUnityMonetizationListener unityMonetizationListener = new UnityMonetizationListener();
        UnityMonetization.initialize(this, unityGameID, unityMonetizationListener, false);
        rewardedAdsButton = findViewById (R.id.rewardedAdsButton);
        rewardedAdsButton.setOnClickListener (this);

    }

    @Override
    public void onClick (View view) {
        if (UnityMonetization.isReady (placementId)) {
            PlacementContent pc = UnityMonetization.getPlacementContent (placementId);
            if (pc.getType ().equalsIgnoreCase ("SHOW_AD")) {
                ShowAdPlacementContent p = (ShowAdPlacementContent) pc;
                p.show(this, this);
            }
        } else {
            Log.e ("Aylin","This Placement is not ready!");
        }
    }

    @Override
    public void onAdStarted(String placementId) {

    }


    @Override
    public void onAdFinished (String s, UnityAds.FinishState finishState) {
        if (finishState == UnityAds.FinishState.COMPLETED) {
            if (s.equals (placementId)) {
                // Reward the player here.
            }
        }
    }
    @Override
    public void onUnityServicesError(UnityServices.UnityServicesError unityServicesError, String s) {

    }


    private class UnityMonetizationListener implements IUnityMonetizationListener {

        @Override
        public void onPlacementContentReady(String placementId, PlacementContent placementContent) {

        }

        @Override
        public void onPlacementContentStateChange(String s, PlacementContent placementContent, UnityMonetization.PlacementContentState placementContentState, UnityMonetization.PlacementContentState placementContentState1) {

        }


        @Override
        public void onUnityServicesError(UnityServices.UnityServicesError unityServicesError, String s) {

        }
    }
}