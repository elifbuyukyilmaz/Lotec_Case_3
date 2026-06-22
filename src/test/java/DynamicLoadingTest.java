import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Dynamic Loading Kontrolü")
 class DynamicLoadingTest extends BaseTest{
    @Override
    protected String path() {
        return "/dynamic_loading/2";
    }
    @Test
    @DisplayName("'Hello World!'mesajı görüntülendi.")

    void dynamicLoadingHiddenElement() {

        driver.findElement(By.xpath("//button[text()='Start']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading")));

        WebElement finish = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish")));


        assertEquals("Hello World!", finish.getText(),
                "Metin hatalı.");
    }

}