package eu.janherzog.csgodatabase.controller;

import eu.janherzog.csgodatabase.controller.model.SkinModel;
import eu.janherzog.csgodatabase.database.model.Collection;
import eu.janherzog.csgodatabase.database.model.Crate;
import eu.janherzog.csgodatabase.database.model.Skin;
import eu.janherzog.csgodatabase.database.repository.CollectionRepository;
import eu.janherzog.csgodatabase.database.repository.CrateRepository;
import eu.janherzog.csgodatabase.database.repository.SkinRepository;
import eu.janherzog.csgodatabase.json.serializer.DefaultDropRates;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ViewController {

    private final CrateRepository repository;
    private final SkinRepository skinRepository;
    private final CollectionRepository collectionRepository;

    @GetMapping("/collections/{tag}")
    public String viewCollection(@PathVariable String tag, Model model) {
        Collection collection = getCollectionbyTag(tag);
        if (collection == null) {
            System.out.println("no exact mapping for tag: " + tag);
            return "redirect:/";
        }
        model.addAttribute("collection", collection.getName());
        List<SkinModel> skinModels = new ArrayList<>();
        List<Skin> skins = skinRepository.findByCollection(collection);
        for (Skin skin : skins) {
            DefaultDropRates drop = DefaultDropRates.getByName(skin.getRarity().getName());
            double rate = drop.getRate() / skins.stream().filter(s -> DefaultDropRates.getByName(s.getRarity().getName()).equals(drop)).count();
            System.out.println(rate);
            skinModels.add(new SkinModel(skin.getName(), skin.getImage(), skin.getRarity().getName(), drop.getColor(), String.valueOf(rate).substring(0,5)));
        }
        model.addAttribute("skins", skinModels);
        return "item";
    }


    @GetMapping("/cases/{tag}")
    public String viewCase(@PathVariable String tag, Model model) {
        Crate crate = getCaseByTag(tag);
        if (crate == null) {
            System.out.println("no exact mapping for tag: " + tag);
            return "redirect:/";
        }
        model.addAttribute("collection", crate.getName());
        List<SkinModel> skinModels = new ArrayList<>();
        List<Skin> skins = skinRepository.findByCrate(crate);
        for (Skin skin : skins) {
            DefaultDropRates drop = DefaultDropRates.getByName(skin.getRarity().getName());
            double rate = drop.getRate() / skins.stream().filter(s -> DefaultDropRates.getByName(s.getRarity().getName()).equals(drop)).count();
            skinModels.add(new SkinModel(skin.getName(), skin.getImage() == null ? "/missing.png" : skin.getImage(), skin.getRarity().getName(), drop.getColor(), String.valueOf(rate).substring(0,5)));
        }
        model.addAttribute("skins", skinModels);
        return "item";
    }

    public Crate getCaseByTag(String tag) {
        for (Crate crate : repository.findAll()) {
            String crateTag = crate.getName().toLowerCase().replace(" ", "_").replace("the_", "").replace(".", "").replace("&_", "");
            if (crateTag.equals(tag))
                return crate;
        }
        return null;
    }

    public Collection getCollectionbyTag(String tag) {
        for (Collection collection : collectionRepository.findAll()) {
            String crateTag = collection.getName().toLowerCase().replace(" ", "_").replace("the_", "").replace(".", "").replace("&_", "").replace(":", "");
            if (crateTag.equals(tag))
                return collection;
        }
        return null;
    }

}
