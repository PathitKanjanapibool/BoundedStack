import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        boolean throwOverCapacity = false;
        try { testcapacity = new BoundedStack(2, Arrays.asList("hello", "I", "am")); }
        catch (IllegalArgumentException e) { throwOverCapacity = true; }
        check("element > capacity -> throw", throwOverCapacity);

        boolean throwLowerthan1 = false;
        try { testcapacity = new BoundedStack(-1); }
        catch (IllegalArgumentException e) { throwLowerthan1 = true; }
        check("capacity <= 0 -> throw",throwLowerthan1);
    }
    
    // Mutator: Add
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
        check("contains found -> return true", test1.contains("Greeting") == true);
        check("contains not found -> return false", test1.contains("Hi") == false);
        
    }

    // Mutator: Remove
    private static void testRemove(){
        System.out.println("===== testRemove =====");
        boolean threw = false;
        BoundedStack test1 = new BoundedStack(1);
        try { test1.pop(null); }
        catch (IllegalArgumentException e) { threw = true; }
        check("String != null -> return false", threw == false);

        BoundedStack test2 = new BoundedStack(4,Arrays.asList("how","are","you"));
        check("if no found -> returns false", test2.pop("hello") == false);
        check("if found -> returns true", test2.pop("how") == true);
        check("contain found",test2.contains("how") == false);
        check("Size decrease -> return 2",test2.size() == 2);
        
    }
    // Observer

    // Producer
    private static void testShuffle() {
        System.out.println("\n-- Producer (shuffled) --");

        BoundedStack original = new BoundedStack(4,Arrays.asList("A", "B", "C", "D"));
        BoundedStack shuffled = original.shuffled();

        check("shuffled has the same size", shuffled.size() == original.size());

        List<String> a = new ArrayList<String>(original.element());
        List<String> b = new ArrayList<String>(shuffled.element());
        Collections.sort(a);
        Collections.sort(b);
        check("shuffled contains exactly the same songs", a.equals(b));

        check("shuffled does not mutate the original",
                original.element().equals(Arrays.asList("A", "B", "C", "D")));
    }
}