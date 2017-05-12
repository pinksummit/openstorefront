/*
 * Copyright 2017 Space Dynamics Laboratory - Utah State University Research Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.usu.sdl.openstorefront.ui.test.admin;

import edu.usu.sdl.openstorefront.ui.test.BrowserTestBase;
import edu.usu.sdl.openstorefront.ui.test.security.AccountSignupActivateTest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ccummings
 */
public class AdminHighlightTest extends AdminTestBase {

    private static final Logger LOG = Logger.getLogger(BrowserTestBase.class.getName());

    @BeforeClass
    public static void setupTest() {
        login();
    }

    @Test
    public void adminHighlightTest() {

        for (WebDriver driver : webDriverUtil.getDrivers()) {

            createHighlight(driver);
            editHighlight(driver);
            deleteHighlight(driver);
            
        }
    }

    public void createHighlight(WebDriver driver) {

        driver.get(webDriverUtil.getPage("AdminTool.action?load=Highlights"));

        sleep(2000);

        // Click add button and fill out form
        driver.findElement(By.xpath("//*[@id='highlightGrid-tools-add']")).click();
        driver.findElement(By.xpath("//*[@id='highlightEntryForm-Title-inputEl']")).sendKeys("TestHighlight1");

        ((JavascriptExecutor) driver).executeScript("tinyMCE.activeEditor.setContent('Test Description 1')");

        sleep(2000);

        driver.findElement(By.xpath("//*[@id=\'highlightEntryForm-Type-trigger-picker\']")).click();
        driver.findElement(By.xpath("//li[contains(.,'Component')]")).click();

        driver.findElement(By.xpath("//*[@id='button-1099']")).click();

        sleep(2000);
    
    }
    
    public void editHighlight(WebDriver driver) {
        
        // locate highlight in table and edit
        if (tableClickRowCol("tableview-1087", "TestHighlight1", driver)) {

            sleep(1000);
            driver.findElement(By.xpath("//*[@id='highlightGrid-tools-edit']")).click();
            sleep(2000);
            
            WebElement iframeMsg = driver.findElement(By.xpath("//*[@id='tinymce_textarea-1097-inputEl_ifr']"));
            driver.switchTo().frame(iframeMsg);
            
            WebElement body = driver.findElement(By.cssSelector("body"));
            String bodyText = body.getText() + "- Edited";
            
            ((JavascriptExecutor)driver).executeScript("arguments[0].innerHTML = '" + bodyText + "'", body);
            sleep(2000);
            
            driver.switchTo().defaultContent();
            
            WebElement linkContainer = driver.findElement(By.xpath("//*[@id='mceu_17']/button"));
            linkContainer.click();
            sleep(2000);
            
            // insert saved search link into description
            WebElement table = driver.findElement(By.className("x-grid-view"));
            WebElement element = table.findElement(By.xpath("//td[contains(.,'Find my test entry please')]"));
            element.click();
            sleep(1000);
            driver.findElement(By.id("insertLinkBtn")).click();
            
            // click save
            sleep(1000);
            driver.findElement(By.xpath("//*[@id='button-1099']")).click();
            
            sleep(2000);
        }
    }
    
    public void deleteHighlight(WebDriver driver) {
        
        if (tableClickRowCol("tableview-1087", "TestHighlight1", driver)) {
            
            driver.findElement(By.xpath("//*[@id='highlightGrid-tools-delete']")).click();
            sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"button-1037\"]")).click();
            sleep(3000);
        }
        
        // refresh table
        driver.findElement(By.xpath("//*[@id='button-1089']")).click();
        sleep(2000);
    }
}
