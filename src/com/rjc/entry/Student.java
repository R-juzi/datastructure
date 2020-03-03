package com.rjc.entry;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @program: datastructure
 * @description: 学生
 * @author: R红茶
 * @create: 2020-03-03 12:09
 **/
public class Student  implements Comparable<Student>{
    private String name;
    private int clazz;
    private int NO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public int getNO() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO = NO;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", clazz=" + clazz +
                ", NO=" + NO +
                '}';
    }

    @Override
    public int compareTo(Student o) {
          return this.NO-o.NO;
    }
}
