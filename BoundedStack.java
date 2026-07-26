import java.util.*;

public class BoundedStack {
    
    private final List<String> element;
    private final int capacity;

    //AF(element,capacity) = รายการ String โดยเรียงตามลำดับใน element ,ข้อมูลขนาดของ Stack
    //RI
    //-
    //-


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
     * @param s เพิ่มข้อมูล String เข้าไปที่ element
     */
    public void push(String s){

    }
}
