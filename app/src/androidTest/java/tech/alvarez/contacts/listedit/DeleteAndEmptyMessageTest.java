package tech.alvarez.contacts.listedit;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tech.alvarez.contacts.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DeleteAndEmptyMessageTest {

    @Rule
    public ActivityTestRule<ListActivity> mActivityTestRule = new ActivityTestRule<>(ListActivity.class);

    @Test
    public void addPersonTest() {
        ViewInteraction floatingActionButton = onView(allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.nameEditText));
        appCompatEditText.perform(scrollTo(), replaceText("Daniel Alvarez"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.addressEditText));
        appCompatEditText2.perform(scrollTo(), replaceText("La Paz, Bolivia"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.phoneEditText));
        appCompatEditText3.perform(scrollTo(), replaceText("591 77242424"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.emailEditText));
        appCompatEditText4.perform(scrollTo(), replaceText("daniel@alvarez.tech"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                withId(R.id.birthdayEditText));
        appCompatEditText5.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageButton")), withContentDescription("Previous month"),
                        withParent(allOf(withClassName(is("android.widget.DayPickerView")),
                                withParent(withClassName(is("com.android.internal.widget.DialogViewAnimator"))))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageButton")), withContentDescription("Previous month"),
                        withParent(allOf(withClassName(is("android.widget.DayPickerView")),
                                withParent(withClassName(is("com.android.internal.widget.DialogViewAnimator"))))),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageButton")), withContentDescription("Previous month"),
                        withParent(allOf(withClassName(is("android.widget.DayPickerView")),
                                withParent(withClassName(is("com.android.internal.widget.DialogViewAnimator"))))),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageButton")), withContentDescription("Previous month"),
                        withParent(allOf(withClassName(is("android.widget.DayPickerView")),
                                withParent(withClassName(is("com.android.internal.widget.DialogViewAnimator"))))),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction floatingActionButton2 = onView(allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton2.perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));

        onView(withText("OK")).perform(click());

        onView(withId(R.id.emptyTextView)).check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
