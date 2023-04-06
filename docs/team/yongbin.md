# Yongbin - Project Portfolio Page

## Overview

WellNUS++ is a Command Line Interface(CLI) app for NUS Computing students to keep track and improve their physical and
mental wellness in various aspects.

## Summary of Contributions

Yongbin was an active PR reviewer for the project, and was designated as one of the members in charge of documentation.

Yongbin contributed to `WellNUS++` by providing insights on the overall application architecture logic and working on
the
`AtomicHabits` and `FocusTimer` features.

### Core Code Contributions

[Link to reposense contribution graph](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=YongbinWang&tabRepo=AY2223S2-CS2113-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

#### AtomicHabit

Yongbin conceptualised and built the main implementation of `AtomicHabits` feature.

Yongbin built the main and initial implementation of the following features:

* `AtomicHabitsManager` which is the main class that manages the `AtomicHabits`
  feature.
* `AtomicHabitList` which is the main class that manages the list of
  AtomicHabits.
* `AtomicHabit` which is the main class that represents an AtomicHabit.
* `add` command under the `wellnus/atomichabit/command` package in
  the `AtomicHabits` feature.
* `list` command under the `wellnus/atomichabit/command` package in  
  the `AtomicHabits` feature.
* `home` command under the `wellnus/atomichabit/command` package in
  the `AtomicHabits` feature.

#### Focus Timer

Yongbin conceptualised and built the main implementation of `FocusTimer` feature through
experimenting with different implementation approaches.

Yongbin built the main and initial implementation of the following features:

* `FocusTimerManager` which is the main class that manages the `FocusTimer`
  feature.
* `Session` which represents a sequence of `Countdown` objects using an arraylist.
* `Check Command` to allow users to check the current time left in the timer.
* `Pause Command` to allow users to pause the timer.
* `Resume Command` to allow users to resume the timer.
* `Next Command` to allow users to proceed to the next countdown in the session.
* `Stop Command` to allow users to stop the session.

#### Focus Timer: Countdown

Yongbin built the `Countdown` class to represent a countdown timer in command line.
This feature required out of topic research on background threads through the use of `Timer` and `TimerTask` classes.
Atomic data types were used to ensure thread safety. The implementation of `FocusTimer` was challenging as there was a
need
to ensure no bugs were introduced into the codebase while implementing a novel concept.

### Enhancements Implemented

Yongbin also contributed to improving the quality of life and defensiveness `WellNUS++`.<br>
Yongbin implemented the following enhancements:

* Improving user interface of the `AtomicHabit` and `FocusTimer` features.
* Implementing duplicate checking for and list output clarity of `AtomicHabit` feature.

### User Guide Contributions

Yongbin set up the initial structure of the user guide and added documentation for the following sections of the user
guide:

- [Add new atomic habit](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#add---add-new-atomic-habit)
- [List all atomic habit](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#list---list-all-atomic-habit)
- [Start Session](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#start---start-session)
- [Pause Session](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#pause---pause-session)
- [Resume Session](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#resume---resume-session)
- [Check Session](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#check---check-time)

### Developer Guide Contributions

Yongbin added documentation for the following sections of the developer guide:

- [Atomic Habit](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#atomichabit-component)
    - [Class diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/AtomicHabit.png)
    - [Sequence diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/AtomicHabitSequenceDiagram.png)

### Team-Based Task Contributions

Yongbin also contributed in the following team tasks:

- PR reviews
- Clarifying certain misconceptions in group chat
- Noting down important matters

### Reviewing/Mentoring Contributions

#### PR Reviews

Yongbin was involved in reviewing PRs pertaining to Focus Timer/Atomic Habit features. As well as key features for the
application such as storage and manager.

- [Add config and restart logic for FocusTimer](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/169)
- [Refactor atomic habits feature ](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/72)
- [Add Manager class](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/33)
- [Feature-Update Atomic Habit](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/59)

#### Mentoring

- Helped to clarify certain misconceptions on application architecture and integration of different features

