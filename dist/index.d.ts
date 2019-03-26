declare type ChartboostEvent = 'didInitialize' | 'didCacheInterstitial' | 'didCacheRewardedVideo' | 'didClickInterstitial' | 'didClickRewardedVideo' | 'didCloseInterstitial' | 'didCloseRewardedVideo' | 'didCompleteRewardedVideo' | 'didCompleteRewardedVideo' | 'didDismissInterstitial' | 'didDismissRewardedVideo' | 'didDisplayInterstitial' | 'didDisplayInterstitial' | 'didDisplayRewardedVideo' | 'didFailToLoadInterstitial' | 'didFailToLoadRewardedVideo' | 'didFailToRecordClick' | 'shouldDisplayRewardedVideo' | 'shouldRequestInterstitial' | 'willDisplayInterstitial' | 'willDisplayVideo';
declare type Handler = (res: {
    location: string;
    reward?: number;
}) => void;
export default class Chartboost {
    static start(): Promise<void>;
    static addListener(event: ChartboostEvent, handler: Handler): void;
    static hasInterstitial(location: string): Promise<boolean>;
    static cacheInterstitial(location: string): void;
    static showInterstitial(location: string): Promise<boolean>;
    static hasRewardedVideo(location: string): Promise<boolean>;
    static cacheRewardedVideo(location: string): void;
    static showRewardedVideo(location: string): void;
    static clearCache(): Promise<void>;
}
export {};
