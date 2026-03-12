# HyperLogLog (HLL) Tasarımı

## Proje Açıklaması
Bu proje, **Büyük Veri Analitiğinde Olasılıksal Veri Yapıları** konusunu uygulamalı olarak incelemek amacıyla hazırlanmıştır. Amaç, **HyperLogLog (HLL) algoritmasını sıfırdan kodlamak**, gerçeklemek ve **teorik hata sınırlarını analiz etmektir**.  

HLL, büyük veri kümelerinde **benzersiz öğe sayısını tahmin etmek** için kullanılır ve hafıza kullanımını düşük tutar.

---

## Kullanılan Teknolojiler
- **Programlama Dili:** Java  
- **IDE:** IntelliJ IDEA 2025.2.2  
- **Hash Fonksiyonu:** Java `hashCode()` kullanıldı  

---

## Proje Özeti

### Algoritma Bileşenleri
1. **Hash Function:** Verileri rastgele dağıtır ve bucket ile leading zero hesaplamasında kullanılır.  
2. **Buckets (Kovalar):** m = 2^p kadar bucket vardır, hash’in ilk p biti bucket seçimi için kullanılır.  
3. **Registers:** Her bucket, gördüğü en fazla leading zero sayısını saklar.  
4. **Harmonik Ortalama:** Tüm register değerlerinden cardinality tahmini alır.  
5. **Merge:** İki HLL nesnesi birleştirilebilir, veri kaybı olmaz.  

### Kod Akışı
1. Random veri üretilir ve hash’e gönderilir.  
2. Hash değerine göre bucket seçilir.  
3. Register güncellenir (max leading zero).  
4. Tahmin harmonik ortalama ile hesaplanır.  
5. Küçük veri düzeltmeleri uygulanır.  
6. Farklı m değerleri ile hata analizi yapılır.  

---

## Hata Analizi -Örnek

| m    | Gerçek | Tahmin | Hata(%) |
|------|--------|--------|---------|
| 16   | 78706  | 56739  | 27,91   |
| 64   | 78797  | 90801  | 15,24   |
| 256  | 78631  | 92187  | 17,24   |
| 1024 | 78655  | 78164  | 0,62    |

- Bucket sayısı arttıkça tahmin hatası azalır.  
- Daha fazla bucket → daha doğru leading zero bilgisi → daha iyi tahmin.  

---
## Lisans
Bu proje eğitim amaçlı hazırlanmıştır.
