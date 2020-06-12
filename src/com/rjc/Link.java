package com.rjc;
import java.util.Scanner;
import java.util.Random;
public class Link{
public static void main(String[] args){
 Scanner sc=new Scanner(System.in);
 System.out.println("==========================");
 System.out.println("\t\t连连看小游戏");
 System.out.println("\t\t版权：lr");
 System.out.println("==========================");

 System.out.println("请先输入图片数量（难度系数 1～9）：");
 int picnum=sc.nextInt();

 System.out.println("请输入棋盘的行数：");
 int rows=sc.nextInt();

 int cols=0; //列数
 int count=0; //计数器
 do{
 if (count>0){
 System.out.println("输入有误，列数必须大于0！");
 }
 System.out.println("请输入棋盘列数：");
 cols=sc.nextInt();
 count++;
 }while( cols<1 || cols%2!=0);
 //创建二维数组，生成棋盘，列数+2的原因：做出边框的效果 数组刚刚生成时，每个元素都是0
 int[][] chessboard=new int[ rows+2 ][ cols+2];

 //随机生成的图片数值存入这个二维数组中，注意：边框不存值，任为0
 initBoard( chessboard ,picnum);
 System.out.println("初始化后的棋盘为：");
 showBoard2( chessboard);
 //打乱棋盘
 shuffle( chessboard );
 //输出
 System.out.println("打乱后的棋盘为：");
 showBoard2( chessboard);

 //实现消除业务
 // 1.定义两个Point对象
 Point p1=new Point();
 Point p2=new Point();
 // 2.游戏状态 isGameOver
 boolean isGameOver=false;
 do{
 // 3.循环输入两个点 do...while
 System.out.println("请输入两个点的坐标");
 p1.x=sc.nextInt();
 p1.y=sc.nextInt();
 p2.x=sc.nextInt();
 p2.y=sc.nextInt();
 // 4.判断这两个数是否可以消除
 if( isErazeOk( chessboard,p1,p2)){
 //如果可以消除，将这两个点在chessboard 中的值都设为0
 chessboard[p1.x][p1.y]=0;
 chessboard[p2.x][p2.y]=0;
 if( checkGameOver( chessboard )){
 isGameOver=true;
 }
 }
 //显示消除后的棋盘
 showBoard2( chessboard );
 }while( !isGameOver );
 System.out.println("游戏结束！");
 }

         //判断是否能消除的业务
         public static boolean isErazeOk(int[][] chessboard ,Point p1,Point p2){
 // 1.两个点不是同一个
 if( p1.equals( p2) ){
 System.out.println("输入的两个点位置不能相同！");
 }
 // 2。两个点的值是否相等
 if(chessboard[p1.x][p1.y] !=chessboard[p2.x][p2.y]){
 System.out.println("输入的两个点值不相同！请重新输入");
 return false;
 }
 // 3.判断两个点的连线情况
 if( doOneLine(chessboard,p1,p2) || doTwoLine(chessboard,p1,p2) || doThreeLine(chessboard,p1,p2)){
 return true;
 }
 return false;
 }

         // 1连线
         public static boolean doOneLine(int[][] chessboard,Point p1,Point p2){
 //定义最大值和最小值
 int max=0;
 int min=0;
 //判断是循环行还是循环列
 if( p1.x==p2.x){
 //找y的最大值、找y的最小值、循环从 min+1 至 max-1、判断是否为0、如果中间有一个不为0，则返回 false
 max=p1.y>p2.y?p1.y:p2.y;
 min=p1.y<p2.y?p1.y:p2.y;
 for(int i=min+1;i<max;i++){
 if(chessboard[p1.x][i]!=0){
 return false;
 }
 }
 return true;
 }else if( p1.y==p2.y){
 //找x的最大值、找x的最小值、循环从 min+1 至 max-1、判断是否为0、如果中间有一个不为0，则返回 false
 max=p1.x>p2.x?p1.x:p2.x;
 min=p1.x<p2.x?p1.x:p2.x;
 for(int i=min+1;i<max;i++){
 if(chessboard[i][p1.y]!=0){
 return false;
 }
 }
 return true;
 }
 return false;
 }

         // 2连线
         public static boolean doTwoLine(int[][] chessboard,Point p1,Point p2){
 //定义两个临时点
 Point t1=new Point();
 t1.x=p1.x;
 t1.y=p2.y;

 Point t2=new Point();
 t2.x=p2.x;
 t2.y=p1.y;

 if( chessboard[t1.x][t1.y]==0 && doOneLine(chessboard, p1,t1 ) && doOneLine(chessboard, t1,p2) ){
 return true;
 }
 if( chessboard[t2.x][t2.y]==0 && doOneLine(chessboard, p1,t2 ) && doOneLine(chessboard, t2,p2) ){
 return true;
 }
 return false;
 }

