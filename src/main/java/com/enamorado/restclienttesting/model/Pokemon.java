package com.enamorado.restclienttesting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record Pokemon(
        List<AbilityWrapper> abilities,
        @JsonProperty("base_experience") int baseExperience,
        Cries cries,
        List<NamedResource> forms,
        @JsonProperty("game_indices") List<GameIndex> gameIndices,
        int height,
        @JsonProperty("held_items") List<HeldItem> heldItems,
        int id,
        @JsonProperty("is_default") boolean isDefault,
        @JsonProperty("location_area_encounters") String locationAreaEncounters,
        List<MoveWrapper> moves,
        String name,
        int order,
        @JsonProperty("past_abilities") List<PastAbility> pastAbilities,
        @JsonProperty("past_stats") List<PastStat> pastStats,
        @JsonProperty("past_types") List<Object> pastTypes,
        NamedResource species,
        Sprites sprites,
        List<StatWrapper> stats,
        List<TypeWrapper> types,
        int weight
) {}

// --- Supporting Components ---

record NamedResource(String name, String url) {}

record AbilityWrapper(
        NamedResource ability,
        @JsonProperty("is_hidden") boolean isHidden,
        int slot
) {}

record Cries(String latest, String legacy) {}

record GameIndex(
        @JsonProperty("game_index") int gameIndex,
        NamedResource version
) {}

record HeldItem(
        NamedResource item,
        @JsonProperty("version_details") List<VersionDetail> versionDetails
) {}

record VersionDetail(int rarity, NamedResource version) {}

record MoveWrapper(
        NamedResource move,
        @JsonProperty("version_group_details") List<VersionGroupDetail> versionGroupDetails
) {}

record VersionGroupDetail(
        @JsonProperty("level_learned_at") int levelLearnedAt,
        @JsonProperty("move_learn_method") NamedResource moveLearnMethod,
        Integer order,
        @JsonProperty("version_group") NamedResource versionGroup
) {}

record PastAbility(
        List<AbilityWrapper> abilities,
        NamedResource generation
) {}

record PastStat(
        NamedResource generation,
        List<StatWrapper> stats
) {}

record StatWrapper(
        @JsonProperty("base_stat") int baseStat,
        int effort,
        NamedResource stat
) {}

record TypeWrapper(int slot, NamedResource type) {}

// --- Sprites (Handling the nested/dynamic versioning) ---

record Sprites(
        @JsonProperty("back_default") String backDefault,
        @JsonProperty("back_female") String backFemale,
        @JsonProperty("back_shiny") String backShiny,
        @JsonProperty("back_shiny_female") String backShiny_female,
        @JsonProperty("front_default") String frontDefault,
        @JsonProperty("front_female") String frontFemale,
        @JsonProperty("front_shiny") String frontShiny,
        @JsonProperty("front_shiny_female") String frontShinyFemale,
        OtherSprites other,
        Map<String, Map<String, Object>> versions // Using a Map for the highly nested version data
) {}

record OtherSprites(
        @JsonProperty("dream_world") SpriteSet dreamWorld,
        SpriteSet home,
        @JsonProperty("official-artwork") SpriteSet officialArtwork,
        SpriteSet showdown
) {}

record SpriteSet(
        @JsonProperty("front_default") String frontDefault,
        @JsonProperty("front_female") String frontFemale,
        @JsonProperty("front_shiny") String frontShiny,
        @JsonProperty("front_shiny_female") String frontShinyFemale,
        @JsonProperty("back_default") String backDefault,
        @JsonProperty("back_female") String backFemale,
        @JsonProperty("back_shiny") String backShiny,
        @JsonProperty("back_shiny_female") String backShinyFemale
) {}