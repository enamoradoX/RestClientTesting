package com.enamorado.restclienttesting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record HeldItem(
        NamedResource item,

        @JsonProperty("version_details")
        List<VersionDetail> versionDetails
) {}

