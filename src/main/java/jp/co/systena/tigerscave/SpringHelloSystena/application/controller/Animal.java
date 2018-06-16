package jp.co.systena.tigerscave.SpringHelloSystena.application.controller;

import jp.co.systena.tigerscave.SpringHelloSystena.application.model.Product;

public class Animal extends Product{

  private String animal;

  Animal(String animal) {
    this.animal = animal;
  }

  public String bark() {
    String cry = "";
    if (animal.equals("犬")) {
      cry = "ワン";
    } else if (animal.equals("猫")) {
      cry = "にゃー";
    }
    String msg = animal + "は" + cry + "と鳴きました。";
    return msg;
  }

  public String move() {
    String move = "";
    if (animal.equals("犬")) {
      move = "走り回った。";
    } else if (animal.equals("猫")) {
      move = "高くジャンプした。";
    }
    String msg = animal + "は" + move;
    return msg;
  }

  public String getAnimal() {
    return animal;
  }
}
