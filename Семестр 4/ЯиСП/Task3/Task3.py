import math

class Vector:
    def __init__(self, x, y):
        self.x = float(x)
        self.y = float(y)

    def dot(self, other):
        return self.x * other.x + self.y * other.y

    def to(self, other):
        return Vector(other.x - self.x, other.y - self.y)

    def len(self):
        return math.sqrt(self.dot(self))


class Triangle:
    def __init__(self, p1, p2, p3):
        self.p1 = p1
        self.p2 = p2
        self.p3 = p3

    @classmethod
    def from_str(cls, string):
        parts = string.split()
        return Triangle(
            Vector(float(parts[0]), float(parts[1])),
            Vector(float(parts[2]), float(parts[3])),
            Vector(float(parts[4]), float(parts[5])
        ))

    def sides_lengths(self):
        side1 = self.p1.to(self.p2).len()
        side2 = self.p1.to(self.p3).len()
        side3 = self.p2.to(self.p3).len()
        return side1, side2, side3

    def is_similar_to(self, other):
        sides1 = list(self.sides_lengths())
        sides2 = list(other.sides_lengths())

        sides1.sort()
        sides2.sort()

        ratios = [sides2[i] / sides1[i] for i in range(3)]

        return ratios[0] == ratios[1] == ratios[2]

    def __str__(self):
        return f'{self.p1.x} {self.p1.y} {self.p2.x} {self.p2.y} {self.p3.x} {self.p3.y}'


def read_triangles():
    triangles = []
    with open('input1.txt') as file:
        for line in file:
            line = line.strip()
            triangles.append(Triangle.from_str(line.strip()))
    return triangles


def find_similar(trig_list):
    partitions = []
    for trig in trig_list:
        found = False
        for p in partitions:
            if trig.is_similar_to(p[0]):
                p.append(trig)
                found = True
        if not found:
            partitions.append([trig])
    return partitions


def write_results(results):
    with open('output1.txt', 'w') as file:
        for r in results:
            file.write('\n'.join((str(x) for x in r)))
            file.write('\n\n')


triangles = read_triangles()
results = find_similar(triangles)
write_results(results)