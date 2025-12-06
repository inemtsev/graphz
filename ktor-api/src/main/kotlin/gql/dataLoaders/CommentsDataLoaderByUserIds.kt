package com.eventslooped.gql.dataLoaders

import com.eventslooped.gql.clients.CommentsClient
import com.eventslooped.gql.models.Comment
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

val commentDataLoaderByUserIds = object : KotlinDataLoader<Int, List<Comment>> {
    val commentsClient = CommentsClient()

    override val dataLoaderName: String = "COMMENTS_LOADER_BY_USER_IDS"

    override fun getDataLoader(graphQLContext: GraphQLContext): DataLoader<Int, List<Comment>> {
        return DataLoaderFactory.newDataLoader { userIds, batchLoaderEnvironment ->
            val coroutineScope = batchLoaderEnvironment.getContext<GraphQLContext>()?.get<CoroutineScope>()
                ?: CoroutineScope(EmptyCoroutineContext)

            val requests = buildList { userIds.forEach { id ->
                    add(coroutineScope.async { commentsClient.getComments(id) })
                }
            }

            coroutineScope.future { requests.awaitAll() }
        }
    }

}