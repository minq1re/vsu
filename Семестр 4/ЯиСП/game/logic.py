all_sprites = []
WIN = True
BORDER_UP = 90
BORDER_DOWN = 490
BORDER_LEFT = 20
BORDER_RIGHT = 603
TILE_SIZE = 40


def step(vector, playe):
    vz = vector + playe.center
    if BORDER_LEFT / TILE_SIZE <= vz.x < (BORDER_RIGHT / TILE_SIZE) - 1 and (
            BORDER_DOWN / TILE_SIZE) - 1 > vz.y > BORDER_UP / TILE_SIZE:
        playe.center = vz
    if ((BORDER_RIGHT / 2) - 25) / TILE_SIZE <= vz.x <= ((BORDER_RIGHT / 2) + 25) / TILE_SIZE and vz.y <= (
            BORDER_UP) / TILE_SIZE:
        playe.center = vz