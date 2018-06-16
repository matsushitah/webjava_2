package jp.co.systena.tigerscave.SpringHelloSystena.application.model;

public abstract class PetShop {
  public final Product create(String animal) {
    Product product = createProduct(animal);
    registerProduct(product);
    return product;
  }

  protected abstract Product createProduct(String animal);
  protected abstract void registerProduct(Product product);
}
