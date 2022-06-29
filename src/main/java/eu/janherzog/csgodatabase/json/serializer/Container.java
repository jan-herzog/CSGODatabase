package eu.janherzog.csgodatabase.json.serializer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Container {

    private String image_url, name;

    private Content content;

}
