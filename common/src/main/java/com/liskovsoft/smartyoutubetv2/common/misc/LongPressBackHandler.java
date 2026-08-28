package com.liskovsoft.smartyoutubetv2.common.misc;

final class LongPressBackHandler {
    private static final int LONG_PRESS_REPEAT_COUNT = 3;
    private final Runnable mOnLongPress;
    private boolean mIsConsuming;

    LongPressBackHandler(Runnable onLongPress) {
        mOnLongPress = onLongPress;
    }

    boolean handle(boolean enabled, boolean isBack, boolean isDown, boolean isUp, int repeatCount, boolean isLongPress) {
        if (!enabled || !isBack) {
            mIsConsuming = false;
            return false;
        }

        if (mIsConsuming) {
            if (isUp) {
                mIsConsuming = false;
            }
            return true;
        }

        if (isDown && (isLongPress || repeatCount >= LONG_PRESS_REPEAT_COUNT)) {
            mIsConsuming = true;
            mOnLongPress.run();
            return true;
        }

        return false;
    }
}
