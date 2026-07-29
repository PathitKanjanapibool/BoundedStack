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

        BoundedStack testcapacity;
        BoundedStack testnull;

        // element != null
        boolean throwNull = false;
        try { testnull = new BoundedStack(2, Arrays.asList((String) null)); }
        catch (IllegalArgumentException e) { throwNull = true; }
        check("throw if element is null", throwNull);

        // จำนวน element ต้องไม่เกิน capacity
        boolean threwOverCapacity = false;
        try { testcapacity = new BoundedStack(2, Arrays.asList("hello", "I", "am")); }
        catch (IllegalArgumentException e) { threwOverCapacity = true; }
        check("element > capacity -> throw", threwOverCapacity);

        BoundedStack test2 = new BoundedStack(-1);
        check("capacity > 0 -> return -1",test2.getcapacity() == -1);
    }
    // Mutator: Add กรณีเต็ม,ไม่เต็ม,ซํํ้า,null
    private static void testAdd(){

        System.out.println("===== testAdd =====");
        boolean throwNull = false;
        BoundedStack test1 = new BoundedStack(3,Arrays.asList("Greeting","Hello"));
        try { test1.push(null); }
        catch (IllegalArgumentException e) { throwNull = true; }
        check("String = null -> throw", throwNull);

        check("Can Add -> return true", test1.push("Good") == true);
        check("Size increase -> return 3",test1.size() == 3);
        check("Dupe -> return false", test1.push("Good") == false);
        
    }

    // Mutator: Remove กรณีnull,,ซํํ้า,null
    private static void testRemove(){
        System.out.println("===== testRemove =====");
        boolean threw = false;
        BoundedStack test1 = new BoundedStack(1);
        try { test1.pop(null); }
        catch (IllegalArgumentException e) { threw = true; }
        check("capacity is not null", test1.pop("Good") == false);

        BoundedStack test2 = new BoundedStack(4,Arrays.asList("how","are","you"));
        check("remove(hello) -> returns false", test2.pop("hello") == false);
        check("remove(how) -> returns true", test2.pop("how") == true);
    }

    private static void testContain(){}
    // private static void testList(){
    //     check("new() -> contains nothing", !.contains("anything"));
    // }
    // private static void testshuffled(){
    //     BoundedStack original = new BoundedStack(Arrays.asList("A", "B", "C", "D"));
    //     BoundedStack shuffled = original.shuffled();
    // }
}