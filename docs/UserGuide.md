# WellNUS++ User Guide

```
,--.   ,--.       ,--.,--.,--.  ,--.,--. ,--. ,---.     |  |        |  |     
|  |   |  | ,---. |  ||  ||  ,'.|  ||  | |  |'   .-',---|  |---.,---|  |---. 
|  |.'.|  || .-. :|  ||  ||  |' '  ||  | |  |`.  `-.'---|  |---''---|  |---' 
|   ,'.   |\\  --.|  ||  ||  | `   |'  '-'  '.-'    |   |  |        |  |     
'--'   '--' `----'`--'`--'`--'  `--' `-----' `-----'    `--'        `--'     
```

## Introduction

WellNUS++ is a Command Line Interface(CLI) app for NUS Computing students to keep track and improve their physical and
mental wellness in various aspects. If you can type fast, WellNUS++ can update their wellness progress faster than
traditional Graphical User Interface(GUI) apps.

## Table of Contents
<!-- TOC -->
* [WellNUS++ User Guide](#wellnus-user-guide)
  * [Introduction](#introduction)
  * [Table of Contents](#table-of-contents)
  * [Quick Start](#quick-start)
  * [Features](#features)
    * [Command Format](#command-format)
    * [Viewing help: `help`](#viewing-help--help)
    * [Home: `home`](#home--home)
    * [Accessing feature: `FEATURE_NAME`](#accessing-feature--featurename)
    * [Accessing atomic habit feature : `hb`](#accessing-atomic-habit-feature--hb)
    * [Add new atomic habit: `add`](#add-new-atomic-habit--add)
    * [List all atomic habit: `list`](#list-all-atomic-habit--list)
    * [Update an atomic habit: `update`](#update-an-atomic-habit--update)
    * [Accessing gamification feature: `gamif`](#accessing-gamification-feature--gamif)
    * [Gamification statistics: `stats`](#gamification-statistics--stats)
    * [Accessing self reflection feature: `reflect`](#accessing-self-reflection-feature--reflect)
    * [Get reflection questions: `get`](#get-reflection-questions--get)
    * [Add reflection question into favorite list: `like INDEX`](#add-reflection-question-into-favorite-list--like-index)
    * [View favorite list: `fav`](#view-favorite-list--fav)
    * [Get the previous set of reflection questions generated: `prev`](#get-the-previous-set-of-reflection-questions-generated--prev)
    * [Return back main WellNUS++: `home`](#return-back-main-wellnus--home)
    * [Accessing Focus Timer Feature: `ft`](#accessing-focus-timer-feature--ft)
    * [Configure the Timer: `config`](#configure-the-timer--config)
    * [Exit WellNUS++: `exit`](#exit-wellnus--exit)
  * [FAQ](#faq)
  * [Command Summary](#command-summary)
<!-- TOC -->

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest CS2113_T12_4_WellNUS.jar from [here](https://github.com/AY2223S2-CS2113-T12-4/tp/releases/latest).

3. Copy the file to the folder you want to use as the home folder for your WellNUS++.

4. Open a command terminal, cd into the folder you put the .jar file in, and use the `java -jar CS2113_T12_4_WellNUS.jar`
command to run the application. A CLI should appear in a few seconds (shown below).
```
------------------------------------------------------------
    Very good day to you! Welcome to
    
    ,--.   ,--.       ,--.,--.,--.  ,--.,--. ,--. ,---.     |  |        |  |
    |  |   |  | ,---. |  ||  ||  ,'.|  ||  | |  |'   .-',---|  |---.,---|  |---.
    |  |.'.|  || .-. :|  ||  ||  |' '  ||  | |  |`.  `-.'---|  |---''---|  |---'
    |   ,'.   |\   --.|  ||  ||  | `   |'  '-'  '.-'    |   |  |        |  |
    '--'   '--' `----'`--'`--'`--'  `--' `-----' `-----'    `--'        `--'
------------------------------------------------------------
------------------------------------------------------------
    Enter a command to start using WellNUS++! Try 'help' if you're new, or just unsure.
