package utils

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.domain.model.Coordinates
import java.util.Date
import java.util.Random

object CacheMock {

    private val random = Random()

    fun mock(
        cacheId: String = random.nextInt().toString(),
        creatorId: String = random.nextInt().toString(),
        title: String = random.nextInt().toString(),
        coordinates: Coordinates = Coordinates(0.0, 0.0),
        difficulty: Float = 1f,
        ground: Float = 1f,
        size: CacheSize = CacheSize.Regular,
        discovered: Boolean = false,
        cacheIdsRequired: List<String> = emptyList(),
        createDate: Date = Date(),
        finalStepRef: String = random.nextInt().toString(),
        tagIds: List<String> = emptyList(),
        description: String = random.nextInt().toString(),
        type: Cache.Type = Cache.Type.Classical,
    ): Cache = Cache(
        cacheId = cacheId,
        creatorId = creatorId,
        title = title,
        coordinates = coordinates,
        difficulty = difficulty,
        ground = ground,
        size = size,
        discovered = discovered,
        cacheIdsRequired = cacheIdsRequired,
        createDate = createDate,
        finalStepRef = finalStepRef,
        tagIds = tagIds,
        description = description,
        type = type,
    )
}
