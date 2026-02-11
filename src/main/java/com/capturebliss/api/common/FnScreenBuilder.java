package com.capturebliss.api.common;

import com.capturebliss.api.entity.Screen;

public interface FnScreenBuilder {
    Screen.ScreenBuilder<?, ?> apply(Screen.ScreenBuilder<?, ?> builder);
}
