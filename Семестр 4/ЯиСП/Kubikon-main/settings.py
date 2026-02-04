# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'settings.ui'
##
## Created by: Qt User Interface Compiler version 5.14.2
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################
from PySide6 import QtGui
from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QMetaObject,
    QObject, QPoint, QRect, QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor, QFont,
    QFontDatabase, QIcon, QKeySequence, QLinearGradient, QPalette, QPainter,
    QPixmap, QRadialGradient)
from PySide6.QtWidgets import *


class Ui_SettingsWindow(object):
    def color_picker_player(self):
        color = QColorDialog.getColor()
        self.playerLineEdit.setText(str(color.red()) + ', ' + str(color.green()) + ', ' + str(color.blue()))

    def color_picker_cube1(self):
        color = QColorDialog.getColor()
        self.Cube1LineEdit.setText(str(color.red()) + ', ' + str(color.green()) + ', ' + str(color.blue()))

    def color_picker_cube2(self):
        color = QColorDialog.getColor()
        self.Cube2LineEdit.setText(str(color.red()) + ', ' + str(color.green()) + ', ' + str(color.blue()))

    def color_picker_cube3(self):
        color = QColorDialog.getColor()
        self.Cube3LineEdit.setText(str(color.red()) + ', ' + str(color.green()) + ', ' + str(color.blue()))

    def color_picker_cube4(self):
        color = QColorDialog.getColor()
        self.Cube4LineEdit.setText(str(color.red()) + ', ' + str(color.green()) + ', ' + str(color.blue()))

    def color_picker_wall(self):
        color = QColorDialog.getColor()
        self.WallLineEdit.setText(str(color.red()) + ', ' + str(color.green()) + ', ' + str(color.blue()))

    def saveSett(self):
        sett = open('sett.ini', 'w')
        sett.write(self.playerLineEdit.text())
        sett.write(self.Cube1LineEdit.text())
        sett.write(self.Cube2LineEdit.text())
        sett.write(self.Cube3LineEdit.text())
        sett.write(self.Cube4LineEdit.text())
        sett.write(self.WallLineEdit.text())
        pass

    def loadIni(self):
        sett = open('sett.ini', 'r')
        filee = []
        for line in sett:
            filee.append(line)
        self.playerLineEdit.setText(filee[0])
        self.Cube1LineEdit.setText(filee[1])
        self.Cube2LineEdit.setText(filee[2])
        self.Cube3LineEdit.setText(filee[3])
        self.Cube4LineEdit.setText(filee[4])
        self.WallLineEdit.setText(filee[5])

    def setupUi(self, SettingsWindow):
        if not SettingsWindow.objectName():
            SettingsWindow.setObjectName(u"SettingsWindow")
        SettingsWindow.resize(329, 287)
        self.centralwidget = QWidget(SettingsWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        # self.backBtn = QPushButton(self.centralwidget)
        # self.backBtn.setObjectName(u"backBtn")
        # self.backBtn.setGeometry(QRect(10, 10, 311, 28))
        self.label = QLabel(self.centralwidget)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(20, 70, 141, 16))
        self.label.setAlignment(Qt.AlignRight|Qt.AlignTrailing|Qt.AlignVCenter)
        self.label_2 = QLabel(self.centralwidget)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setGeometry(QRect(20, 100, 141, 16))
        self.label_2.setAlignment(Qt.AlignRight|Qt.AlignTrailing|Qt.AlignVCenter)
        self.label_3 = QLabel(self.centralwidget)
        self.label_3.setObjectName(u"label_3")
        self.label_3.setGeometry(QRect(20, 130, 141, 16))
        self.label_3.setAlignment(Qt.AlignRight|Qt.AlignTrailing|Qt.AlignVCenter)
        self.label_4 = QLabel(self.centralwidget)
        self.label_4.setObjectName(u"label_4")
        self.label_4.setGeometry(QRect(20, 160, 141, 16))
        self.label_4.setAlignment(Qt.AlignRight|Qt.AlignTrailing|Qt.AlignVCenter)
        self.label_5 = QLabel(self.centralwidget)
        self.label_5.setObjectName(u"label_5")
        self.label_5.setGeometry(QRect(20, 190, 141, 16))
        self.label_5.setAlignment(Qt.AlignRight|Qt.AlignTrailing|Qt.AlignVCenter)
        self.label_6 = QLabel(self.centralwidget)
        self.label_6.setObjectName(u"label_6")
        self.label_6.setGeometry(QRect(20, 220, 141, 16))
        self.label_6.setAlignment(Qt.AlignRight|Qt.AlignTrailing|Qt.AlignVCenter)
        self.playerLineEdit = QLineEdit(self.centralwidget)
        self.playerLineEdit.setObjectName(u"playerLineEdit")
        self.playerLineEdit.setEnabled(False)
        self.playerLineEdit.setGeometry(QRect(170, 70, 113, 22))
        self.Cube1LineEdit = QLineEdit(self.centralwidget)
        self.Cube1LineEdit.setObjectName(u"Cube1LineEdit")
        self.Cube1LineEdit.setEnabled(False)
        self.Cube1LineEdit.setGeometry(QRect(170, 100, 113, 22))
        self.Cube2LineEdit = QLineEdit(self.centralwidget)
        self.Cube2LineEdit.setObjectName(u"Cube2LineEdit")
        self.Cube2LineEdit.setEnabled(False)
        self.Cube2LineEdit.setGeometry(QRect(170, 130, 113, 22))
        self.Cube3LineEdit = QLineEdit(self.centralwidget)
        self.Cube3LineEdit.setObjectName(u"Cube3LineEdit")
        self.Cube3LineEdit.setEnabled(False)
        self.Cube3LineEdit.setGeometry(QRect(170, 160, 113, 22))
        self.Cube4LineEdit = QLineEdit(self.centralwidget)
        self.Cube4LineEdit.setObjectName(u"Cube4LineEdit")
        self.Cube4LineEdit.setEnabled(False)
        self.Cube4LineEdit.setGeometry(QRect(170, 190, 113, 22))
        self.WallLineEdit = QLineEdit(self.centralwidget)
        self.WallLineEdit.setObjectName(u"WallLineEdit")
        self.WallLineEdit.setEnabled(False)
        self.WallLineEdit.setGeometry(QRect(170, 220, 113, 22))

        self.PlayerColorPicker = QPushButton(self.centralwidget)
        self.PlayerColorPicker.setObjectName(u"PlayerColorPicker")
        self.PlayerColorPicker.setGeometry(QRect(290, 70, 31, 22))
        self.PlayerColorPicker.clicked.connect(self.color_picker_player)

        self.Cube1ColorPicker = QPushButton(self.centralwidget)
        self.Cube1ColorPicker.setObjectName(u"Cube1ColorPicker")
        self.Cube1ColorPicker.setGeometry(QRect(290, 100, 31, 22))
        self.Cube1ColorPicker.clicked.connect(self.color_picker_cube1)

        self.Cube2ColorPicker = QPushButton(self.centralwidget)
        self.Cube2ColorPicker.setObjectName(u"Cube2ColorPicker")
        self.Cube2ColorPicker.setGeometry(QRect(290, 130, 31, 22))
        self.Cube2ColorPicker.clicked.connect(self.color_picker_cube2)

        self.Cube3ColorPicker = QPushButton(self.centralwidget)
        self.Cube3ColorPicker.setObjectName(u"Cube3ColorPicker")
        self.Cube3ColorPicker.setGeometry(QRect(290, 160, 31, 22))
        self.Cube3ColorPicker.clicked.connect(self.color_picker_cube3)

        self.Cube4ColorPicker = QPushButton(self.centralwidget)
        self.Cube4ColorPicker.setObjectName(u"Cube4ColorPicker")
        self.Cube4ColorPicker.setGeometry(QRect(290, 190, 31, 22))
        self.Cube4ColorPicker.clicked.connect(self.color_picker_cube4)

        self.WallColorPicker = QPushButton(self.centralwidget)
        self.WallColorPicker.setObjectName(u"WallColorPicker")
        self.WallColorPicker.setGeometry(QRect(290, 220, 31, 22))
        self.WallColorPicker.clicked.connect(self.color_picker_wall)

        self.saveBtn = QPushButton(self.centralwidget)
        self.saveBtn.setObjectName(u"saveBtn")
        self.saveBtn.setGeometry(QRect(192, 250, 131, 28))
        self.saveBtn.clicked.connect(self.saveSett)



        SettingsWindow.setCentralWidget(self.centralwidget)

        self.retranslateUi(SettingsWindow)

        QMetaObject.connectSlotsByName(SettingsWindow)
    # setupUi

    def retranslateUi(self, SettingsWindow):
        SettingsWindow.setWindowTitle(QCoreApplication.translate("SettingsWindow", u"Settings", None))
        # self.backBtn.setText(QCoreApplication.translate("SettingsWindow", u"<- \u041d\u0430\u0437\u0430\u0434", None))
        self.label.setText(QCoreApplication.translate("SettingsWindow", u"\u0426\u0432\u0435\u0442 \u0438\u0433\u0440\u043e\u043a\u0430", None))
        self.label_2.setText(QCoreApplication.translate("SettingsWindow", u"\u0426\u0432\u0435\u0442 \u043f\u0435\u0440\u0432\u043e\u0433\u043e \u043a\u0443\u0431\u0430", None))
        self.label_3.setText(QCoreApplication.translate("SettingsWindow", u"\u0426\u0432\u0435\u0442 \u0432\u0442\u043e\u0440\u043e\u0433\u043e \u043a\u0443\u0431\u0430", None))
        self.label_4.setText(QCoreApplication.translate("SettingsWindow", u"\u0426\u0432\u0435\u0442 \u0442\u0440\u0435\u0442\u044c\u0435\u0433\u043e \u043a\u0443\u0431\u0430", None))
        self.label_5.setText(QCoreApplication.translate("SettingsWindow", u"\u0426\u0432\u0435\u0442 \u0447\u0435\u0442\u0432\u0451\u0440\u0442\u043e\u0433\u043e \u043a\u0443\u0431\u0430", None))
        self.label_6.setText(QCoreApplication.translate("SettingsWindow", u"\u0426\u0432\u0435\u0442 \u0441\u0442\u0435\u043d\u043e\u043a", None))
        self.playerLineEdit.setText(QCoreApplication.translate("SettingsWindow", u"0, 0, 0", None))
        self.Cube1LineEdit.setText(QCoreApplication.translate("SettingsWindow", u"0, 0, 0", None))
        self.Cube2LineEdit.setText(QCoreApplication.translate("SettingsWindow", u"0, 0, 0", None))
        self.Cube3LineEdit.setText(QCoreApplication.translate("SettingsWindow", u"0, 0, 0", None))
        self.Cube4LineEdit.setText(QCoreApplication.translate("SettingsWindow", u"0, 0, 0", None))
        self.WallLineEdit.setText(QCoreApplication.translate("SettingsWindow", u"0, 0, 0", None))
        self.PlayerColorPicker.setText(QCoreApplication.translate("SettingsWindow", u"...", None))
        self.Cube1ColorPicker.setText(QCoreApplication.translate("SettingsWindow", u"...", None))
        self.Cube2ColorPicker.setText(QCoreApplication.translate("SettingsWindow", u"...", None))
        self.Cube3ColorPicker.setText(QCoreApplication.translate("SettingsWindow", u"...", None))
        self.Cube4ColorPicker.setText(QCoreApplication.translate("SettingsWindow", u"...", None))
        self.WallColorPicker.setText(QCoreApplication.translate("SettingsWindow", u"...", None))
        self.saveBtn.setText(QCoreApplication.translate("SettingsWindow", u"\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c", None))
        self.loadIni()
    # retranslateUi

