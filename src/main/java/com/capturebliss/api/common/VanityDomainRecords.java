package com.capturebliss.api.common;

public record VanityDomainRecords(
  DomainRecordType recordType,
  String recordDes,
  String recordKey,
  String recordValue
) {
}
