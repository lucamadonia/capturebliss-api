package com.capturebliss.api.common;

import com.capturebliss.api.transport.resp.ResponseBase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TransportObjRef {
  Class<? extends ResponseBase> cls();
}
