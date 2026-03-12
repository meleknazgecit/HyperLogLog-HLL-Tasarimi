import java.util.HashSet;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int[] pDegerleri = {4, 6, 8, 10}; // farklı bucket sayıları
        int toplamVeri = 100000;
        Random random = new Random();

        System.out.println("m\tGercek\tTahmin\tHata(%)");

        for (int p : pDegerleri) {

            HyperLogLog hll = new HyperLogLog(p);
            HashSet<Integer> gercekKume = new HashSet<>();

            for (int i = 0; i < toplamVeri; i++) {
                int sayi = random.nextInt(200000);
                gercekKume.add(sayi);
                hll.ekle(String.valueOf(sayi));
            }

            int gercekDistinct = gercekKume.size();
            double tahmin = hll.tahminEt();
            double hata = Math.abs(tahmin - gercekDistinct) / gercekDistinct * 100;

            int m = 1 << p;
            System.out.println(m + "\t" + gercekDistinct + "\t" + (int) tahmin + "\t" + String.format("%.2f", hata));
        }
    }

}