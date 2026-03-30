public class rekursioon {



    public static void g(int n) {

        if (n >= 1) {
            System.out.println(n);
            g(n-1);
            g(n-1);
        }
    }

    public static void main(String[] arg) {
        g(2);
    }
}
