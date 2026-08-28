package com.liskovsoft.smartyoutubetv2.common.prefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeneralDataTest {
    @Test
    public void longPressBackExitDefaultsOff() {
        assertFalse(GeneralData.parseLongPressBackExit(new String[0]));
    }

    @Test
    public void longPressBackExitRestoresFromTrailingIndex() {
        String[] split = new String[74];
        split[73] = "true";

        assertTrue(GeneralData.parseLongPressBackExit(split));
    }
}
