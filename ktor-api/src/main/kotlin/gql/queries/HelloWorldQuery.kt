package com.eventslooped.gql.queries

import com.expediagroup.graphql.server.operations.Query

class HelloWorldQuery : Query {
    fun hello(): String = "Hello World!"
}