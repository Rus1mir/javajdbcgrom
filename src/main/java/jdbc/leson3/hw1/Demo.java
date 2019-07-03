package jdbc.leson3.hw1;

public class Demo {

    public static void main(String[] args) throws Exception{

        Solution solution = new Solution();
        System.out.println(solution.findProductsByPrice(300, 20));
        System.out.println(solution.findProductsByName("toy"));
        System.out.println(solution.findProductsWithEmptyDescription());
    }
}
