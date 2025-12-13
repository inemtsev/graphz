package com.eventslooped

import com.eventslooped.gql.dataLoaders.CommentsDataLoaderByIds
import com.eventslooped.gql.dataLoaders.CommentsDataLoaderByUserIds
import com.eventslooped.gql.dataLoaders.PostsDataLoaderByIds
import com.eventslooped.gql.dataLoaders.PostsDataLoaderByUserId
import com.eventslooped.gql.queries.CommentsQuery
import com.eventslooped.gql.queries.HelloWorldQuery
import com.eventslooped.gql.queries.PostsQuery
import com.expediagroup.graphql.dataloader.KotlinDataLoaderRegistryFactory
import com.expediagroup.graphql.server.ktor.GraphQL
import com.expediagroup.graphql.server.ktor.defaultGraphQLStatusPages
import com.expediagroup.graphql.server.ktor.graphQLGetRoute
import com.expediagroup.graphql.server.ktor.graphQLPostRoute
import com.expediagroup.graphql.server.ktor.graphiQLRoute
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.plugin
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.routing.routing

fun Application.configureGraphQLModule() {
    install(GraphQL) {
        schema {
            packages = listOf(
                "com.eventslooped.gql"
            )
            queries = listOf(
                HelloWorldQuery(),
                CommentsQuery(),
                PostsQuery()
            )
        }
        engine {
            dataLoaderRegistryFactory = KotlinDataLoaderRegistryFactory(
                CommentsDataLoaderByIds,
                CommentsDataLoaderByUserIds,
                PostsDataLoaderByIds,
                PostsDataLoaderByUserId
            )
        }
    }

    install(CORS) {
        anyHost()
    }

    install(StatusPages) {
        defaultGraphQLStatusPages()
    }

    routing {
        // Convenient UI for manual testing at GET /graphiql
        graphiQLRoute()

        // Library-provided routes (they parse + respond correctly)
        graphQLGetRoute(endpoint = "graphql")
        graphQLPostRoute(endpoint = "graphql")
    }
}
