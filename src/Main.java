import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input: ");
        String input = scanner.nextLine();
        System.out.println("Output: ");
        System.out.println(parse(input));
    }

    static String parse(String input) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean inRoman;
        String[] operands = input.split("[+\\-*/]");
        if (operands.length <2) throw new Exception("т. к. строка не является математической операцией");
        if (operands.length >2) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        oper = detectOperation(input);
        if (oper == null) throw new Exception("Неподерживаемая математическая операция");
        //если оба числа римские
        if (Roman.isRoman(operands[0])&& Roman.isRoman(operands[1])) {
            //конвертируем оба числа в арабские для вычисления действия
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
        }
        //если оба числа арабские
        else if(!Roman.isRoman(operands[0])&& !Roman.isRoman(operands[1])) {

            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
        }
        //если одно число римское, а другое арабское
        else {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        if (num1>10 || num2>10) {
            throw new Exception("Число должно быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if(Roman.isRoman(operands[0])&& Roman.isRoman(operands[1])) {
            //если римское число получилось меньше либо равно нулю, генерируем ошибку.
            if (arabian <= 0) {
                throw new Exception("т.к. в римской системе нет отрицательных чисел и нуля");
            }
            //конвертируем результат операции из арабского в римское
            result = Roman.convertToRoman(arabian);
        }else {
            //конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return result;
    }
    static String detectOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}

class Roman {
    static String[] romanArray = new String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV",
            "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
            "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII",
            "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    static boolean isRoman(String val ) {
        for (int i= 0; i<romanArray.length; i++){
            if(val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    static int convertToArabian (String roman){
        for (int i=0; i<romanArray.length; i++){
            if(roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    static String convertToRoman (int arabian){
        return romanArray[arabian];
    }
}
