from kivymd.uix.screen import MDScreen


class MealDetailsScreen(MDScreen):
    meal_type = 0

    def on_pre_enter(self):
        # здесь ты потом подгрузишь продукты из БД
        pass
