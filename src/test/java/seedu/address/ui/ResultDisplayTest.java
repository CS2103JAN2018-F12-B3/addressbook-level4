package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.EventsUtil.postNow;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.ResultDisplayHandle;
import seedu.address.commons.events.ui.NewResultAvailableEvent;

public class ResultDisplayTest extends GuiUnitTest {

    private static final NewResultAvailableEvent NEW_RESULT_EVENT_NON_ERROR =
            new NewResultAvailableEvent("Non Error", false);
    private static final NewResultAvailableEvent NEW_RESULT_EVENT_ERROR =
            new NewResultAvailableEvent("Error", true);

    private ArrayList<String> defaultStyleOfResultDisplay;
    private ArrayList<String> errorStyleOfResultDisplay;

    private ResultDisplayHandle resultDisplayHandle;

    @Before
    public void setUp() {
        ResultDisplay resultDisplay = new ResultDisplay();
        resultDisplayHandle = new ResultDisplayHandle(getChildNode(resultDisplay.getRoot(),
                ResultDisplayHandle.RESULT_DISPLAY_ID));
        uiPartRule.setUiPart(resultDisplay);

        defaultStyleOfResultDisplay = new ArrayList<>(resultDisplayHandle.getStyleClass());

        errorStyleOfResultDisplay = new ArrayList<>(defaultStyleOfResultDisplay);
        errorStyleOfResultDisplay.add(ResultDisplay.ERROR_STYLE_CLASS);
    }

    @Test
    public void display() {
        // default result text
        guiRobot.pauseForHuman();
        assertEquals("", resultDisplayHandle.getText());
        assertEquals(defaultStyleOfResultDisplay, resultDisplayHandle.getStyleClass());

        // new error result received
        postNow(NEW_RESULT_EVENT_ERROR);
        guiRobot.pauseForHuman();
        assertBehaviorForErrorResult();

        // new non-error result received
        postNow(NEW_RESULT_EVENT_NON_ERROR);
        guiRobot.pauseForHuman();
        assertBehaviorForNonErrorResult();
    }

    /**
     * Verifies a result which has an error that <br>
     *      - the text remains <br>
     *      - the result display's style is the same as {@code errorStyleOfResultDisplay}.
     */
    private void assertBehaviorForErrorResult() {
        assertEquals(NEW_RESULT_EVENT_ERROR.message, resultDisplayHandle.getText());
        assertEquals(errorStyleOfResultDisplay, resultDisplayHandle.getStyleClass());
    }

    /**
     * Verifies a result which doesn't have an error that <br>
     *      - the text remains <br>
     *      - the result display's style is the same as {@code defaultStyleOfResultDisplay}.
     */
    private void assertBehaviorForNonErrorResult() {
        assertEquals(NEW_RESULT_EVENT_NON_ERROR.message, resultDisplayHandle.getText());
        assertEquals(defaultStyleOfResultDisplay, resultDisplayHandle.getStyleClass());
    }
}
