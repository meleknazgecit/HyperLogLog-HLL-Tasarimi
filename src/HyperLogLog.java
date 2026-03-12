import java.util.Arrays;

public class HyperLogLog {

    private int p;              // bucket bit sayisi
    private int m;              // bucket sayisi
    private int[] registerlar; // register dizisi

    public HyperLogLog(int p) {

        this.p = p;

        // m = 2^p
        this.m = 1 << p;

        // register dizisi olusturulur
        registerlar = new int[m];

        // tum registerlar baslangicta 0
        Arrays.fill(registerlar, 0);
    }

    public void ekle(String deger) {

        int hash = HashFunction.hash(deger);

        // bucket index
        int bucket = hash & (m - 1);

        // geri kalan bitler
        int w = hash >>> p;

        int sifirSayisi = Integer.numberOfTrailingZeros(w) + 1;

        registerlar[bucket] = Math.max(registerlar[bucket], sifirSayisi);
    }


    public double tahminEt() {

        double alpha;

        if (m == 16)
            alpha = 0.673;
        else if (m == 32)
            alpha = 0.697;
        else if (m == 64)
            alpha = 0.709;
        else
            alpha = 0.7213 / (1 + 1.079 / m);

        double toplam = 0.0;

        for (int i = 0; i < m; i++) {

            toplam += Math.pow(2.0, -registerlar[i]);
        }

        double tahmin = alpha * m * m / toplam;

        // kucuk veri duzeltmesi
        int sifirRegister = 0;

        for (int r : registerlar) {
            if (r == 0)
                sifirRegister++;
        }

        if (tahmin <= 2.5 * m && sifirRegister > 0) {

            tahmin = m * Math.log((double) m / sifirRegister);
        }

        return tahmin;
    }

    public void birlestir(HyperLogLog diger) {

        if (this.m != diger.m) {
            throw new IllegalArgumentException("HLL boyutlari ayni olmali");
        }

        for (int i = 0; i < m; i++) {

            registerlar[i] =
                    Math.max(registerlar[i], diger.registerlar[i]);
        }
    }

}