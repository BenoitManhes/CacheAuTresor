package cache

import com.benoitmanhes.domain.usecase.cache.CalculateCachePtsWinUseCase
import com.benoitmanhes.domain.usecase.cache.GetAllMyStepUseCase
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test
import utils.CacheMock
import utils.CacheUserProgressMock
import kotlin.test.assertEquals

class CalculatePtsWinUseCaseTest {

    private val crewPosition: String = "crewPosition"
    private val enigmaStepRef: String = "enigmaStepRef"
    private val finalStepRef: String = "finalStepRef"
    private val intermediateStep: List<String> = listOf("1", "2", "3", "4")
    private val crewStepRefs: Map<String, List<String>> = mapOf(
        crewPosition to listOf("a1", "a2"),
        "b" to listOf("b1", "b2"),
    )

    private lateinit var calculatePtsUseCase: CalculateCachePtsWinUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        calculatePtsUseCase = CalculateCachePtsWinUseCase(GetAllMyStepUseCase())
    }

    @Test
    fun `calculate pts classical 1,1 no clue`() {
        val cache = CacheMock.classical(
            ground = 1f,
            difficulty = 1f,
            finalStepRef = finalStepRef,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = emptySet(),
        )

        assertEquals(2, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts classical 1,1 with clue`() {
        val cache = CacheMock.classical(
            ground = 1f,
            difficulty = 1f,
            finalStepRef = finalStepRef,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = setOf(finalStepRef),
        )

        assertEquals(1, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts classical 2,3 no clue`() {
        val cache = CacheMock.classical(
            ground = 2f,
            difficulty = 3f,
            finalStepRef = finalStepRef,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = emptySet(),
        )

        assertEquals(15, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts classical 4,5 with clue`() {
        val cache = CacheMock.classical(
            ground = 4.5f,
            difficulty = 5f,
            finalStepRef = finalStepRef,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = setOf(finalStepRef),
        )

        assertEquals(23, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts mystery 3,1 no clue`() {
        val cache = CacheMock.mystery(
            ground = 3f,
            difficulty = 1.5f,
            finalStepRef = finalStepRef,
            enigmaStepRef = enigmaStepRef,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = setOf(),
        )

        assertEquals(19, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts mystery 5,5 enigma clue`() {
        val cache = CacheMock.mystery(
            ground = 5f,
            difficulty = 5f,
            finalStepRef = finalStepRef,
            enigmaStepRef = enigmaStepRef,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = setOf(enigmaStepRef),
        )

        assertEquals(35, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts mystery 1,3 full clue`() {
        val cache = CacheMock.mystery(
            ground = 1f,
            difficulty = 3.5f,
            finalStepRef = finalStepRef,
            enigmaStepRef = enigmaStepRef,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = setOf(enigmaStepRef, finalStepRef),
        )

        assertEquals(5, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts coop 2,2 no clue`() {
        val cache = CacheMock.coop(
            ground = 2f,
            difficulty = 2f,
            finalStepRef = finalStepRef,
            crewStepRefs = crewStepRefs,
        )
        val userProgress = CacheUserProgressMock.mock(
            coopMemberRef = crewPosition,
            clueUnlockedStepRef = setOf(),
        )

        assertEquals(13, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts coop 2,2 one clue`() {
        val cache = CacheMock.coop(
            ground = 2f,
            difficulty = 2f,
            finalStepRef = finalStepRef,
            crewStepRefs = crewStepRefs,
        )
        val userProgress = CacheUserProgressMock.mock(
            coopMemberRef = crewPosition,
            clueUnlockedStepRef = setOf("a1"),
        )

        assertEquals(10, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts coop 2,2 full clue`() {
        val cache = CacheMock.coop(
            ground = 2f,
            difficulty = 2f,
            finalStepRef = finalStepRef,
            crewStepRefs = crewStepRefs,
        )
        val userProgress = CacheUserProgressMock.mock(
            coopMemberRef = crewPosition,
            clueUnlockedStepRef = crewStepRefs[crewPosition]!!.toSet() + finalStepRef,
        )

        assertEquals(3, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts piste 3,1 no clue`() {
        val cache = CacheMock.piste(
            ground = 3f,
            difficulty = 1.5f,
            finalStepRef = finalStepRef,
            intermediaryStepRefs = intermediateStep,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = emptySet(),
        )

        assertEquals(30, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts piste 3,1 clue final`() {
        val cache = CacheMock.piste(
            ground = 3f,
            difficulty = 1.5f,
            finalStepRef = finalStepRef,
            intermediaryStepRefs = intermediateStep,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = setOf(finalStepRef),
        )

        assertEquals(15, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts piste 3,1 clue mix`() {
        val cache = CacheMock.piste(
            ground = 3f,
            difficulty = 1.5f,
            finalStepRef = finalStepRef,
            intermediaryStepRefs = intermediateStep,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = setOf(finalStepRef, intermediateStep[0], intermediateStep[2]),
        )

        assertEquals(11, calculatePtsUseCase(cache, userProgress))
    }

    @Test
    fun `calculate pts piste 3,1 clue full`() {
        val cache = CacheMock.piste(
            ground = 3f,
            difficulty = 1.5f,
            finalStepRef = finalStepRef,
            intermediaryStepRefs = intermediateStep,
        )
        val userProgress = CacheUserProgressMock.mock(
            clueUnlockedStepRef = intermediateStep.toSet() + finalStepRef,
        )

        assertEquals(7, calculatePtsUseCase(cache, userProgress))
    }
}