from aoc.day_02 import plan_route


def test_simple_case():
    instructions = [
        "forward 2",
        "down 3",
        "forward 2",
    ]

    position, depth, aim = plan_route(instructions)
    assert position == 4
    assert depth == 6
    assert aim == 3


def test_sample_case():
    instructions = [
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2",
    ]
    position, depth, aim = plan_route(instructions)
    assert position == 15
    assert depth == 60
    assert position * depth == 900
