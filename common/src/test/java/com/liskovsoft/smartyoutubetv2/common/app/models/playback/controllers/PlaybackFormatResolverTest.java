package com.liskovsoft.smartyoutubetv2.common.app.models.playback.controllers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlaybackFormatResolverTest {
    @Test
    public void dashKeepsPriorityWhenSabrIsNotForced() {
        assertEquals(
                PlaybackFormatResolver.FORMAT_DASH,
                PlaybackFormatResolver.resolveAdaptiveFormat(true, true, true, false, false));
    }

    @Test
    public void forcedSabrTakesPriorityOverDashWhenAvailable() {
        assertEquals(
                PlaybackFormatResolver.FORMAT_SABR,
                PlaybackFormatResolver.resolveAdaptiveFormat(true, true, true, false, true));
    }

    @Test
    public void forcedSabrFallsBackToDashWhenSabrIsUnavailable() {
        assertEquals(
                PlaybackFormatResolver.FORMAT_DASH,
                PlaybackFormatResolver.resolveAdaptiveFormat(true, true, false, false, true));
    }

    @Test
    public void forcedSabrDoesNotUseUnsupportedLiveSabr() {
        assertEquals(
                PlaybackFormatResolver.FORMAT_DASH,
                PlaybackFormatResolver.resolveAdaptiveFormat(true, true, true, true, true));
    }
}
