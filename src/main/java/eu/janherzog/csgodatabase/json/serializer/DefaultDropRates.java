package eu.janherzog.csgodatabase.json.serializer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum DefaultDropRates {

    CONTRABAND(0.13, "Contraband", "#E4AE33"),
    RARESPECIAL(0.26, "Rare Special", "#E4AE32"),
    COVERT(0.64, "Covert", "#EB4B4B"),
    CLASSIFIED(3.2, "Classified", "#D32CE6"),
    RESTRICTED(15.98, "Restricted", "#8847FF"),
    MILSPEC(79.92, "Mil-Spec", "#4B69FF"),
    INDUSTRIAL(83.43, "Industrial grade", "#5E98D9"),
    CONSUMER(87.87, "Consumer grade", "#B0C3D9"),
    ;

    @Getter
    private double rate;

    @Getter
    private String name;

    @Getter
    private String color;


    public static DefaultDropRates getByName(String name) {
        return Arrays.stream(DefaultDropRates.values()).filter(e -> e.getName().equals(name)).findAny().orElse(null);
    }

}
