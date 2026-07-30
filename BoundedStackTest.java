import java.util.*;

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

        testCreator();
        testAdd();
        testRemove();
        testObserver();
        testProducer();

        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
    }
    private static void testCreator(){
        System.out.println("\n===== Creators =====");

        BoundedStack testcapacity;
        BoundedStack testnull;
        BoundedStack testempty;
        BoundedStack testdupe;
        BoundedStack testinitialnull;
        BoundedStack testinitialempty;

        // สมาชิกใน initial เป็น null
        boolean throwNull = false;
        try { testnull = new BoundedStack(2, Arrays.asList((String) null)); }
        catch (IllegalArgumentException e) { throwNull = true; }
        check("throw if member is null", throwNull == true);

        // สมาชิกใน initial เป็นสตริงว่าง
        boolean throwEmpty = false;
        try { testempty = new BoundedStack(2, Arrays.asList("")); }
        catch (IllegalArgumentException e) { throwEmpty = true; }
        check("throw if member is empty string", throwEmpty == true);

        // initial == null
        boolean throwInitialNull = false;
        try { testinitialnull = new BoundedStack(2, null); }
        catch (IllegalArgumentException e) { throwInitialNull = true; }
        check("throw if initial is null", throwInitialNull == true);

        // initial เป็น list ว่าง
        boolean throwInitialEmpty = false;
        try { testinitialempty = new BoundedStack(2, new ArrayList<String>()); }
        catch (IllegalArgumentException e) { throwInitialEmpty = true; }
        check("throw if initial is empty string", throwInitialEmpty == true);

        // จำนวน element ต้องไม่เกิน capacity
        boolean throwOverCapacity = false;
        try { testcapacity = new BoundedStack(2, Arrays.asList("hello", "I", "am")); }
        catch (IllegalArgumentException e) { throwOverCapacity = true; }
        check("element > capacity -> throw", throwOverCapacity == true);

        boolean throwLowerthan1_1 = false;
        boolean throwLowerthan1_2 = false;
        try { testcapacity = new BoundedStack(0); }
        catch (IllegalArgumentException e) { throwLowerthan1_1 = true; }

        try { testcapacity = new BoundedStack(-1); }
        catch (IllegalArgumentException e) { throwLowerthan1_2 = true; }
        check("capacity <= 0 case 1 -> throw", throwLowerthan1_1 == true);
        check("capacity <= 0 case 2 -> throw", throwLowerthan1_2 == true);

        // สมาชิกซ้ำใน initial
        boolean throwDupe = false;
        try { testdupe = new BoundedStack(3, Arrays.asList("A","A","A")); }
        catch (IllegalArgumentException e) { throwDupe = true; }
        check("duplicate member -> throw", throwDupe == true);
    }

    // Mutator: Add
    private static void testAdd(){

        System.out.println("\n===== Mutator (add) =====");
        boolean throwNull = false;
        boolean throwEmpty = false;

        BoundedStack empty = new BoundedStack(3);
        try { empty.push(null); }
        catch (IllegalArgumentException e) { throwNull = true; }
        check("String = null -> throw", throwNull == true);

        try { empty.push(""); }
        catch (IllegalArgumentException e) { throwEmpty = true; }
        check("String = empty -> throw", throwEmpty == true);

        check("size = 0 -> return 0",empty.size() == 0);
        check("capacity = 3", empty.getCapacity() == 3);
        check("can Add -> return true", empty.push("Good") == true);
        check("size increase -> return 1",empty.size() == 1);
        check("dupe -> return false", empty.push("Good") == false);
        check("contains found -> return true", empty.contains("Good") == true);
        check("contains not found -> return false", empty.contains("Hi") == false);

    }

    // Mutator: Remove
    private static void testRemove(){
        System.out.println("\n===== Mutator (remove) =====");
        boolean threw1 = false;
        boolean threw2 = false;
        BoundedStack test1 = new BoundedStack(3, Arrays.asList("my","name","is"));
        try { test1.pop(null); }
        catch (IllegalArgumentException e) { threw1 = true; }
        try { test1.pop(""); }
        catch (IllegalArgumentException e) { threw2 = true; }
        check("String != null -> return true", threw1 == true);
        check("String != \"\" -> return true", threw2 == true);

        BoundedStack test2 = new BoundedStack(4,Arrays.asList("how","are","you"));
        check("size = 3 -> return 3",test2.size() == 3);
        check("if no found -> returns false", test2.pop("hello") == false);
        check("if found -> returns true", test2.pop("how") == true);
        check("contain found",!test2.contains("how"));
        check("size decrease -> return 2",test2.size() == 2);

    }
    // Observer
        private static void testObserver() {
            System.out.println("\n===== Observer (shuffled) =====");
            BoundedStack observer = new BoundedStack(5,Arrays.asList("A", "B","C","D","E"));
        check("size = 5", observer.size() == 5);
        check("contains found exist", observer.contains("A"));
        check("contains not found exist", !observer.contains("F"));
        check("songs returns the full list in order",observer.element().equals(Arrays.asList("A", "B","C","D","E")));

        int before = observer.size();
        observer.size();
        observer.contains("A");
        observer.element();
        check("observers have no side effects", observer.size() == before);
        }


    // Producer
    private static void testProducer() {
        System.out.println("\n===== Producer (shuffled) =====");

        BoundedStack original = new BoundedStack(4,Arrays.asList("A", "B", "C", "D"));

        check("toString matches list format", original.toString().equals("[A, B, C, D]"));

        BoundedStack shuffled = original.shuffled();

        check("shuffled has the same size", shuffled.size() == original.size());

        List<String> a = new ArrayList<String>(original.element());
        List<String> b = new ArrayList<String>(shuffled.element());
        Collections.sort(a);
        Collections.sort(b);
        check("shuffled contains exactly the same songs", a.equals(b));

        check("shuffled does not mutate the original",original.element().equals(Arrays.asList("A", "B", "C", "D")));
    }
}