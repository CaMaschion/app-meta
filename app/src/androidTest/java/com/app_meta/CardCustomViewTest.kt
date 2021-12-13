package com.app_meta

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.platform.app.InstrumentationRegistry
import com.app_meta.model.Author
import com.app_meta.model.Item
import com.app_meta.ui.CardCustomView
import com.app_meta.ui.MainActivity
import org.junit.Test

class CardCustomViewTest {

    @Test
    fun cardCustomTest() {
        //given
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val createRepository = createRepositoryGithub()
        val cardCustomView = CardCustomView(context)

        //when
        cardCustomView.launch()
        runOnUiThread {
            cardCustomView.setup(createRepository)
        }

        //then
        onView(withId(R.id.rep_github)).check(matches(withText(createRepository.name)))
        onView(withId(R.id.description_github)).check(matches(withText(createRepository.description)))
        onView(withId(R.id.author_github)).check(matches(withText(createRepository.owner.login)))
    }
}

fun View.launch() {
    ActivityScenario.launch(MainActivity::class.java).onActivity {
        it.setContentView(this)
    }
}

private fun createRepositoryGithub() = Item (
    name = "Repository",
    description = "Describes",
    forks_count = 10,
    stargazers_count = 5,
    owner = Author(
        "Login",
        "")
)
