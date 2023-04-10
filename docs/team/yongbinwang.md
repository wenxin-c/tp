## Wang Yongbin - Project Portfolio Page

### Project: WellNUS++

WellNUS++ is a Command Line Interface(CLI) app for NUS Computing students to keep track and improve their physical and
mental wellness in various aspects.

### Summary of Contributions

- **Code Contributions:** [Link to reposense contribution graph](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=YongbinWang&tabRepo=AY2223S2-CS2113-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).
- **New Feature:** `AtomicHabit` Conceptualised and built the main implementation of `AtomicHabit` feature,
  [#31](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/31).
  - **What it does:** Allows users to track their atomic habits to inculcate good habits.
  - **Implemented:** `AtomicHabitsManager` , `AtomicHabit`,`AtomicHabitList`, `AddCommand`, `ListCommand`,`HomeCommand`.
- **New Feature**: `Focus Timer` Conceptualised and built the main implementation of `FocusTimer` feature through
  experimenting with different implementation approaches,
  [#155](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155).
  - **What it does:** Users can start a offline focus session to focus on their task.
  - **Implemented:** `FocusTimerManager`, `Session`, `StartCommand`, `CheckCommand`, `PauseCommand`, `ResumeCommand`, `NextCommand`, `StopCommand`.
  - **Justification:** The `FocusTimer` feature allows users to have a Work/Break timer that is
    offline and does not require internet connection, minimising online distractions.
- **New Feature:** `Countdown`,
  [#155](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155).
  - **What it does:** Simulates a counting down timer in the command line.
  - **Justification:** A versatile class is required to implement different types of timers for the feature to be robust.
  - **Highlights:** This feature required out of topic research on background threads through the use of `Timer` and `TimerTask` classes.
    Atomic data types were used to ensure thread safety. The implementation of `FocusTimer` was challenging as there was a
    need to ensure no bugs were introduced into the codebase while implementing a novel concept.
- **Enhancement:** Duplicate Checking - Implemented duplicate checking for user inputs,
  [#259](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/259).
  - **What it does:** Checks previously inputted data to ensure that the user does not add duplicate data.
  - **Justification:** Accidental input of duplicate data can be frustrating for the user.
- **Enhancement:** Improve User Interface - Implemented duplicate checking for user inputs,
  [#274](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/274).
  - **What it does:** Improves UI for `AtomicHabit` and `FocusTimer` features.
  - **Justification:** User will have a better experience and can easily differentiate between different features.
- **User Guide Contributions:**
    Yongbin set up the initial structure of the user guide and added documentation for the following sections of the user
    guide:
    - [Add new atomic habit](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#add---add-new-atomic-habit)
    - [List all atomic habit](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#list---list-all-atomic-habit)
    - [Start Session](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#start---start-session)
    - [Pause Session](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#pause---pause-session)
    - [Resume Session](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#resume---resume-session)
    - [Check Session](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#check---check-time)
- **Developer Guide Contributions:**
  Yongbin added documentation for the following sections of the developer guide:
  - [Atomic Habit](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#atomichabit-component), and
    its [Class diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/AtomicHabit.png) and
    [Sequence diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/AtomicHabitSequenceDiagram.png)
  - [Focus Timer](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#commands-1)
- **Team-Based Task Contributions:**
  - Noting down important matters discussed during meetings.
  - Clarifying certain misconceptions in group chat.
  - PR reviews.
- **Reviewing/Mentoring Contributions**
  -  Yongbin was involved in reviewing PRs pertaining to Focus Timer/Atomic Habit features. As well as key features for the
     application such as storage and manager.
  - [List of all reviewed PRs](https://github.com/AY2223S2-CS2113-T12-4/tp/pulls?q=is%3Apr+reviewed-by%3AYongbinWang)
  - [Add config and restart logic for FocusTimer](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/169)
  - [Refactor atomic habits feature ](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/72)
  - [Add Manager class](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/33)
  - [Feature-Update Atomic Habit](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/59)
- **Mentoring**
  - Helped to clarify certain misconceptions on application architecture and integration of different features.
- **Community Contributions**
  - **Reviewing other team's DG:** [DG #1](https://github.com/nus-cs2113-AY2223S2/tp/pull/12),
    [DG #2](https://github.com/nus-cs2113-AY2223S2/tp/pull/42)
  - **Reviewing other team's UG and Program**: [List of all 15 issues filed for T11-3](https://github.com/YongbinWang/ped/issues)