package com.uia.example.my;

// Import the uiautomator libraries
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LaunchSettings extends UiAutomatorTestCase {

		public void testDemo() throws UiObjectNotFoundException {

		// Simulate a short press on the HOME button.
		getUiDevice().pressHome();

		// We’re now in the home screen. Next, we want to simulate
		// a user bringing up the All Apps screen.
		// If you use the uiautomatorviewer tool to capture a snapshot
		// of the Home screen, notice that the All Apps button’s
		// content-description property has the value “Apps”. We can
		// use this property to create a UiSelector to find the button.
		UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));

		// Simulate a click to bring up the All Apps screen.
		allAppsButton.clickAndWaitForNewWindow();

		// In the All Apps screen, the Settings app is located in
		// the Apps tab. To simulate the user bringing up the Apps tab,
		// we create a UiSelector to find a tab with the text
		// label “Apps”.
		UiObject appsTab = new UiObject(new UiSelector().text("Apps"));

		// Simulate a click to enter the Apps tab.
		appsTab.click();

		// Next, in the apps tabs, we can simulate a user swiping until
		// they come to the Settings app icon. Since the container view
		// is scrollable, we can use a UiScrollable object.
		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

		// Set the swiping mode to horizontal (the default is vertical)
		appViews.setAsHorizontalList();

		// Create a UiSelector to find the Settings app and simulate
		// a user click to launch the app.
		UiObject settingsApp = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()),"Settings");
		settingsApp.clickAndWaitForNewWindow();

		getUiDevice().pressDPadUp();
		getUiDevice().pressDPadCenter();
//		fasfasf;
		appViews.scrollForward();
		appViews.scrollForward();
		appViews.scrollBackward(3);
		
		// Validate that the package name is the expected one
		UiObject settingsValidation = new UiObject(new UiSelector().packageName("com.android.settings"));
		assertTrue("Unable to detect Settings", settingsValidation.exists());
	}
}

/*
 * 
 * 
 * import com.android.uiautomator.core.UiObject; import
 * com.android.uiautomator.core.UiObjectNotFoundException; import
 * com.android.uiautomator.core.UiScrollable; import
 * com.android.uiautomator.core.UiSelector; import
 * com.android.uiautomator.testrunner.UiAutomatorTestCase; import
 * android.util.Log;
 * 
 * public class LaunchSettings extends UiAutomatorTestCase {
 * 
 * public void test() {
 * 
 * // Default parameters String toNumber = "123456"; String text =
 * "Test message";
 * 
 * String toParam = getParams().getString("to"); String textParam =
 * getParams().getString("text"); if (toParam != null) { // Remove spaces
 * toNumber = toParam.trim(); } if (textParam != null) { text =
 * textParam.trim(); }
 * 
 * if (textParam != null) { textParam = textParam.replace("blogspaceblog", " ");
 * textParam = textParam.replace("blogamperblog", "&"); textParam =
 * textParam.replace("bloglessblog", "<"); textParam =
 * textParam.replace("blogmoreblog", ">"); textParam =
 * textParam.replace("blogopenbktblog", "("); textParam =
 * textParam.replace("blogclosebktblog", ")"); textParam =
 * textParam.replace("blogonequoteblog", "'"); textParam =
 * textParam.replace("blogtwicequoteblog", "\""); text = textParam.trim(); }
 * Log.i("SendMessageTest", "Start SendMessage"); try { findAndRunApp(); } catch
 * (UiObjectNotFoundException e1) { // TODO Auto-generated catch block
 * e1.printStackTrace(); } try { sendMessage(toNumber, text); } catch
 * (UiObjectNotFoundException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } exitToMainWindow(); Log.i("SendMessageTest",
 * "End SendMessage");
 * 
 * }
 * 
 * private void findAndRunApp() throws UiObjectNotFoundException { // Go to main
 * screen getUiDevice().pressHome();
 * 
 * // Find menu button UiObject allAppsButton = new UiObject(new
 * UiSelector().description("Apps"));
 * 
 * // Click on menu button and wait new window
 * allAppsButton.clickAndWaitForNewWindow();
 * 
 * // Find App tab UiObject appsTab = new UiObject(new
 * UiSelector().text("Apps"));
 * 
 * // Click on app tab appsTab.click();
 * 
 * // Find scroll object (menu scroll) UiScrollable appViews = new
 * UiScrollable(new UiSelector().scrollable(true));
 * 
 * // Set the swiping mode to horizontal (the default is vertical)
 * appViews.setAsHorizontalList();
 * 
 * // Find Messaging application UiObject settingsApp =
 * appViews.getChildByText(new
 * UiSelector().className("android.widget.TextView"),"Messaging");
 * 
 * // Open Messaging application settingsApp.clickAndWaitForNewWindow();
 * 
 * // Validate that the package name is the expected one UiObject
 * settingsValidation = new UiObject( new
 * UiSelector().packageName("com.android.mms"));
 * assertTrue("Unable to detect Messaging", settingsValidation.exists()); }
 * 
 * private void sendMessage(String toNumber, String text) throws
 * UiObjectNotFoundException {
 * 
 * // Find and click New message button UiObject newMessageButton = new
 * UiObject(new UiSelector()
 * .className("android.widget.TextView").description("New message"));
 * newMessageButton.clickAndWaitForNewWindow();
 * 
 * // Find to box and enter the number into it UiObject toBox = new UiObject(new
 * UiSelector()
 * .className("android.widget.MultiAutoCompleteTextView").instance(0));
 * toBox.setText(toNumber);
 * 
 * // Find text box and enter the message into it UiObject textBox = new
 * UiObject(new UiSelector() .className("android.widget.EditText").instance(0));
 * textBox.setText(text);
 * 
 * // Find send button and send message UiObject sendButton = new UiObject(new
 * UiSelector() .className("android.widget.ImageButton").description("Send"));
 * sendButton.click(); }
 * 
 * private void exitToMainWindow() { // Find New message button UiObject
 * newMessageButton = new UiObject(new UiSelector()
 * .className("android.widget.TextView").description("New message"));
 * 
 * // Press back button while new message button doesn't exist
 * while(!newMessageButton.exists()) { getUiDevice().pressBack(); } }
 * 
 * }
 */
