package pages.citilink;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс {@code SearchInCatalog} предоставляет методы для поиска и взаимодействия с разделами каталога
 * на веб-сайте Citilink.
 *
 * @author sergeyTrbv
 */
public class SearchInCatalog {

    /**
     * Объект String с шаблоном начала XPath для кнопки главного меню каталога.
     */
    private static final String HEAD_CATALOG_MENU_BUTTON = "//a[@data-meta-name='DesktopHeaderFixed__catalog-menu']" +
            "//span[contains(text(), '";

    /**
     * Объект String с шаблоном XPath для кнопки раздела товара в выпадающем меню каталога.
     */
    private static final String HEAD_SECTION_BUTTON = "//div[@class='PopupScrollContainer']" +
            "//span[@color='None' and text()='";

    /**
     * Объект String с шаблоном XPath кнопки подраздела из выбранного раздела ранее.
     */
    private static final String HEAD_CHAPTER_BUTTON = "//div[@class='rcs-inner-container']//span[text()='";

    /**
     * Объект String с шаблоном XPath заголовка страницы с товаром.
     */
    private static final String HEAD_CHECK_PAGE_NAME = "//div[@data-meta-name='SubcategoryPageTitle']" +
            "//h1[@color='Main' and text()='";

    /**
     * Объект String с закрывающим шаблоном XPath для закрытия выражения поиска элемента в меню каталога.
     */
    private static final String CATALOG_MENU_TAIL = "')]";

    /**
     * Объект String с закрывающим шаблоном XPath для закрытия выражения поиска элемента.
     */
    private static final String TAIL = "']";


    /**
     * Объект типа {@code WebDriver} для взаимодействия с браузером.
     */
    private WebDriver driver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    private WebDriverWait wait;


    /**
     * Конструктор класса, инициализирующий WebDriver и WebDriverWait.
     *
     * @param currentDriver экземпляр WebDriver для управления браузером.
     */
    public SearchInCatalog(WebDriver currentDriver) {
        this.driver = currentDriver;
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * Метод {@code searchChapterInCatalog} для поиска раздела в каталоге
     * и проверки перехода на соответствующую страницу.
     *
     * @param textMenu текст кнопки главного меню каталога.
     * @param section  раздел "Ноутбуки и компьютеры".
     * @param chapter  раздел "Ноутбуки".
     */
    @Step("Шаги по поиску раздела и проверка раздела {chapter}")
    public void searchChapterInCatalog(String textMenu, String section, String chapter) {
        openCatalog(textMenu);
        hoverOverSection(section);
        selectChapter(chapter);
        verifyTransitionToPage(chapter);
    }

    /**
     * Метод {@code openCatalog} для открытия каталога.
     *
     * @param textMenu текст кнопки главного меню каталога.
     */
    @Step("Открытие каталога")
    private void openCatalog(String textMenu) {
        WebElement catalogProductButton = wait.until(ExpectedConditions.
                elementToBeClickable(By.xpath(HEAD_CATALOG_MENU_BUTTON +
                        textMenu + CATALOG_MENU_TAIL)));
        catalogProductButton.click();
    }

    /**
     * Метод {@code hoverOverSection} наводит курсор на переданный раздел.
     *
     * @param section раздел продукта.
     */
    @Step("Наведение курсора на {section}")
    private void hoverOverSection(String section) {
        WebElement sectionButton = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(HEAD_SECTION_BUTTON + section + TAIL)));
        Actions actions = new Actions(driver);
        actions.moveToElement(sectionButton).perform();
    }

    /**
     * Метод {@code selectChapter} выбирает подраздел в разделе товара.
     *
     * @param chapter заголовок товара.
     */
    @Step("Выбор {chapter}")
    private void selectChapter(String chapter) {
        WebElement productChapterButton = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(HEAD_CHAPTER_BUTTON + chapter + TAIL)));
        productChapterButton.click();
    }

    /**
     * Метод {@code verifyTransitionToPage} для проверки перехода на страницу тестируемого товара.
     *
     * @param chapter заголовок товара.
     */
    @Step("Проверка перехода на страницу {chapter}")
    private void verifyTransitionToPage(String chapter) {
        WebElement actualChapter = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(HEAD_CHECK_PAGE_NAME + chapter + TAIL)));
        String actualText = actualChapter.getText();
        Assertions.assertEquals(chapter, actualText, "URL страницы не соответствует ожидаемому");
    }
}
