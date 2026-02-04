from kivymd.app import MDApp
from kivy.lang import Builder
from kivy.core.window import Window
from kivy.utils import platform

from app.ui.navigation import RootNavigation

# Подключение экранов
from app.ui.screens_py.login_screen import LoginScreen
from app.ui.screens_py.home_screen import HomeScreen
from app.ui.screens_py.meals_screen import MealsScreen
from app.ui.screens_py.meal_details_screen import MealDetailsScreen
from app.ui.screens_py.add_product_screen import AddProductScreen
from app.ui.screens_py.statistics_screen import StatisticsScreen
from app.ui.screens_py.goals_screen import GoalsScreen
from app.ui.screens_py.recommendations_screen import RecommendationsScreen


class CaloriesApp(MDApp):

    def build(self):
        self.title = "Calories App"

        # Загружаем KV файлы
        Builder.load_file("app/ui/kv/login.kv")
        Builder.load_file("app/ui/kv/home.kv")
        Builder.load_file("app/ui/kv/meals.kv")
        Builder.load_file("app/ui/kv/meal_details.kv")
        Builder.load_file("app/ui/kv/add_product.kv")
        Builder.load_file("app/ui/kv/statistics.kv")
        Builder.load_file("app/ui/kv/goals.kv")
        Builder.load_file("app/ui/kv/recommendations.kv")

        return RootNavigation()


if __name__ == "__main__":
    CaloriesApp().run()