------------------------------------------------------------
```

## Features

WellNUS++ comes with a variety of features to help you enhance your overall wellness in NUS!

### Command Format

* Words in UPPER_CASE are the parameters to be supplied by the user.
  e.g. in add --name NAME is a parameter which can be used as add --name John Doe.
* Items in square brackets are optional.
  E.g --name NAME  [--tag TAG] can be used as --name John Doe --tag friend or as --name John Doe.

* Items with … after them can be used multiple times including zero times.
  e.g. [--tag TAG]… can be used as   (i.e. 0 times), --tag friend, --tag friend, --tag family etc.

* Parameters can be in any order.
  e.g. if the command specifies --name NAME --phone PHONE_NUMBER, --phone PHONE_NUMBER --name NAME is also acceptable.

### Viewing help: `help`

Lists all commands available and provide a short description of the application

Format: `help`

* List all commands available in the app and a short description of the app
* Give a detailed explanation of the parameters and subcommands for a given command

Example of usage:

`help`

Expected outcome:

```
------------------------------------------------------------
    WellNUS++ is a Command Line Interface (CLI) app for you to keep track, manage and improve your physical and mental wellness.
    Input `help` to see all available commands.
Input `help [command-to-check]` to get usage help for a specific command.
Here are all the commands available for you!
    
    1. Atomic Habits (hb) - Track and manage your habits with our suite of tools to help you grow and nurture a better you!
    2. Reflection (reflect) - Take some time to pause and reflect with our specially curated list of questions and reflection management tools.
    3. Focus Timer (ft) - Set a configurable timer with work and rest cycles to keep yourself focused and productive!
    4. Gamification (gamif) - Gamification gives you the motivation to continue improving your wellness by rewarding you for your efforts!
    5. exit - Close WellNUS++ and return to your terminal.
    6. help - Get help on what commands can be used in WellNUS++.
------------------------------------------------------------
```

### Home: `home`

To leave the current feature and return back to main interface

Format: `home`

Example of usage:

`home`

Expected outcome:

```
—---------------------------------------------------------------
Thank you for using atomic habits. Do not forget about me!
—---------------------------------------------------------------
```

### Accessing feature: `FEATURE_NAME`

Access specific feature from main interface by inputting the feature_name
Feature name can be referenced by calling the help command

Take note that users are only allowed to access features (i.e. atomic habit, self reflection,
focus timer from the main WellNUS++, cross feature transition is not 
allowed!)

Format: `FEATURE_NAME`

* Accesses unique features to utilise their respective actions

Example of usage:

`reflect`

Expected outcome:

```
============================================================
  _____ ______ _      ______   _____  ______ ______ _      ______ _____ _______ _____ ____  _   _ 
 / ____|  ____| |    |  ____| |  __ \|  ____|  ____| |    |  ____/ ____|__   __|_   _/ __ \| \ | |
| (___ | |__  | |    | |__    | |__) | |__  | |__  | |    | |__ | |       | |    | || |  | |  \| |
 \___ \|  __| | |    |  __|   |  _  /|  __| |  __| | |    |  __|| |       | |    | || |  | | . ` |
 ____) | |____| |____| |      | | \ \| |____| |    | |____| |___| |____   | |   _| || |__| | |\  |
|_____/|______|______|_|      |_|  \_\______|_|    |______|______\_____|  |_|  |_____\____/|_| \_|
============================================================
    Welcome to WellNUS++ Self Reflection section :D
    Feel very occupied and cannot find time to self reflect?
    No worries, this section will give you the opportunity to reflect and improve on yourself!!
============================================================
```

### Accessing atomic habit feature : `hb`

Atomic habit feature allows users to keep track of the daily habits they wish to develop for better self improvement.

Format: `hb`<br>

Example of usage:<br>

`hb`

Expected outcome:
```
------------------------------------------------------------
    Welcome to the atomic habits feature!
    _    _                _       _  _        _     _  _       
  /_\  | |_  ___  _ __  (_) __  | || | __ _ | |__ (_)| |_  ___
 / _ \ |  _|/ _ \| '  \ | |/ _| | __ |/ _` || '_ \| ||  _|(_-<
/_/ \_\ \__|\___/|_|_|_||_|\__| |_||_|\__,_||_.__/|_| \__|/__/
------------------------------------------------------------
```


### Add new atomic habit: `add`

Adds an atomic habit to be tracked by WellNUS++ when accessing atomic habit feature

Format: `add --name ATOMIC_HABIT_NAME `

* ATOMIC_HABIT_NAME is used to uniquely identify each habit(unique and not null)

Example of usage:

`add --name make bed every morning`

Expected outcome:

```
------------------------------------------------------------
    Yay! You have added a new habit:
    'make bed every morning' was successfully added
