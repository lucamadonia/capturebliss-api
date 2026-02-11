package com.capturebliss.api.common;

import java.io.Serializable;
import java.util.Map;

public interface MapSerializable extends Serializable {
    Map<String, String> toMap();
}
