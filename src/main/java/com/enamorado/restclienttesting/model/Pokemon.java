package com.enamorado.restclienttesting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// =====================================================================
// Root Model
// =====================================================================

public record Pokemon(

        List<AbilityWrapper> abilities,

        @JsonProperty("base_experience")
        int baseExperience,

        Cries cries,

        List<NamedResource> forms,

        @JsonProperty("game_indices")
        List<GameIndex> gameIndices,

        int height,

        @JsonProperty("held_items")
        List<HeldItem> heldItems,

        int id,

        @JsonProperty("is_default")
        boolean isDefault,

        @JsonProperty("location_area_encounters")
        String locationAreaEncounters,

        List<MoveWrapper> moves,

        String name,

        int order,

        @JsonProperty("past_abilities")
        List<PastAbility> pastAbilities,

        @JsonProperty("past_stats")
        List<PastStat> pastStats,

        @JsonProperty("past_types")
        List<Object> pastTypes,

        NamedResource species,

        Sprites sprites,

        List<StatWrapper> stats,

        List<TypeWrapper> types,

        int weight

) {}
