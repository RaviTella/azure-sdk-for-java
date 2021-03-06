/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute.v2017_09_01.implementation;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.AzureResponseBuilder;
import com.microsoft.azure.credentials.AzureTokenCredentials;
import com.microsoft.azure.management.apigeneration.Beta;
import com.microsoft.azure.management.apigeneration.Beta.SinceVersion;
import com.microsoft.azure.arm.resources.AzureConfigurable;
import com.microsoft.azure.serializer.AzureJacksonAdapter;
import com.microsoft.rest.RestClient;
import com.microsoft.azure.management.compute.v2017_09_01.ResourceSkus;
import com.microsoft.azure.arm.resources.implementation.AzureConfigurableCoreImpl;
import com.microsoft.azure.arm.resources.implementation.ManagerCore;

/**
 * Entry point to Azure Compute resource management.
 */
public final class ComputeManager extends ManagerCore<ComputeManager, ComputeManagementClientImpl> {
    private ResourceSkus resourceSkus;
    /**
    * Get a Configurable instance that can be used to create ComputeManager with optional configuration.
    *
    * @return the instance allowing configurations
    */
    public static Configurable configure() {
        return new ComputeManager.ConfigurableImpl();
    }
    /**
    * Creates an instance of ComputeManager that exposes Compute resource management API entry points.
    *
    * @param credentials the credentials to use
    * @param subscriptionId the subscription UUID
    * @return the ComputeManager
    */
    public static ComputeManager authenticate(AzureTokenCredentials credentials, String subscriptionId) {
        return new ComputeManager(new RestClient.Builder()
            .withBaseUrl(credentials.environment(), AzureEnvironment.Endpoint.RESOURCE_MANAGER)
            .withCredentials(credentials)
            .withSerializerAdapter(new AzureJacksonAdapter())
            .withResponseBuilderFactory(new AzureResponseBuilder.Factory())
            .build(), subscriptionId);
    }
    /**
    * Creates an instance of ComputeManager that exposes Compute resource management API entry points.
    *
    * @param restClient the RestClient to be used for API calls.
    * @param subscriptionId the subscription UUID
    * @return the ComputeManager
    */
    public static ComputeManager authenticate(RestClient restClient, String subscriptionId) {
        return new ComputeManager(restClient, subscriptionId);
    }
    /**
    * The interface allowing configurations to be set.
    */
    public interface Configurable extends AzureConfigurable<Configurable> {
        /**
        * Creates an instance of ComputeManager that exposes Compute management API entry points.
        *
        * @param credentials the credentials to use
        * @param subscriptionId the subscription UUID
        * @return the interface exposing Compute management API entry points that work across subscriptions
        */
        ComputeManager authenticate(AzureTokenCredentials credentials, String subscriptionId);
    }

    /**
     * @return Entry point to manage ResourceSkus.
     */
    public ResourceSkus resourceSkus() {
        if (this.resourceSkus == null) {
            this.resourceSkus = new ResourceSkusImpl(this);
        }
        return this.resourceSkus;
    }

    /**
    * The implementation for Configurable interface.
    */
    private static final class ConfigurableImpl extends AzureConfigurableCoreImpl<Configurable> implements Configurable {
        public ComputeManager authenticate(AzureTokenCredentials credentials, String subscriptionId) {
           return ComputeManager.authenticate(buildRestClient(credentials), subscriptionId);
        }
     }
    private ComputeManager(RestClient restClient, String subscriptionId) {
        super(
            restClient,
            subscriptionId,
            new ComputeManagementClientImpl(restClient).withSubscriptionId(subscriptionId));
    }
}
