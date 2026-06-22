import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


@DisplayName("Başarılı Login ve Logout Akışı")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginTest extends BaseTest {

    @Override
    protected String path() {
        return "/login";
    }

    private static final String VALID_USERNAME = "tomsmith";
    private static final String VALID_PASSWORD = "SuperSecretPassword!";
    private static final String LOGIN_SUCCESS_MSG = "You logged into a secure area";
    private static final String LOGOUT_SUCCESS_MSG = "You logged out of the secure area!";

    private static final By USERNAME_INPUT = By.id("username");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By SUBMIT_BUTTON = By.cssSelector("button[type='submit']");
    private static final By LOGOUT_BUTTON = By.cssSelector("a[href='/logout']");
    private static final By FLASH_MESSAGE = By.id("flash");

    private void login(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(SUBMIT_BUTTON).click();
    }

    private String getFlashMessage() {
        WebElement flash = wait.until(
                ExpectedConditions.visibilityOfElementLocated(FLASH_MESSAGE));
        return flash.getText();
    }

    @Test
    @Order(1)
    @DisplayName("Geçerli kullanıcı bilgileri ile login başarılı.")
    void shouldLoginWithValidCredentials() {
        login(VALID_USERNAME, VALID_PASSWORD);

        Assertions.assertTrue(
                getFlashMessage().contains(LOGIN_SUCCESS_MSG),
                "Login başarısız,mesaj görüntülenmiyor.");
        Assertions.assertTrue(
                driver.findElement(LOGOUT_BUTTON).isDisplayed(),
                "Logout butonu görüntülenmiyor.");
    }

    @Test
    @Order(2)
    @DisplayName("Login sonrası logout yapılabilmeli ve login sayfasına dönülmeli.")
    void shouldLogoutAfterLogin() {
        login(VALID_USERNAME, VALID_PASSWORD);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON)).click();

        Assertions.assertTrue(
                getFlashMessage().contains(LOGOUT_SUCCESS_MSG),
                "Logout mesajı görüntülenmiyor.");
        Assertions.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BUTTON))
                        .isDisplayed(),
                "Logout sonrası login sayfasına dönülmedi.");
    }
}