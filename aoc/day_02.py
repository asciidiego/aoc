from functools import reduce
from typing import List


def plan_route(instructions: List[str], version="v2"):
    def _plan_route_v1(prev_coordinates, action: str):
        prev_pos, prev_depth = prev_coordinates
        action, amount = action.split(" ")
        amount = int(amount)
        if action == "forward":
            return (prev_pos + amount, prev_depth)
        elif action == "down":
            return (prev_pos, prev_depth + amount)
        elif action == "up":
            return (prev_pos, prev_depth - amount)

    def _plan_route_v2(prev_coordinates, action: str):
        prev_pos, prev_depth, prev_aim = prev_coordinates
        action, amount = action.split(" ")
        amount = int(amount)
        if action == "forward":
            return (prev_pos + amount, prev_depth + amount * prev_aim, prev_aim)
        elif action == "down":
            return (prev_pos, prev_depth, prev_aim + amount)
        return (prev_pos, prev_depth, prev_aim - amount)

    planning_algorithms = {"v1": _plan_route_v1, "v2": _plan_route_v2}

    return reduce(planning_algorithms[version], instructions, (0, 0, 0))


if __name__ == "__main__":
    instructions = ["forward 2", "down 3", "up 2"]
    for version in ["v1", "v2"]:
        print(f"Executing for {instructions=} {version=}")
        print(f"Position and depth = {plan_route(instructions, version)}")
