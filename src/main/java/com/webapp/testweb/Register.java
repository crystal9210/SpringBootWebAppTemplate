package com.webapp.testweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class Register {

    @RequestMapping("/input")
    public String start(Model model){
        model.addAttribute("rb",new RBean());
        return "input.html";
    }

    @RequestMapping("/register")   //https://localhost:8080/registerでアクセス可能
    public ModelAndView register(@ModelAttribute RBean rb,ModelAndView mav){
        mav.addObject("rb",rb); //ビュー内でrbインスタンスを使用できるようになる。rbはキーで、ビュー内でこのキーを使用してモデルデータにアクセスできる。つまり、mavインスタンスにモデルを持たせるための処理
        mav.setViewName("register.html");   //ビュー名を設定している。引数にはビューとして、テンプレートのファイル名を指定する。つまり、mavインスタンスにビューを持たせるための処理
        return mav; //このインスタンスを返すことでビューとモデルデータがコントローラーから呼び出し元に返される。ビューが表示される際には、設定したビュー名に基づいてテンプレートが処理され、モデルデータが適切に表示される。
    }

    @RequestMapping(value="/confirmation",method = RequestMethod.POST)
    public ModelAndView confirmation(@ModelAttribute RBean rb,ModelAndView mav){
        mav.addObject("rb", rb);
        mav.setViewName("confirmation.html");
        return mav;
    }

}

//各処理の補足説明
//@ModelAttribute RBean rb：リクエストから受け取ったデータをRBeanというクラスのインスタンスにバインディングして、それをビューに渡す処理を行っている