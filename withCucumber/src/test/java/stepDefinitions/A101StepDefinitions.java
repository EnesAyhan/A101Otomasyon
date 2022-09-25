package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.Select;
import pages.A101Page;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class A101StepDefinitions {

    A101Page a101Page = new A101Page();


    @Given("Kullanici A101Url sitesine gider")
    public void kullanici_a101url_sitesine_gider() {
        Driver.getDriver().get(ConfigReader.getProperty("A101Url"));
        a101Page.cookiesAccept.click(); //Çerez Bidirimini Kabul et
    }

    @When("Giyim_aksesuar_kadinIcGiyim_dizaltiCorap bolumune girer")
    public void giyim_aksesuar_kadin_ıc_giyim_dizalti_corap_bolumune_girer() {
        a101Page.giyimAksesuarButonu.click(); //Giyim-Aksesuar Butonuna tiklanir
        a101Page.kadinIcGiyimButonu.click(); //Kadin Iç Giyim Butonuna tiklanir
        ReusableMethods.waitFor(2);
        a101Page.dizAltiCorapButonu.click(); //Dizalti  Corap Butonuna tiklanir
        ReusableMethods.waitFor(1);
        a101Page.siyahCorapButonu.click();    //Siyah corap butonu tiklanir


    }

    @Then("Acılan urunun siyah oldugu dogrulanir")
    public void acılan_urunun_siyah_oldugu_dogrulanir() {
        ReusableMethods.waitFor(1);

        String corapRengi = a101Page.corapRenkKontrol.getText(); //Renk kontrolu icin urunun rengi getText methodu ile alinir
        // string bir konteynira konur
        Assert.assertTrue(corapRengi.contains("SİYAH"));       //Urunun siyah oldugu kontrol edilir
    }

    @Then("Sepete ekle butonuna tiklanir")
    public void sepete_ekle_butonuna_tiklanir() {

        ReusableMethods.waitFor(2);
        ReusableMethods.clickWithJS(a101Page.siyahCorapSepeteEkleButonu);

    }

    @Then("Sepeti Goruntule butonuna tiklanir")
    public void sepeti_goruntule_butonuna_tiklanir() {

        ReusableMethods.clickWithJS( a101Page.sepetiGoruntuleButonu);

    }

    @Then("Sepeti Onayla butonuna tiklanir")
    public void sepeti_onayla_butonuna_tiklanir() {

        ReusableMethods.clickWithJS( a101Page.sepetiOnaylaButonu);

    }

    @Then("Uye olmadan devam et butonuna tiklanir")
    public void uye_olmadan_devam_et_butonuna_tiklanir() {

        ReusableMethods.clickWithJS( a101Page.uyeOlmadanDevamEtButonu);

    }

    @Then("Mail ekrani gelir")
    public void mail_ekrani_gelir() {

        ReusableMethods.waitFor(1);
        a101Page.mailTextBox.sendKeys(ConfigReader.getProperty("mail"));
        a101Page.mailTextBoxDevamEtButonu.click();
    }

    @Then("Sonrasinda adres ekrani gelir")
    public void sonrasinda_adres_ekrani_gelir() {
        ReusableMethods.waitFor(1);
        a101Page.adresOlusturButonu.click();
        a101Page.adresOlusturAdresBasligiBox.sendKeys("Ev Adresi");
        a101Page.adresOlusturIsimBox.sendKeys("isim");
        a101Page.adresOlusturSoyadBox.sendKeys("soyad");
        a101Page.adresOlusturCepTelBox.sendKeys("5679999999");
        Select select = new Select(a101Page.adresOlusturIlDropDown);
        select.selectByVisibleText("İSTANBUL");
        ReusableMethods.waitFor(1);

        select = new Select(a101Page.adresOlusturIlceDropDown);
        select.selectByVisibleText("ŞİŞLİ");

        ReusableMethods.waitFor(1);
        select = new Select(a101Page.adresOlusturMahalleDropDown);
        for (int retry = 0; retry < 5; retry++) {
            try {
                select.selectByVisibleText("FULYA MAH");
            } catch (StaleElementReferenceException ex) {
                ex.toString();
            }
        }
        //Mahalle Drop Down'ında StaleElementReferenceException verdigi icin bu sekilde Handle edildi

        Faker faker = new Faker();

        a101Page.adresOlusturAdresBox.sendKeys(faker.address().fullAddress());


    }

    @Then("Adres olustur dedikten sonra odeme ekrani gelir")
    public void adres_olustur_dedikten_sonra_odeme_ekrani_gelir() {
        ReusableMethods.waitFor(1);

        ReusableMethods.clickWithJS(a101Page.adresOlusturKaydetButonu);
        ReusableMethods.waitFor(2);
        ReusableMethods.clickWithJS(a101Page.kargoKaydetVeDevamButonu);
    }

    @Then("Siparisi tamamla butonuna tiklayarak, odeme ekranina gidildigi ,dogru ekrana yonlendiklerini kontrol eder")
    public void siparisi_tamamla_butonuna_tiklayarak_odeme_ekranina_gidildigi_dogru_ekrana_yonlendiklerini_kontrol_eder() {

        ReusableMethods.waitFor(1);
        Faker faker = new Faker();
        int krediKartHaneSayisi = 16;

        //faker classini kullanarak kart bilgilerini doldurduk.
        a101Page.kartBilgileriAdSoyad.sendKeys(faker.name().fullName());
        a101Page.kartBilgileriKartNo.sendKeys(faker.number().digits(krediKartHaneSayisi));

        //Odeme ekranında olundugu kontrol edilir
        String expectedText = "Kart ile ödeme";
        String actualText = a101Page.kartIleOdemeText.getText();

        Assert.assertEquals(actualText, expectedText);
    }


    @And("Kullanici sayfayi kapatir")
    public void kullaniciSayfayiKapatir() {
        Driver.getDriver().quit();
    }
}
