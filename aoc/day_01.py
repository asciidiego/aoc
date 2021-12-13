"""
Advent of Code: Day 1
"""
from functools import reduce
from itertools import islice
from typing import List


def depths(raw_depths: List[str]) -> int:
    """
    Return the number of times a depth measurement
    increases.
    """
    if len(raw_depths) == 0:
        return 0

    def depth_reducer(acc, val: int):
        count, depth = acc
        if val <= depth:
            return count, val
        return count + 1, val

    input_as_ints = map(int, raw_depths)
    depth_increments, _ = reduce(depth_reducer, input_as_ints, (0, next(input_as_ints)))
    return depth_increments


def sliding_depths(raw_depths: List[str]) -> int:
    parsed_depths = list(map(int, raw_depths))
    window_size = 3
    sliding_window_range = range(0, len(raw_depths) - window_size + 1)
    sliding_window = (
        tuple(islice(parsed_depths, i, i + window_size)) for i in sliding_window_range
    )
    sliding_window_sum = map(sum, sliding_window)

    return depths([*map(str, sliding_window_sum)])


if __name__ == "__main__":
    sample = ["0", "3", "2"]
    print(f"Executing for {sample=}")
    print(f"Depth increments: {depths(sample)}")
