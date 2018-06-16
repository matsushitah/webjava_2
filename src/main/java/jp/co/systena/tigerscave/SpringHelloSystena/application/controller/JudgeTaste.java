package jp.co.systena.tigerscave.SpringHelloSystena.application.controller;

import jp.co.systena.tigerscave.SpringHelloSystena.application.model.Judge;

public class JudgeTaste extends Judge {
  public String judge(String food, String animal) {

    String taste = "";
    if (animal.equals("犬")) {
      if (food.equals("肉")) {
        taste = "大好き";
      } else if (food.equals("魚")) {
        taste = "好きではない";
      } else if (food.equals("ペットフード")) {
        taste = "好き";
      } else if (food.equals("野菜")) {
        taste = "あまり好きではない";
      }
    } else if (animal.equals("猫")) {
      if (food.equals("肉")) {
        taste = "あまり好きではない";
      } else if (food.equals("魚")) {
        taste = "大好き";
      } else if (food.equals("ペットフード")) {
        taste = "好き";
      } else if (food.equals("野菜")) {
        taste = "好きではない";
      }
    }

    String msg = animal + "は" + food + "が" + taste + "です。";
    return msg;
  }
}
