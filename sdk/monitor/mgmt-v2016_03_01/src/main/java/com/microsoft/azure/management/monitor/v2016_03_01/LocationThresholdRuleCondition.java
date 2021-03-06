/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.monitor.v2016_03_01;

import org.joda.time.Period;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * A rule condition based on a certain number of locations failing.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "odata.type")
@JsonTypeName("Microsoft.Azure.Management.Insights.Models.LocationThresholdRuleCondition")
public class LocationThresholdRuleCondition extends RuleCondition {
    /**
     * the period of time (in ISO 8601 duration format) that is used to monitor
     * alert activity based on the threshold. If specified then it must be
     * between 5 minutes and 1 day.
     */
    @JsonProperty(value = "windowSize")
    private Period windowSize;

    /**
     * the number of locations that must fail to activate the alert.
     */
    @JsonProperty(value = "failedLocationCount", required = true)
    private int failedLocationCount;

    /**
     * Get the period of time (in ISO 8601 duration format) that is used to monitor alert activity based on the threshold. If specified then it must be between 5 minutes and 1 day.
     *
     * @return the windowSize value
     */
    public Period windowSize() {
        return this.windowSize;
    }

    /**
     * Set the period of time (in ISO 8601 duration format) that is used to monitor alert activity based on the threshold. If specified then it must be between 5 minutes and 1 day.
     *
     * @param windowSize the windowSize value to set
     * @return the LocationThresholdRuleCondition object itself.
     */
    public LocationThresholdRuleCondition withWindowSize(Period windowSize) {
        this.windowSize = windowSize;
        return this;
    }

    /**
     * Get the number of locations that must fail to activate the alert.
     *
     * @return the failedLocationCount value
     */
    public int failedLocationCount() {
        return this.failedLocationCount;
    }

    /**
     * Set the number of locations that must fail to activate the alert.
     *
     * @param failedLocationCount the failedLocationCount value to set
     * @return the LocationThresholdRuleCondition object itself.
     */
    public LocationThresholdRuleCondition withFailedLocationCount(int failedLocationCount) {
        this.failedLocationCount = failedLocationCount;
        return this;
    }

}
