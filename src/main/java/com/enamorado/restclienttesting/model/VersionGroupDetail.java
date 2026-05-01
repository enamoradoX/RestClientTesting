package com.enamorado.restclienttesting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VersionGroupDetail(
        @JsonProperty("level_learned_at")
        int levelLearnedAt,

        @JsonProperty("move_learn_method")
        NamedResource moveLearnMethod,

        Integer order,

        @JsonProperty("version_group")
        NamedResource versionGroup
) {}

