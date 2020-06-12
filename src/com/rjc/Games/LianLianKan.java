package com.rjc.Games;


/** @author rjc
 * @time 2020/5/29 15:03
 */
public class LianLianKan {
    private static  int xnum=4;
    private static  int ynum=4;

    public static void main(String[] args) {
        //初始化一个二维数组
        MyPoint[][] chessArr=new MyPoint[ynum][];
        for (int i=0;i<ynum;i++){
            chessArr[i]=new MyPoint[xnum];
            for (int j=0;j<xnum;j++){
                chessArr[i][j]=new MyPoint(j,i,(i*xnum)+j);
            }
        }
        for (int i=0;i<ynum;i++){
            System.out.println();
            for (int j=0;j<xnum;j++){
                System.out.print(chessArr[i][j]);
            }
        }
    }
}
class MyPoint{
    public int x;
    public int y;
    public int id;
    public MyPoint(int x,int y,int id){
        this.x=x;
        this.y=y;
        this.id=id;
    }
    public MyPoint(){}

    @Override
    public String toString() {
        return "MyPoint{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                '}';
    }
}