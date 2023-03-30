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

- [WellNUS++ User Guide](#wellnus-user-guide)
    - [Quick Start](#quick-start)
    - [Features](#features)
        - [Command format](#command-format)
        - [Viewing help](#viewing-help--help)
        - [Accessing feature](#accessing-feature--featurename)
        - [Add new atomic habit](#add-new-atomic-habit--add)
        - [List all atomic habits](#list-all-atomic-habit--list)
        - [Update an atomic habit](#update-an-atomic-habit---update)
        - [Home](#home--home)
        - [Get reflection questions](#get-reflection-questions--get)
        - [Exit](#exit--exit)
    - [FAQ](#faq)
    - [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest wellnus++.jar from here.

3. Copy the file to the folder you want to use as the home folder for your WellNUS++.

4. Open a command terminal, cd into the folder you put the .jar file in, and use the java -jar wellnus++.jar command to
   run the application.
   A CLI should appear in a few seconds.

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
    We are here to ensure your wellness is taken care of through WellNUS++
    Here are all the commands available for you!
------------------------------------------------------------
```

```
------------------------------------------------------------
    1. hb - Enter Atomic Habits: Track your small daily habits and nurture it to form a larger behaviour
    usage: hb
    2. reflect - Read through introspective questions for your reflection
    usage: reflect
    3. exit - Exit WellNUS++
    usage: exit
------------------------------------------------------------
```

### Accessing feature: `FEATURE_NAME`

Access specific feature from main interface by inputting the feature_name
Feature name can be referenced by calling the help command

Format: `FEATURE_NAME`

* Accesses unique features to utilise their respective actions

Example of usage:

`reflect`

Expected outcome:

```
============================================================
    Welcome to WellNUS++ Self Reflection section :D
    Feel very occupied and cannot find time to self reflect?
    No worries, this section will give you the opportunity to reflect and improve on yourself!!
============================================================
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

### Get reflection questions: `get`

Ask WellNUS++ to get a set of 5 random introspective questions for users to view

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

### Exit: `exit`

To exit the app, data of the current progress will be saved upon exiting the program

Format: `exit`

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

**A**: Yes, each feature has its individual data file on the local storage. When WellNUS++
is launched and the user proceeds to a particular feature, previous data will be loaded
from the feature's corresponding data file.

**Q**: How can I navigate the program?

**A**: Please type help when you start the program to view all the commands available

**Q**: How do I start the program?

**A**: Please run the JAR file on your local machine

**Q**: Where will my data be stored?

**A**: Your data will be stored in the separate folder with reference from the program directory

## Command Summary

* Help `help`
* Access feature `reflect`
* Add habit `add --name make bed`
* View habit `list`
* Update habit `list`
  `update --id 1 [--inc 2]`
* Return to main interface `home`
* Get question `get`
* Exit program `exit`