------------------------------------------------------------
```

### List all atomic habit: `list`

Shows a list of all atomic habits.

Format: `list`

Example of usage:

`list`

Expected outcome:

```
—-------------------------------------------------------------
Here is the current accumulation of your atomic habits!
Keep up the good work and you will develop a helpful habit in no time
1.Make Bed every morning [1]
2.Read for at least 30 minutes every day [3]
3.Avoid checking phone for the first hour after waking up [2]
... 
—--------------------------------------------------------------- 
```

### Update an atomic habit: `update`

Increment the number of times that an atomic habit has been carried out.

Format:

* Step 1: List the current habits using command
* `list`
* Step 2: Select the habit to update by entering the index number of the habit HABIT_INDEX according to index of the
  list output
  The user can specify the number of increments for the habit count via NUMBER_TO_INCREMENT
  The default behaviour is to increment the behaviour by 1
* `update --id HABIT-INDEX [--inc NUMBER_TO_INCREMENT]`

Example of usage:

* `list`
* `update --id 1 --inc 2`

Expected outcome:

```
—---------------------------------------------------------------
Here is the current accumulation of your atomic habits!
Keep up the good work and you will develop a helpful habit in no time
1. Make bed every morning [5]
2. Read for at least 30 minutes every day [3] 
—---------------------------------------------------------------
```

```
—---------------------------------------------------------------
The following habit has been incremented! Keep up the good work!
1. Make bed every morning [7]
—---------------------------------------------------------------
```

### Accessing gamification feature: `gamif`

Format: `gamif`

Gamification system integrated into WellNUS++ to incentivize users to improve
their wellness.

Example of usage:

`gamif`

Expected outcome:

```
######################################################################
    Welcome to
    ______                _ _____            __  _           
   / ____/___ _____ ___  (_) __(_)________ _/ /_(_)___  ____ 
  / / __/ __ `/ __ `__ \/ / /_/ / ___/ __ `/ __/ / __ \/ __ \
 / /_/ / /_/ / / / / / / / __/ / /__/ /_/ / /_/ / /_/ / / / /
 \____/\__,_/_/ /_/ /_/_/_/ /_/\___/\__,_/\__/_/\____/_/ /_/ 
######################################################################
```

### Gamification statistics: `stats`
Displays the user's current XP points and level.

Format: `stats`

Example of usage:

`stats`

Expected outcome:

```
######################################################################
#                 Current XP: Level 2 [===>        ]                 #
#                        7 more XP to Level 3                        #
######################################################################
```

### Accessing self reflection feature: `reflect`

Format: `reflect`

Self reflection feature allows users to get sets of random introspective questions to reflect on to improve overall
wellness and achieve better selves. 

Example of usage: 

`reflect`

Expected outcome:
```
============================================================
  _____ ______ _      ______   _____  ______ ______ _      ______ _____ _______ _____ ____  _   _ 
 / ____|  ____| |    |  ____| |  __ \|  ____|  ____| |    |  ____/ ____|__   __|_   _/ __ \| \ | |
| (___ | |__  | |    | |__    | |__) | |__  | |__  | |    | |__ | |       | |    | || |  | |  \| |
 \___ \|  __| | |    |  __|   |  _  /|  __| |  __| | |    |  __|| |       | |    | || |  | | . ` |
 ____) | |____| |____| |      | | \ \| |____| |    | |____| |___| |____   | |   _| || |__| | |\  |
|_____/|______|______|_|      |_|  \_\______|_|    |______|______\_____|  |_|  |_____\____/|_| \_|
============================================================
    Welcome to WellNUS++ Self Reflection section :D
    Feel very occupied and cannot find time to self reflect?
    No worries, this section will give you the opportunity to reflect and improve on yourself!!
============================================================
```

### Get reflection questions: `get`

Ask WellNUS++ to get a set of 5 random introspective questions for users to view and reflect on. 
The questions are randomised for users to reflect on different aspects of life.

Format: `get`

Example of usage:

`get`

Expected outcome:

```
============================================================
    1.What are three of my most cherished personal values?
    2.What is my purpose in life?
    3.What scares me the most right now?
    4.What is something that brings me joy?
    5.When is the last time I gave back to others?