         // 3连线
         public static boolean doThreeLine(int[][] chessboard,Point p1,Point p2){
 //先循环行 ：x
 for( int i=0;i<chessboard.length;i++){
 Point t=new Point();
 t.x=i;
 t.y=p1.y;
 if( chessboard[t.x][t.y]==0 && doOneLine(chessboard,t, p1) && doTwoLine(chessboard, t,p2) ){
 return true;
 }
 }
 //再循环列 ：y
 for( int i=0;i<chessboard.length;i++){
 Point t=new Point();
 t.x=p1.x;
 t.y=i;
 if( chessboard[t.x][t.y]==0 && doOneLine(chessboard,t, p1) && doTwoLine(chessboard, t,p2) ){
 return true;
 }
 }
 return false;
 }

         //判断游戏是否结束：循环这个数组，判断所有的位置都为0
         public static boolean checkGameOver(int[][] chessboard){
 for(int i=1;i<chessboard.length-1;i++){
 for(int j=1;i<chessboard[i].length-1;j++){
 if( chessboard[i][j]!=0 ){
 return false;
 }
 }
 }
 return true;
 }


         public static void initBoard(int[][] chessboard,int picnum){
 Random r=new Random();
 for( int i=1;i<chessboard.length-1;i++){
 for(int j=1;j<chessboard[i].length-1;j=j+2){
 int pic=r.nextInt( picnum )+1;
 chessboard[i][j]=pic;
 chessboard[i][j+1]=pic;
 }
 }
 }

         //打乱棋盘，只能对中间的值打乱，而不能打扰边框
         //交换数组的两个值 ，随机生成的四个下标，每个下标表示一个数 x1，y1 x2，y2 =》 chessboard【x2】【y1】 将上面两个数交换
         //概率：棋盘越大，交换越多
         public static void shuffle(int[][] chessboard ){
 Random r=new Random();
 int x1=0;
 int y1=0;
 int x2=0;
 int y2=0;
 int temp=0;
 for(int i=0;i<chessboard.length*chessboard[0].length*10;i++){
 //生成的四个下标，不能为0，也不能为 length-1
 x1=r.nextInt( chessboard.length-2 )+1;
 y1=r.nextInt( chessboard[0].length-2 )+1;
 x2=r.nextInt( chessboard.length-2 )+1;
 y2=r.nextInt( chessboard[0].length-2 )+1;
 //完成交换
 temp=chessboard[x1][y1];
 chessboard[x1][y1]=chessboard[x2][y2];
 chessboard[x2][y2]=temp;
 }
 }
 //简单的输出
         public static void showBoard(int[][] chessboard){
 for(int i=0;i<chessboard.length;i++){
 for(int j=0;j<chessboard[i].length;j++){
 System.out.print(chessboard[i][j]+"\t");
 }
 System.out.println();
 }
 }

         //私有方法：专门用来输出棋盘最上面和最下面要出现的列号
         private static void showColsNum( int[][] chessboard ){
 for(int i=0;i<chessboard[0].length;i++){
 if( i==0 || i==chessboard[i].length-1){
 System.out.print("\t");
 }else{
 System.out.print("*"+i+"\t");
 }
 }
 System.out.println();


 }

         //带行列提示的输出
         public static void showBoard2( int[][] chessboard){
 showColsNum( chessboard );//输出error:列号
 //中间完成棋盘
 for(int i=0;i<chessboard.length;i++ ){
 //加入前面行号的输出
 if( i==0 || i==chessboard[i].length-1){
 System.out.print(" ");
 }else{
 System.out.print(""+i);
 }
 for(int j=0;j<chessboard[i].length;j++){
 //边框要用 * 来修饰
 if( i==0 || j==0 || i==chessboard.length-1 || j==chessboard[i].length-1){
 if( j==chessboard[i].length-1){
 System.out.print(" # ");
 }else{
 System.out.print(" #\t");
 }
 }else{//不是边框，就输出数组中对应的数字
 if( chessboard[i][j]==0){

 System.out.print(" \t");
 }else{
 System.out.print(" "+chessboard[i][j]+"\t");
 }
 }
 }
 //加入后面的行号
 if( i==0 || i==chessboard.length-1){
 System.out.print(" ");
 }else{
 System.out.print(""+i);
 }
 System.out.println();
 }
 showColsNum( chessboard );//输出列号

 }

}