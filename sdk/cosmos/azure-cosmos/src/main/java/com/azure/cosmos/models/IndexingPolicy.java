// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.models;

import com.azure.cosmos.implementation.Constants;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the indexing policy configuration for a collection in the Azure Cosmos DB database service.
 */
public final class IndexingPolicy extends JsonSerializable {

    private static final String DEFAULT_PATH = "/*";

    private List<IncludedPath> includedPaths;
    private List<ExcludedPath> excludedPaths;
    private List<List<CompositePath>> compositeIndexes;
    private List<SpatialSpec> spatialIndexes;

    /**
     * Constructor.
     */
    public IndexingPolicy() {
        this.setAutomatic(true);
        this.setIndexingMode(IndexingMode.CONSISTENT);
    }

    /**
     * Initializes a new instance of the IndexingPolicy class with the specified set of indexes as
     * default index specifications for the root path.
     * <p>
     * The following example shows how to override the default indexingPolicy for root path:
     * <pre>
     * {@code
     * HashIndex hashIndexOverride = Index.HASH(DataType.STRING, 5);
     * RangeIndex rangeIndexOverride = Index.RANGE(DataType.NUMBER, 2);
     * SpatialIndex spatialIndexOverride = Index.SPATIAL(DataType.POINT);
     *
     * IndexingPolicy indexingPolicy = new IndexingPolicy(hashIndexOverride, rangeIndexOverride, spatialIndexOverride);
     * }
     * </pre>
     *
     * @param defaultIndexOverrides comma separated set of indexes that serve as default index specifications for the
     * root path.
     * @throws IllegalArgumentException throws when defaultIndexOverrides is null
     */
    public IndexingPolicy(Index[] defaultIndexOverrides) {
        this();

        if (defaultIndexOverrides == null) {
            throw new IllegalArgumentException("defaultIndexOverrides is null.");
        }

        IncludedPath includedPath = new IncludedPath();
        includedPath.setPath(IndexingPolicy.DEFAULT_PATH);
        includedPath.setIndexes(new ArrayList<Index>(Arrays.asList(defaultIndexOverrides)));
        this.getIncludedPaths().add(includedPath);
    }

    /**
     * Constructor.
     *
     * @param jsonString the json string that represents the indexing policy.
     */
    IndexingPolicy(String jsonString) {
        super(jsonString);
    }

    /**
     * Gets whether automatic indexing is enabled for a collection.
     * <p>
     * In automatic indexing, documents can be explicitly excluded from indexing using RequestOptions. In manual
     * indexing, documents can be explicitly included.
     *
     * @return the automatic
     */
    public Boolean getAutomatic() {
        return super.getBoolean(Constants.Properties.AUTOMATIC);
    }

    /**
     * Sets whether automatic indexing is enabled for a collection.
     * <p>
     * In automatic indexing, documents can be explicitly excluded from indexing using RequestOptions. In manual
     * indexing, documents can be explicitly included.
     *
     * @param automatic the automatic
     * @return the Indexing Policy.
     */
    public IndexingPolicy setAutomatic(boolean automatic) {
        super.set(Constants.Properties.AUTOMATIC, automatic);
        return this;
    }

    /**
     * Gets the indexing mode (consistent or lazy).
     *
     * @return the indexing mode.
     */
    public IndexingMode getIndexingMode() {
        IndexingMode result = IndexingMode.LAZY;
        try {
            result = IndexingMode.valueOf(StringUtils.upperCase(super.getString(Constants.Properties.INDEXING_MODE)));
        } catch (IllegalArgumentException e) {
            this.getLogger().warn("INVALID indexingMode value {}.",
                super.getString(Constants.Properties.INDEXING_MODE));
        }
        return result;
    }

    /**
     * Sets the indexing mode (consistent or lazy).
     *
     * @param indexingMode the indexing mode.
     * @return the Indexing Policy.
     */
    public IndexingPolicy setIndexingMode(IndexingMode indexingMode) {
        super.set(Constants.Properties.INDEXING_MODE, indexingMode.toString());
        return this;
    }

    /**
     * Gets the paths that are chosen to be indexed by the user.
     *
     * @return the included paths.
     */
    public List<IncludedPath> getIncludedPaths() {
        if (this.includedPaths == null) {
            this.includedPaths = super.getList(Constants.Properties.INCLUDED_PATHS, IncludedPath.class);

            if (this.includedPaths == null) {
                this.includedPaths = new ArrayList<IncludedPath>();
            }
        }

        return this.includedPaths;
    }

