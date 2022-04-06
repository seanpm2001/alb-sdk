// Copyright 2021 VMware, Inc.
// SPDX-License-Identifier: Apache License 2.0
package models

// This file is auto-generated.

// SeGroupAnalyticsPolicy se group analytics policy
// swagger:model SeGroupAnalyticsPolicy
type SeGroupAnalyticsPolicy struct {

	// Thresholds for various events generated by metrics system. Field introduced in 20.1.3. Allowed in Enterprise with any value edition, Enterprise with Cloud Services edition.
	MetricsEventThresholds []*MetricsEventThreshold `json:"metrics_event_thresholds,omitempty"`
}
