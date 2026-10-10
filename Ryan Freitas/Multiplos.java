import java.util.Scanner;

public class Multiplos {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o número base: ");
        int base = sc.nextInt();
        System.out.print("Digite o limite: ");
        int limite = sc.nextInt();
        for (int i = base; i <= limite; i = i + base) {
            System.out.println(i);
        }
        sc.close();
    }
}