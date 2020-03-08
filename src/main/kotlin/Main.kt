import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun hello(): String {
    return "Hello, World"
}

data class AddResult(val first: Int, val second: Int, val result: Int)

fun main() {
    embeddedServer(Netty, 8080) {
        install(ContentNegotiation) {
            gson { }
        }
        routing {
            get("/") {
                call.respondText(hello())
            }
            get("/add/{first}/{second}") {
                try {
                    val first = call.parameters["first"]!!.toInt()
                    val second = call.parameters["second"]!!.toInt()
                    val addResult = AddResult(first, second, first + second)
                    call.respond(addResult)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }.start(wait = true)
}
