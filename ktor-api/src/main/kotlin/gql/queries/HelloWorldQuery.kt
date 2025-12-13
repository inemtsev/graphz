package com.eventslooped.gql.queries

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query

class HelloWorldQuery : Query {
    @GraphQLDescription("Returns Hello World!")
    fun hello(): String = "Hello World!"
}