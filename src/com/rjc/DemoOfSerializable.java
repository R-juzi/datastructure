package com.rjc;


import java.io.*;

/**
 * @program: datastructure
 * @description: 序列化demo
 * @author: R红茶
 * @create: 2020-03-06 16:11
 **/
public class DemoOfSerializable implements Serializable {
    private static final long serialVersionUID=1;

    public static void main(String[] args)throws Exception{
        DemoOfSerializable obj= new DemoOfSerializable("任建成",21,"gakki is my wife");
        //f1(obj);
        f2();
    }
    public static void f1(DemoOfSerializable obj) throws Exception {
        ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream(new File("E:SERIALIZABLE.TXT")));
        oo.writeObject(obj);
        oo.close();
        System.out.println("序列化成功");
    }
    public static void f2() throws Exception {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("E:SERIALIZABLE.TXT")));
        DemoOfSerializable o=(DemoOfSerializable) ois.readObject();
        System.out.println("反序列化成功");
        System.out.println(o);
    }

    private String name;
    private int age;
    private String description;
    private int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DemoOfSerializable(String name, int age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    @Override
    public String toString() {
        return "DemoOfSerializable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", num=" + num +
                '}';
    }
}
