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
* `Sess`

#### Focus Timer: Countdown

Nicholas built the `config` command under the `wellnus/focus/command` pakage in the focus timer `ft` feature.

#### Focus Timer: Session

Nicholas built the `help` command under the `wellnus/common` package

### Enhancements Implemented

Nicholas also contributed to improving the quality of life and defensiveness `WellNUS++`.

#### Terminal Caret

To improve the user experience, Nicholas suggested and implemented an extensible "terminal caret" `:~$`, which
is an intuitive, visual signifier for users to know when to type in a command and which feature
they are in within `WellNUS++`. This was built with the target user in mind,
where they would feel right as home with the caret mimicking a terminal shell which they
are familiar with.

It looks something like:

```bash
(feature_name):~$ your_command_here
```

#### Focus Timer: Preventing malicious user input

Nicholas added in defensive logic to prevent malicious input.

#### Focus Timer: Streamlining state management

Nicholas helped to streamline how state is being managed and transitioned in the timer.

### User Guide Contributions

Nicholas added documentation for the following sections of the user guide:

- [Command Format](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#command-format)
- [Focus Timer Config](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#configure-the-timer-config)
- [Command Summary](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#command-summary)

### Developer Guide Contributions

Nicholas added documentation for the following sections of the developer guide:

- [CommandParser](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#commandparser-component)
    - [Class diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/CommandParserClass.png)
    - [Sequence diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/CommandParserSequence.png)
- [Storage](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#storage)
    - [Sequence diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/StorageSequence-Saving_Data__Emphasis_on_Storage_Subroutine_.png)
- [Glossary](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#glossary)

### Team-Based Task Contributions

Nicholas also contributed in the following team tasks:

- Setting up of issue templates
- Setting up of issue tags
- Issue tracking and closing
- PR reviews
- Took meeting minutes for some meetings
- Setting up of weekly meeting location

### Reviewing/Mentoring Contributions

#### PR Reviews

Nicholas was very involved with the code review process, giving critical suggestions on
how the logical structure of the code and focussing on the big-picture cohesiveness of
the codebase. Some illustrative examples are listed below:

*Big Picture/Cohesiveness Reviews*

- [Code style consistency](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/65#discussion_r1134946097)
- [Usage of assertions and try-catch](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/76#discussion_r1136795952)
- [Avoiding deep nesting](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155#discussion_r1144643398)

*Critical Suggestions*

- [Critical suggestions on code use](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/27#discussion_r1131190083)
- [Advising against dangerous use of static keyword](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/35#discussion_r1133057443)
- [Making logic simpler](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155#discussion_r1144648259)
- [Discussion with team on static keyword](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/85#issuecomment-1471569085)

#### Mentoring

- Taught the team informally through telegram to setup autoformat on save
-

### Contributions Beyond Team Project

Nicholas was also active in the CS2113 forum, contributing to discussions regarding:

- [Object-oriented programming](https://github.com/nus-cs2113-AY2223S2/forum/issues/24#issuecomment-1417417500)
  theory
- [Code design](https://github.com/nus-cs2113-AY2223S2/forum/issues/34#issuecomment-1463563460)
- [IDE help](https://github.com/nus-cs2113-AY2223S2/forum/issues/34#issuecomment-1463563460)