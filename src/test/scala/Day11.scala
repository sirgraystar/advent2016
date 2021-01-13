package advent2016

import org.scalatest.funsuite.AnyFunSuite

import scalaadventutils.Problem

class Day11Spec extends AnyFunSuite {
    val floorMap = Map(
        1 -> (Set[Item](new Microchip("hydrogen"), new Microchip("lithium"))),
        2 -> (Set[Item](new Generator("hydrogen"))),
        3 -> (Set[Item](new Generator("lithium"))),
        4 -> (Set[Item]())
    )

    val completeFloorMap = Map(
        1 -> (Set[Item]()),
        2 -> (Set[Item]()),
        3 -> (Set[Item]()),
        4 -> (Set[Item](new Microchip("hydrogen"), new Microchip("lithium")))
    )

    val completeFloorMap2 = Map(
        1 -> (Set[Item]()),
        2 -> (Set[Item]()),
        3 -> (Set[Item]()),
        4 -> (Set[Item](new Generator("hydrogen"), new Microchip("lithium")))
    )

    val invalidFloorMap = Map(
        1 -> (Set[Item]()),
        2 -> (Set[Item](new Generator("lithium"), new Microchip("lithium"))),
        3 -> (Set[Item]()),
        4 -> (Set[Item](new Generator("lithium"), new Microchip("hydrogen")))
    )

    val validFloorMap = Map(
        1 -> (Set[Item]()),
        2 -> (Set[Item](new Generator("lithium"), new Microchip("lithium"))),
        3 -> (Set[Item](new Microchip("hydrogen"))),
        4 -> (Set[Item](new Generator("hydrogen"), new Microchip("hydrogen")))
    )

    val floorMap2 = Map(
        1 -> (Set[Item](new Microchip("lithium"))),
        2 -> (Set[Item]()),
        3 -> (Set[Item](new Generator("hydrogen"), new Generator("lithium"), new Microchip("hydrogen"))),
        4 -> (Set[Item]())
    )

    test("Items: equality") {
        val a = new Generator("hydrogen")
        val b = new Microchip("lithium")
        val c = new Generator("hydrogen")
        val d = new Microchip("hydrogen")

        assert(a == c)
        assert(a != b)
        assert(a != d)
        assert(b != d)
        assert(c != d)
    }

    test("Floors: equality") {
        val floors1 = new Floors(1, floorMap, 0)
        val floors2 = new Floors(1, floorMap, 0)
        val floors3 = new Floors(2, floorMap, 0)
        val floors4 = new Floors(1, floorMap, 2)
        val floors5 = new Floors(1, completeFloorMap, 0)
        val floors6 = new Floors(1, completeFloorMap2, 0)

        assert(floors1 == floors2)
        assert(floors1 != floors3)
        assert(floors1 == floors4)
        assert(floors5 != floors6)
    }

    test("Floors: complete") {
        val floors1 = new Floors(1, floorMap, 0)

        assert(!floors1.complete())

        val floors2 = new Floors(1, completeFloorMap, 0)

        assert(floors2.complete)
    }

    test("Floors: valid") {
        val floors1 = new Floors(1, floorMap, 0)
        val floors2 = new Floors(1, completeFloorMap, 0)
        val floors3 = new Floors(1, invalidFloorMap, 0)
        val floors4 = new Floors(1, validFloorMap, 0)
        val floors5 = new Floors(1, floorMap2, 0)

        assert(floors1.valid())
        assert(floors2.valid())
        assert(!floors3.valid())
        assert(floors4.valid())
        assert(floors5.valid())
    }

    test("Floors: parseInput") {
        val lines = Problem.parseInputToList("day11-test")
        val floors = Day11.parseInput(lines).floors

        assertResult(2) {
            floors(1).size
        }
    }

    test("part 1") {
        val lines = Problem.parseInputToList("day11-test")
        val initialState = Day11.parseInput(lines)

        assertResult(11) {
            Day11.solve(initialState)
        }
    }
}
