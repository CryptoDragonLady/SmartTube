package com.liskovsoft.smartyoutubetv2.common.exoplayer;

import com.liskovsoft.smartyoutubetv2.common.prefs.PlayerTweaksData;

/** Resolves the configured playback engine against its DNS capabilities. */
public final class NetworkEngineResolver {
    private NetworkEngineResolver() {
    }

    public static int resolve(int configuredSource, int dnsType, boolean cronetAvailable) {
        // The supported Default and Cronet versions do not expose a custom DNS/address selector.
        // Use the DNS-aware transport whenever a non-system DNS policy must be honored.
        if (dnsType != PlayerTweaksData.DNS_TYPE_SYSTEM) {
            return PlayerTweaksData.PLAYER_DATA_SOURCE_OKHTTP;
        }

        if (configuredSource == PlayerTweaksData.PLAYER_DATA_SOURCE_CRONET && !cronetAvailable) {
            return PlayerTweaksData.PLAYER_DATA_SOURCE_DEFAULT;
        }

        return configuredSource;
    }
}
