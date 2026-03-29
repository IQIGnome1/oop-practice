import java.util.Arrays;

public class tantsupaaridv2 {

    public static void main(String[] args) {

        int i = 0;
        int n = Integer.parseInt(args[i++]); /* parseInt muutub String0 = int */
        /* n = 3 sest i = 0 ja see on indeks */

        int[] boys = new int[n]; /* poiste massiiv */

        for (int b = 0; b < n; b++) {
            boys[b] = Integer.parseInt(args[i++]);
        }
        /* i = 4 sest for-tsükkel töötas 3 korda */

        int m = Integer.parseInt(args[i++]);
        int[] girls = new int[m];
        /* m = 4  nii et meil on 2 tütrukku  */

        for (int g = 0; g < m; g++) {
            girls[g] = Integer.parseInt(args[i++]);
        }

        /*
        Kontrollin mis  präegu maasivis on

        System.out.println("Boys:");
        for (int b = 0; b < n; b++) {
            System.out.println(boys[b]);
        }

        System.out.println("Girls:");
        for (int g = 0; g < m; g++) {
            System.out.println(girls[g]);
        }
        */

        int[][] pairs = combine(boys, girls);

        // Ilus väljund: "poiss ja tüdruk"
        for (int p = 0; p < pairs.length; p++) {
            System.out.println(pairs[p][0] + " ja " + pairs[p][1]);
        }
    }

    public static int[][] combine(int[] boys, int[] girls) {

        Arrays.sort(boys);
        Arrays.sort(girls);

        /*
        System.out.println("Sorteeritud boys massiiv: " + Arrays.toString(boys));
        System.out.println("Sorteeritud girls massiiv: " + Arrays.toString(girls));
        */

        int k = Math.min(boys.length, girls.length);

        // Kui ühe soo esindajaid on rohkem, siis jäetakse sellest soost sobiv arv kõige pikemaid ilma paariliseta.
        // Seega kasutame ainult esimesed k elemendid (pärast sorteerimist on kõige pikemad lõpus).
        int[][] pairs = new int[k][2];

        // Paarid: kõige pikem osalev poiss tantsib kõige pikema osaleva tüdrukuga jne.
        for (int p = 0; p < k; p++) {
            int idx = (k - 1) - p;     // k-1, k-2, ..., 0
            pairs[p][0] = boys[idx];   // poiss
            pairs[p][1] = girls[idx];  // tüdruk
        }

        return pairs;
    }
}
