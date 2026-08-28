package com.liskovsoft.smartyoutubetv2.common.app.models.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.liskovsoft.mediaserviceinterfaces.data.MediaItem;

import org.junit.Test;

public class SimpleMediaItemTest {
    @Test
    public void channelRouteContainsOnlyChannelIdentity() {
        Video video = new Video();
        video.videoId = "video-id";
        video.playlistId = "playlist-id";
        video.reloadPageKey = "reload-key";
        video.playlistParams = "params";
        video.channelId = "channel-id";

        MediaItem route = SimpleMediaItem.fromChannel(video);

        assertEquals("channel-id", route.getChannelId());
        assertNull(route.getVideoId());
        assertNull(route.getPlaylistId());
        assertNull(route.getReloadPageKey());
        assertNull(route.getParams());
    }
}
