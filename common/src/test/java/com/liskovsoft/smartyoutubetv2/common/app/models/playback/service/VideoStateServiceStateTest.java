package com.liskovsoft.smartyoutubetv2.common.app.models.playback.service;

import static org.junit.Assert.assertEquals;

import com.liskovsoft.smartyoutubetv2.common.app.models.data.Video;
import com.liskovsoft.smartyoutubetv2.common.app.models.playback.service.VideoStateService.State;

import org.junit.Test;

public class VideoStateServiceStateTest {
    @Test
    public void fullyWatchedStateRoundTripKeepsCompleteProgress() {
        Video video = createVideo(100);

        State restored = State.from(new State(video, 60_000, 60_000).toString());

        assertEquals(60_000, restored.positionMs);
        assertEquals(60_000, restored.durationMs);
        assertEquals(100f, restored.video.percentWatched, 0f);
    }

    @Test
    public void legacyFullyWatchedStateWithUnknownDurationIsNormalized() {
        Video video = createVideo(100);

        State restored = State.from(new State(video, 60_000).toString());

        assertEquals(60_000, restored.positionMs);
        assertEquals(60_000, restored.durationMs);
        assertEquals(100f, restored.video.percentWatched, 0f);
    }

    @Test
    public void invalidDurationKeepsSerializedProgress() {
        Video video = createVideo(42);

        State restored = State.from(new State(video, 5_000, 0).toString());

        assertEquals(0, restored.durationMs);
        assertEquals(42f, restored.video.percentWatched, 0f);
    }

    @Test
    public void legacyPlayFromStartStateRemainsUnwatched() {
        Video video = createVideo(0);

        State restored = State.from(new State(video, 0).toString());

        assertEquals(0, restored.positionMs);
        assertEquals(-1, restored.durationMs);
        assertEquals(0f, restored.video.percentWatched, 0f);
    }

    private static Video createVideo(float percentWatched) {
        Video video = new Video();
        video.videoId = "video-id";
        video.percentWatched = percentWatched;
        return video;
    }
}
