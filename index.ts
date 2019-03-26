import { DeviceEventEmitter, NativeModules } from 'react-native';
import RNFS from 'react-native-fs';

const CHARTBOOST_CACHE_DIR = `${RNFS.CachesDirectoryPath}/.chartboost`;

const chartboost = NativeModules.RNChartboost;

type ChartboostEvent =
  'didInitialize'
  | 'didCacheInterstitial'
  | 'didCacheRewardedVideo'
  | 'didClickInterstitial'
  | 'didClickRewardedVideo'
  | 'didCloseInterstitial'
  | 'didCloseRewardedVideo'
  | 'didCompleteRewardedVideo'
  | 'didCompleteRewardedVideo'
  | 'didDismissInterstitial'
  | 'didDismissRewardedVideo'
  | 'didDisplayInterstitial'
  | 'didDisplayInterstitial'
  | 'didDisplayRewardedVideo'
  | 'didFailToLoadInterstitial'
  | 'didFailToLoadRewardedVideo'
  | 'didFailToRecordClick'
  | 'shouldDisplayRewardedVideo'
  | 'shouldRequestInterstitial'
  | 'willDisplayInterstitial'
  | 'willDisplayVideo'
  ;
type Handler = (res: { location: string; reward?: number }) => void;

export default class Chartboost {
  public static start(): Promise<void> {
    return chartboost.start();
  }

  public static addListener(event: ChartboostEvent, handler: Handler) {
    DeviceEventEmitter.addListener(event, handler);
  }

  public static hasInterstitial(location: string): Promise<boolean> {
    return chartboost.hasInterstitial(location);
  }

  public static cacheInterstitial(location: string) {
    chartboost.cacheInterstitial(location);
  }

  public static showInterstitial(location: string): Promise<boolean> {
    return chartboost.showInterstitial(location);
  }

  public static hasRewardedVideo(location: string): Promise<boolean> {
    return chartboost.hasInterstitial(location);
  }

  public static cacheRewardedVideo(location: string) {
    chartboost.cacheRewardedVideo(location);
  }

  public static showRewardedVideo(location: string) {
    chartboost.showRewardedVideo(location);
  }

  public static async clearCache() {
    await RNFS.unlink(CHARTBOOST_CACHE_DIR);
  }
}