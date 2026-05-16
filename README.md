🧩 EAPI

Elysium API — Kotlin SDK for interacting with ElysiumAPI.

---

## 📚 Documentation

- 🔹 v1 API docs → [v1 API](./docs/v1.md)
- 🔹 v2 API docs → [v2 API](./docs/v2.md)

---

## 🚀 Quick Start

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

> 💡 Замените `<актуальная_версия>` на последнюю доступную (например, `0.2.4`).  
> Вы можете посмотреть актуальную версии в `build.gradle.kts` или в релизах проекта.

---

### 3. Подключение к plugin.yml
```yml
depend: [EAPI]
```

---

## ⚠️ Важно

- Используйте корутины для асинхронных вызовов
