import json
import box
import game
import vector2d


def load_level(number):
    matrix= read_file("level" + str(number))
    units = []
    for i in range(len(matrix)):
        for j in range(len(matrix[0])):
            if matrix[i][j] == 1:
                units.append(box.create_blocker(i, j, game.BLOCKER))
            elif matrix[i][j] == 2:
                units.append(box.create_h_slider(i, j, game.H_SLIDER))
            elif matrix[i][j] == 3:
                units.append(box.create_v_slider(i, j, game.V_SLIDER))
            elif matrix[i][j] == 4:
                units.append(box.create_vh_slider(i, j, game.VH_SLIDER))
            elif matrix[i][j] == 5:
                units.append(box.create_vh_slider(i, j, game.C))
            elif matrix[i][j] == 6:
                units.append(box.create_vh_slider(i, j, game.Y))
            elif matrix[i][j] == 7:
                units.append(box.create_vh_slider(i, j, game.B))
            elif matrix[i][j] == 8:
                units.append(box.create_vh_slider(i, j, game.E))
            elif matrix[i][j] == 9:
                units.append(box.create_vh_slider(i, j, game.R))
            elif matrix[i][j] == 10:
                units.append(box.create_vh_slider(i, j, game.BBIG))
            elif matrix[i][j] == 11:
                units.append(box.create_vh_slider(i, j, game.O))
            elif matrix[i][j] == 12:
                units.append(box.create_vh_slider(i, j, game.X))
    return units


def write_level(number, boxes, height, width):
    print(height, width)
    height = int(height)
    width = int(width)
    ret = [0] * height
    for i in range(height):
        ret[i] = [0] * width
    for box in boxes:
        dir = box.slideDirection
        if dir.x == 0 and dir.y == 0:
            ret[box.center.x][box.center.y] = 1
        elif dir.x == 0 and dir.y == 1:
            ret[box.center.x][box.center.y] = 3
        elif dir.x == 1 and dir.y == 0:
            ret[box.center.x][box.center.y] = 2
        elif dir.x == 1 and dir.y == 1:
            ret[box.center.x][box.center.y] = 4
    write_file("level" + str(number), ret)


def read_file(filename):
    with open(filename, encoding="utf-8") as json_file:
        data = json.load(json_file)
        matrix = []
        for s in data['level']:
            matrix = s['level_struct']

    return matrix


def write_file(filename, something):
    data = {'level': []}
    data['level'].append({
        'level_struct': something
    })
    with open(filename, 'w', encoding="utf-8"'') as outfile:
        json.dump(data, outfile)
