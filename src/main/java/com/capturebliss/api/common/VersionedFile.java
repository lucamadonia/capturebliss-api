package com.capturebliss.api.common;

@FunctionalInterface
public interface VersionedFile {
  String apply(String filename, Integer version, String name);
}
