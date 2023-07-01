package app.perf

import com.intuit.karate.http.HttpServer

object Utils {
    
    def startServer() = {
        if (System.getenv("START_SERVER") == "true") {
            println("*** env property START_SERVER was 'true', will start server ...")
            val config = app.App.serverConfig("src/main/java/app")
            HttpServer.config(config).http(8080).build()
        }
    }

} 
