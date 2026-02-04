package ru.vsu.cs.loseva;

import ru.vsu.cs.loseva.classes.Faculty;
import ru.vsu.cs.loseva.classes.Institute;
import ru.vsu.cs.loseva.classes.ResearchAssociate;

public class Main {
    public static void main(String[] args) {
        Faculty fkn = new Faculty("ФКН");
        Institute toizi = new Institute("ТОиЗИ", "297");
        Institute is = new Institute("ИС", "387");
        Institute tst = new Institute("ЦТ", "311п");
        Institute itu = new Institute("ИТУ", "312п");
        Institute piit = new Institute("ПиИТ", "380");
        fkn.setInstitutes(new Institute[]{toizi, is, tst, itu, piit});
        ResearchAssociate emp1 = new ResearchAssociate(1234, "Вахтин Алексей Александрович",
                "Компьютерная геометрия и графика");
        ResearchAssociate emp2 = new ResearchAssociate(1234, "Гавшин Алексей Леонидович",
                "Язык программирования Java");
        ResearchAssociate emp3 = new ResearchAssociate(1234, "Михайлов Евгений Михайлович",
                "Информационные технологии");
        ResearchAssociate emp4 = new ResearchAssociate(1234, "Соломатин Дмитрий Иванович",
                "Введение в программирование");
        ResearchAssociate emp5 = new ResearchAssociate(1234, "Чекмарев Андрей Игоревич",
                "Проектирование баз данных");
        piit.setEmployees(new ResearchAssociate[]{emp1, emp2, emp3, emp4, emp5});
        Institute[] fkn_institutes = fkn.getInstitutes();
        System.out.println("Кафедры на факультете " + fkn.getName() + ": \n");
        for (int i = 0; i < fkn_institutes.length; i++) {
            System.out.println(fkn_institutes[i].getName());
        }
        System.out.println("\n");
        ResearchAssociate[] piit_employees = piit.getEmployees();
        System.out.println("Сотрудники кафедры " + piit.getName() + ": \n");
        for (int i = 0; i < piit_employees.length; i++) {
            System.out.println(piit_employees[i].getName());
        }
    }
}