package es.udc.tfg_es.clubtriatlon.test.util;
/* ClubTriatlon: a web app to management of administrative work of a triathlon club
Copyright (C) 2015 Alejandro Mikitinskis

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software Foundation,
Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA

Contact here: alejandro.mikitinskis@udc.es */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class SeleniumMethods {
	
	//Selenium needs App running (mvn jetty:run) and uses the real database, 
	//not test database

	public static final WebDriver auntenticateAdmin() {
		return auntenticate("admin1@triatlon.com", "admin");
	}
	
	public static final WebDriver auntenticateUser() {
		return auntenticate("user3C@gmail.com", "user");
	}
	
	private static final WebDriver auntenticate(String email, String password) {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:9090/triatlon/");
        
		driver.findElement(By.linkText("Autenticarse")).click();
		driver.getCurrentUrl(); //Update the Url
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("loginForm")).findElement(By.id("loginButton")).click();

		return driver;
	}

	public static final void logout(WebDriver driver) {
		driver.findElement(By.id("logout")).click();
	}

}
