package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class shopCartSteps {
    private WebDriver driver;

    By usernameInputLocator = By.id("user-name");
    By passwordInputLocator = By.id("password");
    By loginButtonLocator = By.id("login-button");
    By shoppingCartLogoLocator = By.xpath("//a[@class='shopping_cart_link']");

    By titlePageLocator = By.className("title");

    By removeButtonLocator = By.className("cart_button");
    String nombreProductoAgregado = "";

    @Given("El usuario ha iniciado sesion correctamente")
    public void el_usuario_ha_iniciado_sesion_correctamente() {
        System.setProperty("webdriver.chrome.driver", "./src/test/java/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(usernameInputLocator).sendKeys("standard_user");
        driver.findElement(passwordInputLocator).sendKeys("secret_sauce");
        driver.findElement(loginButtonLocator).click();
    }
    @And("El usuario se encuentra en la pantalla de Inventario")
    public void el_usuario_se_encuentra_en_la_pantalla_de_inventario() {

        String urlActual = driver.getCurrentUrl();

        assertTrue("No se encuentra en la pantalla de Inventario", urlActual.equals("https://www.saucedemo.com/inventory.html"));

    }
    @When("El usuario da click en el boton ADD TO CART de un producto")
    public void el_usuario_da_click_en_el_boton_add_to_cart_de_un_producto() {
        List<WebElement> btnInventory = driver.findElements(By.className("btn_inventory"));
        List<WebElement> nombresProducto = driver.findElements(By.className("inventory_item_name"));

        btnInventory.get(0).click();

        nombreProductoAgregado = nombresProducto.get(0).getText();

    }
    @Then("El usuario visualiza que el producto se añadio al carrito de compras")
    public void el_usuario_visualiza_que_el_producto_se_añadio_al_carrito_de_compras() {
        driver.findElement(shoppingCartLogoLocator).click();

        WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ewait.until(ExpectedConditions.visibilityOf(driver.findElement(titlePageLocator)));

        assertTrue("El carrito de compras ya se encuentra vacío o tiene más de 1 producto",driver.findElement(shoppingCartLogoLocator).getText().equals("1"));

    }

    @And("El usuario añade un producto al carrito de compras")
    public void el_usuario_añade_un_producto_al_carrito_de_compras() {
        List<WebElement> btnInventory = driver.findElements(By.className("btn_inventory"));
        List<WebElement> nombresProducto = driver.findElements(By.className("inventory_item_name"));

        btnInventory.get(0).click();

        nombreProductoAgregado = nombresProducto.get(0).getText();
    }

    @When("El usuario ingresa a la pantalla del Carrito de Compras")
    public void el_usuario_ingresa_a_la_pantalla_del_carrito_de_compras() {
        driver.findElement(shoppingCartLogoLocator).click();

        String urlActual = driver.getCurrentUrl();

        assertTrue("No se encuentra en la pantalla del Carrito de Compras", urlActual.equals("https://www.saucedemo.com/cart.html"));

    }

    @And("El usuario remueve el producto del carrito de compras")
    public void el_usuario_remueve_el_producto_del_carrito_de_compras() {
        driver.findElement(removeButtonLocator).click();

    }

    @Then("El usuario visualiza que el producto se removio del carrito de compras")
    public void el_usuario_visualiza_que_el_producto_se_removio_del_carrito_de_compras() {
        assertTrue("El carrito de compras no está vacío", driver.findElement(shoppingCartLogoLocator).getText().equals(""));
    }

}
