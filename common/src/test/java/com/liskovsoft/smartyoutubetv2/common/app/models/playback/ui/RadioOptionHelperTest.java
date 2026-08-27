package com.liskovsoft.smartyoutubetv2.common.app.models.playback.ui;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;

public class RadioOptionHelperTest {
    @Test
    public void radioSelectionDeselectsConflictingOptionState() {
        OptionItem ipv4 = UiOptionItem.from("IPv4 only", option -> { }, true);
        Set<String> selectedValues = new HashSet<>();
        selectedValues.add(ipv4.toString());

        RadioOptionHelper.deselect(selectedValues, ipv4);

        assertFalse(ipv4.isSelected());
        assertFalse(selectedValues.contains(ipv4.toString()));
    }
}
