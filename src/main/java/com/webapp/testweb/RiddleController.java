package com.webapp.testweb;
import org.springframework.ui.Model;
import com.webapp.testweb.RiddleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RiddleController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RiddleRepository riddleRepository;

    // ... (他のメソッド)

    @RequestMapping("/english_riddles")
    public String processCategories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "english_riddles";
    }

    @RequestMapping(value="/display_riddle",method = RequestMethod.POST)
    public String getRandomRiddleByCategory(@RequestParam long category_id, Model model) {
        List<Riddle> riddles = riddleRepository.findRandomRiddleByCategoryId(category_id);
        System.out.println(riddles);
        if (!riddles.isEmpty()) {
            model.addAttribute("riddle", riddles.get(0));
        }
        return "riddleDisplay";
    }

    @RequestMapping(value="/check_answer", method=RequestMethod.POST)
    public String checkAnswer(@RequestParam Long riddleId, @RequestParam String answer, RedirectAttributes redirectAttributes) {
        Riddle riddle = riddleRepository.findById(riddleId).orElse(null);
        if (riddle != null && riddle.getAnswer().equalsIgnoreCase(answer)) {
            return "redirect:/riddleOk";
        } else {
            redirectAttributes.addFlashAttribute("riddle", riddle);
            return "redirect:/riddleDisplayAgain";
        }
    }

    @RequestMapping("/riddleOk")
    public String displayOk (Model model) {
        // ナゾの取得やモデルへの属性の追加など、必要な処理をここに記述
        return "riddleOk";
    }

    @RequestMapping("/riddleDisplayAgain")
    public ModelAndView showRiddleAgain(@ModelAttribute Riddle riddle,ModelAndView mav){
        mav.addObject("riddle", riddle);
        mav.setViewName("riddleDisplayAgain");
        return mav;
    }


}
