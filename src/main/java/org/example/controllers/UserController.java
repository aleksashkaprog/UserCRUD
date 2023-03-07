package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping()
    public String index(Model model) {
        return null;

    }

    @GetMapping("/{id}")
    public  String ahow(@PathVariable("id") int id, Model model) {
        return null;
    }
}
