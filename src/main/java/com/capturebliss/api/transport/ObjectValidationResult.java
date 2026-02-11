package com.capturebliss.api.transport;

import java.util.List;

public record ObjectValidationResult(boolean isValid, List<String> validationMsg) {
}
