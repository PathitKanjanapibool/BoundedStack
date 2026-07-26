import java.util.*;

public class BoundedStack {
    
    private final List<String> element;
    private final int capacity;

    //AF(element,capacity) = รายการ String โดยเรียงตามลำดับใน element ,ข้อมูลขนาดของ Stack เป็นจำนวนเต็ม
    //RI (element)
    // element != null
    // ไม่มีสมาชิกตัวใดเป็น null
    // ไม่มี String ที่ว่าง
    // String ห้ามซ้ำ
    // มีข้อมูล String ไม่เกิน capacity
    //RI (capacity)
    // capacity != null
    // capacity != ""
    // capacity ต้องไม่ติดลบ
    // ห้ามเว้นวรรค
    // ห้ามเป็นทศนิยม

    private void checkRep(){

    }

    /**
     * 
     * @param capacity กำหนดขนาดของ Stack
     */
    public BoundedStack(int capacity){
        this.element = new ArrayList<>();
        this.capacity = capacity;
    }
    public int getcapacity(){
        return capacity;
    }
    
    // ===== Mutators =====
    
    /**
     * 
     * @param s เพิ่มข้อมูล String เข้าไปที่ element
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
     */
    public void pop(String s){
        if(!element.contains(s)) return false;
        element.remove(s);
        return true;
    }

    // ===== Observers =====
}
