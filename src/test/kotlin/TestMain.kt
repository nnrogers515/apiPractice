import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.ktor.server.testing.withTestApplication
import io.ktor.application.Application
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest

class TestMain : StringSpec({
    "should retrieve root path properly" {
        withTestApplication(Application::adder) {
            handleRequest(Get, "/").apply {
                response.status() shouldBe HttpStatusCode.OK
                response.content shouldBe "Hello, World"
            }
        }
    }
})
