package com.abhishek.mvvmdemo.onboarding

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.abhishek.mvvmdemo.PatientLocalStorage
import com.abhishek.mvvmdemo.R
import com.abhishek.mvvmdemo.api.ApiModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest : KoinTest {
    private val loginViewModel by inject<LoginViewModel>()
    private val localStorage by inject<PatientLocalStorage>()
    private val sharedPreferences by inject<SharedPreferences>()
    private val liveData = MutableLiveData<LoginState>()

    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java, true, false)

    @Before
    fun beforeTest() {
//        loginViewModel = Mockito.mock(LoginViewModel::class.java)
//        localStorage = Mockito.mock(PatientLocalStorage::class.java)
//        sharedPreferences = Mockito.mock(SharedPreferences::class.java)

        val context = InstrumentationRegistry.getInstrumentation().context
        val koinmodules = module {
            single { context.getSharedPreferences("abc", Context.MODE_PRIVATE) }
            single {
                PatientLocalStorage(
                    "asf",
                    context,
                    get()
                )
            }
            viewModel { LoginViewModel(ApiModule.providesApiService()) }
        }

        loadKoinModules(koinmodules)

        declareMock<PatientLocalStorage> {
            `when`(getString(ArgumentMatchers.anyString())).thenReturn("pqrs")
//            given(getString(ArgumentMatchers.anyString())).willReturn("pqrs")
        }

        koinApplication { modules(koinmodules) }.checkModules()

//        declareMock<LoginViewModel>()
//
//        `when`(loginViewModel.mutableLiveData).thenReturn(liveData)
//        `when`(loginViewModel.testMethod()).thenReturn("pqrs")
//        `when`(
//            sharedPreferences.getString(
//                Mockito.anyString(),
//                Mockito.anyString()
//            )
//        ).thenReturn("pqrs")
//        `when`(localStorage.getString(ArgumentMatchers.anyString())).thenReturn("pqrs")

        activityRule.launchActivity(null)
    }

    @Test
    fun testProgress() {
        activityRule.activity.findViewById<EditText>(R.id.emailEt)?.importantForAutofill =
            View.IMPORTANT_FOR_AUTOFILL_NO
        onView(withId(R.id.emailEt))
            .perform(typeText("abhishek"), ViewActions.closeSoftKeyboard())
    }

    @After
    fun afterTest() {
        activityRule.finishActivity()
        stopKoin()
    }
}

