package com.eventslooped.gql.dataLoaders

import com.eventslooped.gql.clients.PostsClient
import com.eventslooped.gql.models.Post
import com.expediagroup.graphql.dataloader.KotlinDataLoader
import com.expediagroup.graphql.generator.extensions.get
import graphql.GraphQLContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.future.future
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import kotlin.coroutines.EmptyCoroutineContext

val PostsDataLoaderByIds = object : KotlinDataLoader<Int, Post?> {
    override val dataLoaderName = "POST_LOADER_BY_IDS"

    override fun getDataLoader(graphQLContext: GraphQLContext): DataLoader<Int, Post?> {
        val postsClient = PostsClient() // Can call service here instead, but for simplicity lets use Client directly

        return DataLoaderFactory.newDataLoader { ids, batchLoaderEnvironment ->
            val coroutineScope = batchLoaderEnvironment.getContext<GraphQLContext>()?.get<CoroutineScope>()
                ?: CoroutineScope(EmptyCoroutineContext)

            val requests = buildList {
                ids.forEach { id ->
                    val request = coroutineScope.async { postsClient.getPost(id) }
                    add(request)
                }
            }

            coroutineScope.future { requests.awaitAll() }
        }
    }
}
