package com.capturebliss.api.common;

import com.capturebliss.api.entity.DemoEntity;

public interface FnTourBuilder {
  DemoEntity.DemoEntityBuilder<?, ?> apply(DemoEntity.DemoEntityBuilder<?, ?> builder);
}
