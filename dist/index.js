import { DeviceEventEmitter, NativeModules } from 'react-native';
import RNFS from 'react-native-fs';
const CHARTBOOST_CACHE_DIR = `${RNFS.CachesDirectoryPath}/.chartboost`;
const chartboost = NativeModules.RNChartboost;
export default class Chartboost {
    static start() {
        return chartboost.start();
    }
    static addListener(event, handler) {
        DeviceEventEmitter.addListener(event, handler);
    }
    static hasInterstitial(location) {
        return chartboost.hasInterstitial(location);
    }
    static cacheInterstitial(location) {
        chartboost.cacheInterstitial(location);
    }
    static showInterstitial(location) {
        return chartboost.showInterstitial(location);
    }
    static hasRewardedVideo(location) {
        return chartboost.hasInterstitial(location);
    }
    static cacheRewardedVideo(location) {
        chartboost.cacheRewardedVideo(location);
    }
    static showRewardedVideo(location) {
        chartboost.showRewardedVideo(location);
    }
    static async clearCache() {
        await RNFS.unlink(CHARTBOOST_CACHE_DIR);
    }
}