    /**
     * Sets included paths.
     *
     * @param includedPaths the included paths
     * @return the included paths
     */
    public IndexingPolicy setIncludedPaths(List<IncludedPath> includedPaths) {
        this.includedPaths = includedPaths;
        return this;
    }

    /**
     * Gets the paths that are not indexed.
     *
     * @return the excluded paths.
     */
    public List<ExcludedPath> getExcludedPaths() {
        if (this.excludedPaths == null) {
            this.excludedPaths = super.getList(Constants.Properties.EXCLUDED_PATHS, ExcludedPath.class);

            if (this.excludedPaths == null) {
                this.excludedPaths = new ArrayList<ExcludedPath>();
            }
        }

        return this.excludedPaths;
    }

    /**
     * Sets excluded paths.
     *
     * @param excludedPaths the excluded paths
     * @return the excluded paths
     */
    public IndexingPolicy setExcludedPaths(List<ExcludedPath> excludedPaths) {
        this.excludedPaths = excludedPaths;
        return this;
    }

    /**
     * Gets the composite indexes for additional indexes.
     *
     * @return the composite indexes.
     */
    public List<List<CompositePath>> getCompositeIndexes() {
        if (this.compositeIndexes == null) {
            this.compositeIndexes = new ArrayList<>();
            ArrayNode compositeIndexes = (ArrayNode) super.get(Constants.Properties.COMPOSITE_INDEXES);
            for (int i = 0; i < compositeIndexes.size(); i++) {
                ArrayNode compositeIndex = (ArrayNode) compositeIndexes.get(i);
                ArrayList<CompositePath> compositePaths = new ArrayList<CompositePath>();
                for (int j = 0; j < compositeIndex.size(); j++) {
                    CompositePath candidateCompositePath = new CompositePath(compositeIndex.get(j).toString());
                    compositePaths.add(candidateCompositePath);
                }
                this.compositeIndexes.add(compositePaths);
            }
        }

        return this.compositeIndexes;
    }

    /**
     * Sets the composite indexes for additional indexes.
     *
     * @param compositeIndexes the composite indexes.
     * @return the Indexing Policy.
     */
    public IndexingPolicy setCompositeIndexes(List<List<CompositePath>> compositeIndexes) {
        this.compositeIndexes = compositeIndexes;
        super.set(Constants.Properties.COMPOSITE_INDEXES, this.compositeIndexes);
        return this;
    }

    /**
     * Sets the spatial indexes for additional indexes.
     *
     * @return the spatial indexes.
     */
    public List<SpatialSpec> getSpatialIndexes() {
        if (this.spatialIndexes == null) {
            this.spatialIndexes = super.getList(Constants.Properties.SPATIAL_INDEXES, SpatialSpec.class);

            if (this.spatialIndexes == null) {
                this.spatialIndexes = new ArrayList<SpatialSpec>();
            }
        }

        return this.spatialIndexes;
    }

    /**
     * Sets the spatial indexes for additional indexes.
     *
     * @param spatialIndexes the spatial indexes.
     * @return the Indexing Policy.
     */
    public IndexingPolicy setSpatialIndexes(List<SpatialSpec> spatialIndexes) {
        this.spatialIndexes = spatialIndexes;
        super.set(Constants.Properties.SPATIAL_INDEXES, this.spatialIndexes);
        return this;
    }

    @Override
    protected void populatePropertyBag() {
        super.populatePropertyBag();
        // If indexing mode is not 'none' and not paths are set, set them to the defaults
        if (this.getIndexingMode() != IndexingMode.NONE && this.getIncludedPaths().size() == 0
                && this.getExcludedPaths().size() == 0) {
            IncludedPath includedPath = new IncludedPath();
            includedPath.setPath(IndexingPolicy.DEFAULT_PATH);
            this.getIncludedPaths().add(includedPath);
        }

        if (this.includedPaths != null) {
            for (IncludedPath includedPath : this.includedPaths) {
                includedPath.populatePropertyBag();
            }
            super.set(Constants.Properties.INCLUDED_PATHS, this.includedPaths);
        }

        if (this.excludedPaths != null) {
            for (ExcludedPath excludedPath : this.excludedPaths) {
                excludedPath.populatePropertyBag();
            }
            super.set(Constants.Properties.EXCLUDED_PATHS, this.excludedPaths);
        }
    }
}
