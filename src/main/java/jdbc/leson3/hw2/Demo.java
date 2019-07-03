package jdbc.leson3.hw2;

public class Demo {

    public static void main(String[] args) {

        Solution solution = new Solution();

        System.out.println("Do save...");
        System.out.println(solution.testSavePerformance());
        //Result 159967 ms

        System.out.println("Do select several requests...");
        System.out.println(solution.testSelectByIdPerformance());
        //Result 156310 ms

        System.out.println("Do select one request...");
        System.out.println(solution.testSelectPerformance());
        //Result 163 ms

        System.out.println("Do delete several requests...");
        System.out.println(solution.testDeleteByIdPerformance());
        //Result 160144 ms

        System.out.println("Do save again...");
        System.out.println(solution.testSavePerformance());
        //Result 163456 ms

        System.out.println("Do delete one request...");
        System.out.println(solution.testDeletePerformance());
        //Result 202 ms
    }
}
