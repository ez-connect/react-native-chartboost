using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace React.Native.Chartboost.RNReactNativeChartboost
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNReactNativeChartboostModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNReactNativeChartboostModule"/>.
        /// </summary>
        internal RNReactNativeChartboostModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNReactNativeChartboost";
            }
        }
    }
}
