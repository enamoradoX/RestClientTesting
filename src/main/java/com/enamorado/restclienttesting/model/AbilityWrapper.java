package com.enamorado.restclienttesting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AbilityWrapper(
        NamedResource ability,

        @JsonProperty("is_hidden")
        boolean isHidden,

        int slot
) {}

