import os
import level_reader
import logic
from collider_box import *
import pygame
from logic import *
import player

WIDTH = 617
HEIGHT = 580
FPS = 30
GRAY = (120, 120, 120)
BLACK = (0, 0, 0)
DIRECTION_UP = Vector2D(0, -1)
DIRECTION_DOWN = Vector2D(0, 1)
DIRECTION_LEFT = Vector2D(-1, 0)
DIRECTION_RIGHT = Vector2D(1, 0)
BORDER_UP = logic.BORDER_UP
BORDER_DOWN = logic.BORDER_DOWN
BORDER_LEFT = logic.BORDER_LEFT
BORDER_RIGHT = logic.BORDER_RIGHT
TILE_SIZE = logic.TILE_SIZE
LEVEL_NUM = 0
LEVEL_NUM_MAX = 2

GAME_FOLDER = os.path.dirname(__file__)
SOUND_FOLDER = os.path.join(GAME_FOLDER, 'sound')
IMAGE_FOLDER = os.path.join(GAME_FOLDER, 'img')

pygame.init()
pygame.mixer.init()  # для звука

my_font = pygame.font.SysFont('Comic Sans MS', 15)
my_font_win = pygame.font.SysFont('Comic Sans MS', 40)
text1 = my_font.render('Arrows to move', True, (0, 0, 0))
text2 = my_font.render('R to retry level', True, (0, 0, 0))
text3 = my_font.render('SLIDERS-', True, (0, 0, 0))
text4 = my_font.render('These boxes slide in the', True, (0, 0, 0))
text5 = my_font.render('directions of their arrowheads', True, (0, 0, 0))
text6 = my_font.render('BLOCKERS-', True, (0, 0, 0))
text7 = my_font.render('These boxes do not slide at all', True, (0, 0, 0))
text8 = my_font_win.render('YOU WIN!', True, (0, 0, 0))
textRect1 = text1.get_rect()
textRect2 = text2.get_rect()
textRect3 = text3.get_rect()
textRect4 = text4.get_rect()
textRect5 = text5.get_rect()
textRect6 = text6.get_rect()
textRect7 = text7.get_rect()
textRect8 = text8.get_rect()

screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("CyberBox")
textRect1.center = (WIDTH / 2, 520)
textRect2.center = (WIDTH / 2, 535)
textRect3.center = (WIDTH / 2 - 50, 220)
textRect4.center = (WIDTH / 2 + 80, 210)
textRect5.center = (WIDTH / 2 + 110, 230)
textRect6.center = (WIDTH / 2 - 50, 300)
textRect7.center = (WIDTH / 2 + 110, 300)
textRect8.center = (WIDTH / 2, 250)

pygame.mixer.music.load(os.path.join(SOUND_FOLDER, 'music.mp3'))
pygame.mixer.music.play(-1)

H_SLIDER = pygame.image.load(os.path.join(IMAGE_FOLDER, 'slideleft.png')).convert()
V_SLIDER = pygame.image.load(os.path.join(IMAGE_FOLDER, 'slideup.png')).convert()
VH_SLIDER = pygame.image.load(os.path.join(IMAGE_FOLDER, 'slider.png')).convert()
BLOCKER = pygame.image.load(os.path.join(IMAGE_FOLDER, 'block.png')).convert()
B = pygame.image.load(os.path.join(IMAGE_FOLDER, 'b.png')).convert()
BBIG = pygame.image.load(os.path.join(IMAGE_FOLDER, 'Bbig.png')).convert()
C = pygame.image.load(os.path.join(IMAGE_FOLDER, 'c.png')).convert()
E = pygame.image.load(os.path.join(IMAGE_FOLDER, 'e.png')).convert()
O = pygame.image.load(os.path.join(IMAGE_FOLDER, 'o.png')).convert()
R = pygame.image.load(os.path.join(IMAGE_FOLDER, 'r.png')).convert()
X = pygame.image.load(os.path.join(IMAGE_FOLDER, 'x.png')).convert()
B = pygame.image.load(os.path.join(IMAGE_FOLDER, 'b.png')).convert()
Y = pygame.image.load(os.path.join(IMAGE_FOLDER, 'y.png')).convert()
PLAYER = pygame.image.load(os.path.join(IMAGE_FOLDER, 'playa.png')).convert()
PLAYER_POS_START = Vector2D(13, 5)

