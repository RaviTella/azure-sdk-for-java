/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.datalakeanalytics.v2016_11_01;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.management.datalakeanalytics.v2016_11_01.implementation.StorageContainerInner;
import com.microsoft.azure.arm.model.Indexable;
import com.microsoft.azure.arm.model.Refreshable;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.microsoft.azure.management.datalakeanalytics.v2016_11_01.implementation.DataLakeAnalyticsManager;
import org.joda.time.DateTime;

/**
 * Type representing StorageContainer.
 */
public interface StorageContainer extends HasInner<StorageContainerInner>, Indexable, Refreshable<StorageContainer>, HasManager<DataLakeAnalyticsManager> {
    /**
     * @return the id value.
     */
    String id();

    /**
     * @return the lastModifiedTime value.
     */
    DateTime lastModifiedTime();

    /**
     * @return the name value.
     */
    String name();

    /**
     * @return the type value.
     */
    String type();

}
