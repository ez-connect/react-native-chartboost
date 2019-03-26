// Version 7.3.1

package ez.react.chartboost

import com.chartboost.sdk.Chartboost
import com.chartboost.sdk.ChartboostDelegate
import com.chartboost.sdk.Model.CBError.CBClickError
import com.chartboost.sdk.Model.CBError.CBImpressionError
import com.facebook.react.bridge.*
import com.facebook.react.modules.core.DeviceEventManagerModule

class RNChartboostModule(val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext), LifecycleEventListener {

    var delegate: ChartboostDelegate = object : ChartboostDelegate() {
        //Called after the SDK has been successfully initialized and video prefetching has been completed.
        override fun didInitialize() {
            sendEvent("didInitialize", "")
        }

        // Called if a click is registered by the app, but the user is not forwarded to the App Store.
        override fun didFailToRecordClick(uri: String?, error: CBClickError?) {
            sendEvent("didFailToRecordClick", uri)
        }

        // // Called before requesting an interstitial via the Chartboost API server.
        // public boolean shouldRequestInterstitial(String location)

        // // Called before an interstitial will be displayed on the screen.
        // public boolean shouldDisplayInterstitial(String location)

        // Called after an interstitial has been displayed on the screen.
        override fun didDisplayInterstitial(location: String?) {
            sendEvent("didDisplayInterstitial", location)
        }

        // // Called after an interstitial has been loaded from the Chartboost API
        // // servers and cached locally.
        // public void didCacheInterstitial(String location)

        // Called after an interstitial has attempted to load from the Chartboost API
        // servers but failed.
        override fun didFailToLoadInterstitial(location: String?, error: CBImpressionError?) {
            sendEvent("didFailToLoadInterstitial", location)
        }

        // Called after an interstitial has been dismissed.
        override fun didDismissInterstitial(location: String?) {
            sendEvent("didDismissInterstitial", location)
        }

        // Called after an interstitial has been closed.
        override fun didCloseInterstitial(location: String?) {
            sendEvent("didCloseInterstitial", location)
        }

        // Called after an interstitial has been clicked.
        override fun didClickInterstitial(location: String?) {
            sendEvent("didClickInterstitial", location)
        }

        // // Called before a rewarded video will be displayed on the screen.
        // public boolean shouldDisplayRewardedVideo(String location)

        // Called after a rewarded video has been displayed on the screen.
        override fun didDisplayRewardedVideo(location: String?) {
            sendEvent("didDisplayRewardedVideo", location)
        }

        // // Called after a rewarded video has been loaded from the Chartboost API
        // // servers and cached locally.
        // public void didCacheRewardedVideo(String location)

        // Called after a rewarded video has attempted to load from the Chartboost API
        // servers but failed.
        override fun didFailToLoadRewardedVideo(location: String?, error: CBImpressionError?) {
            sendEvent("didFailToLoadRewardedVideo", location)
        }

        // Called after a rewarded video has been dismissed.
        override fun didDismissRewardedVideo(location: String?) {
            sendEvent("didDismissRewardedVideo", location)
        }

        // Called after a rewarded video has been closed.
        override fun didCloseRewardedVideo(location: String?) {
            sendEvent("didCloseRewardedVideo", location)
        }

        // Called after a rewarded video has been clicked.
        override fun didClickRewardedVideo(location: String?) {
            sendEvent("didClickRewardedVideo", location)
        }

        // Called after a rewarded video has been viewed completely and user is eligible for reward.
        override fun didCompleteRewardedVideo(location: String?, reward: Int) {
            sendEvent("didCompleteRewardedVideo", location, reward)
        }

        // Implement to be notified of when a video will be displayed on the screen for
        // a given CBLocation. You can then do things like mute effects and sounds.
        override fun willDisplayVideo(location: String?) {
            sendEvent("willDisplayVideo", location)
        }

    }

    override fun getName(): String {
        return "RNChartboost"
    }

    override fun onHostResume() {
        Chartboost.onResume(getCurrentActivity())
    }

    override fun onHostPause() {
        Chartboost.onPause(getCurrentActivity())
    }

    override fun onHostDestroy() {
        Chartboost.onDestroy(getCurrentActivity())
    }

    @ReactMethod
    fun start(/*String appId, String appSignature*/promise: Promise) {
        // Chartboost.startWithAppId(getCurrentActivity(), appId, appSignature);
        Chartboost.setDelegate(this.delegate)
        promise.resolve(true)
    }

    @ReactMethod
    fun hasInterstitial(location: String, promise: Promise) {
        if (Chartboost.hasInterstitial(location)) {
            promise.resolve(true)
        } else {
            promise.resolve(false)
            Chartboost.cacheInterstitial(location)
        }
    }

    @ReactMethod
    fun cacheInterstitial(location: String) {
        Chartboost.cacheInterstitial(location)
    }

    @ReactMethod
    fun showInterstitial(location: String, promise: Promise) {
        Chartboost.showInterstitial(location)
        promise.resolve(true)
    }

    @ReactMethod
    fun hasRewardedVideo(location: String, promise: Promise) {
        if (Chartboost.hasRewardedVideo(location)) {
            promise.resolve(true)
        } else {
            promise.resolve(false)
            Chartboost.cacheRewardedVideo(location)
        }
    }

    @ReactMethod
    fun cacheRewardedVideo(location: String) {
        Chartboost.cacheRewardedVideo(location)
    }

    @ReactMethod
    fun showRewardedVideo(location: String, promise: Promise) {
        Chartboost.showRewardedVideo(location)
        promise.resolve(true)
    }

    private fun sendEvent(event: String, location: String?, reward: Int) {
        val params = Arguments.createMap()
        params.putString("location", location)
        if (reward > 0) {
            params.putInt("reward", reward)
        }
        this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java).emit(event, params)
    }

    private fun sendEvent(event: String, location: String?) {
        this.sendEvent(event, location, 0)
    }
}
