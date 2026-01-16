package Chess_Assignment_3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package javaapplication1;
//
///**
// *
// * @author Can Zorlu
// */
//public class NewClass {
//     public static void pattern() {
//        System.out.println(" 0  1 2  3  4  5 6  7 ");
//        System.out.print("-----------------------");
//        for (int i = 0; i <= 7; i++) {
//            System.out.println("");
//            System.out.print("|");
//
//            for (int j = 0; j <= 7; j++) {
//                if ((j % 2) != 0) {
//
//                    System.out.print("***");
//
//                } else {
//                    System.out.print("   ");
//                }
//
//            }
//
//            
//
//        }
//        
//        System.out.println("-----------------------");
//    }
//    
//}
//public class Bishop extends Piece {
//
//    public Bishop(int x, int y, String color) {
//        super(x, y, color);
//    }
//
//    @Override
//    public boolean canMove(int newX, int newY) {
//        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
//            return false;
//        } else if (Math.abs(newX - x) == Math.abs(newY - y) && newX > x && newY > y) {
//
//            for (int i = x + 1, j=y+1; i != newX&&j!=newY; i++,j++) {
//                if (Board.board[i][j] != null) {
//                    return false;
//                }
//            }
//            return true;
//        } else if (Math.abs(newX - x) == Math.abs(newY - y) && newX < x && newY > y) {
//            for (int i = x - 1; i != newX; i--) {
//                if (Board.board[i][-i + 2] != null) {
//                    return false;
//                }
//            }
//            return true;
//
//        } else if (Math.abs(newX - x) == Math.abs(newY - y) && newX >x && newY < y) {
//            for (int i = x + 1; i != newX; i++) {
//                if (Board.board[i][-i + 2] != null) {
//                    return false;
//                }
//            }
//            return true;
//        }else if (Math.abs(newX - x) == Math.abs(newY - y) && newX < x && newY < y) {
//
//            for (int i = x - 1, j=y-1; i != newX&&j!=newY; i--,j--) {
//                if (Board.board[i][j] != null) {
//                    return false;
//                }
//            }
//            return true;
//        }
//        return false;
//    }