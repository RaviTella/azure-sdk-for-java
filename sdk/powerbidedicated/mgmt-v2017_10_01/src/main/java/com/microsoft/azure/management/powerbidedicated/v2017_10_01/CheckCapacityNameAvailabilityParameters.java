/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.powerbidedicated.v2017_10_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Details of capacity name request body.
 */
public class CheckCapacityNameAvailabilityParameters {
    /**
     * Name for checking availability.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * The resource type of PowerBI dedicated.
     */
    @JsonProperty(value = "type")
    private String type;

    /**
     * Get name for checking availability.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set name for checking availability.
     *
     * @param name the name value to set
     * @return the CheckCapacityNameAvailabilityParameters object itself.
     */
    public CheckCapacityNameAvailabilityParameters withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the resource type of PowerBI dedicated.
     *
     * @return the type value
     */
    public String type() {
        return this.type;
    }

    /**
     * Set the resource type of PowerBI dedicated.
     *
     * @param type the type value to set
     * @return the CheckCapacityNameAvailabilityParameters object itself.
     */
    public CheckCapacityNameAvailabilityParameters withType(String type) {
        this.type = type;
        return this;
    }

}
