package com.enamorado.restclienttesting.model;

import java.util.List;

public record PastAbility(
        List<AbilityWrapper> abilities,
        NamedResource generation
) {}

