package com.app_meta

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.app_meta.model.Author
import com.app_meta.model.Item
import org.junit.Test

class CardCustomViewTest {

    @Test
    fun cardCustomTest() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val createRepository = createRepositoryGithub()
        val cardCustomView = CardCustomView(context)

        cardCustomView.launch()
        cardCustomView.setup(createRepository)

        onView(withId(R.id.rep_github)).check(matches(withText("Repositório")))
        onView(withId(R.id.description_github)).check(matches(withText("Descrição")))

    }

}

fun View.launch() {
    ActivityScenario.launch(MainActivity::class.java).onActivity {
        it.setContentView(this)
    }
}

private fun createRepositoryGithub() = Item(
    name = "Repositório",
    description = "Descrição",
    forks_count = 0,
    stargazers_count = 0,
    owner = Author("Login", "Avatar")
)
