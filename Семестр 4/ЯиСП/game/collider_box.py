from vector2d import *


class ColliderBox:
    def __init__(self, x, y, width, height):
        self.center = Vector2D(x, y)
        self.width = width
        self.height = height

    def set_center(self, x, y):
        self.center = Vector2D(x, y)

    @staticmethod
    def collide_vs_box(box1, box2):
        return box1.center == box2.center

    @staticmethod
    def collide_vs_point(box, ray):
        return (box.center.x - box.width / 2 < ray.x < box.center.x + box.width / 2 and box.center.y + box.height / 2 <
                    ray.y < box.center.y - box.height / 2)
