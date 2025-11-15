import com.eventslooped.gql.clients.PostsClient
import com.eventslooped.gql.models.Post
import com.expediagroup.graphql.dataloader.KotlinDataLoader
import com.expediagroup.graphql.generator.extensions.get
import graphql.GraphQLContext
import kotlinx.coroutines.CoroutineScope
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import kotlin.coroutines.EmptyCoroutineContext


val PostsDataLoaderByUserId = object : KotlinDataLoader<Int, Post?> {
    override val dataLoaderName = "POST_LOADER_BY_USER_ID"

    override fun getDataLoader(graphQLContext: GraphQLContext): DataLoader<Int, Post?> {
        val postsClient = PostsClient()

        return DataLoaderFactory.newDataLoader { id, batchDataLoaderEnvironment ->
            val coroutineScope = batchDataLoaderEnvironment.getContext<GraphQLContext>()?.get<CoroutineScope>()
                ?: CoroutineScope(EmptyCoroutineContext)

            postsClient.getPosts().filter { p -> p.userId == id }.toList()
        }
    }
}
