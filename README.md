# Приложение DailyQuiz 📑✏️
## 📞 Контакты
* Разработчик: Губанов Александр
* Почта: aleksandrovv436@gmail.com
* Телеграм: @AleksandrPravdin
## ✅ Реализовано
* Весь базовый функционал приложения: прохождение квизов, сохранение сессий и детальный просмотра результатов
* Доп. фича Фильтр, позволяющая выбирать категорию и сложность квиза
* Доп. фича Подсветка ответов, позволяющая пользователю узнавать корректность ответа после каждого вопроса
* Доп. фича Анимация переходов между экранами
## 🎥 Демонстрация приложения
https://github.com/user-attachments/assets/14ba2661-fe3d-484f-853b-60346d20dd8b
## 🛠️ Стэк технологий
* Kotlin 1.9.0
* Retrofit 2.10.0
* Hilt 2.51.1
* Coroutines 1.7.3
* Room 2.6.1
## 📁 Структура проекта
Проект реализован с использованием архитектуры MVVM и разбиением на слои data, domain presentation.
* data - слой работы с данными
* domain - слой бизнес логики
* presentation - слой представления

Ниже представлена папочная структура проекта DailyQuiz
```
DailyQuiz/
├── app/                           
│   ├── src/                          
│   │   ├── main/                    
│   │   │   ├── java/                 
│   │   │   │   └── com/example/dailyquiz/  # Пакет приложения
│   │   │   │       ├── di/           # Dependency Injection
│   │   │   │       │   └── AppModule.kt
│   │   │   │       ├── utils/        # Утилиты
│   │   │   │       │   └── DateExtensions.kt
│   │   │   │       ├── domain/       # Доменный слой
│   │   │   │       │   ├── usecases/     # Use Cases (бизнес-логика)
│   │   │   │       │   │   ├── GetQuizResultByIdUseCase.kt
│   │   │   │       │   │   ├── GetUserAnswersUseCase.kt
│   │   │   │       │   │   ├── SaveQuizResultUseCase.kt
│   │   │   │       │   │   ├── GetQuizQuestionsUseCase.kt
│   │   │   │       │   │   ├── GetQuizResultsUseCase.kt
│   │   │   │       │   │   ├── GetLastQuizNumberUseCase.kt
│   │   │   │       │   │   └── DeleteQuizResultUseCase.kt
│   │   │   │       │   ├── repository/   # Интерфейсы репозиториев
│   │   │   │       │   │   ├── QuizResultRepository.kt
│   │   │   │       │   │   └── QuizPassingRepository.kt
│   │   │   │       │   └── model/        # Доменные модели
│   │   │   │       │       ├── QuizCategory.kt
│   │   │   │       │       ├── QuizDifficult.kt
│   │   │   │       │       ├── UserAnswer.kt
│   │   │   │       │       └── Question.kt
│   │   │   │       ├── data/        # Слой данных
│   │   │   │       │   ├── local/       # Локальное хранилище
│   │   │   │       │   │   ├── QuizDatabase.kt
│   │   │   │       │   │   ├── entities/     # Entity классы
│   │   │   │       │   │   │   ├── UserAnswerEntity.kt
│   │   │   │       │   │   │   └── QuizResultEntity.kt
│   │   │   │       │   │   └── dao/          # Data Access Objects
│   │   │   │       │   │       └── QuizResultDao.kt
│   │   │   │       │   ├── remote/      # Удаленное API
│   │   │   │       │   │   ├── api/         # API сервисы
│   │   │   │       │   │   │   └── QuizApiService.kt
│   │   │   │       │   │   ├── dto/         # Data Transfer Objects
│   │   │   │       │   │   │   ├── QuestionDto.kt
│   │   │   │       │   │   │   └── QuizResponseDto.kt
│   │   │   │       │   │   └── mapper/      # Мапперы
│   │   │   │       │   │       └── QuizMapper.kt
│   │   │   │       │   └── repository/  # Реализации репозиториев
│   │   │   │       │       ├── QuizResultRepositoryImpl.kt
│   │   │   │       │       └── QuizPassingRepositoryImpl.kt
│   │   │   │       ├── presentation/   # Слой представления
│   │   │   │       │   ├── theme/          
│   │   │   │       │   │   ├── Type.kt
│   │   │   │       │   │   ├── Theme.kt
│   │   │   │       │   │   └── Color.kt
│   │   │   │       │   └── feature/        
│   │   │   │       │       ├── detail/      # Экран детальной информации
│   │   │   │       │       │   ├── screen/      
│   │   │   │       │       │   │   └── DetailQuizScreen.kt
│   │   │   │       │       │   ├── component/   
│   │   │   │       │       │   │   ├── DetailResultCard.kt
│   │   │   │       │       │   │   └── DetailAnswerCard.kt
│   │   │   │       │       │   └── viewmodel/ 
│   │   │   │       │       │       └── DetailQuizViewModel.kt
│   │   │   │       │       ├── passing/     # Экран прохождения теста
│   │   │   │       │       │   ├── screen/     
│   │   │   │       │       │   │   ├── ResultQuizScreen.kt
│   │   │   │       │       │   │   ├── PassingQuizScreen.kt
│   │   │   │       │       │   │   ├── FilterScreen.kt
│   │   │   │       │       │   │   └── StartScreen.kt
│   │   │   │       │       │   ├── component/   
│   │   │   │       │       │   │   ├── PassingResultCard.kt
│   │   │   │       │       │   │   ├── WelcomeCard.kt
│   │   │   │       │       │   │   ├── NextButton.kt
│   │   │   │       │       │   │   └── ExpandableDropdown.kt
│   │   │   │       │       │   ├── viewmodel/ 
│   │   │   │       │       │   │   └── PassingQuizViewModel.kt
│   │   │   │       │       │   ├── PassingQuizHostScreen.kt
│   │   │   │       │       │   └── PassingQuizScreenState.kt
│   │   │   │       │       └── history/     # Экран истории
│   │   │   │       │           ├── screen/      
│   │   │   │       │           │   └── HistoryQuizScreen.kt
│   │   │   │       │           ├── component/   
│   │   │   │       │           │   └── QuizResultCard.kt
│   │   │   │       │           └── viewmodel/  
│   │   │   │       │               └── HistoryQuizViewModel.kt
│   │   │   │       ├── AppNavigation.kt    # Навигация приложения
│   │   │   │       ├── MainActivity.kt     # Главная активность
│   │   │   │       └── DailyQuizApp.kt     # Приложение
│   │   │   ├── res/                
│   │   │   │   ├── drawable/           # Изображения
│   │   │   │   ├── mipmap-*/           # Иконки приложения
│   │   │   │   ├── values/             # Значения (строки, цвета, размеры)
│   │   │   │   └── xml/               
│   │   │   └── AndroidManifest.xml     # Манифест приложения
│   │   ├── androidTest/           
│   │   └── test/                  
│   ├── build.gradle.kts          
│   └── proguard-rules.pro        
├── gradle/                       
├── .gradle/                      
├── build.gradle.kts           
├── settings.gradle.kts           
├── gradle.properties             
├── gradlew                  
├── gradlew.bat                   
├── local.properties              
└── .gitignore    
```  
