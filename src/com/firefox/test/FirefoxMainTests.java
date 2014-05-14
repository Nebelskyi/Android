package com.firefox.test;

import android.graphics.Point;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.firefox.screens.BaseScreen;
import com.firefox.screens.MainCentralScreen;
// UI AUTOMATOR
public class FirefoxMainTests extends BaseScreen {

	BaseScreen baseScreen = new BaseScreen();
	MainCentralScreen mainScreen = new MainCentralScreen();

	protected void setUp() throws Exception {
        System.out.println("Global setUp ");
        closeTestApplication();
        openTestApplication();
//        getUiDevice().pressHome();
    }

	protected void tearDown() throws Exception {
        System.out.println("Global tearDown ");
        closeTestApplication();
    }
    
	public void AddNewWebPageZoomingAndSwiping() throws UiObjectNotFoundException {
		Log("Log:", "Start testAddNewWebPageZoomingAndSwiping Test");
		
		openAllTabsMenu();
		
		clickAddTabIcon();
		
		enableUrlField();
		
		UiObject tipInUrl = new UiObject(new UiSelector().resourceIdMatches("org.mozilla.firefox:id/url_edit_text"));
		assertTrue("Tips In URL is not visible", (tipInUrl.isEnabled() && tipInUrl.toString().equals("about:home")));
		
		openUrl("test");
		
		UiObject webView = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/layer_view"));
        
		waitForPageToLoad();
		
		webView.pinchOut(100, 150);
		webView.pinchIn(100, 100);
		webView.pinchOut(15, 150);
		
        UiObject webPageView = new UiObject(new UiSelector().className("android.view.View"));
        System.out.println("is scrollable " + webPageView.isScrollable());
        
        webPageView.swipeUp(125);
        webPageView.swipeDown(118);
        webPageView.swipeRight(112);
        webPageView.swipeLeft(118);
        
        Log("Log:", "Start testAddNewWebPageZoomingAndSwiping Test");
	}

	public void VerifyLastVisitedWebSiteInHistory() throws UiObjectNotFoundException {
	
	Log("Log:", "Start testVerifyLastVisitedWebSiteInHistory Test");
	
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
	
	Log("Log:", "End testAddBookmark Test");
	
	}

	public void testSwipeBetweenMainTabs() throws UiObjectNotFoundException {
		
		mainScreen.clickTopSitesTab();
		
		mainScreen.scrollToRightScreen();
		
		mainScreen.verifyBookamarkExists("Firefox: Support");
		
		mainScreen.scrollToRightScreen();
		
		mainScreen.verifyReadingListEmptyTipExists();
		
		mainScreen.scrollToRightScreen();
		
		mainScreen.clickRecentsButton();
		
		mainScreen.clickRecentsTabsButton();

		assertTrue("RecentsTabs Buttons is not found", mainScreen.isVisibleEmptyRecentTabsImage() );

		mainScreen.clickRecentsButton();
		
		assertTrue("", !mainScreen.isVisibleEmptyRecentTabsImage());
		Log("Log:", "Recents Button LIST is found but it should not");
		
	}
	
	public void UrlHint() throws UiObjectNotFoundException {
		
		UiObject addTab = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/url_bar_title"));
		addTab.click();

		UiObject urlEditText = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/url_edit_text"));
		urlEditText.setText("sma");
	    
		UiObject urlTip = new UiObject(new UiSelector().textContains("smarttech.com/"));
		assertTrue("Url tip is not found", urlTip.exists());
	    Log("Log:"," Hint is verified");
		
		getUiDevice().pressEnter();
		waitForPageToLoad();

		UiObject btnBack = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/back"));
		btnBack.click();
		
	    UiObject tabTopSites = new UiObject(new UiSelector().textContains("TOP SITES"));
	    assertTrue("TOP SITES tab is not found - wrong screen", tabTopSites.exists());
	    Log("Log:", "Returned to main screen, test completed");
	    
	}
	
	public void SwitchLanguage() throws UiObjectNotFoundException {
		
		baseScreen.openAllTabsMenu();
		
		baseScreen.clickAllTabsIcon();
		baseScreen.clickPrivateIcon();
		baseScreen.clickSyncIcon();
		
		clickAtMiddleOfTheScreen();
		
		Log("Log:", "Returned to main screen, test completed");
		
	}
	
	public void switchLanguage(){
		
		getUiDevice().pressKeyCode(KeyEvent.KEYCODE_LANGUAGE_SWITCH);
		
	}

	public void closeTestApplication() {
		UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
		while(!allAppsButton.exists()) {
			getUiDevice().pressBack();
			sleep(500);
		}
		Log("Log:","Opened AllAps screen");
		getUiDevice().pressBack();
		Log("Log:","Opened Home screen");
	}
	
    private void openTestApplication() throws UiObjectNotFoundException {
    	UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
		allAppsButton.clickAndWaitForNewWindow();

		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
		appViews.setAsHorizontalList();
		    
		UiObject firefoxApp = appViews.getChildByText(new UiSelector().className("android.widget.TextView"), "Firefox");
		firefoxApp.clickAndWaitForNewWindow();
		Log("Log:", "App is opened");
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
