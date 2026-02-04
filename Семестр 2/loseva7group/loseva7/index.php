<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href='style.css'>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?;600&family=Tinos:ital@0;1&display=swap" rel="stylesheet">
  <title>Эртильские новости</title>
</head>
<body>
  <div class='header' id="main">
    <div class='container'>
      <div class='header-line'>

        <div class="header-logo">
          <img src="img/logotype.png" alt="Логотип сайта" style="width: 30;">
        </div>

        <div class="nav">
          <a class="nav-item" href="#main">ГЛАВНАЯ</a>
          <a class="nav-item" href="#news">НОВОСТИ</a>
          <a class="nav-item" href="#about">О НАС</a>
          <a class="nav-item" href="#feedback">ОБРАТНАЯ СВЯЗЬ</a>
          <a class="nav-item" href="#followers">СПИСОК ПОЛЬЗОВАТЕЛЕЙ</a>
        </div>
        
        <div class="phone">
          <div class="phone-holder">
            <div class="phone-img">
              <img src="img/phone.png">
            </div>

            <div class="number">
              <a href="*" class="num">+7(952)-956-12-72</a>
            </div>
          </div>

          <div class="phone-text">
            Свяжитесь с нами!
          </div>

        </div>
      </div>

      <div class="header-down">
        <div class="header-title">
          Все главные новости

          <div class="header_subtitle">
            Эртильского района
          </div>
        </div>

        <div class="header-center-img">
          <img src="img/center.jpg" alt="" style="width:400px">
        </div>

        <div class="header-center-text">
          Свежие выпуски уже в продаже!
        </div>
      </div>

    </div>
  </div>

  <div class="about">

    <div class="container">

      <div class="about-holder">

        <div class="about-info">

          <div class="about-title" id="about">
            О нас
          </div>

          <div class="about-description">
            <br>Друзья! Мы рады приветствовать вас на сайте Эртильского филиала <a href="https://riavrn.ru/">"РИА "Воронеж"</a> - районной общественно-политической газеты "Эртильские новости".</br>
            <br>Ежедневно на сайте «Эртильские новости» выходит более 30 сообщений, формируя целостную и объективную картину событий как в Эртиле, так и в районе. Наши основные принципы — оперативность, достоверность, объективность. На ресурсе реализованы современные и перспективные тренды цифровых медиа: спецпроекты, инфографика, фото и видео и другие.</br>
            <br>Показатели сайта: более 80 000 посетителей в день, более 8 000 000 просмотров в месяц. Более 80% аудитории читают ertil-news.ru с мобильных устройств — смартфонов и планшетов. Среди нашей аудитории 42% мужчин и 58% женщин. Ядро аудитории составляют люди от 25 до 44 лет.</br>
            <br>Издание «Эртильские новости» представлено в самых популярных социальных сетях и мессенджерах: «ВКонтакте», «Одноклассники», Twitter и Telegram.</br>
         </div>

       </div>

        <div class="about-img">
          <img src="img/about.jpg" alt="">
        </div>

      </div>
      
    </div>

  </div>

  <div class="newss">

    <div class="container">

      <div class="newss-title">
        <b>Новости</b>
      </div>

      <input type="button" onclick="deleteAllCookies()" value="Удалить cookie">

      <div class="news-holder">
        <div class="text">

          <ul id="wrap">
            <li id="block1">
              <div class="news-img">
                <img class="news-img" src="img/n1.jpg" style="width:350px">
              </div>
              <h2>Новости медицины</h2>
              <div class="content">
                <div class="news-text">
                  <p>В Воронежской области зафиксировали более 483 тыс случаев ковида. Общее количество выздоровевших превысило 470 тыс.</p>
                </div>
              </div>
           </li>
          <li id="block2">
          <div class="news-img">
              <img class="news-img" src="img/n2.jpg" style="width:350px">
          </div>
          <h2>Новости спорта</h2>
            <div class="content">
              <div class="news-text">
                <p>В городе Усмани прошел открытый турнир по вольной борьбе среди юношей и девушек 2008 года рождения. Cпортсменки вернулись домой с одной золотой и тремя серебряными медалями.</p>
              </div>
            </div>
          </li>
          <li id="block3">
            <div class="news-img">
              <img class="news-img" src="img/n3.jpg" style="width:350px">
            </div>
            <h2>Новости погоды</h2>
            <div class="content">
              <div class="news-text">
                <p>До +13 градусов ожидается в Воронежской области 9 марта. Синоптики прогнозируют небольшие дожди.</p>
              </div>
            </div>
          </li>
        </ul>
    
        <script> 

          function deleteAllCookies() {
            var listItems = document.querySelectorAll("#wrap>li");
            for (var i = 0; i < listItems.length; i++) {
              var listItem = listItems[i];
              deleteCookie(listItem.getAttribute("id"));
            }
          } 

          function setCookie(name, value, options) {
            if (options.expires) {
              var date = new Date();
              date.setTime(date.getTime() + 1000 * 60 * 60 * 24 * options.expires);
              options.expires = date.toUTCString();
            }
            var forSave = name + "=" + value;
            for (var key in options) {
              forSave += ";" + key + "=" + options[key];
            }
            document.cookie = forSave;
          }

          function getCookie(name) {
            var result = document.cookie.match(
              new RegExp("(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, "\\$1") + "=([^;]*)")
            );
            return result ? result[1] : undefined;
          }

          function deleteCookie(name) {
            setCookie(name, "", { expires: -1 });
          }

          function updateListItems() {
            var listItems = document.querySelectorAll("#wrap>li");
            for (var i = 0; i < listItems.length; i++) {
              var listItem = listItems[i];
              if (getCookie(listItem.getAttribute("id")) == "show") {
                listItem.classList.add("show");
              }
              if (listItem.classList.contains("show")) {
                listItem.querySelector(".content").style.display = "block";
              }
            }
          }

          function addHeaderEventListeners() {
            var headers = document.querySelectorAll("#wrap>li>h2");
            for (var i = 0; i < headers.length; i++) {
              var header = headers[i];
              header.addEventListener("click", function (event) {
                var parent = this.parentNode;
                parent.classList.toggle("show");
                if (parent.classList.contains("show")) {
                  parent.querySelector(".content").style.display = "block";
                  setCookie(parent.getAttribute("id"), "show", { expires: 10 });
                } else {
                  parent.querySelector(".content").style.display = "none";
                  deleteCookie(parent.getAttribute("id"));
                }
              });
            }
          }

          updateListItems();
          addHeaderEventListeners();
          
        </script>
      </div>

    </div>
  </div>

  <div class="feedback">

    <div class="container">
      <div class="feedback-title" id="feedback">
        Обратная связь
      </div>

      <div class="me">
        <div class="me-img">
          <img src="img/me.jpg" alt="администратор сайта" style="width:300px">
        </div>

        <div class="feedback-text">
          <p>Есть вопросы и предложения? Свяжитесь с нашим <a href="mailto: elizavetaloseva99@gmail.com">администратором</a> - Лосевой Елизаветой Юрьевной, студенткой 1 курса ФКН ВГУ, рожденной в городе Эртиле, которому посвящен этот сайт, 22 сентября 2004 года. </p>

          <p>Соцсети: <a href='https://vk.com/kss_drken'>ВКонтакте</a>, <a href="https://m.ok.ru/dk?st.cmd=userProfile&tkn=9856&_prevCmd=userMain&_aid=leftMenuClick">Одноклассники</a>, <a href="https://instagram.com/minq1re?igshid=YmMyMTA2M2Y=">Инстаграм.</a></p> 

          <p>Телефон: +7(952)-956-12-72</p>
        </div>
        
      </div>

    </div>

  </div>

  <div class="followers">
    <div class="container">

      <div class="feedback-title" id="followers">
        Новый пользователь? Расскажите о себе:)
      </div>

      <?php

      require_once 'news\config\connect.php';

      ?>
      <style>
        th, td {
          padding: 10px;
        }

        th {
          background: #606060
          color: #fff;
        }
        td {
          background: #b5b5b5;
        }
      </style>
      <table>
        <tr>
          <th>ID</th>
          <th>Имя пользователя</th>
          <th>Дата регистрации</th>
          <th>Описание профиля</th>
        </tr>
        <?php
          $followers = mysqli_query($connect, "SELECT * FROM `followers`");
          $followers = mysqli_fetch_all($followers);
          foreach ($followers as $follower) {
        ?>

        <tr>
          <td><?= $follower[0] ?></td>
          <td><?= $follower[1] ?></td>
          <td><?= $follower[2] ?></td>
          <td><?= $follower[3] ?></td>
          <td><a href="news/follower.php?id=<?= $follower[0] ?>">Просмотр пользователя</a></td>
          <td><a href="news/pdate.php?id=<?= $follower[0] ?>">Обновить</a></td>
          <td><a style="color: red" href="news/vendor/delete.php?id=<?= $follower[0] ?>">Удалить</a></td>
        </tr>

        <?php
          }
        ?>

      </table>
      <h3>Добавить нового пользователя</h3>
      <form action="news/vendor/create.php" method="post">
        <p>Имя пользователя</p>
        <input type="text" name="title">
        <p>Дата регистрации</p>
        <input type="date" name="date">
        <p>Описание профиля</p>
        <textarea name="contacts"></textarea>
        <br> <br>
        <button type="submit">Добавить нового пользователя</button><br><br>
      </form>
    </div>
    
  </div>

</body>
</html>