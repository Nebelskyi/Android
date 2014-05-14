package com.firefox.screens;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

public class MainCentralScreen extends BaseScreen{
	
	UiScrollable mainScrollableView = new UiScrollable(new UiSelector().resourceId("org.mozilla.firefox:id/home_pager"));
	UiObject lblTopSites = new UiObject(new UiSelector().textContains("TOP SITES"));
	UiObject lblBooksmarks = new UiObject(new UiSelector().textContains("BOOKMARKS"));
	UiObject lblReadingList = new UiObject(new UiSelector().textContains("READING"));
	UiObject lblHistory = new UiObject(new UiSelector().textContains("HISTORY"));
	
	UiObject lblTipReadingList = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/home_empty_hint"));
	UiObject btnRecents = new UiObject(new UiSelector().className("android.widget.ImageButton").instance(0));
	UiObject btnRecentsTabs = new UiObject(new UiSelector().className("android.widget.ImageButton").instance(1));
	UiObject imgEmptyRecentsTabs = new UiObject(new UiSelector().resourceId("org.mozilla.firefox:id/home_empty_image"));
	
	
	public void scrollToRightScreen() throws UiObjectNotFoundException{
		mainScrollableView.setAsHorizontalList();
		mainScrollableView.swipeLeft(50);
	}

	public void scrollToLeftScreen() throws UiObjectNotFoundException{
		mainScrollableView.setAsHorizontalList();
		mainScrollableView.swipeRight(50);
	}
	
	public void clickTopSitesTab() throws UiObjectNotFoundException{
		lblTopSites.click();
		assertTrue("'TOP SITES' tab is not enabled", lblTopSites.isEnabled());
	    Log("Log:", "TOP SITES tab is clicked");
	}
	
	public void clickBookmarksTab() throws UiObjectNotFoundException{
		lblTopSites.click();
		assertTrue("'Bookmarks' tab is not enabled", lblBooksmarks.isEnabled());
		Log("Log:", "'Bookmarks' tab is clicked");
	}
	
	public void clickReadingListTab() throws UiObjectNotFoundException{
		lblTopSites.click();
		assertTrue("'ReadingList' tab is not enabled", lblReadingList.isEnabled());
		Log("Log:", "'ReadingList' tab is clicked");
	}
	
	public void clickHistoryTab() throws UiObjectNotFoundException{
		lblTopSites.click();
		assertTrue("'History' tab is not enabled", lblHistory.isEnabled());
		Log("Log:", "History tab is clicked");
	}

	
	public void verifyBookamarkExists(String bookmarkText) throws UiObjectNotFoundException {
		
		assertTrue("Wrong current screen, expected 'Bookmarks'", lblBooksmarks.isEnabled());
		
		UiObject bookmark = new UiObject(new UiSelector().textContains("Firefox: Support"));
		assertTrue(bookmarkText + " bookmark is not found", bookmark.exists());
		Log("Log:", bookmarkText + " Bookmark is found");
	}

	public void verifyReadingListEmptyTipExists() throws UiObjectNotFoundException {
		assertTrue("'READING LIST' empty tip is not found", lblTipReadingList.exists());
		assertTrue("'READING LIST' text is not as expected", lblTipReadingList.getText().toString().contains("TIP: Save articles to your reading list by long pressing"));
		Log("Log:", "'READING LIST' empty tip is verified");		
	}

	public void clickRecentsButton() throws UiObjectNotFoundException {
		btnRecents.click();
		assertTrue("'Recents' button is not enabled/found", (btnRecents.exists() && btnRecents.isEnabled()));
		Log("Log:", "'Recents' button is clicked");		
	}

	
	
	public void clickRecentsTabsButton() throws UiObjectNotFoundException {
		btnRecentsTabs.click();
		assertTrue("'RecentsTabs' button is not enabled/found", (btnRecentsTabs.exists() && btnRecentsTabs.isEnabled()));
		Log("Log:", "'RecentsTabs' button is clicked");
	}

	public boolean isVisibleEmptyRecentTabsImage(){
		return imgEmptyRecentsTabs.exists();
	}
}
