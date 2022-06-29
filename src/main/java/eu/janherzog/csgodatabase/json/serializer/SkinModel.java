package eu.janherzog.csgodatabase.json.serializer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkinModel {

    private String name, desc, lore, date_added;
    private boolean can_be_souvenir, can_be_stattrak;
    private Wears wears;

}
