# Wenxin's Project Portfolio Page

## Project: WellNUS++
WellNUS++ is a Command Line Interface(CLI) app for NUS Computing students to keep track and improve their physical and
mental wellness in various aspects.

### Summary of contributions
- **New Feature 1**: `TextUi` class to read user input and print output. 
[#27](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/27) [#48](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/48)
  - What it does: Read user inputs from terminal and print output with a user-friendly layout.
  - Justification: It allows users to input commands and receive outputs from WellNUS++.
  - Highlights: Command usages are given with error message to guide users.
- **New Feature 2**: Self Reflection `ReflectionManager` implementation. [#35](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/35)
  - What it does: main event driver of Self Reflection section.
- **New Feature 3**: Self Reflection `get` command implementation. [#35](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/35)
  - What it does: Allows users to get a **random set of 5 reflection questions** to reflect on.
  - Justification: It allows users to think and reflect on themselves. The set of questions is designed to be randomised 
  so that users can reflect on different aspects
  of lives.
  - Highlights: Multiple data structures such as **ArrayList** and **Set** are used to randomise the sets of questions. 
- **New Feature 4**: Self Reflection `like` command implementation. [#164](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/164)
  - What it does: Allows users to add reflection questions they resonate well into favorite list for review in the future.
  - Highlights: A **HashMap** is used with **display index** of questions being the **key** and **real question index** 
  being the **value** to match questions. 
- **New Feature 5**: Self Reflection `fav` command implementation. [#164](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/164)
  - What it does: Allows users to list all reflection questions in the favorite list.
- **New Feature 6**: Self Reflection `home` command to return back main WellNUS++. 
[#35](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/35) [#103](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/103)
- **New Feature 7**: Helped in Self Reflection `prev` command implementation.
- **Enhancement 1**: Abstract `QuestionList` class. [#164](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/164)
  - What it does: A `QuestionList` class is abstracted to store and modify user data (e.g. the random sets and favorite list).
  - Implementation: To centralise and share data among classes, a common `QuestionList` object is passed into different command objects.
  As such, the single responsibility principle can be better achieved.
- **Enhancement 2**: Self Reflection `unlike` command implementation. [#253](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/253)
  - What it does: Allows users to remove reflection questions they no longer resonate from the favorite list to maintain the relevancy 
  and size of the favorite list. 
- **Enhancement 3**: Store Self Reflection data in data file. [#172](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/172)
  - Justification: To ensure the usage of WellNUS++ in the long run.
- **Enhancement 4**: Standardize error message across WellNUS++. [#260](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/260)
  - Implementation: A standardised format of error message is used across WellNUS++. The messages pinpoint the specific 
error made and give the correct command usage for users.
- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=wenxin-c&breakdown=true)
- **Project Management:**
  - Set up the GitHub team organisation and repository.
  - Maintain issue tracker and PR review.
  - Team lead: responsible for the overall project coordination, defining, assigning, and tracking project tasks.
  - In charge of the program and testing of TextUi class and Self Reflection section.
  - Enable assertion in gradle file. [Enable Team Assertion](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/141)
- **Community:**
  - PRs reviewed(with non-trivial comments):
[#31](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/31),
[#65](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/65),
[#72](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/72),
[#151](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/151),
[#252](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/252)
  - Reported bugs and suggestions to other teams:
[#66](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/66), [#72](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/72),
[#83](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/83), [#92](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/92)
[#103](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/103), [#106](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/106)
[#DG1](https://github.com/nus-cs2113-AY2223S2/tp/pull/15/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b),
[#DG2](https://github.com/nus-cs2113-AY2223S2/tp/pull/3/files/6539d4f8311a3ce7587eae50de850c64e742f2a3#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b),
[#DG3](https://github.com/nus-cs2113-AY2223S2/tp/pull/5/files/e3180a6667d0623ba95e1212667ebf9afc4ecbc1#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
- **Tools:**
  - Integrated PlantUML into team repo
- **Documentations:**
  - **User Guide:**
    - Added [WellNUS++ structure overview](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#overview-of-wellnus) 
    and structure diagram [#263](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/263/)<br>
    - Added [Access Self Reflection](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#reflect---accessing-self-reflection-feature)
      documentation [#198](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/198/)
    - Added Self Reflection documentation for [like](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#like---add-reflection-question-into-favorite-list),
      [fav](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#fav---view-favorite-list) and
      [prev](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#prev---get-the-previous-set-of-reflection-questions-generated)
      [#198](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/198/)
    - Added Self Reflection documentation for [unlike](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#unlike---remove-questions-from-favorite-list) 
    command [#253](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/253)
    - Adjusted cosmetic and errors [#206](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/206) [#263](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/263)
  - **Developer Guide:** 
    - Set up initial developer guide [#144](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/144)
    - Added [UI Class Diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/UiComponent.png) 
    and [Implementation Details](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#ui-implementation) 
    [#291](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/291)
    - Added [Reflection Class Diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/ReflectionClassDiagram.png) 
    and [Implementation Details](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#self-reflection-implementation) 
    [#153](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/153) 
    - Added [Reflection Sequence Diagram](https://ay2223s2-cs2113-t12-4.github.io/tp/diagrams/ReflectionSequenceDiagram.png) 
    [#181](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/181)
    [#183](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/183)
    - Fixed Reflection class and sequence diagram errors [#268](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/268/)
    - Restructured Reflection documentation and 
    added [Design Considerations](https://ay2223s2-cs2113-t12-4.github.io/tp/DeveloperGuide.html#design-considerations) 
    [#291](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/291)<br>
    