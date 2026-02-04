import matplotlib.pyplot as plt
import matplotlib.dates as mdates
from datetime import datetime, timedelta

# === Данные по пакетам работ ===
tasks = [
    ("1.1", "Управление проектом (PM)", "2025-09-22", "2026-01-22", "PM"),
    ("1.2.1", "Анализ и проектирование (Эксперт, PM)", "2025-09-29", "2025-10-06", "Expert"),
    ("1.2.2", "Дизайн и UX/UI (Дизайнер)", "2025-10-06", "2025-10-20", "Design"),
    ("1.2.3", "Веб-разработка (2 Разраб.)", "2025-10-20", "2025-12-05", "Dev"),
    ("1.2.4", "Интеграция платёжных систем (Backend)", "2025-12-05", "2025-12-12", "Dev"),
    ("1.2.5", "Интеграция служб доставки (Backend)", "2025-12-12", "2025-12-19", "Dev"),
    ("1.2.6", "Тестирование (QA, Команда)", "2025-12-19", "2026-01-05", "QA"),
    ("1.2.7", "Развёртывание и сервер (Dev, PM)", "2026-01-05", "2026-01-12", "Dev"),
    ("1.3", "Запуск и эксплуатация (Команда)", "2026-01-12", "2026-01-22", "PM"),
    ("1.4", "Маркетинг и продвижение (Дизайнер, PM)", "2025-10-20", "2026-01-22", "Marketing")
]

# === Цвета для ролей ===
colors = {
    "PM": "#8ECBEA",         # голубой
    "Expert": "#C6A0E8",     # фиолетовый
    "Design": "#FFD86E",     # жёлтый
    "Dev": "#A7EFA4",        # зелёный
    "QA": "#F28E8E",         # красный
    "Marketing": "#F4A4C0"   # розовый
}

# === Преобразуем даты ===
start_dates = [datetime.strptime(t[2], "%Y-%m-%d") for t in tasks]
end_dates = [datetime.strptime(t[3], "%Y-%m-%d") for t in tasks]
durations = [(end - start).days for start, end in zip(start_dates, end_dates)]

# === Настройка графика ===
fig, ax = plt.subplots(figsize=(12, 6))
ax.set_title("Диаграмма Ганта проекта «Интернет-магазин FitLife»", fontsize=14, weight='bold', pad=20)

# === Отрисовка полос (Gantt Bars) ===
for i, (code, desc, start, end, role) in enumerate(tasks):
    start_dt = datetime.strptime(start, "%Y-%m-%d")
    duration = (datetime.strptime(end, "%Y-%m-%d") - start_dt).days
    ax.barh(i, duration, left=start_dt, height=0.5,
            color=colors.get(role, 'gray'), edgecolor='black')
    ax.text(start_dt + timedelta(days=duration / 2), i,
            f"{code} {desc}", va='center', ha='center',
            fontsize=9, color='black', weight='bold')

# === Оформление осей ===
ax.set_yticks(range(len(tasks)))
ax.set_yticklabels([t[0] for t in tasks], fontsize=9)
ax.invert_yaxis()
ax.xaxis_date()
ax.xaxis.set_major_formatter(mdates.DateFormatter("%d.%m"))
ax.xaxis.set_major_locator(mdates.WeekdayLocator(interval=1))
plt.xticks(rotation=45)

# === Сетка и легенда ===
ax.grid(True, which='major', axis='x', linestyle='--', alpha=0.6)
legend_handles = [plt.Rectangle((0, 0), 1, 1, color=c) for c in colors.values()]
plt.legend(legend_handles, colors.keys(), title="Роли", loc="upper right")

# === Подписи по осям ===
ax.set_xlabel("Календарные даты (2025–2026)", fontsize=10, labelpad=10)
ax.set_ylabel("Пакеты работ (WBS)", fontsize=10, labelpad=10)

plt.tight_layout()
plt.show()
