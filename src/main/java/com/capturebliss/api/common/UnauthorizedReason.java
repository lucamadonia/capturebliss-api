package com.capturebliss.api.common;

import com.capturebliss.api.transport.GenerateTSDef;

@GenerateTSDef
public enum UnauthorizedReason {
  OrgSuggestedButInvalidAssociation,
  EmailIdExistsButLoginMethodDoesNotMatch
}
