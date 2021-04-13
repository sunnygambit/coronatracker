package com.wander.corona.tracker.controller;

import com.wander.corona.tracker.dto.CoronaDTO;
import com.wander.corona.tracker.service.CoronaVirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    CoronaVirusService coronaVirusService;

    @GetMapping("/")
    public String root(Model model) {

            List<CoronaDTO> allStats = coronaVirusService.getCoronaCasesDetails();
            if(!CollectionUtils.isEmpty(allStats)) {
                int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTodayTotalCases()).sum();
                int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
                model.addAttribute("locStats", allStats);
                model.addAttribute("totalReportedCases", totalReportedCases);
                model.addAttribute("totalNewCases", totalNewCases);
            }else{
                model.addAttribute("Error","Error While preparing the COVID Data");
            }
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
