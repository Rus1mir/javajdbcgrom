package lesson5;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {

        ProductRepository repository = new ProductRepository();

        Product product = new Product();
        product.setId(100);
        product.setName("mustdie123");
        product.setDescription("i will go to hell");
        product.setPrice(33);

        //repository.save(product);
        //repository.update(product);
        repository.delete(100L);
    }
}
