import java.util.Scanner;

public class Third{
    public static void main(String[] args) {
        CashDispanser cashDispanser = new CashDispanser("Cбербанк", "IBA");
        cashDispanser.getAllInfo();
        System.out.printf("\nВведите сумму которую хотите получить\n");
        Scanner amount =new Scanner(System.in);
        cashDispanser.getMoney(amount.nextInt());
        cashDispanser.getAllInfo();
        cashDispanser.addMoney();
        cashDispanser.getAllInfo();
    }
}

abstract class Cashpoint implements GetAmount, GetQuantityOfBils, GetInfo {
    public abstract int getAmount();

    public abstract void getInfo();

    public abstract void getQuantityOfBils();

    public abstract void getMoney(int amount);

    public abstract void addMoney();
}

class CashDispanser extends Cashpoint {

    private TwentyDollarBills twentyDollarBills = new TwentyDollarBills(20, 5);
    private FiftyDollarBills fiftyDollarBills = new FiftyDollarBills(50, 11);
    private OneHundredDollarBills oneHundredDollarBills = new OneHundredDollarBills(100, 30);
    private String bankName;
    private String dispanserProduser;

    CashDispanser(String bankName, String dispanserProduser) {
        this.bankName = bankName;
        this.dispanserProduser = dispanserProduser;
    }

    public int getAmount() {
        int amount = twentyDollarBills.quantity * twentyDollarBills.valueOfBill + fiftyDollarBills.quantity * fiftyDollarBills.valueOfBill + oneHundredDollarBills.valueOfBill * oneHundredDollarBills.quantity;
        return amount;
    }

    public void getQuantityOfBils() {
        System.out.printf("20$ -%d\n50$ -%d\n100$-%d", twentyDollarBills.quantity, fiftyDollarBills.quantity, oneHundredDollarBills.quantity);
    }

    public void getAllInfo() {
        getInfo();
        System.out.printf("Cумма купюр в банкомате = %d\n", getAmount());
        getQuantityOfBils();

    }

    public void getInfo() {
        System.out.printf("\n~~~%s~~~ \nПроизводитель банкомата %s\n", bankName, dispanserProduser);

    }

    public void getMoney(int amount) {
        int manCanGetMoney = 0;
        int haveHundredDollarBills = oneHundredDollarBills.quantity;
        int haveFiftyDollarBills = fiftyDollarBills.quantity;
        int haveTwentyDollarBills = twentyDollarBills.quantity;
        int getHundredDollarBills ;
        int getFiftyDollarBills ;
        int getTwentyDollarBills ;

        int needHundredDollarBills = amount / 100;
        int remainderBills = haveHundredDollarBills - needHundredDollarBills;
        if (remainderBills > 0) {
            getHundredDollarBills = needHundredDollarBills;
        } else {
            getHundredDollarBills = needHundredDollarBills + remainderBills;
        }
        amount = amount - getHundredDollarBills * 100;
        manCanGetMoney = manCanGetMoney + getHundredDollarBills * 100;
        int needFiftyDollarBills = amount / 50;
        remainderBills = haveFiftyDollarBills - needFiftyDollarBills;
        if (remainderBills > 0) {
            getFiftyDollarBills = needFiftyDollarBills;
        } else {
            getFiftyDollarBills = needFiftyDollarBills + remainderBills;
        }
        amount = amount - getFiftyDollarBills * 50;
        manCanGetMoney = manCanGetMoney + getFiftyDollarBills * 50;
        int needTwentyDollarBills = amount / 20;
        remainderBills = haveTwentyDollarBills - needTwentyDollarBills;
        if (remainderBills > 0) {
            getTwentyDollarBills = needTwentyDollarBills;
        } else {
            getTwentyDollarBills = needTwentyDollarBills + remainderBills;
        }
        amount = amount - getTwentyDollarBills * 20;
        manCanGetMoney = manCanGetMoney + getTwentyDollarBills * 20;
        if (amount != 0) {
            System.out.printf("\nБанкомат в данный момент не может выдать вам данную сумму , однако для вывода доступно %d$ \nВывести деньги ? \n1--ДА\n2-НЕТ\n", manCanGetMoney);
            Scanner in = new Scanner(System.in);
            if (in.nextInt() == 1) {
                System.out.printf("|Банкомат выводит %d$ , количество купюр:100$=%d 50$=%d 20$=%d |", manCanGetMoney, getHundredDollarBills, getFiftyDollarBills, getTwentyDollarBills);
                oneHundredDollarBills.quantity = oneHundredDollarBills.quantity - getHundredDollarBills;
                fiftyDollarBills.quantity = fiftyDollarBills.quantity - getFiftyDollarBills;
                twentyDollarBills.quantity = twentyDollarBills.quantity - getTwentyDollarBills;
            }
        } else {
            System.out.printf("\n|Банкомат выдает %d$ , количество купюр:100$=%d 50$=%d 20$=%d |", manCanGetMoney, getHundredDollarBills, getFiftyDollarBills, getTwentyDollarBills);
            oneHundredDollarBills.quantity = oneHundredDollarBills.quantity - getHundredDollarBills;
            fiftyDollarBills.quantity = fiftyDollarBills.quantity - getFiftyDollarBills;
            twentyDollarBills.quantity = twentyDollarBills.quantity - getTwentyDollarBills;
        }
    }

    public void addMoney() {
        boolean refill=true;
        do {
            System.out.print("\n-------Операция-пополнения-------\nКакие купюры вы хотите пополнить ? \n1--------100$--------\n2--------50$---------\n3--------20$---------\n4-Прекратить операцию\n");
Scanner in=new Scanner(System.in);
            switch (in.nextInt()) {
                case 1:
                    System.out.printf("Количество купюр ");
                    oneHundredDollarBills.addBills(in.nextInt());
                    break;
                case 2:
                    System.out.printf("Количество купюр ");
                    fiftyDollarBills.addBills(in.nextInt());
                    break;
                case 3:
                    System.out.printf("Количество купюр ");
                    twentyDollarBills.addBills(in.nextInt());
                    break;
                case 4:
                    refill=false;
                    break;
            }
        }while(refill);
    }
}

interface GetMoney {
    void getMoney();
}

interface AddMoney {
    void addMoney();
}

interface GetInfo {
    void getInfo();
}

interface GetAmount {
    int getAmount();
}

interface GetQuantityOfBils {
    void getQuantityOfBils();
}

abstract class Bills {
    public int quantity;
    int valueOfBill;

    Bills(int valueOfBill) {
        this.valueOfBill = valueOfBill;
        this.quantity = 0;
    }

    Bills(int valueOfBill, int quantity) {
        this.valueOfBill = valueOfBill;
        this.quantity = quantity;
    }

    public void addBills(int quantity) {
        this.quantity = this.quantity + quantity;
    }
    public void getBills(int quantity) {
        this.quantity = this.quantity - quantity;
    }

}

class TwentyDollarBills extends Bills {
    TwentyDollarBills(int valueOfBill) {
        super(valueOfBill);
    }
    TwentyDollarBills(int valueOfBill, int quantity) {
        super(valueOfBill, quantity);
    }
}

class FiftyDollarBills extends Bills {
    FiftyDollarBills(int valueOfBill) {
        super(valueOfBill);
    }
    FiftyDollarBills(int valueOfBill, int quantity) {
        super(valueOfBill, quantity);
    }
}

class OneHundredDollarBills extends Bills {
    OneHundredDollarBills(int valueOfBill, int quantity) {
        super(valueOfBill, quantity);
    }
    OneHundredDollarBills(int valueOfBill) {
        super(valueOfBill);
    }
}