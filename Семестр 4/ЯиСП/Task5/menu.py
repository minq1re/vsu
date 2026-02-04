from PyQt5 import QtWidgets
from PyQt5.QtCore import (QCoreApplication, QMetaObject, QRect, Qt)
from PyQt5.QtGui import QFont
from PyQt5.QtWidgets import *
from levels import Ui_LevelsWindow


class opnLvls(QtWidgets.QMainWindow):
    def __init__(self):
        super(opnLvls, self).__init__()
        self.ui = Ui_LevelsWindow()
        self.ui.setupUi(self)
class Ui_MenuWindow(object):
    def openLevelsWindow(self):
        self.lavals = opnLvls()
        self.lavals.show()

    def setupUi(self, MenuWindow):
        if not MenuWindow.objectName():
            MenuWindow.setObjectName(u"MenuWindow")
        MenuWindow.resize(230, 195)
        self.centralwidget = QWidget(MenuWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        self.levelsBtn = QPushButton(self.centralwidget)
        self.levelsBtn.setObjectName(u"levelsBtn")
        self.levelsBtn.setGeometry(QRect(20, 70, 201, 31))
        self.levelsBtn.clicked.connect(self.openLevelsWindow)

        self.label = QLabel(self.centralwidget)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(10, 0, 211, 61))
        font = QFont()
        font.setPointSize(16)
        font.setBold(True)
        font.setWeight(75)
        self.label.setFont(font)
        self.label.setAlignment(Qt.AlignCenter)
        MenuWindow.setCentralWidget(self.centralwidget)

        self.retranslateUi(MenuWindow)

        QMetaObject.connectSlotsByName(MenuWindow)

    def retranslateUi(self, MenuWindow):
        MenuWindow.setWindowTitle(QCoreApplication.translate("MenuWindow", u"Main menu", None))
        self.levelsBtn.setText(QCoreApplication.translate("MenuWindow", u"\u0423\u0440\u043e\u0432\u043d\u0438", None))
        self.label.setText(
            QCoreApplication.translate("MenuWindow", u"\u041a\u0443\u0431\u0438\u043a\u043e\u043d", None))