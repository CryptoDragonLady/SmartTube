package com.liskovsoft.smartyoutubetv2.common.prefs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainUIDataTest {
    @Test
    public void openChannelUploadsIsEnabledByDefault() {
        assertEquals(
                MainUIData.MENU_ITEM_OPEN_CHANNEL_UPLOADS,
                MainUIData.MENU_ITEM_DEFAULT & MainUIData.MENU_ITEM_OPEN_CHANNEL_UPLOADS);
    }
}
