package org.hellgren.utilities.searchers;

import lombok.Builder;

@Builder
public record SearchSettings(
        double xMin, double xMax, double tol, int nIterMax
) {
}
