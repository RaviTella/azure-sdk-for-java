/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.netapp.v2017_08_15;

import rx.Observable;
import com.microsoft.azure.management.netapp.v2017_08_15.implementation.MountTargetsInner;
import com.microsoft.azure.arm.model.HasInner;

/**
 * Type representing MountTargets.
 */
public interface MountTargets extends HasInner<MountTargetsInner> {
    /**
     * List mount targets.
     *
     * @param resourceGroupName The name of the resource group.
     * @param accountName The name of the NetApp account
     * @param poolName The name of the capacity pool
     * @param volumeName The name of the volume
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable for the request
     */
    Observable<MountTarget> listAsync(String resourceGroupName, String accountName, String poolName, String volumeName);

}
