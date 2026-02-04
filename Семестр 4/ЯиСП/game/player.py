import logic
from collider_box import *
import pygame


class Player(ColliderBox):
    def __init__(self, x, y, img):
        ColliderBox.__init__(self, x, y, logic.TILE_SIZE, logic.TILE_SIZE)
        self.image = img

    def draw(self, screen):
        screen.blit(self.image, (self.center.x * logic.TILE_SIZE, self.center.y * logic.TILE_SIZE))
