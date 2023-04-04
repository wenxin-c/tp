## Wenxin's Project Portfolio Page

### Project: WellNUS++
WellNUS++ is a CLI application that aims to help NUS computing and engineering students improve their overall wellness and obtain
better self achievements.

Given below are my contributions to the project:

- **New Feature 1:** TextUi class to read user input and print output.
  - What it does: Read user inputs from terminal, print line separators, output messages and error messages.
  - Justification: This feature is fundamental as it allows users to input commands and receive outputs from WellNUS++.
Thus, this class is used throughout WellNUS++. 
  - Highlights: Exclamation marks are used as separators for error messages to catch users' attention. 
An error note section is designed to complement error messages to guide users on using WellNUS++. 
- **New Feature 2:** Get a random set of reflection questions for Self Reflection feature.
  - What it does: Allows users to get a **random set of 5** reflection questions, generated from p list of 10 questions.
**questions** upon `get` command.
  - Justification: This is the fundamental feature of Self Reflection section as it gives users an opportunity to think 
and reflect on themselves. The set of questions is designed to be randomised so that users can reflect on different aspects
of lives.
  - Highlights: Multiple data structures are used to get the randomised set of questions. An **arrayList** of 10 questions 
will be loaded upon launching the program. A **set** of 5 randomised distinct integers ranging from 0-9 will be generated.
This **set** of integers are the used as the index of questions in the **arrayList**.
- **New Feature 3:** Like self reflection questions.
  - What it does: Allows users to add the reflection questions they like into the favorite list.
  - Justification: Users might resonate well with some questions. They can use `like` command to add these questions into
the favorite list for review in the future. `like` command can only be used when users have gotten **at least** one set of 
questions previously.
  - Highlights: The indexes of questions are used to determine the random set of questions generated. However, the displayed
index should range from 1-5. To match the display indexes to questions real indexes, a **HashMap** is used with **display index** 
being the **key** and **real question index** being the **value**. From the display index given by users, the real index of
the question can be determined easily.
- **New Feature 4:** List the reflection questions in the favorite list.
  - What it does: Allows users to list and review all reflection questions in the favorite list.
  - Justification: Users should not only just add questions into the favorite list, but also review them.
- **New Feature 5:** Get the previous set of reflection questions.
  - What it does: Allows users to review the previous set of questions generated.
  - Justification: 
- **New Feature 6:** Return from Self Reflection feature back main WellNUS++
  - What is does: Allows users to return from Self Reflection feature back to main WellNUS++.
  - Justification: WellNUS++ is a multi-featured app and users can navigate to different features from the main WellNUS++.
As such, it is essential to have this return feature.
- **Enhancement 1**: Abstracted `QuestionList` class
  - What it does: Self Reflection section relies heavily on the set of random integers generated(refer to feature 2) and
this set will be shared across different classes. Based on the Single Responsibility Principle, a `QuestionList` class is
used to store and manipulate lists of questions such as the random sets and favorite list of questions.
  - Implementation: A common `QuestionList` object is constructed at the beginning and passed into different command objects
as an argument. As such, information of lists of questions and their associated methods are centralised and shared among classes. 
- **Enhancement 2**: Remove reflection questions from the favorite list.
  - What it does: Allows users to remove reflection questions from the favorite list.
  - Justification: As time passes, users might no longer resonate with some questions and wish to remove those questions from
the favorite list. This feature is important without which the size of the favorite list to increment infinitely, making it 
inconvenient for users to review all the questions.
- **Enhancement 3:** Store Self Reflection data in data file.

- **Enhancement 4:** Standardize error message across WellNUS++.

- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=wenxin-c&breakdown=true)
- **Project Management:**
  - Set up the GitHub team organisation and repository
  - Maintain issue tracker
  - Maintain workflow and set agenda for weekly meeting
  - Set up developer guide skeleton [#144](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/144)
- **Documentations:**
  - User Guide:
  - Developer Guide: 
- **Community:**
  - PRs reviewed(with non-trivial review comments):
[#31](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/31)
[#64](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/64)
[#65](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/65)
[#66](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/66)
[#72](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/72)
[#82](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/82)
[#86](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/86)
[#104](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/104)
[#147](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/147)
[#151](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/151)
[#155](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155)
[#162](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/162)
[#182](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/182)
[#200](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/200)
[#202](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/202)
[#252](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/252)
[#254](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/254)
  - Reported bugs and suggestions to other teams:
[#66](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/66)
[#72](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/72)
[#78](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/78)
[#83](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/83)
[#87](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/87)
[#92](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/92)
[#95](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/95)
[#101](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/101)
[#103](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/103)
[#106](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/106)
[#107](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/107)
[#108](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/108)
[#114](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/114)
[#116](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/116)
[#DG Review 1](https://github.com/nus-cs2113-AY2223S2/tp/pull/15/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
[#DG Review 2](https://github.com/nus-cs2113-AY2223S2/tp/pull/3/files/6539d4f8311a3ce7587eae50de850c64e742f2a3#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
[#DG Review 3](https://github.com/nus-cs2113-AY2223S2/tp/pull/5/files/e3180a6667d0623ba95e1212667ebf9afc4ecbc1#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
  - Issues created
[#17](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/17)
[#24](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/24)
[#40](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/40)
[#42](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/42)
[#47](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/47)
[#53](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/53)
[#62](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/62)
[#67](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/67)
[#75](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/75)
[#88](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/88)
[#98](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/98)
[#100](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/100)
[#105](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/105)
[#109](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/109)
[#141](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/141)
[#143](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/143)
[#145](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/145)
[#168](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/168)
[#171](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/171)
[#173](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/173)
[#251](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/251)
- **Tools:**
  - Integrated PlantUML into team repo