package eu.janherzog.csgodatabase.controller;

import eu.janherzog.csgodatabase.database.repository.CrateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/collections")
    public String collections(Model model) {
        return "collections";
    }

    @GetMapping("/cases")
    public String cases(Model model) {
        return "cases";
    }
}
