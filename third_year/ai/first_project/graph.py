import itertools
from random import randint

from utils import draw_grid
from alghoritms.ds import GridWithWeights
from alghoritms import a_star_search, distance_on_unit_sphere


with open("input/RO_small.txt") as f:
    min_lat = 99
    min_lng = 0

    max_lat = 0
    max_lng = -99

    cities = {}
    for line in f.readlines():
        fields = line.split("\t")[1:]
        try:
            lng = list(filter(lambda x: x.startswith("4"), fields))[0]
            lat = list(filter(lambda x: x.startswith("2") and not x.startswith("20"), fields))[0]
            cities[fields[0]] = (float(lat), -1 * float(lng))

            min_lat = min([float(lat), min_lat])
            min_lng = min([-1*float(lng), min_lng])

            max_lat = max([float(lat), max_lat])
            max_lng = max([-1*float(lng), max_lng])
        except:
            continue

size = len(cities.keys())
indexed = []
new_cities = {}
for name, v in cities.items():
    x = int(7 * (v[0] - min_lat) / (max_lat - min_lat) + 1)
    y = int(7  * (v[1] - min_lng) / (max_lng - min_lng) + 1)
    new_cities[name] = (v[0], v[1], x, y)
    indexed.append((x, y))
cities = new_cities

grid = GridWithWeights(size, size)
grid.min_x = min_lat
grid.min_y = min_lng
grid.max_y = max_lng
grid.max_x = max_lat

# randomly create some walls
grid.walls = []
for i in range(int((size * size) * 30 / 100)):
    x = randint(1, size)
    y = randint(1, size)
    if (x, y) not in indexed:
        grid.walls.append((x, y))
        indexed.append((x, y))

grid.weights = {city: randint(1, 200) for city in list(itertools.permutations(range(size), 2))}

city1 = "Timișoara"
city2 = "Arad"

print(cities[city1], cities[city2])

came_from, cost_so_far = a_star_search(grid, cities[city1], cities[city2], distance_on_unit_sphere)
draw_grid(grid, width=3, point_to=came_from, start=cities[city1], goal=cities[city2])
print()
draw_grid(grid, width=3, number=cost_so_far, start=cities[city1], goal=cities[city2])

