/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.vision.contentmoderator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The category1 score details of the text. &lt;a
 * href="https://aka.ms/textClassifyCategories"&gt;Click here&lt;/a&gt; for
 * more details on category classification.
 */
public class ClassificationCategory1 {
    /**
     * The category1 score.
     */
    @JsonProperty(value = "Score")
    private Double score;

    /**
     * Get the score value.
     *
     * @return the score value
     */
    public Double score() {
        return this.score;
    }

    /**
     * Set the score value.
     *
     * @param score the score value to set
     * @return the ClassificationCategory1 object itself.
     */
    public ClassificationCategory1 withScore(Double score) {
        this.score = score;
        return this;
    }

}
