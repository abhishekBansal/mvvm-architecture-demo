package com.abhishek.mvvmdemo.onboarding

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.abhishek.mvvmdemo.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest : KoinTest {
    private lateinit var loginViewModel: LoginViewModel

    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun beforeTest() {
        loginViewModel = declareMock()
        loadKoinModules(
            module {
//                single { ApiModule.providesApiService() }
                viewModel { loginViewModel }
            }
        )
    }

    @Test
    fun testProgress() {
        activityRule.launchActivity(null)
        onView(withId(R.id.emailEt))
            .perform(ViewActions.typeText("abhishek"))
    }

    @After
    fun afterTest() {
        stopKoin()
    }
}
