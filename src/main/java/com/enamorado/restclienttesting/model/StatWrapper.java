package com.enamorado.restclienttesting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StatWrapper(
        @JsonProperty("base_stat")
        int baseStat,

        int effort,

        NamedResource stat
) {}

