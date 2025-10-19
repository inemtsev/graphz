package com.eventslooped

import com.eventslooped.gql.queries.HelloWorldQuery
import com.eventslooped.gql.routes.graphQLGetModule
import com.expediagroup.graphql.server.ktor.GraphQL
import com.expediagroup.graphql.server.ktor.defaultGraphQLStatusPages
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.routing.routing

fun Application.graphQLModule() {
    install(GraphQL) {
        schema {
            packages = listOf("com.eventslooped.gql.schema")
            queries = listOf(
                HelloWorldQuery()
            )
        }
    }

    install(StatusPages) {
        defaultGraphQLStatusPages()
    }

    routing {
        graphQLGetModule()
    }
}
