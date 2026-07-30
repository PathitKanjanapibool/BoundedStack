import java.util.*;

public class BoundedStack {
    
    private final List<String> element;
    private final int capacity;

    //AF(element,capacity) = รายการ String โดยเรียงตามลำดับใน element ,ข้อมูลขนาดของ Stack เป็นจำนวนเต็ม
    
    //RI (element):
    //    element != null
    //    element <= capacity
    //    ไม่มีสมาชิกตัวใดเป็น null
    //    ไม่มี String ที่ว่าง
    //    String ห้ามซ้ำ
    //    มีข้อมูล String ไม่เกิน capacity
    //RI (capacity):
    //    capacity มากกว่า 0

    //Safety from rep exposure:
    //    element และ capacity เป็น private final
    //    คัดลอกทั้งขาเข้าและขาออก

    private void checkRep(){
        assert element != null:"element ไม่เป็น null";
        assert element.size() <= capacity : "จำนวนสมาชิกต้องไม่เกินลิมิต";
        Set<String> seen = new HashSet<>();
        for (String s : element){
            assert s != null : "สมาชิกต้องไม่เป็น null";
            assert !s.isEmpty() : "ชื่อสมาชิกต้องไม่เป็นสตริงว่าง";
            assert seen.add(s) : "ชื่อสมาชิกซ้ำ: " + s;
        }
        assert capacity > 0 : "capacity มากกว่า 0";
    }

    /**
     * สร้างที่เก็บข้อมูลโดยไม่มีข้อมูลเริ่มต้น
     * @param capacity กำหนดขนาดของ ArrayList, capacity > 0 
     * @throws capacity <= 0
     */
    public BoundedStack(int capacity){
        if (capacity <= 0) throw new IllegalArgumentException();

        this.element = new ArrayList<>(); //ขาเข้า
        this.capacity = capacity;
        checkRep();
    }
    /**
     * สร้างที่เก็บข้อมูลโดยมีข้อมูลเริ่มต้นที่ให้มา
     * @param capacity ขนาดของ ArrayList, initial ข้อมูลเริ่มต้น, capacity > 0, สมาชิกไม่เป็น null หรือสตริงว่าง ไม่ซํ้า และไม่เกิน capacity
     * @exception IllegalArgumentException ถ้า capacity <= 0 ,initial เป็น nullหรือสตริงว่าง ขนาดเกิน capacity, สมาชิกเป็น nullหรือสตริงว่าง สมาชิกซํ้า
     */
    public BoundedStack(int capacity,List<String> initial) {
        if (capacity <= 0) throw new IllegalArgumentException();
        if(initial == null|| initial.isEmpty()) throw new IllegalArgumentException();
        if(initial.size() > capacity) throw new IllegalArgumentException();
        Set<String> seen = new HashSet<>();
        for (String s : initial){
            if (s==null || s=="") throw new IllegalArgumentException();
            if(!seen.add(s)) throw new IllegalArgumentException();
        }
        this.element = new ArrayList<>(initial); //ขาเข้า
        this.capacity = capacity;
        checkRep();
    }
    
    // ===== Mutators =====
    
    /**
     * 
     * @param s เพิ่มข้อมูล String เข้าไปที่ element, ข้อมูลต้องไม่เป็น null หรือสตริงว่าง
     * @return true ถ้าเพิ่มสำเร็จ
     * @throws IllegalArgumentException ถ้าข้อมูลเป็น null หรือสตริงว่าง
     */
    public boolean push(String s){
        if(s == null|| s =="") throw new IllegalArgumentException();
        if(element.contains(s)||element.size()==capacity) return false;
        element.add(s);
        checkRep();
        return true;
    }


    /**
     * ลบข้อมูล String ภายในที่เก็บข้อมูล
     * @param s ลบข้อมูล String ใน element, ข้อมูลไม่เป็น null หรือสตริงว่าง
     * @return true ถ้าลบสำเร็จ
     * @throws IllegalArgumentException ถ้าข้อมูลเป็น null หรือสตริงว่าง
     */
    public boolean pop(String s){
        if(s == null|| s =="") throw new IllegalArgumentException();
        if(!element.contains(s))    return false;
        element.remove(s);
        checkRep();
        return true;
    }
    
    // ===== Observers =====
    /**
     * คืนค่าของขนาดข้อมูลที่มี
     * @return ขนาดของที่เก็บข้อมูล
     */
    public int size(){
        checkRep();
        return element.size();
    }

    /**
     * คืนค่าของ capacity
     * @return ค่าของ capacity
     */
    public int getCapacity(){
        return capacity;
    }

     /**
      * ตรวจสอบข้อมูลภายในที่เก็บข้อมูลว่ามีหรือไม่
     * @return ตรวจว่ามี String นี้อยู่หรือไม่
     */
    public boolean contains(String s){
        return element.contains(s);
    }

    /**
     * คืนข้อมูลที่อยู่ภายในที่เก็บข้อมูล
     * @return รายการ String ทั้งหมดตามลำดับ
     */
    public List<String> element() {
        return new ArrayList<>(element); //ขาออก
    }


    // ===== Producer =====

    /**
     * คืนที่เก็บข้อมูลใหม่ที่มีข้อมูลเดียวกันแต่สลับลำดับ
     * @return ลิสต์ของ String ที่ถูกสลับตำแหน่งแล้ว
     */
     public BoundedStack shuffled() {
        List<String> copy = new ArrayList<>(element);
        Collections.shuffle(copy);
        checkRep();
        return new BoundedStack(capacity, copy); 
    }

    @Override
    public String toString() {
        return element.toString();
    }
}