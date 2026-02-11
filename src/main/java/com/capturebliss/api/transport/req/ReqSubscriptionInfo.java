package com.capturebliss.api.transport.req;

import com.capturebliss.api.transport.GenerateTSDef;
import com.capturebliss.api.transport.OptionalPropInTS;
import com.capturebliss.api.transport.PaymentTerms;

// INFO if pricing interval is lifetime, the plan is identified by license (via appsumo)
//      at that time pricingPlan is not read from this request object.
@GenerateTSDef
public record ReqSubscriptionInfo(
  PaymentTerms.Plan pricingPlan,
  PaymentTerms.Interval pricingInterval,
  @OptionalPropInTS
  String lifetimeLicense
) {
  public ReqSubscriptionInfo normalize() {
    PaymentTerms.Plan plan = pricingPlan() == null ? PaymentTerms.Plan.BUSINESS : pricingPlan();
    PaymentTerms.Interval interval = pricingInterval() == null ? PaymentTerms.Interval.YEARLY : pricingInterval();
    return new ReqSubscriptionInfo(plan, interval, lifetimeLicense);
  }
}
