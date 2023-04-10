# Wang Haoyang's Project Portfolio Page

## Project: WellNUS++
WellNUS++ is a Command Line Interface(CLI) app for NUS Computing students to keep track and improve their physical and
mental wellness in various aspects. It is written in Java, and has about 10 kLoC.

### Summary of Contributions
Haoyang's main roles are to peer review team code contributions, assist with bug fixing, and write quality 
documentation. He has consistently enforced code style guidelines(based on the 
[SE-EDU style guide](https://se-education.org/guides/conventions/java/basic.html)) in team code.
* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=haoyangw&breakdown=true)
* **New feature**: `gamification` feature  
  [#178](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/178) [#265](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/265)
  * What it does: Provides the user with experience points and levels 
  * Justification: Increase XP points and levels for completing atomic habits to incentivize the user to keep doing so
  * Highlights: This feature integrates with atomic habits and thus needs careful implementation to reduce coupling
  between multiple classes within the two features. It was also challenging to fulfil SRP which requires gamification
  data logic to be abstracted from other classes and encapsulated within one class.
* **New feature**: `MainManager` implementation to provide the main CLI user interface
  [#65](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/65)
  * Highlights: Highly extensible(support new features simply by updating `setSupportedFeatureManagers()`)
* **New feature**: `help` command implementation and architecture for other commands to provide `help` description 
  [#30](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/30)
* **New feature**: `exit` command to quit the app [#30](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/30)
* **New feature**: `Command` abstract class to define the app's user command architecture 
  [#29](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/29)
* **New feature**: `Tokenizer` interface to define the architecture for app data -> String and vice versa conversion
  [#137](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/137) [#147](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/147)
* **Enhancements to existing features**:
  * Redirect all logging to a log file to clean up CLI [#272](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/272)
  [#344](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/344)
    * Highlights: `Singleton` paradigm to ensure one shared `FileHandler` in the entire app. Auto wipes log file when
    file size > 5MB.
  * Refactor atomic habits feature to use `Command` and `Manager` abstract class and improve associated unit tests
  [#72](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/72)
  * Fix app crash in `reflect` feature [#86](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/86)
  * JUnit Testing [#72](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/72)
  [#350](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/350)
  [#363](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/363)
  * General bug fixing [#203](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/203) 
  [#207](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/207)
  [#267](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/267)
  [#269](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/269)
  [#286](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/286)
  [#343](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/343)
  [#353](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/353)
  * General debugging [#139](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/139)
  [#155](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155#issuecomment-1479317735)
  [#157](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/157)
  [#256](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/256)
  [#261](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/261)
  [#278](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/278)
  [#279](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/279)
  * Refactor main `WellNus` class for more OOP [#65](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/65)
* **Documentation**:
  * User Guide:
    * Added documentation for gamification feature [#200](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/200)
    * Make explanations clearer and fix inconsistent formatting 
    [#280](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/280)
    [#348](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/348)
    * Better FAQs [#283](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/283)
  * Developer Guide:
    * Added design considerations and lifecycle details for `Manager` classes [#158](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/158)
    [#184](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/184)
    * Added design considerations for `gamification` feature
    [#340](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/340)
    * Provide test cases for `Saving Data` section [#293](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/293)
* **Contributions to team-based tasks**:
  * Setup checkstyle for the project [#90](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/90)
  * Add new GitHub issue template for enhancements [#57](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/57)
  * Setup GitHub repo permissions to enforce forking workflow [#13](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/13)
  * Setup gradle for creating full-fat jar of project [#91](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/91)
  * Detailed PR reviews(see below)
  * Creating GitHub issues for suggested improvements/bugs
  * Closing team GitHub issues
  * Creating GitHub milestone v2.1
  * Closing GitHub milestone v2.0
  * Publishing git tag v1.0 and v2.0 and corresponding GitHub releases
* **Review/Mentoring contributions**:
  * Code quality reviews: [#19](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/19#pullrequestreview-1331066165)
  [#27](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/27#pullrequestreview-1333065611)
  [#31](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/31#pullrequestreview-1333329196)
  [#35](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/35#pullrequestreview-1339014294)
  [#151](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/151#pullrequestreview-1352440143)
  [#155](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155#pullrequestreview-1352217126)
  [#253](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/253#pullrequestreview-1370261026)
  [#274](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/274#pullrequestreview-1374373316)
  * Code architecture review: [#33](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/33#pullrequestreview-1334183073)
  * Logic/implementation reviews: [#77](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/77#pullrequestreview-1343698960)
  [#121](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/121#pullrequestreview-1345355927)
  [#164](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/164#pullrequestreview-1357941910)
  [#179](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/179#pullrequestreview-1361712239)
  [#260](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/260#pullrequestreview-1370332618)
  * Exception handling/assertion reviews: [#140](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/140#pullrequestreview-1348134976)
  [#146](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/146#pullrequestreview-1348716347)
  [#164](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/164#pullrequestreview-1357941910)
  * Team DG reviews: [#153](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/153#pullrequestreview-1352384116)
  [#154](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/154#pullrequestreview-1352507615)
  * Team UML reviews: [#152](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/152#pullrequestreview-1350978626)
  [#153](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/153#pullrequestreview-1352580505)
  [#157](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/157)
  * Help teammate with debugging Java `Thread` logic and teach him to use IntelliJ Profiler [#155](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/155/commits/3de219e27ebfabd3135ed3923489e52efb9cae4e)
  * Team discussion on static variables and improving `reflect` feature implementation [#85](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/85)
  * Proposal to further reduce static variable use [#148](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/148)
  * Teach teammate to use more OOP in `reflect` feature [#164](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/164#pullrequestreview-1357941910)
  * Teach team to set up checkstyle config locally [#90](https://github.com/AY2223S2-CS2113-T12-4/tp/pull/90)
  * Teach teammates how to use RepoSense `@@author` tags [#129](https://github.com/AY2223S2-CS2113-T12-4/tp/issues/129)
  * Teach teammates about git commands like `stash`, `rebase -i` and `patch`
* **Community**:
  * Contributed to forum: [#47](https://github.com/nus-cs2113-AY2223S2/forum/issues/47)
  * Other teams' DG reviewed: [DG #1](https://github.com/nus-cs2113-AY2223S2/tp/pull/46#pullrequestreview-1364294694),
  [DG #2](https://github.com/nus-cs2113-AY2223S2/tp/pull/52#pullrequestreview-1364319607)
  * Other teams' UG reviewed: [W15-4 #105](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/105), 
  [W15-4 #118](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/118)
  * Other teams' code reviewed: [W15-4 #108](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/108), 
  [W15-4 #114](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/114),
  [W15-4 #125](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/125),
  [W15-4 #127](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/127),
  [W15-4 #132](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/132),
  [W15-4 #135](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/135),
  [W15-4 #138](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/138),
  [W15-4 #141](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/141),
  [W15-4 #145](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/145),
  [W15-4 #147](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/147),
  [W15-4 #149](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/149),
  [W15-4 #151](https://github.com/AY2223S2-CS2113-W15-4/tp/issues/151)
  * Summary/Proof of aforementioned UG/code reviews: [haoyangw/ped](https://github.com/haoyangw/ped/issues)
