package com.liskovsoft.smartyoutubetv2.common.exoplayer;

import com.liskovsoft.smartyoutubetv2.common.prefs.PlayerTweaksData;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NetworkEngineResolverTest {
    @Test
    public void systemDnsKeepsDefaultEngine() {
        assertEquals(
                PlayerTweaksData.PLAYER_DATA_SOURCE_DEFAULT,
                NetworkEngineResolver.resolve(
                        PlayerTweaksData.PLAYER_DATA_SOURCE_DEFAULT,
                        PlayerTweaksData.DNS_TYPE_SYSTEM,
                        true));
    }

    @Test
    public void systemDnsKeepsAvailableCronetEngine() {
        assertEquals(
                PlayerTweaksData.PLAYER_DATA_SOURCE_CRONET,
                NetworkEngineResolver.resolve(
                        PlayerTweaksData.PLAYER_DATA_SOURCE_CRONET,
                        PlayerTweaksData.DNS_TYPE_SYSTEM,
                        true));
    }

    @Test
    public void unavailableCronetFallsBackToDefaultEngine() {
        assertEquals(
                PlayerTweaksData.PLAYER_DATA_SOURCE_DEFAULT,
                NetworkEngineResolver.resolve(
                        PlayerTweaksData.PLAYER_DATA_SOURCE_CRONET,
                        PlayerTweaksData.DNS_TYPE_SYSTEM,
                        false));
    }

    @Test
    public void ipv4FirstUsesDnsAwareEngineForDefault() {
        assertEquals(
                PlayerTweaksData.PLAYER_DATA_SOURCE_OKHTTP,
                NetworkEngineResolver.resolve(
                        PlayerTweaksData.PLAYER_DATA_SOURCE_DEFAULT,
                        PlayerTweaksData.DNS_TYPE_IPV4,
                        true));
    }

    @Test
    public void ipv4FirstUsesDnsAwareEngineForCronet() {
        assertEquals(
                PlayerTweaksData.PLAYER_DATA_SOURCE_OKHTTP,
                NetworkEngineResolver.resolve(
                        PlayerTweaksData.PLAYER_DATA_SOURCE_CRONET,
                        PlayerTweaksData.DNS_TYPE_IPV4,
                        true));
    }

    @Test
    public void ipv4OnlyUsesDnsAwareEngineForEveryConfiguredEngine() {
        assertEquals(
                PlayerTweaksData.PLAYER_DATA_SOURCE_OKHTTP,
                NetworkEngineResolver.resolve(
                        PlayerTweaksData.PLAYER_DATA_SOURCE_DEFAULT,
                        PlayerTweaksData.DNS_TYPE_IPV4_ONLY,
                        true));
        assertEquals(
                PlayerTweaksData.PLAYER_DATA_SOURCE_OKHTTP,
                NetworkEngineResolver.resolve(
                        PlayerTweaksData.PLAYER_DATA_SOURCE_CRONET,
                        PlayerTweaksData.DNS_TYPE_IPV4_ONLY,
                        true));
        assertEquals(
                PlayerTweaksData.PLAYER_DATA_SOURCE_OKHTTP,
                NetworkEngineResolver.resolve(
                        PlayerTweaksData.PLAYER_DATA_SOURCE_OKHTTP,
                        PlayerTweaksData.DNS_TYPE_IPV4_ONLY,
                        true));
    }

    @Test
    public void googleDnsUsesDnsAwareEngineForDefaultAndCronet() {
        assertEquals(
                PlayerTweaksData.PLAYER_DATA_SOURCE_OKHTTP,
                NetworkEngineResolver.resolve(
                        PlayerTweaksData.PLAYER_DATA_SOURCE_DEFAULT,
                        PlayerTweaksData.DNS_TYPE_GOOGLE,
                        true));
        assertEquals(
                PlayerTweaksData.PLAYER_DATA_SOURCE_OKHTTP,
                NetworkEngineResolver.resolve(
                        PlayerTweaksData.PLAYER_DATA_SOURCE_CRONET,
                        PlayerTweaksData.DNS_TYPE_GOOGLE,
                        true));
    }
}
