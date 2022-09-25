@A101
Feature: a101_test

  Scenario: Uye kaydı olusturmadan uctan_uca_odeme_test

    Given Kullanici A101Url sitesine gider
    When Giyim_aksesuar_kadinIcGiyim_dizaltiCorap bolumune girer
    Then Acılan urunun siyah oldugu dogrulanir
    And Sepete ekle butonuna tiklanir
    And Sepeti Goruntule butonuna tiklanir
    And Sepeti Onayla butonuna tiklanir
    Then Uye olmadan devam et butonuna tiklanir
    And Mail ekrani gelir
    Then Sonrasinda adres ekrani gelir
    And Adres olustur dedikten sonra odeme ekrani gelir
    Then Siparisi tamamla butonuna tiklayarak, odeme ekranina gidildigi ,dogru ekrana yonlendiklerini kontrol edecekler