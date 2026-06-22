import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("File Upload Doğrulama")
class FileUploadTest extends BaseTest {

    @Override
    protected String path() {
        return "/upload";
    }

    private static final Path UPLOAD_FILE = Paths.get("src/test/resources/Upload/example.txt");
    private static final By FILE_INPUT = By.id("file-upload");
    private static final By SUBMIT_BUTTON = By.id("file-submit");
    private static final By UPLOADED_FILES = By.id("uploaded-files");
    private static final By RESULT_HEADER = By.tagName("h3");

    @DisplayName("Uploaded dosya adı doğrulama.")
    @Test
    void uploadsFileAndShowsCorrectName() {
        String expectedFileName = UPLOAD_FILE.getFileName().toString();

        uploadFile(UPLOAD_FILE);

        String resultHeader = waitForText(RESULT_HEADER);
        String displayedName = waitForText(UPLOADED_FILES);

        assertAll("Dosya upload sonucu : ",
                () -> assertEquals("File Uploaded!", resultHeader,
                        "File Uploaded! başlığı hatalı."),
                () -> assertEquals(expectedFileName, displayedName,
                        "Görüntülenen dosya adı ile yüklenen dosya adı farklı."),
                () -> assertTrue(driver.getCurrentUrl().endsWith("/upload"),
                        "Upload sayfası url hatalı.")
        );
    }

    private void uploadFile(Path file) {
        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(FILE_INPUT));
        fileInput.sendKeys(file.toAbsolutePath().toString());
        driver.findElement(SUBMIT_BUTTON).click();
    }

    private String waitForText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getText()
                .trim();
    }
}