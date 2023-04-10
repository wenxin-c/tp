## Yek Jin Teck, Nicholas - Project Portfolio Page

### Project: WellNUS++
WellNUS++ is a Command Line Interface(CLI) app for NUS Computing students to keep track and improve their physical and
mental wellness in various aspects.  

### Summary of Contributions
- **Code Contributions:** [Link to reposense contribution](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=nichyjt&tabRepo=AY2223S2-CS2113-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).  
- **Feature:** `CommandParser`
  [#15](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/15), 
  [#19](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/19).   
  - **What:** Dictates the syntax of the commands 
  and how the program unpacks user inputs into its various components.  
  - **Justification:** After researching on the 
  [usability, design and justification](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#design-considerations-1)
  of different CLI syntax forms, the `unix`-like syntax was chosen as it was the most user-friendly and maintainable. 
- **Boilerplate Feature:** `Manager`, the core boilerplate that
  all sub-features are built off.
  [#22](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/22),
  [ #33](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/33).  
- **Feature:** `Storage`, built the interface for developers to store and load data
  [#134](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/134),
  [#140](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/140).  
  - **Highlights:** The design of storage ensures usability for any arbitrary feature
  that requires different data structures to be saved to file.
- **Feature:** Focus Timer `config` command implementation,
  [#165](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/165),
  [#169](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/169).
- **Feature:** MainManager - `help` command implementation, [#104](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/104).
- **Enhancement:** Terminal Caret - an customizable & user-friendly caret,
  [#79](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/79),
  [#258](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/258).  
  - **What**: Adds a caret to the start of the terminal screen,
  similar to what you'd see in any shell-based terminal like `zsh, cmd, bash`.
    For example: `(reflect):~$`.  
  - **[Design Justification](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/79)**:
  Feedback from the [PE-D](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/235) and peers showed that 
  they were often lost within the features. Further, the lack of a visual signifier made it ambiguous to users
  to know when they should input commands.
  - **Highlights**: Terminal caret unambiguously shows where the user is, and allows users to feel
  comfortable as it mimics a terminal shell which they are familiar with.
- **Enhancement:** Focus Timer - Streamlining state management,
  [#169](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/169).  
  Created state-management methods, enforcing SRP with only `Session` directly querying and handling timer state.
  - **What:** The first focus timer implementation lacked proper state management. 
  It was hard to figure out what commands can be run (e.g. `resume` cannot be run before `pause`).
  - **Highlights:** Without changing the core logic, a developer-friendly API was made to make state management easier.
  **Design & justification** are in the
  [Developer Guide](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#focus-timer-implementation).
- **User Guide Contributions:**
  The user guide structure was [templated from](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/108#issue-1627844297)
  Nicholas' [ip User Guide](https://nichyjt.github.io/ip/). 
  Apart from proofreading the document, Nicholas added documentation for the following sections of the user guide:
    - [Command Format](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#command-format)
    - [Focus Timer Access](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#ft---accessing-focus-timer-feature)
    - [Focus Timer Config Command](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#config---configure-the-timer)
    - [Command Summary](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#command-summary)
- **Developer Guide Contributions:**
  Nicholas added documentation and diagrams for the following sections of the developer guide, focussing on
  readability and simplicity:  
  - [CommandParser](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#commandparser-component), and
  its [Class diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/CommandParserClass.png) and
    [Sequence diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/CommandParserSequence.png)
  - [Storage](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#storage)
   and its [Sequence diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/StorageSequence-Saving_Data__Emphasis_on_Storage_Subroutine_.png)
  - [FocusTimer](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#focus-timer-component)
   and its [Class diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/FocusTimerClassDiagram.png)
   and [abridged 'Finite State Machine' diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/FocusTimerState.png)
  - [Glossary](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#glossary)
- **Team-Based Task Contributions:**
  - Setting up of [issue templates, #4](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/4)
    and issue tags
  - [Issue creation, tracking and closing](https://github.com/AY2223S2-CS2113-T12-4/tp/issues?q=is%3Aissue+involves%3Anichyjt)
  - Took meeting minutes for meetings & booking & setup of weekly meeting venue
- **Reviewing/Mentoring Contributions**
  - [List of all reviewed PRs](https://github.com/AY2223S2-CS2113-T12-4/tp/pulls?q=is%3Apr+reviewed-by%3Anichyjt).
    Nicholas focussed on giving critical suggestions and on the quality of
    the codebase. Some illustrative examples:
    - [Avoiding deep nesting](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155#discussion_r1144643398)
    - [Usage of assertions and try-catch](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/76#discussion_r1136795952)
    - [Leveraging on hidden method opportunity](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/27#discussion_r1131190083)
    - [Advising against dangerous use of static keyword](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/35#discussion_r1133057443)
    - [Refactor suggestion for maintainability](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155#discussion_r1144648259)
    - [Refactor suggestion for performance & maintainability](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155#discussion_r1144683078)
    - [Discussion with team on static keyword](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/85#issuecomment-1471569085)
    - [Code style consistency](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/65#discussion_r1134946097)
- **Mentoring** 
  - Taught the team informally through telegram to set up code auto-formatting on save
  - Informally debugged and acted as a rubber duck for teammates on technical issues via Telegram
- **Community Contributions**  
  - **Forum Contributions:** [Object-oriented programming](https://github.com/nus-cs2113-AY2223S2/forum/issues/24#issuecomment-1417417500)
    theory, [Code design](https://github.com/nus-cs2113-AY2223S2/forum/issues/34#issuecomment-1463563460) and
    [IDE help](https://github.com/nus-cs2113-AY2223S2/forum/issues/34#issuecomment-1463563460).
  - **Reviewing other team's DG:** [DG #1](https://github.com/nus-cs2113-AY2223S2/tp/pull/14#discussion_r1152711554), 
  [DG #2](https://github.com/nus-cs2113-AY2223S2/tp/pull/14#discussion_r1152715587),
  [DG #3](https://github.com/nus-cs2113-AY2223S2/tp/pull/14#discussion_r1152717757),
  [DG #4](https://github.com/nus-cs2113-AY2223S2/tp/pull/14#discussion_r1152731276)
  - **Reviewing other team's UG and Program**: [List of all 19 issues filed for T15-3](https://github.com/nichyjt/ped/issues)