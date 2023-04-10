## Bernard Lesley Efendy - Project Portfolio Page

### Project: WellNUS++
WellNUS++ is a Command Line Interface(CLI) app for NUS Computing students to keep track and improve their physical and
mental wellness in various aspects.

### Summary of Contributions
- **Code Contributions:** [Link to reposense contribution](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=BernardLesley&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17).
- **Feature:** `AtomicHabitTokenizer`
  [#151](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/151).
  - **What:** `AtomicHabitTokenizer` is responsible for the following function:
    1. Tokenize list of `AtomicHabit` objects into Strings that can be stored.
    2. Detokenize the Strings back into list of `AtomicHabit` objects to be used by `AtomicHabitManager`.
    3. Check if the contents of `habit.txt` has been tampered/corrupted.
  - **Justification:** Since `AtomicHabit` objects cannot be stored directly into the storage, there is a need for a class to tokenize and detokenize AtomicHabit objects.
- **Feature:** `ReflectionTokenizer`
  [#151](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/151).
  - **What:** `ReflectionTokenizer` is responsible for the following function:
    1. Tokenize like and prev indexes into Strings that can be stored.
    2. Detokenize the Strings back into like and prev indexes to be used by `ReflectionManager`.
    3. Check if the contents of `reflect.txt` has been tampered/corrupted.
  - **Justification:** Since `Reflection` feature needs to save 2 types of indexes (like and prev), there is a need for a class to tokenize and detokenize like and prev indexes.
- **Feature:** WellNUS++ `Atomic Habit`, `Reflection`, `Focus Timer` and `Gamification` - `help` command implementation, [#175](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/175).
- **Feature:** Atomic Habit - `update` & `delete` command implementation, [#59](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/59) [#196](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/196).
- **User Guide Contributions:**<br/>
  Bernard added documentation for the following sections of the user guide:
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
- **Developer Guide Contributions:**<br/>
Bernard added documentation for the following sections of the developer guide:
  - [Tokenizer](
    https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#tokenizer
    )
  - [Tokenizer Class Diagram](
    https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/Tokenizer.png
    )
- **Team-Based Task and Review Contributions**<br/>
Bernard also contributed in the following tasks:
    - Attend weekly meetings to discuss the implementation of WellNUS++. 
    - **Finding bugs for WellNUS++**: [#298](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/298) [#317](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/317) [#321](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/321) [#323](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/323)
- **Community Contributions**<br/>
Bernard also contributed to other teams by reviewing other team's UG and Program during PE-D and PE.
  - **Reviewing other team's UG and Program**: [List of all 9 issues filed for T15-1](https://github.com/BernardLesley/ped/issues)

