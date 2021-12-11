"Day 1"
from aoc.day_01 import depths


def test_simple_positive_case():
    empty = []
    assert depths(empty) == 0

    x = ["0", "1"]
    assert depths(x) == 1


def test_simple_negative_case():
    x = ["199"]
    y = ["200", "199"]
    assert depths(x) == depths(y) == 0
