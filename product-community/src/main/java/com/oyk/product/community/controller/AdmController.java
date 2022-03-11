package com.oyk.product.community.controller;

import com.oyk.product.community.service.AdmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm")
@RequiredArgsConstructor
public class AdmController {

    private final AdmService admService;

    @GetMapping("/page")
    public String showAdminPage(Model model ){

        model.addAttribute("memberStateData", admService.getMemberStateDto());
        model.addAttribute("boardStateData", admService.getBoardStateDto());

        return "adm/general/main";
    }

}
