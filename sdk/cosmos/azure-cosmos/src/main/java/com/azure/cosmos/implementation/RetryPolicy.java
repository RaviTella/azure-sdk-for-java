// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.implementation;

import com.azure.cosmos.ConnectionPolicy;
import com.azure.cosmos.ThrottlingRetryOptions;

/**
 * While this class is public, but it is not part of our published public APIs.
 * This is meant to be internally used only by our sdk.
 *
 * Represents the retry policy configuration associated with a DocumentClient instance.
 */
public class RetryPolicy implements IRetryPolicyFactory {
    private final GlobalEndpointManager globalEndpointManager;
    private final boolean enableEndpointDiscovery;
    private final ThrottlingRetryOptions throttlingRetryOptions;

    public RetryPolicy(GlobalEndpointManager globalEndpointManager, ConnectionPolicy connectionPolicy) {
        this.enableEndpointDiscovery = connectionPolicy.isEndpointDiscoveryEnabled();
        this.globalEndpointManager = globalEndpointManager;
        this.throttlingRetryOptions = connectionPolicy.getThrottlingRetryOptions();
    }

    @Override
    public DocumentClientRetryPolicy getRequestPolicy() {
        ClientRetryPolicy clientRetryPolicy = new ClientRetryPolicy(this.globalEndpointManager,
                this.enableEndpointDiscovery, this.throttlingRetryOptions);

        return clientRetryPolicy;
    }
}
