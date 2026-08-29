package com.liskovsoft.smartyoutubetv2.common.exoplayer;

import com.liskovsoft.mediaserviceinterfaces.data.MediaItemFormatInfo;

final class PlaybackRequestHeaders {
    private PlaybackRequestHeaders() {
    }

    static String resolveUserAgent(MediaItemFormatInfo.ClientInfo clientInfo, String defaultUserAgent) {
        String userAgent = clientInfo != null ? clientInfo.getUserAgent() : null;
        return userAgent != null && !userAgent.trim().isEmpty() ?
                userAgent : defaultUserAgent;
    }
}
