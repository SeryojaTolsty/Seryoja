package Lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final int SIZE = 3;

    private static final char DOT_EMPTY = '•';
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';

    private static final String SPACE_MAP = " ";
    private static final String HEADER_FIRST_MAP = "♥";

    private static final char[][] MAP = new char[SIZE][SIZE];
    private static final Scanner in = new Scanner(System.in);
    private static final Random random = new Random();

    private static int turnsCount;


    public static void turnGame() {
        initMap();
        printMap();
        playGame();
    }

    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        printHeaderMap();
        printBodyMap();
    }

    private static void printHeaderMap() {
        System.out.print(HEADER_FIRST_MAP + SPACE_MAP);
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
        }
        System.out.println();
    }

    private static void printMapNumber(int i) {
        System.out.print(i + 1 + SPACE_MAP);
    }

    private static void printBodyMap() {
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + SPACE_MAP);
            }
            System.out.println();
        }
    }


    private static void playGame() {
        while (true) {
            turnHuman();
            printMap();
            if (checkEnd(DOT_HUMAN)) {
                break;
            }

            turnAI();
            printMap();
            if (checkEnd(DOT_AI)) {
                break;
            }

        }
        //следующий ход

    }

    private static void turnHuman() {
        System.out.println("Кусок мяса: ");
        int rowNumber, columnNumber;

        while (true) {
            rowNumber = getValidNumberFromUser() - 1;
            columnNumber = getValidNumberFromUser() - 1;
            if(isCellFree(rowNumber, columnNumber)) {
                break;
            } else {
                System.out.println("\nВыбрана занятая ячейка");
            }
        }

        MAP[rowNumber][columnNumber] = DOT_HUMAN;
        turnsCount++;
    }

    private static int getValidNumberFromUser() {
        while (true) {
            System.out.print("Введи координаты(1 - "+ SIZE +"): ");
            if(in.hasNextInt()) {
                int n = in.nextInt();
                if(isNumberValid(n)){
                    return n;
                } else {
                    System.out.println("\nПроверить значение координаты. Должно быть до " + SIZE);
                }
            }else {
                in.nextInt();
                System.out.println("\nВвод допускает лишь целые числа");
            }
        }
    }

    private static boolean isNumberValid(int n) {
        return n >= 1 && n <= SIZE;
    }

    private static boolean isCellFree(int rowNumber, int columnNumber) {
        return MAP[rowNumber][columnNumber] == DOT_EMPTY;
    }


    private static void turnAI() {
        System.out.println("Банка: ");
        int rowNumber, columnNumber;

        do {
            rowNumber = random.nextInt(SIZE);
            columnNumber = random.nextInt(SIZE);
        } while (!isCellFree(rowNumber, columnNumber));

        MAP[rowNumber][columnNumber] = DOT_AI;
        turnsCount++;
    }

    private static boolean checkEnd(char symbol) {
        if(checkWin(symbol)){
            if(symbol == DOT_HUMAN){
                System.out.println("\nПобеда за сопротивлением");
            } else {
                System.out.println("\nВосстание машин1");
            }
            return true;
        }
        if(checkLoose()){
            System.out.println("Ничья");
            return true;
        }
        return false;
    }

    private static boolean checkLoose() {
        return turnsCount >= SIZE * SIZE;
    }


    public static boolean checkWin(char symbol) {
        if(MAP[0][0] == symbol && MAP[0][1] == symbol && MAP[0][2] == symbol) return true;
        if(MAP[1][0] == symbol && MAP[1][1] == symbol && MAP[1][2] == symbol) return true;
        if(MAP[2][0] == symbol && MAP[2][1] == symbol && MAP[2][2] == symbol) return true;
        if(MAP[0][0] == symbol && MAP[1][0] == symbol && MAP[2][0] == symbol) return true;
        if(MAP[0][1] == symbol && MAP[1][1] == symbol && MAP[2][1] == symbol) return true;
        if(MAP[0][2] == symbol && MAP[1][2] == symbol && MAP[2][2] == symbol) return true;
        if(MAP[0][0] == symbol && MAP[1][1] == symbol && MAP[2][2] == symbol) return true;
        if(MAP[2][0] == symbol && MAP[1][1] == symbol && MAP[0][2] == symbol) return true;
        return false;
    }

}
