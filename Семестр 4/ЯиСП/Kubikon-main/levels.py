# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'levels.ui'
##
## Created by: Qt User Interface Compiler version 5.14.2
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################
import sys

from PySide6 import QtWidgets
from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QMetaObject,
                            QObject, QPoint, QRect, QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor, QFont,
                           QFontDatabase, QIcon, QKeySequence, QLinearGradient, QPalette, QPainter,
                           QPixmap, QRadialGradient)
from PySide6.QtWidgets import *

from game import drawCustomWidget


class Ui_LevelsWindow(object):
    def openLevel1(self):
        self.lvl = drawCustomWidget('level1')
        self.lvl.show()

    def openLevel2(self):
        self.lvl = drawCustomWidget('level2')
        self.lvl.show()

    def setupUi(self, LevelsWindow):
        if not LevelsWindow.objectName():
            LevelsWindow.setObjectName(u"LevelsWindow")
        LevelsWindow.resize(156, 154)
        self.centralwidget = QWidget(LevelsWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        # self.backBtn = QPushButton(self.centralwidget)
        # self.backBtn.setObjectName(u"backBtn")
        # self.backBtn.setGeometry(QRect(20, 20, 121, 28))
        self.pushButton_2 = QPushButton(self.centralwidget)
        self.pushButton_2.setObjectName(u"pushButton_2")
        self.pushButton_2.setGeometry(QRect(20, 70, 121, 28))
        self.pushButton_2.clicked.connect(self.openLevel1)

        self.pushButton_3 = QPushButton(self.centralwidget)
        self.pushButton_3.setObjectName(u"pushButton_3")
        self.pushButton_3.setGeometry(QRect(20, 110, 121, 28))
        self.pushButton_3.clicked.connect(self.openLevel2)

        LevelsWindow.setCentralWidget(self.centralwidget)

        self.retranslateUi(LevelsWindow)

        QMetaObject.connectSlotsByName(LevelsWindow)

    # setupUi

    def retranslateUi(self, LevelsWindow):
        LevelsWindow.setWindowTitle(QCoreApplication.translate("LevelsWindow", u"Levels", None))
        # self.backBtn.setText(QCoreApplication.translate("LevelsWindow", u"<- \u041d\u0430\u0437\u0430\u0434", None))
        self.pushButton_2.setText(
            QCoreApplication.translate("LevelsWindow", u"\u0423\u0440\u043e\u0432\u0435\u043d\u044c 1", None))
        self.pushButton_3.setText(
            QCoreApplication.translate("LevelsWindow", u"\u0423\u0440\u043e\u0432\u0435\u043d\u044c 2", None))
    # retranslateUi
