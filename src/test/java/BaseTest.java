import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BaseTest {

    protected static final Duration TIMEOUT = Duration.ofSeconds(10);
    protected static final String BASE_URL = "https://the-internet.herokuapp.com";

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, TIMEOUT);
        driver.get(BASE_URL + path());
    }

    protected abstract String path();

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}