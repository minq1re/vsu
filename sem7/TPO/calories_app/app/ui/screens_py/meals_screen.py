from kivymd.uix.screen import MDScreen


class MealsScreen(MDScreen):

    def open_meal(self, meal_type):
        self.manager.get_screen("meal_details").meal_type = meal_type
        self.manager.current = "meal_details"