clock = pygame.time.Clock()

playe = player.Player(PLAYER_POS_START.x, PLAYER_POS_START.y, PLAYER)

all_sprites.extend(level_reader.load_level(LEVEL_NUM))


# h_box = box.create_blocker(1, 3, BLOCKER)
# all_sprites.append(h_box)

last_step = DIRECTION_UP


#level_reader.write_level(2,all_sprites,HEIGHT/TILE_SIZE+1,WIDTH/TILE_SIZE+1)

def window():
    running = True
    global last_step
    global LEVEL_NUM
    while running:
        clock.tick(FPS)

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            pressed = pygame.key.get_pressed()
            if pressed[pygame.K_UP]:
                step(DIRECTION_UP, playe)
                last_step = DIRECTION_UP
                # print(playe.center.x, playe.center.y)
            elif pressed[pygame.K_DOWN]:
                step(DIRECTION_DOWN, playe)
                last_step = DIRECTION_DOWN
                # print(playe.center.x, playe.center.y)
            elif pressed[pygame.K_LEFT]:
                step(DIRECTION_LEFT, playe)
                last_step = DIRECTION_LEFT
                # print(playe.center.x, playe.center.y)
            elif pressed[pygame.K_RIGHT]:
                step(DIRECTION_RIGHT, playe)
                last_step = DIRECTION_RIGHT
                # print(playe.center.x, playe.center.y)
            elif pressed[pygame.K_r]:
                clear()
            elif pressed[pygame.K_1]:
                LEVEL_NUM = 1
                clear()

        collide_box()
        screen.fill(GRAY)

        if LEVEL_NUM != 0 and LEVEL_NUM <= LEVEL_NUM_MAX:
            playe.draw(screen)
        elif LEVEL_NUM == 0:
            screen.blit(text3, textRect3)
            screen.blit(text4, textRect4)
            screen.blit(text5, textRect5)
            screen.blit(text6, textRect6)
            screen.blit(text7, textRect7)
        else:
            screen.blit(text8, textRect8)

        for box in all_sprites:
            box.draw(screen)
        pygame.draw.line(screen, BLACK, (BORDER_LEFT, BORDER_UP), (BORDER_RIGHT / 2 - 25, BORDER_UP), width=3)
        pygame.draw.line(screen, BLACK, (BORDER_RIGHT / 2 - 25, BORDER_UP), (BORDER_RIGHT / 2 - 25, 17), width=3)
        pygame.draw.line(screen, BLACK, (BORDER_RIGHT / 2 - 25, 17), (BORDER_RIGHT / 2 + 25, 17), width=3)
        pygame.draw.line(screen, BLACK, (BORDER_RIGHT / 2 + 25, 17), (BORDER_RIGHT / 2 + 25, BORDER_UP), width=3)
        pygame.draw.line(screen, BLACK, (BORDER_RIGHT / 2 + 25, BORDER_UP), (BORDER_RIGHT, BORDER_UP), width=3)
        pygame.draw.line(screen, BLACK, (BORDER_RIGHT, BORDER_UP), (BORDER_RIGHT, BORDER_DOWN), width=3)
        pygame.draw.line(screen, BLACK, (BORDER_RIGHT, BORDER_DOWN), (BORDER_LEFT, BORDER_DOWN), width=3)
        pygame.draw.line(screen, BLACK, (BORDER_LEFT, BORDER_DOWN), (BORDER_LEFT, BORDER_UP), width=3)
        screen.blit(text1, textRect1)
        screen.blit(text2, textRect2)
        if playe.center.x == 7 and playe.center.y == 1:
            LEVEL_NUM += 1
            clear()

        pygame.display.flip()


def collide_box():
    global last_step
    for box in all_sprites:
        if type(box) is not player.Player:
            if ColliderBox.collide_vs_box(playe, box):
                last_step_norm = last_step.normalize()
                collide_result = box.on_collide(last_step_norm)
                if collide_result:
                    step(-last_step, playe)


def clear():
    all_sprites.clear()
    if LEVEL_NUM <= LEVEL_NUM_MAX:
        all_sprites.extend(level_reader.load_level(LEVEL_NUM))
    playe.set_center(PLAYER_POS_START.x, PLAYER_POS_START.y)
