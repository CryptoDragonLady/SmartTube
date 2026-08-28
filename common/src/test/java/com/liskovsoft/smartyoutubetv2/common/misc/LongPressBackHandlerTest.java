package com.liskovsoft.smartyoutubetv2.common.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LongPressBackHandlerTest {
    @Test
    public void shortBackPressIsNotConsumed() {
        int[] exitCount = {0};
        LongPressBackHandler handler = new LongPressBackHandler(() -> exitCount[0]++);

        assertFalse(handler.handle(true, true, true, false, 0, false));
        assertFalse(handler.handle(true, true, false, true, 0, false));
        assertEquals(0, exitCount[0]);
    }

    @Test
    public void flaggedLongPressExitsOnceAndConsumesRelease() {
        int[] exitCount = {0};
        LongPressBackHandler handler = new LongPressBackHandler(() -> exitCount[0]++);

        assertTrue(handler.handle(true, true, true, false, 1, true));
        assertTrue(handler.handle(true, true, true, false, 2, true));
        assertTrue(handler.handle(true, true, false, true, 0, false));
        assertEquals(1, exitCount[0]);

        assertFalse(handler.handle(true, true, true, false, 0, false));
    }

    @Test
    public void repeatedBackPressTriggersFallback() {
        int[] exitCount = {0};
        LongPressBackHandler handler = new LongPressBackHandler(() -> exitCount[0]++);

        assertTrue(handler.handle(true, true, true, false, 3, false));
        assertEquals(1, exitCount[0]);
    }

    @Test
    public void otherKeyResetsConsumedSequence() {
        int[] exitCount = {0};
        LongPressBackHandler handler = new LongPressBackHandler(() -> exitCount[0]++);

        assertTrue(handler.handle(true, true, true, false, 3, false));
        assertFalse(handler.handle(true, false, true, false, 0, false));
        assertFalse(handler.handle(true, true, false, true, 0, false));
        assertEquals(1, exitCount[0]);
    }

    @Test
    public void disabledSettingAndOtherKeysDoNotExit() {
        int[] exitCount = {0};
        LongPressBackHandler handler = new LongPressBackHandler(() -> exitCount[0]++);

        assertFalse(handler.handle(false, true, true, false, 3, true));
        assertFalse(handler.handle(true, false, true, false, 3, true));
        assertEquals(0, exitCount[0]);
    }
}
