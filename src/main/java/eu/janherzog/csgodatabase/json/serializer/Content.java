package eu.janherzog.csgodatabase.json.serializer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Content {

    @SerializedName("Rare Special Items")
    private SkinModel[] rareSpecialItems;


    @SerializedName("Covert Skins")
    private SkinModel[] covertSkins;

    @SerializedName("Classified Skins")
    private SkinModel[] classifiedSkins;

    @SerializedName("Restricted Skins")
    private SkinModel[] restrictedSkins;

    @SerializedName("Mil-Spec Skins")
    private SkinModel[] milspecSkins;

    @SerializedName("Consumer Grade Skins")
    private SkinModel[] consumerGradeSkins;


}
