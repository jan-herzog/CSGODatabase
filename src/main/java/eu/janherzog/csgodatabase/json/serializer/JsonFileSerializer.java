package eu.janherzog.csgodatabase.json.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.janherzog.csgodatabase.database.model.*;
import eu.janherzog.csgodatabase.database.model.Collection;
import eu.janherzog.csgodatabase.database.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class JsonFileSerializer {

    private final static Gson gson = new GsonBuilder()
            .serializeNulls()
            .create();

    private List<Container> containers = new ArrayList<>();

    private final CrateRepository crateRepository;
    private final CollectionRepository collectionRepository;
    private final DropRateRepository dropRateRepository;
    private final RarityRepository rarityRepository;
    private final SkinRepository skinRepository;
    private final WeaponRepository weaponRepository;


    @EventListener
    public void onStart(ApplicationReadyEvent event) {
        File folder = new File("data/cases/json");
        for (String s : folder.list()) {
            readFile(s);
            System.out.println(s);
        }
        for (Container container : containers) {
            Content content = container.getContent();
            String date_added = getDate(content);
            Crate crate = new Crate(0, container.getName(), getDate(date_added));
            crateRepository.save(crate);
            for (SkinModel skinModel : content.getRareSpecialItems())
                insertSkin(DefaultDropRates.RARESPECIAL, skinModel, crate);
            for (SkinModel skinModel : content.getCovertSkins())
                insertSkin(DefaultDropRates.COVERT, skinModel, crate);
            for (SkinModel skinModel : content.getClassifiedSkins())
                insertSkin(DefaultDropRates.CLASSIFIED, skinModel, crate);
            for (SkinModel skinModel : content.getRestrictedSkins())
                insertSkin(DefaultDropRates.RESTRICTED, skinModel, crate);
            for (SkinModel skinModel : content.getMilspecSkins())
                insertSkin(DefaultDropRates.MILSPEC, skinModel, crate);
            for (SkinModel skinModel : content.getConsumerGradeSkins())
                insertSkin(DefaultDropRates.CONSUMER, skinModel, crate);
        }
    }

    private String getDate(Content content) {
        if (content.getRareSpecialItems().length != 0)
            return content.getRareSpecialItems()[0].getDate_added();
        if (content.getCovertSkins().length != 0)
            return content.getCovertSkins()[0].getDate_added();
        if (content.getClassifiedSkins().length != 0)
            return content.getClassifiedSkins()[0].getDate_added();
        if (content.getRestrictedSkins().length != 0)
            return content.getRestrictedSkins()[0].getDate_added();
        if (content.getMilspecSkins().length != 0)
            return content.getMilspecSkins()[0].getDate_added();
        if (content.getConsumerGradeSkins().length != 0)
            return content.getConsumerGradeSkins()[0].getDate_added();
        return "kek";
    }

    @SneakyThrows
    private void readFile(String s) {
        File file = new File("data/cases/json/" + s);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder stringBuilder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            stringBuilder.append(line).append("\n");
            line = reader.readLine();
        }

        String json = stringBuilder.toString();
        System.out.println(json);
        this.containers.add(gson.fromJson(json, Container.class));
    }

    public void insertSkin(DefaultDropRates defaultDropRates, SkinModel skinModel, Collection collection) {
        DropRate dropRate = new DropRate(0, defaultDropRates.getRate(), collection, null);
        dropRateRepository.save(dropRate);
        Rarity rarity = new Rarity(0, defaultDropRates.getName(), collection, null, dropRate);
        rarityRepository.save(rarity);
        String name = skinModel.getName().split(" \\| ")[0];
        System.out.println(name);
        Weapon weapon = weaponRepository.findByName(defaultDropRates.equals(DefaultDropRates.RARESPECIAL) ? "Knife" : name);
        weaponRepository.save(weapon);
        Skin skin = new Skin(0, skinModel.getName(),
                skinModel.getDesc(),
                skinModel.getLore(),
                getDate(skinModel.getDate_added()),
                skinModel.isCan_be_souvenir(),
                skinModel.isCan_be_stattrak(),
                skinModel.getWears().getBest(),
                collection,
                rarity,
                weapon.getWeaponType(),
                null,
                weapon);
        skinRepository.save(skin);
    }

    public void insertSkin(DefaultDropRates defaultDropRates, SkinModel skinModel, Crate crate) {
        DropRate dropRate = new DropRate(0, defaultDropRates.getRate(), null, crate);
        dropRateRepository.save(dropRate);
        Rarity rarity = new Rarity(0, defaultDropRates.getName(), null, crate, dropRate);
        rarityRepository.save(rarity);
        String name = skinModel.getName().split(" \\| ")[0];
        System.out.println(name);
        Weapon weapon = weaponRepository.findByName(defaultDropRates.equals(DefaultDropRates.RARESPECIAL) ? "Knife" : name);
        weaponRepository.save(weapon);
        Skin skin = new Skin(0, skinModel.getName(),
                skinModel.getDesc(),
                skinModel.getLore(),
                getDate(skinModel.getDate_added()),
                skinModel.isCan_be_souvenir(),
                skinModel.isCan_be_stattrak(),
                skinModel.getWears().getBest(),
                null,
                rarity,
                weapon.getWeaponType(),
                crate,
                weapon);
        skinRepository.save(skin);
    }


    @SneakyThrows
    private Date getDate(String date_added) {
        if (date_added.split(" ")[0].length() == 1)
            date_added = "0" + date_added;
        return new SimpleDateFormat("dd MMMM yyyy", new Locale("en")).parse(date_added);
    }

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("dd MMMM yyyy").parse("03 Dezember 2020"));
    }
}
