package com.liskovsoft.smartyoutubetv2.common.app.models.playback.ui;

import java.util.Set;

/** Applies mutual-exclusion updates to checkbox-style option groups. */
public final class RadioOptionHelper {
    private RadioOptionHelper() {
    }

    public static void deselect(Set<?> selectedValues, OptionItem... radioItems) {
        if (radioItems == null) {
            return;
        }

        for (OptionItem radioItem : radioItems) {
            if (selectedValues != null) {
                selectedValues.remove(radioItem.toString());
            }
            radioItem.onSelect(false);
        }
    }
}
