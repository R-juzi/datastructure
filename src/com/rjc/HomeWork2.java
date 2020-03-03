package com.rjc;

/**
 * 把简单工厂、工厂方法、抽象工厂的演变过程，自己手过一遍。
 *
 * @program: datastructure
 * @description:
 * @author: R红茶
 * @create: 2020-02-18 19:17
 **/
interface Food {
    void cooking();
}

class Apple implements Food {
    @Override
    public void cooking() {
        System.out.println("锅里做出了苹果派");
    }
}

class Rice implements Food {
    @Override
    public void cooking() {
        System.out.println("锅里做出了米饭");
    }
}
/** ------------------下面是简单工厂 ------------------*/
class FoodFactory {
    public static Food getFood(int i) {
        Food food = null;
        switch (i) {
            case 1:
                food = new Apple();
                break;
            case 2:
                food =new Rice();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
        return food;
    }

}

/** ------------------下面是方法工厂 ------------------*/
interface MaterialFactory{
    Food getFood();
}
class AppleFactory implements MaterialFactory{

    @Override
    public Food getFood() {
        return new Apple();
    }
}
class RiceFactory implements MaterialFactory{
    @Override
    public Food getFood() {
        return new Rice();
    }
}
public class HomeWork2 {
    public static void main(String[] args) {
        Food food =FoodFactory.getFood(2);
        food.cooking();
        /**方法工厂测试*/
        Food food1=new AppleFactory().getFood();
        food1.cooking();
        /**抽象工厂测试*/

    }

}
