package lesson4.cw;

import leson3.cw.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws SQLException {

        TransactionDemo transactionDemo = new TransactionDemo();

        Product product1 = new Product(232, "!!!", "!!!", 7777);
        Product product2 = new Product(336, "!!!", "!!!", 7777);
        Product product3 = new Product(338, "!!!", "!!!", 7777);

        List<Product> products = new ArrayList<>();

        products.add(product1);
        products.add(product2);
        products.add(product3);

        transactionDemo.save(products);
    }
}
