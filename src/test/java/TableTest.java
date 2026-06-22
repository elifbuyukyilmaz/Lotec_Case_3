import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tablo Verilerini Doğrulama")
class TableTest extends BaseTest {

    @Override
    protected String path() {
        return "/tables";
    }

    static final String TARGET_EMAIL = "jdoe@hotmail.com";
    @DisplayName("Kullanıcı doğrulama.")

    @Test
    void emailRowShouldBeVerified() {
        WebElement table = driver.findElement(By.id("table1"));

        WebElement foundRow = findRowByEmail(table, TARGET_EMAIL);
        assertNotNull(foundRow, "Kullanıcının mail adresi bulunamadı: " + TARGET_EMAIL);

        List<WebElement> cols = foundRow.findElements(By.tagName("td"));

        assertEquals(TARGET_EMAIL, cols.get(2).getText(), "Kullanıcı bilgileri hatalı.");
    }


    WebElement findRowByEmail(WebElement table, String email) {
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                if (col.getText().equalsIgnoreCase(email)) {
                    System.out.println("row: " +row.getText());
                    return row;
                }
            }
        }
        return null;
    }
}
