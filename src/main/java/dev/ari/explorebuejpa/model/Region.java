package dev.ari.explorebuejpa.model;

import lombok.Getter;

@Getter
public enum Region {

    North_Zone("North Zone"), South_Zone("South Zone"),
    East_Zone("East Zone"), West_Zone("West Zone"),
    Capital("Capital"), Varies("Varies");

    private String label;

    private Region(String label) {
        this.label = label;
    }

    public static Region findByLabel(String byLabel) {
        for(Region r:Region.values()) {
            if (r.label.equalsIgnoreCase(byLabel))
                return r;
        }
        return null;
    }

}
