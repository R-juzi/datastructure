package com.rjc;



/**
 * @program: datastructure
 * @description: 我的数组
 * @author: R红茶
 * @create: 2020-02-17 16:37
 * @see #capacity 数组的大小
 * @see #size 数组中真实元素的个数
 **/
public class MyArrayList<E> {
    private static final int DEAULT_SIZE = 10;
    private static final double GROW_SIZE=0.75;
    private static final Object[] DEAULT_ARRAY={};
    private E[] array;
    private int size;
    private int capacity;

    public MyArrayList() {
        capacity = DEAULT_SIZE;
        array =(E[]) new Object[capacity];
    }

    public MyArrayList(int num) {
        if (num == 0) {
            array =(E[]) new Object[0];
        } else if (num > 0) {
            capacity = num;
            array = (E[])new Object[capacity];
        } else {
            throw new IllegalArgumentException("非法参数：" + num);
        }
    }

    /**
     * @return 返回数组中元素长度
     */
    public int getSize() {
        return size;
    }

    /**
     * @param o 添加
     */
    public void add(E o) {
       ensureCapacity(size+1);
        array[size] = o;
        size++;
    }
    private void ensureCapacity(int size){
        if(size+1>capacity*GROW_SIZE){
           grow();
        }
    }

    /**
     * @see # array 数组的扩容算法
     * @return
     */
    private void grow(){
        capacity=capacity+capacity/2;
        System.out.println(capacity);
        E[] newArray=(E[])new Object[capacity];
        for (int i = 0; i <array.length ; i++) {
            newArray[i]=array[i];
        }
        array=newArray;
    }

    /**
     * @param index
     * @return 根据索引取元素
     */
    public Object get(int index) {
        checkIndex(index);
        return array[index];
    }

    /**
     * @param index 检查索引是否越界
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * @param index 根据索引删除元素 并返回被删除的元素
     * @return
     */
    public Object remove(int index){
        checkIndex(index);
        Object removeObj=array[index];
        for(int i=index;i<size;i++){
            array[i]=array[i+1];
        }
        size--;
        return removeObj;
    }

    /**
     * @return 判断是否是空数组
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * @param obj 返回元素第一次出现的索引
     * @return
     */
    public int indeOf(Object obj){
        if(obj==null){
            return -1;
        }
        for (int i = 0; i <size ; i++) {
            if(obj==array[i]){
                return i;
            }
        }
        return -1;
    }

    /**
     * @param obj 返回元素在数组中出现的次数
     * @return
     */
    public int countObj(Object obj){
        if(obj==null){
            return -1;
        }
        int result=0;
        for (int i = 0; i <size ; i++) {
            if(obj==array[i]){
                result++;
            }
        }
        return result;
    }
    public void update(E obj,int index){
        checkIndex(index);
        array[index]=obj;
    }
    public int lenth(){
        return capacity;
    }
}
