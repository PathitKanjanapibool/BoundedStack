import java.util.*;

public class BoundedStack {
    
    private final List<String> element;
    private final int capacity;

    //AF(element,capacity) = รายการ String โดยเรียงตามลำดับใน element ,ข้อมูลขนาดของ Stack เป็นจำนวนเต็ม
    //RI (element)
    // element != null
    // element <= capacity
    // ไม่มีสมาชิกตัวใดเป็น null
    // ไม่มี String ที่ว่าง
    // String ห้ามซ้ำ
    // มีข้อมูล String ไม่เกิน capacity
    //RI (capacity)
    // capacity มากกว่า 0

    private void checkRep(){
        assert element != null:"element ไม่เป็น null";
        assert element.size() <= capacity : "จำนวนสมาชิกต้องไม่เกินลิมิต";
        Set<String> seen = new HashSet<>();
        for (String s : element){
            assert s != null : "สมาชิกต้องไม่เป็น null";
            assert s.isEmpty() == true : "ชื่อสมาชิกต้องไม่เป็นสตริงว่าง";
            assert seen.add(s) : "ชื่อสมาชิกซ้ำ: " + s;
        assert capacity > 0 : "capacity มากกว่า 0";
        }
    }

    /**
     * 
     * @param capacity กำหนดขนาดของ Stack
     */
    public BoundedStack(int capacity){
        this.element = new ArrayList<>();
        this.capacity = capacity;
    }
    /**
     * 
     * @param capacity กำหนดขนาดของ Stack
     * @exception throw เมื่อสมาชิก element เป็น null
     */
    public BoundedStack(int capacity,List<String> initial) {
        if(initial == null) throw new IllegalArgumentException();
        if(initial.size() > capacity) throw new IllegalArgumentException();
        Set<String> seen = new HashSet<>();
        for (String s : initial){
            if (s==null || s=="") throw new IllegalArgumentException();
            if(!seen.add(s)) throw new IllegalArgumentException();
        }
        this.element = new ArrayList<>(initial);
        this.capacity = capacity;

    }
    public int getcapacity(){
        return capacity;
    }
    
    // ===== Mutators =====
    
    /**
     * 
     * @param s เพิ่มข้อมูล String เข้าไปที่ element
     * @return true ถ้าเพิ่มสำเร็จ
     */
    public boolean push(String s){
        if(s == null|| s =="") throw new IllegalArgumentException();
        if(element.contains(s)||element.size()==capacity) return false;
        element.add(s);
        return true;
    }


    /**
     * 
     * @param s ลบข้อมูล String ใน element
     * @return true ถ้าลบสำเร็จ
     */
    public boolean pop(String s){
        if(!element.contains(s)) return false;
        element.remove(s);
        return true;
    }
    /**
     * 
     * @return ขนาดของelement
     */
    public int size(){
        return element.size();
    }

    // ===== Observers =====
}