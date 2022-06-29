package eu.janherzog.csgodatabase.json.serializer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Wears {

    @SerializedName("Factory New")
    private String fn;

    @SerializedName("Minimal Wear")
    private String mw;

    @SerializedName("Field-Tested")
    private String ft;

    @SerializedName("Well-Worn")
    private String ww;

    @SerializedName("Battle-Scarred")
    private String bs;

    public String getBest() {
        if (fn != null)
            return fn;
        if (mw != null)
            return mw;
        if (ft != null)
            return ft;
        if (ww != null)
            return ww;
        if (bs != null)
            return bs;
        return null;
    }

}
