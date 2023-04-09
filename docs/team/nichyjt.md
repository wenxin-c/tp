### Yek Jin Teck, Nicholas - Project Portfolio Page

### Overview
WellNUS++ is a Command Line Interface(CLI) app for NUS Computing students to keep track and improve their physical and
mental wellness in various aspects.  

### Summary of Contributions
Nicholas was one of the main PR reviewers for the project, and was designated as one of the testers and
Git/Github helper.
- **Code Contributions:** [Link to reposense contribution](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=nichyjt&tabRepo=AY2223S2-CS2113-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).  
- **Feature: CommandParser**  
  Designed and built the CommandParser,
  [#19](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/19).   
  - **What:** Dictates the syntax of the commands that the user inputs
  and how the program unpacks user inputs into its various components.  
  - **Justification:** After researching on the 
  [usability, design and justification](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#design-considerations-1)
  of different CLI syntax forms, the `unix`-like syntax was chosen as it was the most user-friendly and maintainable. 
- **Boilerplate Feature: Manager**  
  Using OOP, built Manager which the main features
  `hb`, `ft`, `reflect` and `gamif` are built off.
  [ #33](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/33).  
- **Feature: Storage**  
  Built the interface for developers to store and load data,
  [#140](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/140).  
  - **Highlights:** The design of storage ensures usability for any arbitrary feature
  that requires different data structures to be saved to file.
- **Feature: Focus Timer - Config Command**  
  Built the `config` command under the `wellnus/focus/command` package in the focus timer `ft` feature,
[#169](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/169).
- **Feature: MainManager - Help Command**  
  Built the `help` command under the `wellnus/common` package,
  [#104](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/104).
- **Enhancement: Terminal Caret**  
To improve the user experience, Nicholas suggested and implemented an extensible "terminal caret/cursor".
[PR #258](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/258).  
  - **What**: This enhancement added a descriptive caret to the start of the terminal screen,
  similar to what you'd see in any shell-based terminal like `zsh, cmd, bash`.
    For example: `(reflect):~$`.  
  - **Justification**:  Feedback from the PE-D and peers showed that 
  they were often lost within the features.
  After further consideration of our target user (Computing students & CLI-savvy users)
  showed that users were not entirely sure **when** to input commands
  due to the lack of a visual signifier.
  - **Highlights**: Hence, this **targetted enhancement** was made to not only unambiguously
  show where the user is, but also allows users to feel right as home as the caret
  mimics a terminal shell which they are familiar with.
- **Enhancement: Focus Timer - Streamlining state management**  
  Improved cohesivenesss and ensured SRP in state management by having state be managed by `Session` [#169](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/169).
  - **What:** The initial focus timer implementation had a big issue in maintainability and state management. 
  It was non-trivial to figure out what commands could be run at which time (e.g. `resume` cannot be run before `pause`).
  - **Highlights:** Without changing the core implementation logic, managing state in 
  focus timer is much easier and maintainable. 
  The **justification and thought process** is detailed in the
  [Developer Guide](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#focus-timer-implementation).
- **User Guide Contributions:**
  The user guide structure was [templated from](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/108#issue-1627844297)
  Nicholas' [ip User Guide](https://nichyjt.github.io/ip/). 
  Apart from proofreading the document, Nicholas added documentation for the following sections of the user guide:
    - [Command Format](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#command-format)
    - [Focus Timer Access](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#ft---accessing-focus-timer-feature)
    - [Focus Timer Config Command](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#configure-the-timer-config)
    - [Command Summary](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#command-summary)
- **Developer Guide Contributions:**
  Nicholas added documentation for the following sections of the developer guide:
  - [CommandParser](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#commandparser-component), and
  its [Class diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/CommandParserClass.png) and
    [Sequence diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/CommandParserSequence.png)
  - [Storage](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#storage)
   and its [Sequence diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/StorageSequence-Saving_Data__Emphasis_on_Storage_Subroutine_.png)
  - [FocusTimer](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#focus-timer-component)
   and its [Class diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/FocusTimerClassDiagram.png)
  - [Glossary](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#glossary)
- **Team-Based Task Contributions:**
  - Setting up of [issue templates, #4](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/4)
  - [PR authoring](https://github.com/AY2223S2-CS2113-T12-4/tp/pulls?q=author%3Anichyjt+).
  - [Issue creation, tracking and closing](https://github.com/AY2223S2-CS2113-T12-4/tp/issues?q=is%3Aissue+involves%3Anichyjt)
  - Setting up of issue tags
  - Took meeting minutes for meetings
  - Setting up of weekly meeting location
- **Reviewing/Mentoring Contributions**
  - [List of all reviewed PRs](https://github.com/AY2223S2-CS2113-T12-4/tp/pulls?q=is%3Apr+reviewed-by%3Anichyjt).
    Nicholas was involved in many PR reviews, giving critical suggestions and
    focussing on the cohesiveness and maintainability of
    the codebase. Some illustrative examples:
  - **Cohesiveness Comments**
    - [Code style consistency](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/65#discussion_r1134946097)
    - [Usage of assertions and try-catch](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/76#discussion_r1136795952)
    - [Avoiding deep nesting](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155#discussion_r1144643398)
  - **Critical Suggestions**
    - [Critical suggestions on code use](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/27#discussion_r1131190083)
    - [Advising against dangerous use of static keyword](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/35#discussion_r1133057443)
    - [Refactor suggestion for maintainability](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155#discussion_r1144648259)
    - [Refactor suggestion for performance & maintainability](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155#discussion_r1144683078)
    - [Discussion with team on static keyword](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/85#issuecomment-1471569085)
- **Mentoring** 
  - Taught the team informally through telegram to set up code auto-formatting on save
  - Informally debugged and acted as a rubber duck for teammates on technical issues via Telegram
- **Community Contributions**  
Nicholas was also active in the CS2113 forum, contributing to discussions regarding:  
  - [Object-oriented programming](https://github.com/nus-cs2113-AY2223S2/forum/issues/24#issuecomment-1417417500)
  theory
  - [Code design](https://github.com/nus-cs2113-AY2223S2/forum/issues/34#issuecomment-1463563460)
  - [IDE help](https://github.com/nus-cs2113-AY2223S2/forum/issues/34#issuecomment-1463563460)
