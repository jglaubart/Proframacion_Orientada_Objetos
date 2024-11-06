package Parcial2.RepasoParcial2.Ej6_RateLimitedCache;

public class RateLimitedCacheTester {
    private static final String USER1 = "Alice";
    private static final String USER2 = "Bob";
    private static final String DATE1 = "11/11/2019";
    private static final String DATE2 = "12/11/2019";

    public static void main(String[] args) {
        RateLimitedCache<Integer, String> cache = new RateLimitedCache<>();

        System.out.println(cache.size());

        cache.register(USER1, QuotaType.LIMITED);

        System.out.println("Testing puts with a limited quota (maximum 1 per date)");
        cache.put(USER1, DATE1, 1, "1");
        try {
            cache.put(USER1, DATE1, 1, "2");
        } catch (RateLimitedException e) {
            System.out.println("Cannot put 1->\"2\" on  " + DATE1);
        }
        System.out.println("-----");
        System.out.println(cache.get(USER1, DATE1, 1));   //La funcion debe imprimir el get y luego en el tester se imprime el numeor, en este caso 1
        System.out.println("-----");

        System.out.println("Testing reads with a limited quota (maximum 2 per date)");
        cache.put(USER1, DATE2, 2, "2");
        System.out.println("-----");
        System.out.println(cache.get(USER1, DATE2, 1));
        System.out.println("-----");
        System.out.println(cache.get(USER1, DATE2, 2));
        System.out.println("-----");
        try {
            System.out.println(cache.get(USER1, DATE2, 3));
        } catch (RateLimitedException e) {
            System.out.println("Cannot read 1 on " + DATE2);
        }

        cache.register(USER2, QuotaType.UNLIMITED);

        System.out.println("Testing puts with a unlimited");
        cache.put(USER2, DATE1, 3, "3");
        cache.put(USER2, DATE1, 3, "4");

        System.out.println("Testing reads with a unlimited");
        System.out.println("-----");
        System.out.println(cache.get(USER2, DATE1, 3));
        System.out.println("-----");
        System.out.println(cache.get(USER2, DATE1, 3));
        System.out.println("-----");
        System.out.println(cache.get(USER2, DATE1, 3));
        System.out.println("-----");

        System.out.println(cache.size());
    }
}

//0
//Testing puts with a limited quota (maximum 1 per date)
//Alice put value 1 for key 1 on 11/11/2019
//Cannot put 1->"2" on  11/11/2019
//-----
//Alice retrieved value 1 for key 1 on 11/11/2019
//1
//-----
//Testing reads with a limited quota (maximum 2 per date)
//Alice put value 2 for key 2 on 12/11/2019
//-----
//Alice retrieved value 1 for key 1 on 12/11/2019
//1
//-----
//Alice retrieved value 2 for key 2 on 12/11/2019
//2
//-----
//Cannot read 1 on 12/11/2019
//Testing puts with a unlimited
//Bob put value 3 for key 3 on 11/11/2019
//Bob put value 4 for key 3 on 11/11/2019
//Testing reads with a unlimited
//-----
//Bob retrieved value 4 for key 3 on 11/11/2019
//4
//-----
//Bob retrieved value 4 for key 3 on 11/11/2019
//4
//-----
//Bob retrieved value 4 for key 3 on 11/11/2019
//4
//-----
//3