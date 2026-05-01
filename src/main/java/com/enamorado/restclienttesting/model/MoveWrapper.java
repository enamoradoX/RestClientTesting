package com.enamorado.restclienttesting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MoveWrapper(
        NamedResource move,

        @JsonProperty("version_group_details")
        List<VersionGroupDetail> versionGroupDetails
) {}

