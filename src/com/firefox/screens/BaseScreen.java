package com.firefox.screens;

import android.util.Log;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class BaseScreen extends UiAutomatorTestCase {

	UiObject openTabsButton = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/tabs"));
	UiObject arrowLeft = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/back"));
	UiObject arrowRight = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/forward"));
	UiObject firefoxIcon = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/favicon"));
	UiObject urlNotEnabled = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/url_bar_title"));
	UiObject urlEnabled = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/url_edit_text"));
	UiObject goUrlIcon = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/go"));
	UiObject reloadIcon = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/reload"));
	UiObject settingsIcon = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/menu_icon"));
	
	UiObject iconTabs = new UiObject(new UiSelector().description("Tabs"));
	UiObject iconPrivate = new UiObject(new UiSelector().description("Private"));
	UiObject iconSync = new UiObject(new UiSelector().description("Synced"));
	UiObject addTabIcon = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/add_tab"));
	
	UiObject progressIcon = new UiObject(new UiSelector().resourceIdMatches("org.mozilla.firefox:id/progress"));


	public void openAllTabsMenu() throws UiObjectNotFoundException{
		openTabsButton.click();
		Log("Log:"," Clicked 'openTabsButton' icon");
	}

	public void clickAddTabIcon() throws UiObjectNotFoundException{
		addTabIcon.click();
		Log("Log:"," Clicked 'addTabIcon' icon");
	}
	
	public void clickAllTabsIcon() throws UiObjectNotFoundException{
		iconTabs.click();
		Log("Log:"," Clicked 'AllTabs' icon");
	}
	
	public void clickPrivateIcon() throws UiObjectNotFoundException{
		iconPrivate.click();
		Log("Log:"," Clicked 'private' icon");
	}
	
	public void clickSyncIcon() throws UiObjectNotFoundException{
		iconSync.click();
		Log("Log:"," Clicked 'Sync' icon");
	}

	public void clickGoUrlArrowIcon() throws UiObjectNotFoundException{
		goUrlIcon.click();
		Log("Log:"," Clicked 'goUrlIcon' icon");
	}

	public void enableUrlField() throws UiObjectNotFoundException{
		assertTrue("Unable to detect url field", urlNotEnabled.exists());
		urlNotEnabled.click();
	}
	
	public void openUrl(String url) throws UiObjectNotFoundException{
		if (urlNotEnabled.exists())	urlNotEnabled.click();
		urlEnabled.setText(url);
		getUiDevice().pressEnter();
		Log("Log:"," opening http://'" + url + "' url");
		waitForPageToLoad();
	}

	public void waitForPageToLoad() {
		try{
		progressIcon.waitUntilGone(10000);
		}
		catch (Exception e) {
			Log("Timeout error:", " page is not loaded in 10 seconds.");
			Log("Error message:", e.getMessage().toString());
		}
	}

	public void clickAtMiddleOfTheScreen(){
		getUiDevice().click(400, 600);
		Log("Log:", "Clicking at the middle of the screen");
	}
	
	public void keysKeyboard(){
		
		getUiDevice().pressKeyCode(KeyEvent.KEYCODE_4);
		getUiDevice().pressKeyCode(KeyEvent.KEYCODE_P);
		getUiDevice().pressKeyCode(KeyEvent.KEYCODE_T);
		getUiDevice().pressKeyCode(KeyEvent.KEYCODE_N);
		getUiDevice().pressKeyCode(KeyEvent.KEYCODE_SPACE);
		getUiDevice().pressKeyCode(KeyEvent.KEYCODE_P);
		getUiDevice().pressKeyCode(KeyEvent.KEYCODE_N);
		getUiDevice().pressKeyCode(KeyEvent.KEYCODE_H);
		
	}
	
	public void Log(String tag, String text){
		Log.i(tag, text);
		System.out.println(tag + " " + text);
	}

}