============================================================
```

### Add reflection question into favorite list: `like INDEX`

Users can add the reflection question they like into favorite list and review afterwards.

Format: `like INDEX`

Note that the users are supposed to at least `get` a set of questions before liking them. 
Index parameter is limited to integer 1-5 as only 5 questions will be generated in every random set.

Example of usage:

`like 1`

Expected output:

```
============================================================
    You have added question: What is my purpose in life? into favorite list!!
============================================================
```

### View favorite list: `fav`

Users can review the list of reflection questions they liked.

Format: `fav`

Example of usage: 

`fav`

Example output:

```
============================================================
    1.What are three of my most cherished personal values?
    2.What is my purpose in life?
    3.What matters to me most right now?
============================================================
```

### Get the previous set of reflection questions generated: `prev`

Users can view the previous set of questions generated for review.

Format: `prev`

Note that the users are supposed to at least `get` a set of questions before viewing the previous set.

Example of usage: 

`prev`

Example output: 

```
============================================================
    1.What is my purpose in life?
    2.What is my personality type?
    3.Did I make time for myself this week?
    4.Am I making time for my social life?
    5.What is something I find inspiring?
============================================================
```
### Return back main WellNUS++: `home`

Users can return back to the main WellNUS++. 

Format: `home`

Example of usage: `home`

Example output:

```
============================================================
    How do you feel after reflecting on yourself?
    Hope you have gotten some takeaways from self reflection, see you again!!
============================================================
```

### Accessing Focus Timer Feature: `ft`  
Our Focus Timer feature allows users to be productive by setting a configurable work-break timer, inspired by the [Pomodoro technique](https://en.wikipedia.org/wiki/Pomodoro_Technique). 

Format: `ft`<br>

Example of usage:<br>

`ft`

Expected outcome:
```
------------------------------------------------------------
    Welcome to Focus Timer.
    Start a focus session with `start`, or `config` the session first!
------------------------------------------------------------
```
### Configure the Timer: `config`

Configures the focus timer's settings.
The number of work-break cycles, work length and break length can be configured. 
When leaving `ft`, the configuration will be reset to the default values.

Format: `config [--cycle numCycle --work workTime --break breakTime --longbreak longBreakTime]`

* At least one of the arguments, `cycle, work, break, longbreak` must be included along with the main `config` command 
* `numCycle` is an **integer** that is `>= 2`
* `workTime, breakTime, longBreakTime` is an **integer** that is `>= 1`

The initial default values for Focus Timer:
* `numCycles = 2`
* `workTime = 1`
* `breakTime = 1`
* `longBreakTime = 1`

Example of usage:

`config --cycle 4`

Expected outcome:

```
------------------------------------------------------------
    Okay, here's your new session details!
    Cycles: 4
    Work: 1 minute
    Break: 1 minute
    Long break: 1 minute
------------------------------------------------------------
```

Example of usage 2:

`config --longbreak 2 --cycle 4 --work 5`

Expected outcome:
```
------------------------------------------------------------
    Okay, here's your new session details!
    Cycles: 4
    Work: 5 minutes
    Break: 1 minute
    Long break: 2 minutes
------------------------------------------------------------
```

### Exit WellNUS++: `exit`

To exit the app, data of the current progress will be saved in data files.

Format: `exit`

Take note that users are only allowed to exit from main WellNUS++ (i.e. users cannot exit the program from other 
features like atomic habit.)

Example of usage:

`exit`

Expected outcome:

```
------------------------------------------------------------
    Thank you for using WellNUS++! See you again soon Dx
------------------------------------------------------------
```

## FAQ

**Q**: Will my data be saved after every update?

**A**: Yes, data will be saved upon updating and restored when the application is relaunched.

**Q**: How can I navigate the program?

**A**: Please type `help` when you start the program to view all the commands available

**Q**: How do I start the program?

**A**: Please run the JAR file on your local machine

**Q**: Where will my data be stored?

**A**: Your data will be stored in the separate folder with reference from the program directory

## Command Summary

* Help `help`
* Access feature `reflect`, `hb`, `ft`, `gamif`
* Add habit `add --name make bed`
* View habit `list`
* Update habit `list`
  `update --id 1 [--inc 2]`
* Get reflect question `get`
* Like reflect question `like INDEX`
* View favorite list `fav`
* Return to main interface `home`
* Get question `get`
* Display gamification statistics `stats`
* Exit program `exit`
