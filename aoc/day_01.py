"""
Advent of Code: Day 1
"""

from functools import reduce
from typing import List


def depths(raw_depths: List[str]) -> int:
    """
    Return the number of times a depth measurement
    increases.
    """
    if len(raw_depths) <= 1:
        return 0

    def depth_reducer(acc, val: int):
        count, depth = acc
        if val <= depth:
            return count, val
        return count + 1, val

    input_as_ints = map(int, raw_depths)
    depth_increments, _ = reduce(depth_reducer, input_as_ints, (0, next(input_as_ints)))
    return depth_increments


if __name__ == "__main__":
    print(depths([]))

