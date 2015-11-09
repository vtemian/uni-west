# utility functions for dealing with square grids
def from_id_width(id, width):
    return (id % width, id // width)

def draw_tile(graph, id, style, width):
    r = "."
    if 'number' in style:
        for point in style['number']:
            if id == point[2:]:
                r = "%d" % style['number'][point]
    if 'point_to' in style:
        for point in style['point_to']:
            if id == point[2:] and style['point_to'][point] is not None:
                (x1, y1) = id
                (x2, y2) = style['point_to'][point][2:]
                if x2 == x1 + 1: r = "\u2192"
                if x2 == x1 - 1: r = "\u2190"
                if y2 == y1 + 1: r = "\u2193"
                if y2 == y1 - 1: r = "\u2191"
    if 'start' in style and id == style['start'][2:]: r = "A"
    if 'goal' in style and id == style['goal'][2:]: r = "Z"
    if 'path' in style and id in style['path']: r = "@"
    if id in graph.walls: r = "#" * width
    return r

def draw_grid(graph, width=2, **style):
    for y in range(graph.height):
        for x in range(graph.width):
            print("%%-%ds" % width % draw_tile(graph, (x, y), style, width), end="")
        print()
