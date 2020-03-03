package com.rjc;


/**
 * 1. 一款游戏，有个角色类，角色类有以下子类，它们都有攻击方法，和不同的外观：
 * A. 国王，用斧头
 * B. 王子，用剑
 * C. 皇后，用法杖
 * D. 公主，用剑
 * E. 骑士，用长枪
 * F. 怪物，用斧头
 * 请你设计一下这个类。
 * 如果要新添加一个新的角色：圆桌骑士，可以在运行时替换任何武器，你又当如何设计？
 */
interface AttackAble {
    void attack();
}
abstract class Role {
    AttackAble attackAble;
    abstract void paly();
    void attack() {
        attackAble.attack();
    }
}
class Axe implements AttackAble {
    @Override
    public void attack() {
        System.out.println("使用斧头攻击");
    }

}
class Sword implements AttackAble {
    @Override
    public void attack() {
        System.out.println("使用剑攻击");
    }

}
class Wand implements AttackAble {
    @Override
    public void attack() {
        System.out.println("使用法杖攻击");
    }

}

class King extends Role {
    King() {
        attackAble = new Axe();
    }

    @Override
    void paly() {
        System.out.println("国王出战");
    }
}

class Prince extends Role {
    Prince() {
        attackAble = new Sword();
    }

    @Override
    void paly() {
        System.out.println("王子出战");
    }
}

class Queen extends Role {
    Queen() {
        attackAble = new Wand();
    }

    @Override
    void paly() {
        System.out.println("王后出战");
    }
}

class Knight extends Role {
    Knight(AttackAble attackStyle) {
        attackAble=attackStyle;
    }

    @Override
    void paly() {
        System.out.println("骑士出战");
    }
}


/**
 * @program: datastructure
 * @description:
 * @author: R红茶
 * @create: 2020-02-18 17:15
 **/
public class HomeWork {
    public static void main(String[] args) {
        Role role = new King();
        role.paly();
        role.attack();
        Role role1 = new Prince();
        role1.paly();
        role1.attack();
        Role role2 = new Queen();
        role2.paly();
        role2.attack();
        Role role3=new Knight(new Axe());
        role3.paly();
        role3.attack();
        Role role4=new Knight(new Sword());
        role4.paly();
        role4.attack();
    }


}
