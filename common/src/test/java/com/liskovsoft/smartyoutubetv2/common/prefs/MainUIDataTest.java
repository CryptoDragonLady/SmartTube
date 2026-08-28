package com.liskovsoft.smartyoutubetv2.common.prefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MainUIDataTest {
    @Test
    public void notInterestedFeedbackDefaultsToEnabledWhenMissing() {
        assertTrue(MainUIData.parseNotInterestedFeedbackEnabled(new String[24]));
    }

    @Test
    public void notInterestedFeedbackReadsTrailingDisabledValue() {
        String[] data = new String[25];
        data[24] = "false";

        assertFalse(MainUIData.parseNotInterestedFeedbackEnabled(data));
    }
}
