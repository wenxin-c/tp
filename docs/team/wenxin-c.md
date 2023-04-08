# Wenxin's Project Portfolio Page

## Project: WellNUS++
WellNUS++ is a Command Line Interface(CLI) app for NUS Computing students to keep track and improve their physical and
mental wellness in various aspects.

### Summary of contributions
- **New Feature 1: TextUi class to read user input and print output.** 
[#27](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/27) [#48](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/48)
  - What it does: Read user inputs from terminal and print output with a user-friendly layout.
  - Justification: This feature is fundamental as it allows users to input commands and receive outputs from WellNUS++.
This class is used throughout WellNUS++. 
  - Highlights: An error note section is designed to complement error messages to guide users on using WellNUS++.
- **New Feature 2: Get a random set of self reflection questions.** [#35](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/35)
  - What it does: Allows users to get a **random set of 5 reflection questions**, generated from a list of 10 questions
using `get` command.
  - Justification: This is fundamental for Self Reflection section as it gives users an opportunity to think 
and reflect on themselves. The set of questions is designed to be randomised so that users can reflect on different aspects
of lives.
  - Highlights: Data structures **ArrayList** and **Set** are used to randomise the sets of questions. 
- **New Feature 3: Like self reflection questions.** [#164](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/164)
  - What it does: Allows users to add the reflection questions they resonate well into the favorite list.
  - Justification: Users might resonate well with some questions, they can use `like` command to add these questions into
the favorite list for review in the future. 
  - Highlights: A **HashMap** is used with **display index** of questions being the **key** and **real question index** 
  being the **value** for question mapping. 
- **New Feature 4: List the reflection questions in the favorite list.** [#164](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/164)
  - What it does: Allows users to list and review all reflection questions in the favorite list.
  - Justification: Users should not only add questions into the favorite list, but also review them.
- **New Feature 5: Return from Self Reflection feature back main WellNUS++.** 
[#35](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/35) [#103](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/103)
- **New Feature 6: Get the previous set of reflection questions.**
  - Helped in structuring and implementing this feature to fit into the Self Reflection big picture.
- **Enhancement 1: Abstract `QuestionList` class.** [#164](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/164)
  - What it does: A `QuestionList` class is abstracted to store and modify user data (e.g. the random sets and favorite list).
  - Implementation: A common `QuestionList` object is constructed and passed into different command objects. Lists of questions 
  and associated methods are centralised and shared among classes.
- **Enhancement 2: Remove reflection questions from the favorite list.** [#253](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/253)
  - What it does: Allows users to remove reflection questions from the favorite list.
  - Justification: Users can remove questions they no longer resonate from the favorite list. It also prevents the favorite 
  list from incrementing infinitely.
- **Enhancement 3: Store Self Reflection data in data file.** [#172](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/172)
  - What is does: Allows Self Reflection data to be stored in a data file.
  - Justification: To ensure the sustainability of WellNUS++, user data should be stored for users to view in the long run.
- **Enhancement 4: Standardize error message across WellNUS++.** [#260](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/260)
  - Implementation:A standardised format of error message is used across WellNUS++. The messages pinpoint the specific 
error made(e.g. wrong command keyword, extra payload) and the correct usage of the command is given to better guide users.
- **Enhancement 5: Add unit tests to TextUi and Self Reflection.** [#27](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/27)
  [#35](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/35)
- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=wenxin-c&breakdown=true)
- **Project Management:**
  - Set up the GitHub team organisation and repository.
  - Maintain issue tracker and PR review.
  - Team lead: responsible for the overall project coordination, defining, assigning, and tracking project tasks.
  - In charge of the program and testing of TextUi class and Self Reflection section.
  - Enable assertion in gradle file. [Enable Team Assertion](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/141)
- **Community:**
  - PRs reviewed(with non-trivial review comments):
[Code Style](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/31),
[Storage Code Style](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/162),
[Atomic Code Quality](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/64),
[MainManager Discussion](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/65),
[Atomic Integration](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/72),
[Tokenizer Suggestion](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/151),
[Tokenizer Bug Fix](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/252)
  - Reported bugs and suggestions to other teams:
[Invalid exit command](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/66), [Feature Flaws](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/72),
[Invalid update command](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/83), [Invalid delete command](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/92)
[Capital Input](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/103), [Duplicate arguments](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/106)
[#DG Review 1](https://github.com/nus-cs2113-AY2223S2/tp/pull/15/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b),
[#DG Review 2](https://github.com/nus-cs2113-AY2223S2/tp/pull/3/files/6539d4f8311a3ce7587eae50de850c64e742f2a3#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b),
[#DG Review 3](https://github.com/nus-cs2113-AY2223S2/tp/pull/5/files/e3180a6667d0623ba95e1212667ebf9afc4ecbc1#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
  - Issues addressed(examples)
[Reflection v1.0](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/17),
[Reflection Like Feature](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/145),
[Reflection Storage](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/171),
[TextUi](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/24),
[Get Command Bug Fix](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/98),
[Developer Guide](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/143),
[PE-D](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/251)
- **Tools:**
  - Integrated PlantUML into team repo
- **Documentations:**
  - **User Guide:**
    - Added overview section and structure diagram [#263](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/263/)
    [WellNUS++ structure overview](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#overview-of-wellnus)
    - Added access Self Reflection documentation [#198](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/198/)
    [Access Self Reflection section](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#reflect---accessing-self-reflection-feature)
    - Added Self Reflection documentation for `like`, `fav` and `prev` commands [#198](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/198/)
    [Self Reflection like feature](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#like---add-reflection-question-into-favorite-list)
    [Self Reflection fav feature](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#fav---view-favorite-list)
    [Self Reflection prev feature](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#prev---get-the-previous-set-of-reflection-questions-generated)
    - Added Self Reflection documentation for `unlike` command [#253](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/253)
    [Self Reflection unlike feature](https://ay2223s2-cs2113-t12-4.github.io/tp/UserGuide.html#unlike---remove-questions-from-favorite-list)
    - Adjusted `home` command order [#206](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/206)
    - Fixed outdated documentation errors [#263](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/263) [#284](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/284)
  - **Developer Guide:** 
    - Set up initial developer guide [#144](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/144)
    - Added UI class diagram and implementation details [#291](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/291)
    - Added Self Reflection class diagram and implementation details [#153](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/153)
    - Added Self Reflection sequence diagram [#181](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/181)
    [#183](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/183)
    - Fixed Self Reflection class and sequence diagram errors [#268](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/268/)
    - Restructured Self Reflection implementation details and added design consideration 
    [#291](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/291)