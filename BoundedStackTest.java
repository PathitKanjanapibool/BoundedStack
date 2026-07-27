import java.util.Arrays;

/**
 * Test Runner
 */

public class BoundedStackTest{
    private static int passed = 0;
    private static int failed = 0;

    private static void check(String name,boolean condition){
        if(condition){
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }

    public static void main(String[] args) {

        testBoundedStack();
        testAdd();
        testRemove();
        testSize();

        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
    }
    private static void testBoundedStack(){
        System.out.println("===== testBoundedStack =====");
        boolean threw1 = false;
        boolean threw2 = false;
        BoundedStack testcapacity;
        BoundedStack testnull;
        try { testnull = new BoundedStack(1,Arrays.asList((String)null)); }
                catch(IllegalArgumentException e){ threw2 = true; }
                check("throw if element is null", threw2 == true);

        try { testcapacity = new BoundedStack(1,Arrays.asList("hello","I","am")); }
        catch(IllegalArgumentException e){ threw1 = true; }
        check("throw if element > capacity", threw1 == true);
        

        BoundedStack test2 = new BoundedStack(1);
        check("capacity > 0",test2.getcapacity() == 1);
    }

    private static void testAdd(){
        System.out.println("===== testAdd =====");
        boolean threw = false;
        BoundedStack test1 = new BoundedStack(1);
        try { test1.push(null); }
        catch (IllegalArgumentException e) { threw = true; }
        check("capacity is not null", test1.push("Good") == true);

        BoundedStack test2 = new BoundedStack(4,Arrays.asList("how","are","you"));
        check("add(A) -> returns false", test2.push("hello") == true);
        check("add(A) -> returns false", test2.push("how") == false);
    }
    private static void testRemove(){
        System.out.println("===== testRemove =====");
        boolean threw = false;
        BoundedStack test1 = new BoundedStack(1);
        try { test1.push(null); }
        catch (IllegalArgumentException e) { threw = true; }
        check("capacity is not null", test1.pop("Good") == false);

        BoundedStack test2 = new BoundedStack(4,Arrays.asList("how","are","you"));
        check("add(A) -> returns false", test2.pop("hello") == false);
        check("add(A) -> returns false", test2.pop("how") == true);
    }
    private static void testSize(){
        BoundedStack test1 = new BoundedStack(1,Arrays.asList("how"));
        check("Size = 1", test1.size() == 1);

    }
    private static void testContain(){}
    private static void testList(){}
    private static void testshuffled(){}
}