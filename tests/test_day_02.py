from aoc.day_02 import plan_route


def test_simple_case():
    instructions = [
        "forward 2",
        "down 3",
    ]

    position, depth = plan_route(instructions)
    assert position * depth == 6


def test_sample_case():
    instructions = [
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2",
    ]
    position, depth = plan_route(instructions)
    assert position * depth == 150
