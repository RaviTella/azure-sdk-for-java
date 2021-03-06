/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.automation.v2015_10_31.implementation;

import com.microsoft.azure.management.automation.v2015_10_31.UsageCounterName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Definition of Usage.
 */
public class UsageInner {
    /**
     * Gets or sets the id of the resource.
     */
    @JsonProperty(value = "id")
    private String id;

    /**
     * Gets or sets the usage counter name.
     */
    @JsonProperty(value = "name")
    private UsageCounterName name;

    /**
     * Gets or sets the usage unit name.
     */
    @JsonProperty(value = "unit")
    private String unit;

    /**
     * Gets or sets the current usage value.
     */
    @JsonProperty(value = "currentValue")
    private Double currentValue;

    /**
     * Gets or sets max limit. -1 for unlimited.
     */
    @JsonProperty(value = "limit")
    private Long limit;

    /**
     * Gets or sets the throttle status.
     */
    @JsonProperty(value = "throttleStatus")
    private String throttleStatus;

    /**
     * Get gets or sets the id of the resource.
     *
     * @return the id value
     */
    public String id() {
        return this.id;
    }

    /**
     * Set gets or sets the id of the resource.
     *
     * @param id the id value to set
     * @return the UsageInner object itself.
     */
    public UsageInner withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get gets or sets the usage counter name.
     *
     * @return the name value
     */
    public UsageCounterName name() {
        return this.name;
    }

    /**
     * Set gets or sets the usage counter name.
     *
     * @param name the name value to set
     * @return the UsageInner object itself.
     */
    public UsageInner withName(UsageCounterName name) {
        this.name = name;
        return this;
    }

    /**
     * Get gets or sets the usage unit name.
     *
     * @return the unit value
     */
    public String unit() {
        return this.unit;
    }

    /**
     * Set gets or sets the usage unit name.
     *
     * @param unit the unit value to set
     * @return the UsageInner object itself.
     */
    public UsageInner withUnit(String unit) {
        this.unit = unit;
        return this;
    }

    /**
     * Get gets or sets the current usage value.
     *
     * @return the currentValue value
     */
    public Double currentValue() {
        return this.currentValue;
    }

    /**
     * Set gets or sets the current usage value.
     *
     * @param currentValue the currentValue value to set
     * @return the UsageInner object itself.
     */
    public UsageInner withCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
        return this;
    }

    /**
     * Get gets or sets max limit. -1 for unlimited.
     *
     * @return the limit value
     */
    public Long limit() {
        return this.limit;
    }

    /**
     * Set gets or sets max limit. -1 for unlimited.
     *
     * @param limit the limit value to set
     * @return the UsageInner object itself.
     */
    public UsageInner withLimit(Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Get gets or sets the throttle status.
     *
     * @return the throttleStatus value
     */
    public String throttleStatus() {
        return this.throttleStatus;
    }

    /**
     * Set gets or sets the throttle status.
     *
     * @param throttleStatus the throttleStatus value to set
     * @return the UsageInner object itself.
     */
    public UsageInner withThrottleStatus(String throttleStatus) {
        this.throttleStatus = throttleStatus;
        return this;
    }

}
