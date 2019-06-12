package leson3;

public class Demo {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();

        Product product = new Product(105, "test_updated", "test_description", 99);

        //productDAO.save(product);

        //System.out.println(productDAO.getProducts());

        //productDAO.update(product);

        System.out.println(productDAO.delete(10));
    }
}
