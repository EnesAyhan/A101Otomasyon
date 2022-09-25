package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class A101Page {

    public A101Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//*[text()='Kabul Et']")
    public WebElement cookiesAccept;

    @FindBy(xpath = "//a[@title='GİYİM & AKSESUAR']")
    public WebElement giyimAksesuarButonu;

    @FindBy(xpath = "//a[@data-value='1565']")
    public WebElement kadinIcGiyimButonu;

    @FindBy(xpath = "//a[@data-value='1567']")
    public WebElement dizAltiCorapButonu;

    @FindBy(xpath = "//a[@title='Penti Kadın 50 Denye Pantolon Çorabı Siyah']")
    public WebElement siyahCorapButonu;

    @FindBy(xpath = "//div[@class='selected-variant-text']")
    public WebElement corapRenkKontrol;

    @FindBy(xpath = "//i[@class='icon-sepetekle']")
    public WebElement siyahCorapSepeteEkleButonu;

    @FindBy(xpath = "(//*[text()='Sepeti Görüntüle'])[2]")
    public WebElement sepetiGoruntuleButonu;

    @FindBy(xpath = "(//a[@title='Sepeti Onayla'])[2]")
    public WebElement sepetiOnaylaButonu;

    @FindBy(xpath = "//a[@title='ÜYE OLMADAN DEVAM ET']")
    public WebElement uyeOlmadanDevamEtButonu;

    @FindBy(xpath = "//input[@name='user_email']")
    public WebElement mailTextBox;

    @FindBy(xpath = "//button[@class='button block green']")
    public WebElement mailTextBoxDevamEtButonu;

    @FindBy(xpath = "//a[@class='new-address js-new-address']")
    public WebElement adresOlusturButonu;

    @FindBy(xpath = "//input[@name='title']")
    public WebElement adresOlusturAdresBasligiBox;

    @FindBy(xpath = "//input[@name='first_name']")
    public WebElement adresOlusturIsimBox;

    @FindBy(xpath = "//input[@name='last_name']")
    public WebElement adresOlusturSoyadBox;

    @FindBy(xpath = "//input[@name='phone_number']")
    public WebElement adresOlusturCepTelBox;

    @FindBy(xpath = "//select[@name='city']")
    public WebElement adresOlusturIlDropDown;

    @FindBy(xpath = "//select[@name='township']")
    public WebElement adresOlusturIlceDropDown;

    @FindBy(xpath = "//select[@class='js-district']")
    public WebElement adresOlusturMahalleDropDown;

    @FindBy(xpath = "//textarea[@name='line']")
    public WebElement adresOlusturAdresBox;

    @FindBy(xpath = "//button[@class='button green js-set-country js-prevent-emoji']")
    public WebElement adresOlusturKaydetButonu;

    @FindBy(xpath = "//button[@class='button block green js-proceed-button']")
    public WebElement kargoKaydetVeDevamButonu;

    @FindBy(xpath = "(//input[@name='name'])[2]")
    public WebElement kartBilgileriAdSoyad;

    @FindBy(xpath = "//input[@class='js-masterpassbin-payment-card']")
    public WebElement kartBilgileriKartNo;

    @FindBy(xpath = "(//div[@data-type='masterpass'])[1]")
    public WebElement kartIleOdemeText;


}
