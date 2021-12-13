"Day 1"
from aoc.day_01 import depths, sliding_depths


def test_simple_positive_case():
    "Part 1"
    empty = []
    one = ["0"]
    assert depths(empty) == depths(one) == 0

    x = ["0", "1"]
    assert depths(x) == 1


def test_simple_negative_case():
    "Part 1"
    x = ["199"]
    y = ["200", "199"]
    assert depths(x) == depths(y) == 0


def test_sliding_window_case():
    "Part 2"
    x = ["199", "200", "208", "210", "200", "207", "240", "269", "260", "263"]

    assert sliding_depths(x) == 5
