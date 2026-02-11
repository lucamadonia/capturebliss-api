package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;

import java.util.Set;

@GenerateTSDef
public record ReqCreateOrDeleteCustomFields(Set<String> customFields) {
}
