# HiltTesing


### If you are not using compose, use the below setup
@HiltAndroidTest
class MyTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var testRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        hiltRule.inject()
        testRule.launchActivity(Intent(targetContext, MainActivity::class.java))
    }
}

### Links

https://dagger.dev/hilt/testing.html
https://developer.android.com/training/dependency-injection/hilt-testing



