# -*- coding: utf-8 -*-
from PySide6 import QtWidgets
from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QMetaObject,
                            QObject, QPoint, QRect, QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor, QFont,
                           QFontDatabase, QIcon, QKeySequence, QLinearGradient, QPalette, QPainter,
                           QPixmap, QRadialGradient)
from PySide6.QtWidgets import *
from levels import Ui_LevelsWindow
from rules import Ui_RulesWindow
from settings import Ui_SettingsWindow


class opnLvls(QtWidgets.QMainWindow):
    def __init__(self):
        super(opnLvls, self).__init__()
        self.ui = Ui_LevelsWindow()
        self.ui.setupUi(self)

class opnSettings(QtWidgets.QMainWindow):
    def __init__(self):
        super(opnSettings, self).__init__()
        self.ui = Ui_SettingsWindow()
        self.ui.setupUi(self)

class opnRules(QtWidgets.QMainWindow):
    def __init__(self):
        super(opnRules, self).__init__()
        self.ui = Ui_RulesWindow()
        self.ui.setupUi(self)

class Ui_MenuWindow(object):
    def openSettingsWindow(self):
        self.settings = opnSettings()
        self.settings.show()

    def openLevelsWindow(self):
        self.lavals = opnLvls()
        self.lavals.show()

    def openRulesWindow(self):
        self.rules = opnRules()
        self.rules.show()

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

        self.settingBtn = QPushButton(self.centralwidget)
        self.settingBtn.setObjectName(u"settingBtn")
        self.settingBtn.setGeometry(QRect(20, 110, 201, 31))
        self.settingBtn.clicked.connect(self.openSettingsWindow)

        self.label = QLabel(self.centralwidget)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(10, 0, 211, 61))
        font = QFont()
        font.setPointSize(16)
        font.setBold(True)
        font.setWeight(75)
        self.label.setFont(font)
        self.label.setAlignment(Qt.AlignCenter)
        self.descriptionBtn = QPushButton(self.centralwidget)
        self.descriptionBtn.setObjectName(u"descriptionBtn")
        self.descriptionBtn.setGeometry(QRect(20, 150, 201, 31))
        self.descriptionBtn.clicked.connect(self.openRulesWindow)
        MenuWindow.setCentralWidget(self.centralwidget)

        self.retranslateUi(MenuWindow)

        QMetaObject.connectSlotsByName(MenuWindow)

    # setupUi

    def retranslateUi(self, MenuWindow):
        MenuWindow.setWindowTitle(QCoreApplication.translate("MenuWindow", u"Main menu", None))
        self.levelsBtn.setText(QCoreApplication.translate("MenuWindow", u"\u0423\u0440\u043e\u0432\u043d\u0438", None))
        self.settingBtn.setText(
            QCoreApplication.translate("MenuWindow", u"\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438", None))
        self.label.setText(
            QCoreApplication.translate("MenuWindow", u"\u041a\u0443\u0431\u0438\u043a\u043e\u043d", None))
        self.descriptionBtn.setText(
            QCoreApplication.translate("MenuWindow", u"\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435", None))
    # retranslateUi

