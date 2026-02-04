import pygame

import logic
from collider_box import *


class Box(ColliderBox):
    def __init__(self, vertical, horizontal, x: int, y: int, img):
        ColliderBox.__init__(self, x, y, logic.TILE_SIZE, logic.TILE_SIZE)
        self.image = pygame.transform.scale(img, (logic.TILE_SIZE, logic.TILE_SIZE))
        self.slideDirection = Vector2D(horizontal, vertical)

    def draw(self, screen):
        screen.blit(self.image, (self.center.x * logic.TILE_SIZE, self.center.y * logic.TILE_SIZE))

    def on_collide(self, normal):
        if self.can_slide(normal) and self.can_move_to(normal):
            boxes = self.boxes(normal)
            for box in boxes:
                box.center += normal
            self.center += normal
            return False
        else:
            return True

    def can_slide(self, normal):
        return normal.dot_product(self.slideDirection) != 0

    def can_move_to(self, offset):
        offsetishe = self.center + offset
        # if 0 <= offsetishe.x < 12 and 0 <= offsetishe.y < 12:
        if logic.BORDER_LEFT / logic.TILE_SIZE <= offsetishe.x < (logic.BORDER_RIGHT / logic.TILE_SIZE) - 1 and (
                logic.BORDER_DOWN / logic.TILE_SIZE) - 1 > offsetishe.y > logic.BORDER_UP / logic.TILE_SIZE:
            boxes = self.boxes(offset)
            if self.can_slide(offset.normalize()):
                for box in boxes:
                    return box.can_move_to(offset)
                return True
        return False

    def boxes(self, offset):
        result = []
        coord = self.center + offset
        for box in logic.all_sprites:
            if box.center == coord and (box is not self):
                result.append(box)
                result += box.boxes(offset)
        return result


def create_blocker(x: int, y: int, pict):
    return Box(0, 0, x, y, pict)


def create_h_slider(x: int, y: int, pict):
    return Box(0, 1, x, y, pict)


def create_v_slider(x: int, y: int, pict):
    return Box(1, 0, x, y, pict)


def create_vh_slider(x: int, y: int, pict):
    return Box(1, 1, x, y, pict)
