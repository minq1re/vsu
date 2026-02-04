from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PySide6.QtWidgets import QWidget, QApplication

level = []
filling_line = []


def getBlock(level, blockId):
    x, y = 0, 0
    for line in level:
        for block in line:
            if int(block) is blockId:
                return x, y
            x += 1
        y += 1
        x = 0
    return 0, 0


def isLine(level, num):
    y, x = getBlock(level, num)
    try:
        if level[x - 1][y] == num or level[x + 1][y] == num or level[x][y - 1] == num or level[x][y + 1] == num:
            return True
    except(IndexError):
        pass


def array_is_fill(arr):
    is_fill = False
    for el in arr:
        if el:
            is_fill = True
        else:
            return False
    return is_fill


class drawCustomWidget(QWidget):
    def __init__(self, lnum):
        super(drawCustomWidget, self).__init__()
        self.resize(600, 600)
        self.setWindowTitle("Custom title")
        self.heroX = 0
        self.heroY = 0
        self.isWin = False
        self.max_block_id = 0

        f = open(str(lnum)+'.txt')
        level.clear()
        for line in f:
            stringLevel = line.replace('\n', '').split()

            intLevel = []
            for el in stringLevel:
                intLevel.append(int(el))

            level.append(intLevel)

            max_id = max(intLevel)
            self.max_block_id = max_id if max_id > self.max_block_id else self.max_block_id
        print(level)

    def paintEvent(self, event):
        rec = event.rect()

        if False:
            rec = QRect()
        painter = QPainter()
        painter.begin(self)
        x = 0
        y = 0

        filling_line = []
        for id, el in enumerate(range(3, self.max_block_id + 1)):
            filling_line.append(isLine(level, el))
        if array_is_fill(filling_line):
            self.isWin = True
        print(filling_line)

        if self.isWin:
            print('You win')
            painter.setPen(QColor(168, 34, 3))
            painter.setFont(QFont('Arial', 12))
            painter.drawText(QPoint(400, 400), 'Your Win!')

        painter.setPen(QPen(QBrush(Qt.white), 2))
        painter.setBrush(QBrush(Qt.black))
        settings = open('sett.ini', 'r')
        settClr = []
        for line in settings:
            ln = line.split(',')
            settClr.append([int(ln[0]), int(ln[1]), int(ln[2])])
        for line in level:
            for block in line:
                painter.setBrush(QBrush(Qt.black))
                if int(block) == 0:
                    pass
                if int(block) == 1:
                    painter.setBrush(QBrush(QColor().fromRgb(settClr[5][0], settClr[5][1], settClr[5][2])))
                    painter.drawRect(QRect(x, y, 50, 50))
                if int(block) == 2:
                    painter.setBrush(QBrush(QColor().fromRgb(settClr[0][0], settClr[0][1], settClr[0][2])))
                    painter.drawRect(QRect(x, y, 50, 50))
                if int(block) == 3:
                    painter.setBrush(QBrush(QColor().fromRgb(settClr[1][0], settClr[1][1], settClr[1][2])))
                    painter.drawRect(QRect(x, y, 50, 50))
                if int(block) == 4:
                    painter.setBrush(QBrush(QColor().fromRgb(settClr[2][0], settClr[2][1], settClr[2][2])))
                    painter.drawRect(QRect(x, y, 50, 50))
                if int(block) == 5:
                    painter.setBrush(QBrush(QColor().fromRgb(settClr[3][0], settClr[3][1], settClr[3][2])))
                    painter.drawRect(QRect(x, y, 50, 50))
                if int(block) == 6:
                    painter.setBrush(QBrush(QColor().fromRgb(settClr[4][0], settClr[4][1], settClr[4][2])))
                    painter.drawRect(QRect(x, y, 50, 50))
                x += 50
            y += 50
            x = 0

        # painter.drawLine(0, 0, rec.width(), rec.height())
        painter.end()

    def keyPressEvent(self, e):
        if e.key() == Qt.Key_Escape:
            self.close()
        if e.key() == Qt.Key_W:
            self.heroY -= 10
            self.repaint()
        if e.key() == Qt.Key_S:
            self.heroY += 10
            self.repaint()
        if e.key() == Qt.Key_A:
            self.heroX -= 10
            self.repaint()
        if e.key() == Qt.Key_D:
            self.heroX += 10
            self.repaint()
        i, j = getBlock(level, 2)
        print(i, j)
        self.repaint()

        # if e.key() == Qt.Key_W:
        #     if level[i - 1][j] == 0:
        #         level[i][j] = 0
        #         level[i - 1][j] = 2
        #     if level[i - 1][j] == 4 and level[i - 2][j] == 0:
        #         level[i][j] = 0
        #         level[i - 2][j] = 4
        #         level[i - 1][j] = 2
        #     if level[i - 1][j] == 3 and level[i - 2][j] == 0:
        #         level[i][j] = 0
        #         level[i - 2][j] = 3
        #         level[i - 1][j] = 2
        #     self.repaint()
        #     return 0
        # if e.key() == Qt.Key_S:
        #     if level[i + 1][j] == 0:
        #         level[i][j] = 0
        #         level[i + 1][j] = 2
        #     if level[i + 1][j] == 4 and level[i + 2][j] == 0:
        #         level[i][j] = 0
        #         level[i + 2][j] = 4
        #         level[i + 1][j] = 2
        #     if level[i + 1][j] == 3 and level[i + 2][j] == 0:
        #         level[i][j] = 0
        #         level[i + 2][j] = 3
        #         level[i + 1][j] = 2
        #     self.repaint()
        #     return 0
        # if e.key() == Qt.Key_A:
        #     if level[i][j - 1] == 0:
        #         level[i][j] = 0
        #         level[i][j - 1] = 2
        #         self.repaint()
        # if e.key() == Qt.Key_D:
        #     if level[i][j + 1] == 0:
        #         level[i][j] = 0
        #         level[i][j + 1] = 2
        #         self.repaint()

        if e.key() == Qt.Key_W:
            if level[j - 1][i] == 0:
                level[j][i] = 0
                level[j - 1][i] = 2
                self.repaint()
                return 0
            for block_num in range(3, self.max_block_id + 1):
                if level[j - 1][i] == block_num and level[j - 2][i] == 0:
                    level[j - 2][i] = block_num
                    level[j][i] = 0
                    level[j - 1][i] = 2
                    self.repaint()
                    return 0
        if e.key() == Qt.Key_S:
            if level[j + 1][i] == 0:
                level[j][i] = 0
                level[j + 1][i] = 2
                self.repaint()
                return 0
            for block_num in range(3, self.max_block_id + 1):
                if level[j + 1][i] == block_num and level[j + 2][i] == 0:
                    level[j + 2][i] = block_num
                    level[j][i] = 0
                    level[j + 1][i] = 2
                    self.repaint()
                    return 0
        if e.key() == Qt.Key_A:
            if level[j][i - 1] == 0:
                level[j][i] = 0
                level[j][i - 1] = 2
                self.repaint()
                return 0
            for block_num in range(3, self.max_block_id + 1):
                if level[j][i - 1] == block_num and level[j][i - 2] == 0:
                    level[j][i - 2] = block_num
                    level[j][i] = 0
                    level[j][i - 1] = 2
                    self.repaint()
                    return 0
        if e.key() == Qt.Key_D:
            if level[j][i + 1] == 0:
                level[j][i] = 0
                level[j][i + 1] = 2
                self.repaint()
                return 0
            for block_num in range(3, self.max_block_id + 1):
                if level[j][i + 1] == block_num and level[j][i + 2] == 0:
                    level[j][i + 2] = block_num
                    level[j][i] = 0
                    level[j][i + 1] = 2
                    self.repaint()
                    return 0


# if __name__ == '__main__':
#     app = QApplication([])
#     w = drawCustomWidget()
#     w.show()
#     app.exec_()
