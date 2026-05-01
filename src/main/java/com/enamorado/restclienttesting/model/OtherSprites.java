package com.enamorado.restclienttesting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OtherSprites(
        @JsonProperty("dream_world")
        SpriteSet dreamWorld,

        SpriteSet home,

        @JsonProperty("official-artwork")
        SpriteSet officialArtwork,

        SpriteSet showdown
) {}

