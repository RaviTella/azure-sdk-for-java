/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.apimanagement.v2018_06_01_preview.implementation;

import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.ApiDiagnosticContract;
import com.microsoft.azure.arm.model.implementation.CreatableUpdatableImpl;
import rx.Observable;
import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.AlwaysLog;
import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.SamplingSettings;
import com.microsoft.azure.management.apimanagement.v2018_06_01_preview.PipelineDiagnosticSettings;

class ApiDiagnosticContractImpl extends CreatableUpdatableImpl<ApiDiagnosticContract, DiagnosticContractInner, ApiDiagnosticContractImpl> implements ApiDiagnosticContract, ApiDiagnosticContract.Definition, ApiDiagnosticContract.Update {
    private final ApiManagementManager manager;
    private String resourceGroupName;
    private String serviceName;
    private String apiId;
    private String diagnosticId;
    private String cifMatch;
    private String uifMatch;

    ApiDiagnosticContractImpl(String name, ApiManagementManager manager) {
        super(name, new DiagnosticContractInner());
        this.manager = manager;
        // Set resource name
        this.diagnosticId = name;
        //
    }

    ApiDiagnosticContractImpl(DiagnosticContractInner inner, ApiManagementManager manager) {
        super(inner.name(), inner);
        this.manager = manager;
        // Set resource name
        this.diagnosticId = inner.name();
        // set resource ancestor and positional variables
        this.resourceGroupName = IdParsingUtils.getValueFromIdByName(inner.id(), "resourceGroups");
        this.serviceName = IdParsingUtils.getValueFromIdByName(inner.id(), "service");
        this.apiId = IdParsingUtils.getValueFromIdByName(inner.id(), "apis");
        this.diagnosticId = IdParsingUtils.getValueFromIdByName(inner.id(), "diagnostics");
        //
    }

    @Override
    public ApiManagementManager manager() {
        return this.manager;
    }

    @Override
    public Observable<ApiDiagnosticContract> createResourceAsync() {
        ApiDiagnosticsInner client = this.manager().inner().apiDiagnostics();
        return client.createOrUpdateAsync(this.resourceGroupName, this.serviceName, this.apiId, this.diagnosticId, this.inner(), this.cifMatch)
            .map(innerToFluentMap(this));
    }

    @Override
    public Observable<ApiDiagnosticContract> updateResourceAsync() {
        ApiDiagnosticsInner client = this.manager().inner().apiDiagnostics();
        return client.createOrUpdateAsync(this.resourceGroupName, this.serviceName, this.apiId, this.diagnosticId, this.inner(), this.uifMatch)
            .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<DiagnosticContractInner> getInnerAsync() {
        ApiDiagnosticsInner client = this.manager().inner().apiDiagnostics();
        return client.getAsync(this.resourceGroupName, this.serviceName, this.apiId, this.diagnosticId);
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }


    @Override
    public AlwaysLog alwaysLog() {
        return this.inner().alwaysLog();
    }

    @Override
    public PipelineDiagnosticSettings backend() {
        return this.inner().backend();
    }

    @Override
    public Boolean enableHttpCorrelationHeaders() {
        return this.inner().enableHttpCorrelationHeaders();
    }

    @Override
    public PipelineDiagnosticSettings frontend() {
        return this.inner().frontend();
    }

    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public String loggerId() {
        return this.inner().loggerId();
    }

    @Override
    public String name() {
        return this.inner().name();
    }

    @Override
    public SamplingSettings sampling() {
        return this.inner().sampling();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

    @Override
    public ApiDiagnosticContractImpl withExistingApi(String resourceGroupName, String serviceName, String apiId) {
        this.resourceGroupName = resourceGroupName;
        this.serviceName = serviceName;
        this.apiId = apiId;
        return this;
    }

    @Override
    public ApiDiagnosticContractImpl withLoggerId(String loggerId) {
        this.inner().withLoggerId(loggerId);
        return this;
    }

    @Override
    public ApiDiagnosticContractImpl withIfMatch(String ifMatch) {
        if (isInCreateMode()) {
            this.cifMatch = ifMatch;
        } else {
            this.uifMatch = ifMatch;
        }
        return this;
    }

    @Override
    public ApiDiagnosticContractImpl withAlwaysLog(AlwaysLog alwaysLog) {
        this.inner().withAlwaysLog(alwaysLog);
        return this;
    }

    @Override
    public ApiDiagnosticContractImpl withBackend(PipelineDiagnosticSettings backend) {
        this.inner().withBackend(backend);
        return this;
    }

    @Override
    public ApiDiagnosticContractImpl withEnableHttpCorrelationHeaders(Boolean enableHttpCorrelationHeaders) {
        this.inner().withEnableHttpCorrelationHeaders(enableHttpCorrelationHeaders);
        return this;
    }

    @Override
    public ApiDiagnosticContractImpl withFrontend(PipelineDiagnosticSettings frontend) {
        this.inner().withFrontend(frontend);
        return this;
    }

    @Override
    public ApiDiagnosticContractImpl withSampling(SamplingSettings sampling) {
        this.inner().withSampling(sampling);
        return this;
    }

}
