import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class TestMain: StringSpec ({
    "Should Greet Properly" {
        hello() shouldBe "Hello, World"
    }
})