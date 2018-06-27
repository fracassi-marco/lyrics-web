package maf

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class HomePage(val browser: WebDriver, val port: Int) {

    fun open() {
        browser.get("http://localhost:$port/")
    }

    fun searchFor(author: String, title: String) {
        browser.findElement(By.id("input-author")).sendKeys(author)
        browser.findElement(By.id("input-title")).sendKeys(title)
        browser.findElement(By.id("btn-search")).click()
    }
}
