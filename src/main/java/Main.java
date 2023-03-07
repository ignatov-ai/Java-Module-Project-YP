import java.util.Scanner;

class Obrabotchiki {
    static String checkBook = "";
    static float cashOut;

    public static int peopleCountQuestion() {
        float peopleCount;
        System.out.println("На скольких человек необходимо разделить счёт?");
        do {
            Scanner sc = new Scanner(System.in);

            peopleCount = digitCheck(sc);

            if (peopleCount < 1) {
                System.out.println("Некорректное значение для подсчёта. Введите значение еще раз");
            } else if (peopleCount == 1) {
                System.out.println("Нет смысла делить счёт. Введите значение еще раз");
            } else {
                System.out.printf("Отлично, разделим счет поровну на %.0f гостей%n", peopleCount);
            }
        } while (peopleCount <= 1);

        return (int) peopleCount;
    }

    public static void checkBook(){
        System.out.println("\nДобавленные товары:\n" + checkBook);
    }

    public static float digitCheck(Scanner sc) {
        float result;
        while (true) {
            try {
                String input = sc.nextLine();
                result = Float.parseFloat(input);
                System.out.println(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка при вводе числа (встретился символ, отличный от цифры), попробуйте ввод еще раз");
            }
        }
        return result;
    }

    public static void calculator(){
        Scanner sc = new Scanner(System.in);
        String productName;
        float productCost;
        int i = 1;
        while(true){
            System.out.println("Для ЗАВЕРШЕНИЯ ввода товаров введите \"Завершить\", в противном случае введите название следующего товара");
            productName = sc.nextLine();
            if (productName.equalsIgnoreCase("Завершить")) {
                break;
            }
            checkBook = checkBook + String.format("%d.) %s - ",i,productName);
            System.out.println("Введите цену в формате РУБЛИ.КОПЕЙКИ: ");
            do {
                productCost = digitCheck(sc);
                if (productCost < 0){
                    System.out.println("Ошибка при вводе цены товара, встречено отрицательное значение!\nПовторите ввод цены:");
                }
            } while(productCost < 0);
            checkBook = checkBook + String.format("%.2f %s\n", productCost, rubli(productCost));
            cashOut = cashOut + productCost;

            System.out.println("Товар успешно ДОБАВЛЕН");
            i++;
        }
        sc.close();
    }

    public static String rubli(double money){
        String rubleName = "";
        int intMoney = (int) Math.floor(money);
        int lastDecimalIntMoney = intMoney % 10;
        int twoLastDecimalIntMoney = intMoney % 100;

        if (intMoney > 4 && intMoney < 21 || twoLastDecimalIntMoney > 4 && twoLastDecimalIntMoney < 21) {
            rubleName = " рублей";
        }
        else{
            switch(lastDecimalIntMoney){
                case 1:
                    rubleName = "рубль";
                    break;
                case 2:
                case 3:
                case 4:
                    rubleName = "рубля";
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 0:
                    rubleName = "рублей";
                    break;
            }
        }
        return rubleName;
    }
}

public class Main {
    public static void main(String[] args) {
        int peopleCount = Obrabotchiki.peopleCountQuestion();

        Obrabotchiki.calculator();
        Obrabotchiki.checkBook();

        float cashByPeople = Obrabotchiki.cashOut / peopleCount;
        System.out.println(String.format("Сумма на каждого из %d гостей %.2f %s",peopleCount, cashByPeople, Obrabotchiki.rubli(cashByPeople)));
    }
}