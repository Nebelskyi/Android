package com.uia.example.my;

import android.graphics.Point;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LaunchSettings extends UiAutomatorTestCase {

	public void testDemo() throws UiObjectNotFoundException {
		
		getUiDevice().pressHome();
		
		UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
		allAppsButton.clickAndWaitForNewWindow();

		UiObject firefoxApp = new UiObject(new UiSelector().text("Firefox"));
		firefoxApp.click();

		UiObject addTab = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/tabs"));
		addTab.click();

		UiObject addTabIcon = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/add_tab"));
		addTabIcon.click();
		
		UiObject urlField = new UiObject(new UiSelector().textContains("Enter Search"));
		assertTrue("Unable to detect url field", urlField.exists());
		
		urlField.click();
		
		UiObject tipInUrl = new UiObject(new UiSelector().resourceIdMatches("org.mozilla.firefox:id/url_edit_text"));
		assertTrue("Tips In URL is not visible", tipInUrl.isEnabled());
		
		tipInUrl.setText("test");
		
		UiObject goIcon = new UiObject(new UiSelector().resourceIdMatches("org.mozilla.firefox:id/go"));
		goIcon.click();
		
		UiObject webView = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/layer_view"));
        
		webView.waitForExists(5000);
		
        Point p1s = new Point(200,300);
        Point p1e = new Point(150,300);

        Point p2s = new Point(300,300);
        Point p2e = new Point(500,600);
        
        webView.performTwoPointerGesture(p1s, p1e, p2s, p2e, 10);
	}
}