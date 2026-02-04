from kivymd.uix.screen import MDScreen
from kivymd.toast import toast
from app.logic.users import authenticate_user


class LoginScreen(MDScreen):

    def login(self):
        email = self.ids.email.text
        password = self.ids.password.text

        user = authenticate_user(email, password)

        if not user:
            toast("Неверный email или пароль")
            return

        self.manager.app.user_id = user["id"]
        self.manager.current = "home"
