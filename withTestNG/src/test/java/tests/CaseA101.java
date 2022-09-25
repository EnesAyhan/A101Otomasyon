package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.A101Page;
import utilities.*;

import java.io.IOException;

public class CaseA101 extends TestBaseRapor  {

    A101Page a101Page = new A101Page();

    @BeforeClass
    public void setUp() {

        extentTest=extentReports.createTest("A101 Otomasyon","Uctan uca Odeme Sistemi");
        Driver.getDriver().get(ConfigReader.getProperty("A101Url"));
        extentTest.info("A101 Anasayfsina gidildi");

        ReusableMethods.waitFor(2);
        a101Page.cookiesAccept.click(); //Çerez Bidirimini Kabul et


    }

    @Test
    public void a101Case() throws IOException {
        //1-Giyim--> Aksesuar--> Kadın İç Giyim-->Dizaltı Çorap bölümüne girilir.

        a101Page.giyimAksesuarButonu.click(); //Giyim-Aksesuar Butonuna tiklanir
        extentTest.info("Giyim-Aksesuar Butonuna tiklanir");
        a101Page.kadinIcGiyimButonu.click(); //Kadin Iç Giyim Butonuna tiklanir
        extentTest.info("Kadin Iç Giyim Butonuna tiklanir");
        ReusableMethods.waitFor(2);
        a101Page.dizAltiCorapButonu.click(); //Dizalti  Corap Butonuna tiklanir
        extentTest.info("Dizalti  Corap Butonuna tiklanir");
        a101Page.siyahCorapButonu.click();    //Siyah corap butonu tiklanir
        extentTest.info("Siyah corap butonu tiklanir");

        //2-Açılan ürünün siyah olduğu doğrulanır.
        String corapRengi = a101Page.corapRenkKontrol.getText(); //Renk kontrolu icin urunun rengi getText methodu ile alinir
        // string bir konteynira konur
        Assert.assertTrue(corapRengi.contains("SİYAH"));       //Urunun siyah oldugu kontrol edilir
        extentTest.info("Urunun siyah oldugu kontrol edilir");

        ReusableMethods.getScreenshot("CorapRenkKontrol");//Urunun resmi de alinarak test saglamlastirilir.
        //Kaydedilen dosya ismi testin yapildigi hem ismi hem de
        //testin yapildigi tarih ve saat olacak sekilde ayarlanmistir.


        //3-Sepete ekle butonuna tıklanır.
        ReusableMethods.waitFor(1);
        a101Page.siyahCorapSepeteEkleButonu.click();
        extentTest.info("Sepete ekle butonuna tıklanır");

        //4-Sepeti Görüntüle butonuna tıklanır.
        a101Page.sepetiGoruntuleButonu.click();
        extentTest.info("Sepeti Görüntüle butonuna tıklanır");

        //5-Sepeti Onayla butonuna tıklanır.
        a101Page.sepetiOnaylaButonu.click();
        extentTest.info("Sepeti Onayla butonuna tıklanır");

        //6-Üye olmadan devam et butonuna tıklanır.
        a101Page.uyeOlmadanDevamEtButonu.click();
        extentTest.info("Üye olmadan devam et butonuna tıklanır.");


        //7-Mail ekranı gelir.
        a101Page.mailTextBox.sendKeys(ConfigReader.getProperty("mail"));
        a101Page.mailTextBoxDevamEtButonu.click();

        //8-Sonrasında adres ekranı gelir. Adres oluştur dedikten sonra ödeme ekranı gelir.
        a101Page.adresOlusturButonu.click();
        a101Page.adresOlusturAdresBasligiBox.sendKeys("Ev Adresi");
        a101Page.adresOlusturIsimBox.sendKeys("isim");
        a101Page.adresOlusturSoyadBox.sendKeys("soyad");
        a101Page.adresOlusturCepTelBox.sendKeys("5679999999");
        extentTest.info("Sonrasında adres ekranı gelir. Adres oluştur dedikten sonra ödeme ekranı gelir");


        Select select = new Select(a101Page.adresOlusturIlDropDown);
        select.selectByVisibleText("İSTANBUL");

        select = new Select(a101Page.adresOlusturIlceDropDown);
        select.selectByVisibleText("ŞİŞLİ");

        select = new Select(a101Page.adresOlusturMahalleDropDown);
        for (int retry = 0; retry < 5; retry++) {
            try {
                select.selectByVisibleText("FULYA MAH");
            } catch (StaleElementReferenceException ex) {
                ex.toString();
            }
        }
        //Mahalle Drop Down'ında StaleElementReferenceException verdigi icin bu sekilde Handle edildi

        Faker faker=new Faker();

        a101Page.adresOlusturAdresBox.sendKeys(faker.address().fullAddress());

        ReusableMethods.clickWithJS(a101Page.adresOlusturKaydetButonu);
        ReusableMethods.waitFor(2);
        ReusableMethods.clickWithJS( a101Page.kargoKaydetVeDevamButonu);


        //9-Siparişi tamamla butonuna tıklayarak, ödeme ekranına gidildiği ,doğru ekrana yönlendiklerini kontrol edecekler.

        int krediKartHaneSayisi=16;

            //faker classini kullanarak kart bilgilerini doldurduk.
        a101Page.kartBilgileriAdSoyad.sendKeys(faker.name().fullName());
        a101Page.kartBilgileriKartNo.sendKeys(faker.number().digits(krediKartHaneSayisi));

        //Odeme ekranında olundugu kontrol edilir
        String expectedText="Kart ile ödeme";
        String actualText= a101Page.kartIleOdemeText.getText();

        Assert.assertEquals(actualText,expectedText);
        extentTest.info("Odeme ekranında olundugu kontrol edilir");
        extentTest.pass("Test basari ile gecti");


    }



}
