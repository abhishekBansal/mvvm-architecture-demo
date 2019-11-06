package com.abhishek.mvvmdemo.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.abhishek.mvvmdemo.R
import io.mockk.every
import io.mockk.mockk
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

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest : KoinTest {
    private lateinit var loginViewModel: LoginViewModel
    private val liveData = MutableLiveData<LoginState>()

    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java, false, false)

    @Before
    fun beforeTest() {
        loginViewModel = mockk()
        every { loginViewModel.mutableLiveData } returns liveData

        loadKoinModules(
            module {
                viewModel { loginViewModel }
            }
        )

        activityRule.launchActivity(null)
    }

    @Test
    fun testProgress() {
        onView(withId(R.id.emailEt))
            .perform(ViewActions.replaceText("abhishek"), ViewActions.closeSoftKeyboard())
    }

    @After
    fun afterTest() {
        activityRule.finishActivity()
        stopKoin()
    }
}
