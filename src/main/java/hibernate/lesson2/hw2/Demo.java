package hibernate.lesson2.hw2;

public class Demo {
    public static void main(String[] args) throws Exception {
        ProductDAO dao = new ProductDAO();

        System.out.println(dao.findById(3L));
        System.out.println(dao.findByName("superpro"));
        System.out.println(dao.findByContainedName("su"));
        System.out.println(dao.findByPrice(20, 10));
        System.out.println(dao.findByName("superpro"));
        System.out.println(dao.findByNameSortedAsc("superpro"));
        System.out.println(dao.findByNameSortedDesc("superpro"));
        System.out.println(dao.findByPriceSortedDesc(100, 100));
    }
}
