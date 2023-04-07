# Bernard Lesley Efendy - Project Portfolio Page

## Overview
`WellNUS++` is a Command Line Interface(CLI) app for NUS Computing students to keep track and improve their physical and
mental wellness in various aspects. <br/>
`WellNUS++` offers a wide variety of features, which focuses on many aspects of wellness.

## Summary of Contributions
Bernard contributed to `WellNUS++` by building the `tokenizer` feature and integrating `helpCommand` to various `WellNUS++` feature. 
In addition, Bernard help to enhance `AtomicHabit` feature, such as `deleteCommand`

## Core Code Contributions
[Link to reposense contribution graph](
https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=BernardLesley&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17
)

### Tokenizer
Bernard built the `Tokenizer` feature that is responsible to connect `Feature Managers` and `Storage`. It converts Managers' data into Strings that can be stored as .txt file by `Storage`.<br/>
`Tokenizer` mainly consist of 2 sub-features:<br/>
1. `AtomicHabitTokenizer`
2. `ReflectionTokenizer`

In addition, this feature is also responsible to `Detokenize` the data back from the Storage, as well as handling corrupted data in case of the tampering of `Stroage` file.<br/>

Justification: `Tokenizer` is fundamental as it allows the different object data, such as `AtomicHabit` and Integer from `LikeIndex` and `PrevIndex` to be converted and stored according to the needs of each Feature Manager. Therefore, allowing further extensions should there be a new feature introduced to the `WellNUS++` (Open Closed Principle).

### HelpCommand
Bernard is also responsible to integrate `helpCommand` to `AtomicHabit`, `Reflection`, `Focus Timer`, `Gamification`. This allows user to know the command available within each feature and how to use it.

Justification: User might be confused with a plethora of feature that `WellNUS++` offered, therefore it is essential to have a command that can guide new user for each of the features.

### AtomicHabit: deleteCommand
Bernard also introduced a `deleteCommand` to allow user to delete a habit that they don't want to continue.

Justification: As the user progresses to do their habit, they may feel that the habit is not relevant anymore. In addition, the users might simply mistype a habit, and they wish to delete it. Hence, `deleteCommand` is introduced in `WellNUS++ AtomicHabit`. 

## User Guide Contribution
- [Delete Atomic Habit](
https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#delete---delete-an-atomic-habit
)
- [Viewing Help Atomic Habit](
https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#help---viewing-atomic-habit-help
)
- [Viewing Help Reflection](
https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#help---viewing-reflection-help
)
- [Viewing Help Focus Timer](
https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#help---viewing-focus-timer-help
)
- [Viewing Help Gamification](
https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#help---viewing-gamification-help
)
## Developer Guide Contribution
- [Tokenizer](
https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#tokenizer
)
- [Tokenizer Class Diagram](
  https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/Tokenizer.png
)


