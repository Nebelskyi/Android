package com.uia.example.my;

import android.graphics.Point;
import android.webkit.WebView;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
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
        
		UiObject progressIcon = new UiObject(new UiSelector().resourceIdMatches("org.mozilla.firefox:id/progress"));
		progressIcon.waitUntilGone(5000);
		
		
        Point p1s = new Point(200,300);
        Point p1e = new Point(150,450);

        Point p2s = new Point(300,300);
        Point p2e = new Point(500,250);
        
        webView.performTwoPointerGesture(p1s, p2s , p1e, p2e, 10);
        
        UiObject webPageView = new UiObject(new UiSelector().className("android.view.View"));
        System.out.println("is scrollable " + webPageView.isScrollable());
        
        webPageView.swipeUp(25);
        webPageView.swipeDown(8);
        webPageView.swipeRight(2);
        webPageView.swipeLeft(18);
        
//        UiScrollable webPage = new UiScrollable(new UiSelector().scrollable(true));	
//        webPage.scrollForward();
//        webPage.scrollForward();
        
	}
}