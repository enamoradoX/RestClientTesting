package com.enamorado.restclienttesting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameIndex(
        @JsonProperty("game_index")
        int gameIndex,

        NamedResource version
) {}

