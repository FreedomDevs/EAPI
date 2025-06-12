# 🧩 EAPI v0.2.3

**Elysium API** — библиотека для взаимодействия с REST-эндпоинтами **ElysiumAPI**.

---

## 🔧 Установка

### 1. Публикация библиотеки локально

Перед использованием необходимо опубликовать библиотеку в локальный Maven-репозиторий:

```bash
  .\gradlew publishToMavenLocal
```
---

### 2. Подключение к Gradle (build.gradle.kts)

```kotlin
repositories {
    mavenLocal()
}

dependencies {
    compileOnly(kotlin("stdlib"))

    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")

    compileOnly("dev.elysium.eapi:eapi:<актуальная_версия>")
}
```

> 💡 Замените `<актуальная_версия>` на последнюю доступную (например, `0.2.3`).  
> Вы можете посмотреть актуальную версии в `build.gradle.kts` или в релизах проекта.

---

### 3. Подключение к plugin.yml
```yml
depend: [EAPI]
```

---

## 📦 Пример использования

```kotlin
// Проверка наличия плагина EAPI при запуске
fun checkEAPI()  {
    val eapiPlugin = server.pluginManager.getPlugin("EAPI")
    if (eapiPlugin == null) {
        // Если плагин EAPI не найден — выводим ошибку и отключаем текущий плагин
        logger.severe("Плагин EAPI не найден!")
        server.pluginManager.disablePlugin(this)
    } else {
        // Если найден — всё хорошо
        logger.info("Плагин EAPI найден.")
    }
}

override fun onEnable() {
    // Метод вызывается при включении плагина
    // Проверяем, установлен ли EAPI
    checkEAPI()
}
```

```kotlin
// 📥 Получение данных пользователя

// Создаём CoroutineScope для выполнения асинхронных задач в плагине.
// Он будет жить столько же, сколько и плагин.
internal val pluginScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

// Пример асинхронного вызова API EAPI
// Этот код можно использовать в любом месте проекта
// Важно: если вы вызываете его вне главного класса плагина,
// передайте экземпляр плагина и обращайтесь к plugin.pluginScope.launch
pluginScope.launch(Dispatchers.IO) {
    // Выполняем запрос к API: получаем информацию о пользователе по нику
    val result = EAPIBukkit.instance.api.getUser.fetch("foksik")

    // Выводим результат в консоль
    println(result)
}
```

```kotlin
// ⏱ Добавление времени игры игроку

pluginScope.launch(Dispatchers.IO) {
    // Выполняем запрос к API для добавления времени игры пользователю
    // В данном случае — добавляем 1200 секунд (20 минут) игроку с ником "mikinol"
    val result = EAPIBukkit.instance.api.addPlaytime.fetch(
        AddPlaytime.RequestBody(
            name = "mikinol",       // Никнейм игрока
            playTime = 1200        // Время игры в секундах, которое нужно добавить
        )
    )

    // Выводим ответ от API в консоль (можно заменить на логгер или обработку результата)
    println(result)
}
```
> 💡 Если метод требует передачу данных (как в примере выше),
> используйте соответствующий ``RequestBody`` класс, например: ``AddPlaytime.RequestBody``.
> В данном случае ``AddPlaytime`` — это имя объекта API,
> к которому вы обращаетесь, а ``RequestBody`` — структура,
> в которую вы передаёте параметры запроса.
