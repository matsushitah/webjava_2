package jp.co.systena.tigerscave.SpringHelloSystena.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.SpringHelloSystena.application.model.Judge;
import jp.co.systena.tigerscave.SpringHelloSystena.application.model.MainForm;
import jp.co.systena.tigerscave.SpringHelloSystena.application.model.PetShop;
import jp.co.systena.tigerscave.SpringHelloSystena.application.model.Product;

@Controller
public class MainController {

  @Autowired
  HttpSession session;

  private Product pet;

  @RequestMapping(value = "/petShop", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView showPetShop(ModelAndView mav) {

    mav.setViewName("petShop");
    return mav; // 文字列を返却

  }

  @RequestMapping(value = "/myHome", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView showMyHome(ModelAndView mav) {

 // Viewに渡すデータを設定
    MainForm mainForm = (MainForm) session.getAttribute("form");
    session.removeAttribute("form");


    if (mainForm != null) {
      String animal = mainForm.getAnimal();
      mav.addObject("message", animal +"を飼いました。");
      mav.addObject("message2", animal +"と遊ぼう。");
    }

    mav.addObject("mainForm", new MainForm());

    BindingResult bindingResult = (BindingResult) session.getAttribute("result");
    if (bindingResult != null) {
      mav.addObject("bindingResult", bindingResult);
    }

    mav.setViewName("animal");
    return mav; // 文字列を返却

  }

  @RequestMapping(value="/myHome", method = RequestMethod.POST)  // URLとのマッピング
  private ModelAndView order(ModelAndView mav, @Valid MainForm mainForm, BindingResult bindingResult, HttpServletRequest request) {

    String animal = request.getParameter("animal");
    mainForm.setAnimal(animal);

    PetShop petShop = new AnimalFactory();
    Product pet = petShop.create(animal);
    this.pet = pet;

    mav.addObject(animal);

 // データをセッションへ保存
    session.setAttribute("form", mainForm);
    session.setAttribute("animal", animal);
    return new ModelAndView("redirect:/myHome");        // リダイレクト
  }

  @RequestMapping(value="/myHome", params="bark", method = RequestMethod.POST)
  private ModelAndView barkAnimal(ModelAndView mav) {
    String msg = pet.bark();
    String animal = (String) session.getAttribute("animal");
    mav.addObject("message2", animal +"と遊ぼう。");
    mav.addObject("msg", msg);

    mav.setViewName("animal");
    return mav;
  }

  @RequestMapping(value="/myHome", params="move", method = RequestMethod.POST)
  private ModelAndView watchAnimal(ModelAndView mav) {
    String msg = pet.move();
    String animal = (String) session.getAttribute("animal");
    mav.addObject("message2", animal +"と遊ぼう。");
    mav.addObject("msg", msg);

    mav.setViewName("animal");
    return mav;
  }

  @RequestMapping(value="/myHome", params="feed", method = RequestMethod.POST)
  private ModelAndView feed(ModelAndView mav) {
    String animal = (String) session.getAttribute("animal");
    mav.addObject("message2", animal +"と遊ぼう。");
    mav.addObject("msg", animal +"に餌を与えよう。");
    mav.addObject("selectFeed", true);

    mav.setViewName("animal");
    return mav;
  }

  @RequestMapping(value="/myHome", params="give", method = RequestMethod.POST)
  private ModelAndView give(ModelAndView mav, HttpServletRequest request) {
    String animal = (String) session.getAttribute("animal");
    mav.addObject("message2", animal +"と遊ぼう。");
    mav.addObject("msg", animal +"に餌を与えよう。");
    mav.addObject("selectFeed", true);

    String food = request.getParameter("food");

    Judge judge = new JudgeTaste();
    String giveMsg = animal + "に" + food + "をあげました。";
    String tasteMsg = judge.judge(food, animal);

    mav.addObject("giveMsg", giveMsg);
    mav.addObject("tasteMsg", tasteMsg);

    mav.setViewName("animal");
    return mav;
  }

  @RequestMapping(value="/myHome", params="back", method = RequestMethod.POST)
  private String back() {
    return "petShop";
  }
}
