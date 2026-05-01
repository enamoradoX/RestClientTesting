package com.enamorado.restclienttesting.model;

import java.util.List;

public record PastStat(
        NamedResource generation,
        List<StatWrapper> stats
) {}

