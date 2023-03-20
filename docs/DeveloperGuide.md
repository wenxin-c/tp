# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Command Parser

```mermaid
classDiagram
    direction BT
    class CommandParser {
        `+ parseUserInput(String)`
        `+ getMainArgument(String)`
        `- splitIntoCommands(String)`
        `- getArgumentFromCommand(String)`
        `- getPayloadFromCommand(String)`
    }
    
    class Manager["<i>Manager</i>"]{
       - commandParser : CommandParser
        *+ getCommandParser()*
    }  
   CommandParser  "1" -- "" Manager
    class AtomicHabitManager {
        `# getCommandParser()`
    }
    class ReflectionManager {
        `# getCommandParser()`
    }
    class FooManager {
        `# getCommandParser()`
    }
    Manager <|-- AtomicHabitManager 
    Manager <|-- ReflectionManager
    Manager <|-- FooManager
     
```

## Product scope

### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...             | So that I can ...                                           |
|---------|----------|---------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions    | refer to them when I forget how to use the application      |
| v2.0    | user     | find a to-do item by name | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
