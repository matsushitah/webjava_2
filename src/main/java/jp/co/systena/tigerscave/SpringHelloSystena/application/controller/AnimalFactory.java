package jp.co.systena.tigerscave.SpringHelloSystena.application.controller;

import java.util.ArrayList;
import java.util.List;
import jp.co.systena.tigerscave.SpringHelloSystena.application.model.PetShop;
import jp.co.systena.tigerscave.SpringHelloSystena.application.model.Product;

public class AnimalFactory extends PetShop {
  private List animals = new ArrayList();

  protected Product createProduct(String animal) {
    return new Animal(animal);
  }

  protected void registerProduct(Product product) {
    animals.add(((Animal)product).getAnimal());
  }

  public List getAnimals() {
    return animals;
  }
}
