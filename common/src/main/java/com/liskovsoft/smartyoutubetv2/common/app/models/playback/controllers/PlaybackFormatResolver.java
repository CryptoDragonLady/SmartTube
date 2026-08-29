package com.liskovsoft.smartyoutubetv2.common.app.models.playback.controllers;

/** Chooses between adaptive DASH and SABR playback paths. */
public final class PlaybackFormatResolver {
    public static final int FORMAT_NONE = 0;
    public static final int FORMAT_DASH = 1;
    public static final int FORMAT_SABR = 2;

    private PlaybackFormatResolver() {
    }

    public static int resolveAdaptiveFormat(
            boolean adaptiveAccepted,
            boolean dashAvailable,
            boolean sabrAvailable,
            boolean live,
            boolean forceSabr) {
        if (adaptiveAccepted && forceSabr && sabrAvailable && !live) {
            return FORMAT_SABR;
        }

        // YouTube increasingly returns web responses intended for SABR. Prefer that endpoint when
        // it is available; direct DASH remains the fallback for native clients and live streams.
        if (adaptiveAccepted && sabrAvailable && !live) {
            return FORMAT_SABR;
        }

        if (adaptiveAccepted && dashAvailable) {
            return FORMAT_DASH;
        }

        return FORMAT_NONE;
    }
}
