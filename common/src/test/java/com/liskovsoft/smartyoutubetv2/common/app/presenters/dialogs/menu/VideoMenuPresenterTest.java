package com.liskovsoft.smartyoutubetv2.common.app.presenters.dialogs.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.liskovsoft.mediaserviceinterfaces.MediaItemService;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;

public class VideoMenuPresenterTest {
    @Test
    public void directNotInterestedUsesOriginalTokenAndCompletesBeforeRemoval() {
        AtomicReference<String> submittedToken = new AtomicReference<>();
        AtomicBoolean removed = new AtomicBoolean();
        AtomicBoolean failed = new AtomicBoolean();
        MediaItemService service = createService(Observable.empty(), submittedToken);

        VideoMenuPresenter.submitNotInterested(
                service, "original-token", error -> failed.set(true), () -> removed.set(true));

        assertEquals("original-token", submittedToken.get());
        assertTrue(removed.get());
        assertFalse(failed.get());
    }

    @Test
    public void directNotInterestedErrorDoesNotRemoveCard() {
        AtomicReference<String> submittedToken = new AtomicReference<>();
        AtomicReference<Throwable> failure = new AtomicReference<>();
        AtomicBoolean removed = new AtomicBoolean();
        RuntimeException expected = new RuntimeException("failed");
        MediaItemService service = createService(Observable.error(expected), submittedToken);

        VideoMenuPresenter.submitNotInterested(
                service, "original-token", failure::set, () -> removed.set(true));

        assertEquals("original-token", submittedToken.get());
        assertSame(expected, failure.get());
        assertFalse(removed.get());
    }

    private static MediaItemService createService(Observable<Void> result, AtomicReference<String> submittedToken) {
        return (MediaItemService) Proxy.newProxyInstance(
                MediaItemService.class.getClassLoader(),
                new Class<?>[]{MediaItemService.class},
                (proxy, method, args) -> {
                    if ("markAsNotInterestedObserve".equals(method.getName())) {
                        submittedToken.set((String) args[0]);
                        return result;
                    }
                    throw new UnsupportedOperationException(method.getName());
                });
    }
}
