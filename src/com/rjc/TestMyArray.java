package com.rjc;

/**
 * @program: datastructure
 * @description: 测试我的数组
 * @author: R红茶
 * @create: 2020-02-18 12:39
 **/
class Person{
    void play(Instruments instrument){
        instrument.play();
    }
}
interface Instruments{
    void play();
}
class Piano implements Instruments{
    @Override
    public void play() {
        System.out.println("弹奏出了动听的钢琴声");
    }
}
class Gutar implements Instruments{
    @Override
    public void play() {
        System.out.println("弹奏出了动听的吉他声");
    }
}
/**
 * @author r红茶
 */
public class TestMyArray {

    public static void main(String[] args) {
        Person person=new Person();
        person.play(new Piano());
        person.play(new Gutar());
        MyArray myArray = new MyArray();
        System.out.println(myArray.getSize()+"---"+myArray.lenth());
        myArray.add(1);
        myArray.add(1);
        myArray.add(2);
        myArray.add(3);
        myArray.add(4);
        myArray.add(2);
        myArray.add("admin");
        myArray.add(5);
        myArray.add(6);
        myArray.add(7);
        System.out.println(myArray.getSize()+"---"+myArray.lenth());
        myArray.remove(0);
        System.out.println(myArray.getSize()+"---"+myArray.lenth());
        for (int i = 0; i <myArray.getSize() ; i++) {
            System.out.println(myArray.get(i));
        }
    }
}
