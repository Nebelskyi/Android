package com.firefox.test;

import android.graphics.Point;
import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
// UI AUTOMATOR
public class FirefoxMainTests extends UiAutomatorTestCase {

	
	public void AddNewWebPageZoomingAndSwiping() throws UiObjectNotFoundException {
		
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
        
		waitForPageToLoad();
		
		webView.pinchOut(100, 150);
		webView.pinchIn(100, 100);
		webView.pinchOut(15, 150);
		
//		scaleUp(webView);
		
        UiObject webPageView = new UiObject(new UiSelector().className("android.view.View"));
        System.out.println("is scrollable " + webPageView.isScrollable());
        
        webPageView.swipeUp(125);
        webPageView.swipeDown(118);
        webPageView.swipeRight(112);
        webPageView.swipeLeft(118);
        
        exitToMainWindow();
//        UiScrollable webPage = new UiScrollable(new UiSelector().scrollable(true));	
//        webPage.scrollForward();
//        webPage.scrollForward();
        
	}

	public void VerifyLastVisitedWebSiteInHistory() throws UiObjectNotFoundException {
	
	Log.i("testAddBookmark", "Start testAddBookmark Test");
	getUiDevice().pressHome();
	
	UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
	allAppsButton.clickAndWaitForNewWindow();
	
    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
    appViews.setAsHorizontalList();
    
    UiObject firefoxApp = appViews.getChildByText(new UiSelector().className("android.widget.TextView"), "Firefox");
    firefoxApp.clickAndWaitForNewWindow();
	
	UiObject addTab = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/url_bar_title"));
	addTab.click();

	UiObject urlEditText = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/url_edit_text"));
	urlEditText.setText("http://smarttech.com/");
	
	getUiDevice().pressEnter();
	waitForPageToLoad();
	
	getUiDevice().pressBack();
	
	UiObject historyBar = new UiObject(new UiSelector().text("HISTORY"));
	assertTrue("HISTORY text is not exists", historyBar.exists());
	historyBar.click();

	UiObject lastWebSite = new UiObject(new UiSelector().textContains("SMART Interactive Solutions for Education")); 
	assertTrue("SMART is not in the history of visited web sites", lastWebSite.exists());
	
	exitToMainWindow();
	Log.i("testAddBookmark", "End testAddBookmark Test");
	
	}

	public void testSwipeBetweenMainTabs() throws UiObjectNotFoundException {
		
		Log("testAddBookmark", "Start testAddBookmark Test");
		getUiDevice().pressHome();
		
		UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
		allAppsButton.clickAndWaitForNewWindow();
		Log("Log:","Opened AllAps screen");
		
	    UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
	    appViews.setAsHorizontalList();
	    
	    UiObject firefoxApp = appViews.getChildByText(new UiSelector().className("android.widget.TextView"), "Firefox");
	    firefoxApp.clickAndWaitForNewWindow();
	    Log("Log:", "App is opened");
	    
		UiScrollable scrollableView = new UiScrollable(new UiSelector().resourceId("org.mozilla.firefox:id/home_pager"));
		scrollableView.setAsHorizontalList();
		
		scrollableView.swipeLeft(50);
		
		UiObject tabBookmarkFirefoxSupport = new UiObject(new UiSelector().textContains("Firefox: Support"));
		assertTrue("Firefox:Support bookmark is not found", tabBookmarkFirefoxSupport.exists());
		Log("Log:", "Bookmark is found");
		
		scrollableView.swipeLeft(100);
		
		UiObject hintReadingList = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/home_empty_hint"));
		assertTrue("READING LIST is not found", hintReadingList.exists());
		Log("Log:", "READING LIST is found");

		scrollableView.swipeLeft(25);
		
		UiObject buttonRecents = new UiObject(new UiSelector().className("android.widget.ImageButton").instance(0));
		assertTrue("Recents Buttons is not found", (buttonRecents.exists() && buttonRecents.isEnabled()));
		Log("Log:", "Recents Button LIST is found");
		
		UiObject buttonRecentsTabs = new UiObject(new UiSelector().className("android.widget.ImageButton").instance(1));
		buttonRecentsTabs.click();
		assertTrue("RecentsTabs Buttons is not found", (buttonRecentsTabs.exists() && buttonRecentsTabs.isEnabled()));
		Log("Log:", "CLICKING recents tabs button");
		
		UiObject imageEmptyRecentsTabs = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/home_empty_image"));
		assertTrue("RecentsTabs Buttons is not found", imageEmptyRecentsTabs.exists() );
		
		exitToMainWindow();
		
	}
	
	public void exitToMainWindow() {
		
		UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
		
		// Press back button while new message button doesn't exist
		while(!allAppsButton.exists()) {
			getUiDevice().pressBack();
			sleep(500);
		}
	
	}
	
	private void waitForPageToLoad() {
	
		UiObject progressIcon = new UiObject(new UiSelector().resourceIdMatches("org.mozilla.firefox:id/progress"));
		progressIcon.waitUntilGone(10000);
		Log.i("Timeout", "page is not loaded in 10 seconds");
	}

	private void Log(String tag, String text){
		
		Log.i(tag, text);
		System.out.println(tag + " " + text);
		
	}
	
	@SuppressWarnings("unused")
	private void scaleUp(UiObject webView){
		
        Point p1s = new Point(200,300);
        Point p1e = new Point(150,450);

        Point p2s = new Point(300,300);
        Point p2e = new Point(500,250);
        
        webView.performTwoPointerGesture(p1s, p2s , p1e, p2e, 10);
		
	}
}
