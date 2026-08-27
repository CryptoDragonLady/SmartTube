package com.liskovsoft.smartyoutubetv2.common.exoplayer;

import com.liskovsoft.smartyoutubetv2.common.prefs.PlayerTweaksData;

/** Resolves the automatic DNS fallback applied after an unknown-host error. */
public final class DnsFallbackPolicy {
    private DnsFallbackPolicy() {
    }

    public static int resolveAfterUnknownHost(int currentDnsType) {
        return currentDnsType == PlayerTweaksData.DNS_TYPE_IPV4 ||
                currentDnsType == PlayerTweaksData.DNS_TYPE_IPV4_ONLY ||
                currentDnsType == PlayerTweaksData.DNS_TYPE_IPV6_ONLY ?
                currentDnsType : PlayerTweaksData.DNS_TYPE_IPV4;
    }
}
