package com.liskovsoft.smartyoutubetv2.common.exoplayer;

import com.liskovsoft.smartyoutubetv2.common.prefs.PlayerTweaksData;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DnsFallbackPolicyTest {
    @Test
    public void systemDnsFallsBackToPreferIpv4AfterUnknownHost() {
        assertEquals(
                PlayerTweaksData.DNS_TYPE_IPV4,
                DnsFallbackPolicy.resolveAfterUnknownHost(PlayerTweaksData.DNS_TYPE_SYSTEM));
    }

    @Test
    public void forcedIpv4SurvivesUnknownHostFallback() {
        assertEquals(
                PlayerTweaksData.DNS_TYPE_IPV4_ONLY,
                DnsFallbackPolicy.resolveAfterUnknownHost(PlayerTweaksData.DNS_TYPE_IPV4_ONLY));
    }

    @Test
    public void forcedIpv6SurvivesUnknownHostFallback() {
        assertEquals(
                PlayerTweaksData.DNS_TYPE_IPV6_ONLY,
                DnsFallbackPolicy.resolveAfterUnknownHost(PlayerTweaksData.DNS_TYPE_IPV6_ONLY));
    }
}
