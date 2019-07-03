package hibernate.lesson2.hw1;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) throws Exception {

        ProductDao dao = new ProductDao();

        ArrayList<Product> products = new ArrayList<Product>();

        for (int i = 1; i < 10; i++) {
            products.add(new Product(-1, "Product " + i, "test", 99));
        }

        Product savedProduct = new Product(-1, "Prod", "save", 22);
        dao.save(savedProduct);
        System.out.println("Saved: " + savedProduct);

        savedProduct.setName("Updated!!!");
        dao.update(savedProduct);
        System.out.println("Updated: " + savedProduct);

        dao.delete(savedProduct);
        System.out.println(savedProduct);

        dao.saveAll(products);

        for (Product p : products) {
            p.setName(p.getName() + "U");
        }

        dao.updateAll(products);
        dao.deleteAll(products);
    }
}
