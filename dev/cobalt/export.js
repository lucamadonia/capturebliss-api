/*
 * This file contains list of things that are hardcoded / uploaded to cobalt.
 * Keep this list updated, in case we need vendor lock out
 */

// Used [Create contact property and group](https://app.gocobalt.io/workflows/65fd03daa2d053b048cdf2ab)
// Step 7
const CaptureblissContactProps = [{
  "name": "capturebliss_demo_completion",
  "label": "Demo completion (%)",
  "description": "Demo completion percentage across all the demos this contact has seen",
  "groupName": "capturebliss",
  "type": "number",
  "formField": false
}, {
  "name": "capturebliss_total_time_spent",
  "label": "Time spent on demos (in seconds)",
  "description": "ime spent in seconds interacting with the demos",
  "groupName": "capturebliss",
  "type": "number",
  "fieldType": "number",
  "formField": false
}, {
  "name": "capturebliss_demo_unique_view",
  "label": "Unique demos viewed",
  "description": "Number of unique demos that the contact has interacted with",
  "groupName": "capturebliss",
  "type": "number",
  "fieldType": "number",
  "formField": false
}, {
  "name": "capturebliss_demo_total_view",
  "label": "Total demos viewed",
  "description": "Number of demos that the contact has interacted with",
  "groupName": "capturebliss",
  "type": "number",
  "fieldType": "number",
  "formField": false
}, {
  "name": "capturebliss_demo_cta_click_rate",
  "label": "CTA click rate",
  "description": "CTA click rate for all the demos this contact has interacted with",
  "groupName": "capturebliss",
  "type": "number",
  "fieldType": "number",
  "formField": false
}, {
  "name": "capturebliss_last_active_at",
  "label": "Last active at",
  "description": "Last time this contact has interacted with the demos",
  "groupName": "capturebliss",
  "type": "date",
  "fieldType": "date",
  "formField": false
}];

