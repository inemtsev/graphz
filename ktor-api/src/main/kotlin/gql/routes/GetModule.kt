package com.eventslooped.gql.routes

import com.expediagroup.graphql.server.ktor.GraphQL
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.plugin
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.routing.get

fun Route.graphQLGetModule(): Route {
    val graphQLPlugin = this.application.plugin(GraphQL)
    val route = get("/graphql") {
        graphQLPlugin.server.execute(call.request)
    }
    route.install(ContentNegotiation) {
        jackson(streamRequestBody = true) {
            // configure jackson here
        }
    }

    return route
}
