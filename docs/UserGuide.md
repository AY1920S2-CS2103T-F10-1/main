# ResuMe User Guide

1. [Introduction](#introduction)

2. [Quick Start](#quick-start)

3. [Features](#features)

    3.1. Viewing help : [help](#viewing-help)

    3.2. Listing all items: [list](#)
    
    3.3. Adding an item: [add](#)
    
    3.4. Editing an item: [edit](#)
    
    3.5. Locating items by name: [find](#)
    
    3.6. Deleting an item: [delete](#)
    
    3.7. Viewing items in a resume: [rview](#)
    
    3.8. Previewing a resume: [rpreview](#)
    
    3.9. Editing a resume: [redit](#redit)
    
    3.10. Generating a resume: [rgen](#rgen)
    
    3.11. Exiting the program : [exit](#exit)
    
    3.12. Saving the [data](#)
    
    3.13. Encrypting data files [coming in [v2.0](#)]
    
4. [FAQ](#faq)

5. [Command Summary](#5command-summary)

# 1. Introduction
**ResuMe** is for those who **prefer to use a desktop app for managing resumes**. More importantly, **ResuMe** is **optimized for those who prefer to work with a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, **ResuMe** can get your resume management tasks done faster than traditional GUI apps. Interested? Jump to the Section 2, “Quick Start” to get started. Enjoy!

ResuMe allows management of different items and they must be one of the 7 item types:
```sh
No.
Alias
Item type
Attributes
```
| No. | Alias | Item type        | Attributes                                 |
|-----|-------|------------------|--------------------------------------------|
| 1   | pd    | personal details | id, name, phone number, email, tag, description             |
| 2   | edu   | education        | id, name, from, to, description, tag           |
| 3   | achv  | achievements     | id, name, time, description, tag               |
| 4   | int   | internships      | id, name, position, from, to, description, tag |
| 5   | proj  | projects         | id, name, from, to, description, tag           |
| 6   | res   | resumes          | id, name, description, tag                                  |
| 7   | tag   | tags             | id, name, tag                                       |

As you may have noticed from number 7, we are able to manage different resumes as well. Items from number 1 to 6 can be shared across different resumes. 

The following is the GUI for our application:



# 2. Quick Start



# 3. Features

**Command Format**
+ Words in `UPPER_CASE` are the parameters to be supplied by the user 
> e.g. in `add n/NAME`, `NAME` is a parameter which can be used as add `n/Orbital`.
+ Items in square brackets are optional e.g `n/NAME [#/TAG]` can be used as `n/Orbital #/software` as `n/Orbital`.

+ Items with `...` after them can be used multiple times including zero times 
 > e.g. `[#/TAG]...​` can be used as   (i.e. 0 times), `#/UX`, `#/software`, `#/java` etc.
+ Parameters can be in any order 
> e.g. if the command specifies `n/NAME [p/PHONE_NUMBER]`, `p/PHONE_NUMBER] n/NAME` is also acceptable.


#### 3.1. Viewing help : `help`

```sh
Lists out the function and usage of each command.
```

Format: `help`

#### 3.2. Listing all items: `list`

```sh
List items in the storage.
```
Format `list [-TYPE]`

* List items that have type matches the `TYPE` argument. If no `TYPE` is given, all items will be listed.

* Listed items are in short form, only showing their name and `ID`. To view items in full, use `view`.


#### 3.3. Adding an item: `add`
```sh
Adds an item to the ResuMe application.
```
Format: `add -TYPE n/NAME [ATTRIBUTE/VALUE]... [#/TAG]...`

| Type alias | Format                                                            |
|------------|-------------------------------------------------------------------|
| Personal details         | `add -pd n/NAME [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB] [#/TAG]...` |
| Education        | `add -edu n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`      |
| Achievement       | `add -achv n/NAME [t/TIME] [d/DESCRIPTION] [#/TAG]...`            |
| Internship        | `add -int n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`     |
| Project       | `add -proj n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`     |
| Skill        | `add -ski n/NAME [#/TAG]...`                                      |
| Resume        | `add -res n/NAME [#/TAG]...`                                      |

+ Name is a **compulsory** field of add command, while other attributes are *optional*.

+ Other optional attributes depend on the type of the item. Eg. project items will have `FROM, TO, DESCRIPTION`.

+ Any item, excluding tags, could have any number of tags *(including 0)*.

+ A unique `ID` is generated, and will be used to uniquely identify the item in the later commands.

+ Examples:

    > `add -edu n/National University of Singapore f/Aug 2018 t/May 2022 d/Bachelor of Computing`

    > `add -proj n/Orbital f/May 2019 t/July 2019 d/Independent Software Project #/Software Engineer #/UX #UI`

#### 3.4. Editing an item: `edit`
```sh
Edits an existing item in the ResuMe application.
```
Format: `edit -TYPE ID [ATTRIBUTE/VALUE]... [#/TAG]...`

| Type alias | Format                                                            |
|------------|-------------------------------------------------------------------|
| Personal details         | `add -pd n/NAME [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB] [#/TAG]...` |
| Education        | `add -edu n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`      |
| Achievements       | `add -achv n/NAME [t/TIME] [d/DESCRIPTION] [#/TAG]...`            |
| Internship        | `add -proj n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`     |
| Project       | `add -proj n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`     |
| Skill        | `add -ski n/NAME [#/TAG]...`                                      |
| Resume        | `add -res n/NAME [#/TAG]...`                                      |

+ Edits the item of type `TYPE` with the specified `ID`. The `ID` refers to the unique identification number given to the item when it was first added. The `ID` must be a **positive integer** that identifies an existing item.

+ At least **one** of the optional fields must be provided.

+ Existing values will be updated to the input values.

+ When editing tags, existing tags of the person will be removed. <br> i.e adding of tags is not cumulative.

+ You can remove **all** the person’s tags by typing `#/` without specifying any tags after it.

+ Examples:

    > `edit -edu 9823 t/May 2023 d/Bachelor of Science`
    <br> Edits the ‘from’ and ‘description’ attributes of the education item with ID 9823 to be `May 2023` and `Bachelor of Science` respectively.

    > `edit -proj 14 n/CP2106 Orbital #/`
    <br> Edits the 'name' of the project item with `ID` 14 to be `CP2106 Orbital` and clears all existing tags.

#### 3.5. Locating items by name: `find`

```sh
Finds items in the ResuMe application whose names contain the specified keyword(s).
```
Format: `find -TYPE KEYWORD [MORE_KEYWORDS]`

+ At least one keyword must be provided.

+ The search is **case insensitive**. 
    > e.g. orbital will match Orbital


+ The **order** of the keywords does **not** matter. 
    > e.g. Orbital CP2106 will match CP2106 Orbital

+ If the `TYPE` is not specified. The program will search across **_all_** items.

+ Only **_full_** words will be matched. 
    > e.g. CP210 will not match CP2106*

+ Examples:

    > `find -project Orbital CP2106`
    <br> Returns the project item CP2106 Orbital

    > `find NUS`
    <br> Returns **_all_** items with `NUS`. 

    > e.g. `NUS Valedictorian, NUS`


#### 3.6. Deleting an item: `delete`

```sh
Deletes an existing item in the ResuMe application
```

Format: `delete -TYPE ID`

+ Deletes the item of type `TYPE` with the specified `ID`. The `ID` refers to the **unique** identification number given to the item when it was first added. The `ID` must be a **positive integer** that identifies an existing item.

+ Once the item with the specified `ID` is deleted, the `ID` will no longer be used throughout the application.

+ Examples:

    > `delete -edu 9823`
    <br> Deletes the education item with `ID` 9823.

    > `delete -proj 14`
    <br> Deletes the project item with `ID` 14. 

#### 3.7. Viewing items: `view`

```sh
Shows all items in an existing resume.
```

Format: `view -TYPE ID`

+ Examples: 

    > `view -res 135`
    <br> Shows the resume with `ID` 135, listing all of its items.

    > `view -edu 123`
    <br> Shows the education item with `ID` 123, listing all of its attributes.
                                                                                                          
#### 3.8. Previewing a resume: `rpreview`

```sh
Previews the resume in string format.
```

Format: `rpreview RESUME_ID`

+ Examples: 
    > `rpreview 135`
    <br> Shows the text format  of the resume with `RESUME_ID` 135.

#### 3.9. Editing a resume: `redit`

```sh
Edits an existing resume in the ResuMe application
```

Format: `redit RESUME_ID TYPE/[ITEM_ID...] [MORE_TYPE/[ITEM_ID...]]... [#/TAG]...`

+ Edits the resume with the specified `RESUME_ID`. The `RESUME_ID` refers to the unique identification number 
given to the item when it was first added. The `ITEM_ID` and `RESUME_ID`
 must be a positive integer that identifies an existing item of that type. 
 
+ The `TYPE` to be edited cannot be resume(`res`) and at least one `TYPE` must be provided.
 
+ For each `TYPE`, existing values will be updated to the input values.

+ You can add multiple items of a certain type to a resume by chaining `ITEM_ID` after
`TYPE/` <br> e.g. `edu/ 123 109` 

+ You can remove all the resumes' tags by typing `#/` without specifying any tags after it. 
Similarly, you can remove all items of type `TYPE` by typing `TYPE/` without specifying
any `ITEM_ID` after it.

+ Examples:
    > `redit 135 edu/9823 achv/102 201`
    <br> Edits the resume of `RESUME_ID` 135, overriding education content with 
              education item of `ITEM_ID` 9823, achievement content with 
              achievement items of `ITEM_ID` 102 and 201. Content of other object 
              types in the resume remain unchanged.

    > `redit 148 edu/ int/ proj/`
    <br> Edits the resume of `RESUME_ID` 135, overriding education content, 
    internship content and project content with empty string. This effectively 
    deletes all education, internship, and project items from the resume. Content of other object types in the resume 
    remain unchanged.

#### 3.10. Generating a resume: `rgen`

```sh
Generates a `.pdf` file from an existing resume object.
```

Format: `rgen RESUME_ID`

Generates a `.pdf` file from resume object with id `RESUME_ID`. 
The `RESUME_ID` must be a positive integer that identifies an existing item.	

+ Examples: 

    > `rgen 1234` 
    Generates a `.pdf` file from resume object with `RESUME_ID` 1234.

#### 3.11. Exiting the application: `exit`

```sh
Exits the program.
```

Format: `exit`

#### 3.12. Saving the data
ResuMe data is saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

# 4. FAQ
Q: What is the difference between `redit` and `edit -res`?

A: `redit` is used when you want to add/remove certain items from your resume. 
<br> `edit -res` is used when you want to edit attributes of the resume, like its name or tags.

Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Address Book folder.

# 5. Command Summary
Command Summary

**Help:** `help`

**List:** `list [-TYPE]`

**Add:** `add -TYPE n/NAME [ATTRIBUTE/VALUE]... [#/TAG]...`
+ Personal details: `add -pd n/NAME [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB] [#/TAG]...`
+ Education: `add -edu n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Achievement: `add -achv n/NAME [t/TIME] [d/DESCRIPTION] [#/TAG]...`
+ Internship: `add -int n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Project: `add -proj n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Skill: `add -ski n/NAME [#/TAG]...`
+ Resume: `add -res n/NAME [#/TAG]...`
# ResuMe User Guide

1. [Introduction](#introduction)

2. [Quick Start](#quick-start)

3. [Features](#features)

    3.1. Viewing help : [help](#viewing-help)

    3.2. Listing all items: [list](#)
    
    3.3. Adding an item: [add](#)
    
    3.4. Editing an item: [edit](#)
    
    3.5. Locating items by name: [find](#)
    
    3.6. Deleting an item: [delete](#)
    
    3.7. Viewing items in a resume: [rview](#)
    
    3.8. Previewing a resume: [rpreview](#)
    
    3.9. Editing a resume: [redit](#redit)
    
    3.10. Generating a resume: [rgen](#rgen)
    
    3.11. Exiting the program : [exit](#exit)
    
    3.12. Saving the [data](#)
    
    3.13. Encrypting data files [coming in [v2.0](#)]
    
4. [FAQ](#faq)

5. [Command Summary](#5command-summary)

# 1. Introduction
**ResuMe** is for those who **prefer to use a desktop app for managing resumes**. More importantly, **ResuMe** is **optimized for those who prefer to work with a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, **ResuMe** can get your resume management tasks done faster than traditional GUI apps. Interested? Jump to the Section 2, “Quick Start” to get started. Enjoy!

ResuMe allows management of different items and they must be one of the 7 item types:
```sh
No.
Alias
Item type
Attributes
```
| No. | Alias | Item type        | Attributes                                 |
|-----|-------|------------------|--------------------------------------------|
| 1   | pd    | personal details | id, name, phone number, email, tag, description             |
| 2   | edu   | education        | id, name, from, to, description, tag           |
| 3   | achv  | achievements     | id, name, time, description, tag               |
| 4   | int   | internships      | id, name, position, from, to, description, tag |
| 5   | proj  | projects         | id, name, from, to, description, tag           |
| 6   | res   | resumes          | id, name, description, tag                                  |
| 7   | tag   | tags             | id, name, tag                                       |

As you may have noticed from number 7, we are able to manage different resumes as well. Items from number 1 to 6 can be shared across different resumes. 

The following is the GUI for our application:



# 2. Quick Start



# 3. Features

**Command Format**
+ Words in `UPPER_CASE` are the parameters to be supplied by the user 
> e.g. in `add n/NAME`, `NAME` is a parameter which can be used as add `n/Orbital`.
+ Items in square brackets are optional e.g `n/NAME [#/TAG]` can be used as `n/Orbital #/software` as `n/Orbital`.

+ Items with `...` after them can be used multiple times including zero times 
 > e.g. `[#/TAG]...​` can be used as   (i.e. 0 times), `#/UX`, `#/software`, `#/java` etc.
+ Parameters can be in any order 
> e.g. if the command specifies `n/NAME [p/PHONE_NUMBER]`, `p/PHONE_NUMBER] n/NAME` is also acceptable.


#### 3.1. Viewing help : `help`

```sh
Lists out the function and usage of each command.
```

Format: `help`

#### 3.2. Listing all items: `list`

```sh
List items in the storage.
```
Format `list [-TYPE]`

* List items that have type matches the `TYPE` argument. If no `TYPE` is given, all items will be listed.

* Listed items are in short form, only showing their name and `ID`. To view items in full, use `view`.


#### 3.3. Adding an item: `add`
```sh
Adds an item to the ResuMe application.
```
Format: `add -TYPE n/NAME [ATTRIBUTE/VALUE]... [#/TAG]...`

| Type alias | Format                                                            |
|------------|-------------------------------------------------------------------|
| Personal details         | `add -pd n/NAME [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB] [#/TAG]...` |
| Education        | `add -edu n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`      |
| Achievement       | `add -achv n/NAME [t/TIME] [d/DESCRIPTION] [#/TAG]...`            |
| Internship        | `add -int n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`     |
| Project       | `add -proj n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`     |
| Skill        | `add -ski n/NAME [#/TAG]...`                                      |
| Resume        | `add -res n/NAME [#/TAG]...`                                      |

+ Name is a **compulsory** field of add command, while other attributes are *optional*.

+ Other optional attributes depend on the type of the item. Eg. project items will have `FROM, TO, DESCRIPTION`.

+ Any item, excluding tags, could have any number of tags *(including 0)*.

+ A unique `ID` is generated, and will be used to uniquely identify the item in the later commands.

+ Examples:

    > `add -edu n/National University of Singapore f/Aug 2018 t/May 2022 d/Bachelor of Computing`

    > `add -proj n/Orbital f/May 2019 t/July 2019 d/Independent Software Project #/Software Engineer #/UX #UI`

#### 3.4. Editing an item: `edit`
```sh
Edits an existing item in the ResuMe application.
```
Format: `edit -TYPE ID [ATTRIBUTE/VALUE]... [#/TAG]...`

| Type alias | Format                                                            |
|------------|-------------------------------------------------------------------|
| Personal details         | `add -pd n/NAME [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB] [#/TAG]...` |
| Education        | `add -edu n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`      |
| Achievements       | `add -achv n/NAME [t/TIME] [d/DESCRIPTION] [#/TAG]...`            |
| Internship        | `add -proj n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`     |
| Project       | `add -proj n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`     |
| Skill        | `add -ski n/NAME [#/TAG]...`                                      |
| Resume        | `add -res n/NAME [#/TAG]...`                                      |

+ Edits the item of type `TYPE` with the specified `ID`. The `ID` refers to the unique identification number given to the item when it was first added. The `ID` must be a **positive integer** that identifies an existing item.

+ At least **one** of the optional fields must be provided.

+ Existing values will be updated to the input values.

+ When editing tags, existing tags of the person will be removed. <br> i.e adding of tags is not cumulative.

+ You can remove **all** the person’s tags by typing `#/` without specifying any tags after it.

+ Examples:

    > `edit -edu 9823 t/May 2023 d/Bachelor of Science`
    <br> Edits the ‘from’ and ‘description’ attributes of the education item with ID 9823 to be `May 2023` and `Bachelor of Science` respectively.

    > `edit -proj 14 n/CP2106 Orbital #/`
    <br> Edits the 'name' of the project item with `ID` 14 to be `CP2106 Orbital` and clears all existing tags.

#### 3.5. Locating items by name: `find`

```sh
Finds items in the ResuMe application whose names contain the specified keyword(s).
```
Format: `find -TYPE KEYWORD [MORE_KEYWORDS]`

+ At least one keyword must be provided.

+ The search is **case insensitive**. 
    > e.g. orbital will match Orbital


+ The **order** of the keywords does **not** matter. 
    > e.g. Orbital CP2106 will match CP2106 Orbital

+ If the `TYPE` is not specified. The program will search across **_all_** items.

+ Only **_full_** words will be matched. 
    > e.g. CP210 will not match CP2106*

+ Examples:

    > `find -project Orbital CP2106`
    <br> Returns the project item CP2106 Orbital

    > `find NUS`
    <br> Returns **_all_** items with `NUS`. 

    > e.g. `NUS Valedictorian, NUS`


#### 3.6. Deleting an item: `delete`

```sh
Deletes an existing item in the ResuMe application
```

Format: `delete -TYPE ID`

+ Deletes the item of type `TYPE` with the specified `ID`. The `ID` refers to the **unique** identification number given to the item when it was first added. The `ID` must be a **positive integer** that identifies an existing item.

+ Once the item with the specified `ID` is deleted, the `ID` will no longer be used throughout the application.

+ Examples:

    > `delete -edu 9823`
    <br> Deletes the education item with `ID` 9823.

    > `delete -proj 14`
    <br> Deletes the project item with `ID` 14. 

#### 3.7. Viewing items: `view`

```sh
Shows all items in an existing resume.
```

Format: `view -TYPE ID`

+ Examples: 

    > `view -res 135`
    <br> Shows the resume with `ID` 135, listing all of its items.

    > `view -edu 123`
    <br> Shows the education item with `ID` 123, listing all of its attributes.
                                                                                                          
#### 3.8. Previewing a resume: `rpreview`

```sh
Previews the resume in string format.
```

Format: `rpreview RESUME_ID`

+ Examples: 
    > `rpreview 135`
    <br> Shows the text format  of the resume with `RESUME_ID` 135.

#### 3.9. Editing a resume: `redit`

```sh
Edits an existing resume in the ResuMe application
```

Format: `redit RESUME_ID TYPE/[ITEM_ID...] [MORE_TYPE/[ITEM_ID...]]... [#/TAG]...`

+ Edits the resume with the specified `RESUME_ID`. The `RESUME_ID` refers to the unique identification number 
given to the item when it was first added. The `ITEM_ID` and `RESUME_ID`
 must be a positive integer that identifies an existing item of that type. 
 
+ The `TYPE` to be edited cannot be resume(`res`) and at least one `TYPE` must be provided.
 
+ For each `TYPE`, existing values will be updated to the input values.

+ You can add multiple items of a certain type to a resume by chaining `ITEM_ID` after
`TYPE/` <br> e.g. `edu/ 123 109` 

+ You can remove all the resumes' tags by typing `#/` without specifying any tags after it. 
Similarly, you can remove all items of type `TYPE` by typing `TYPE/` without specifying
any `ITEM_ID` after it.

+ Examples:
    > `redit 135 edu/9823 achv/102 201`
    <br> Edits the resume of `RESUME_ID` 135, overriding education content with 
              education item of `ITEM_ID` 9823, achievement content with 
              achievement items of `ITEM_ID` 102 and 201. Content of other object 
              types in the resume remain unchanged.

    > `redit 148 edu/ int/ proj/`
    <br> Edits the resume of `RESUME_ID` 135, overriding education content, 
    internship content and project content with empty string. This effectively 
    deletes all education, internship, and project items from the resume. Content of other object types in the resume 
    remain unchanged.

#### 3.10. Generating a resume: `rgen`

```sh
Generates a `.pdf` file from an existing resume object.
```

Format: `rgen RESUME_ID`

Generates a `.pdf` file from resume object with id `RESUME_ID`. 
The `RESUME_ID` must be a positive integer that identifies an existing item.	

+ Examples: 

    > `rgen 1234` 
    Generates a `.pdf` file from resume object with `RESUME_ID` 1234.

#### 3.11. Exiting the application: `exit`

```sh
Exits the program.
```

Format: `exit`

#### 3.12. Saving the data
ResuMe data is saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

# 4. FAQ
Q: What is the difference between `redit` and `edit -res`?

A: `redit` is used when you want to add/remove certain items from your resume. 
<br> `edit -res` is used when you want to edit attributes of the resume, like its name or tags.

Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Address Book folder.

# 5. Command Summary
Command Summary

**Help:** `help`

**List:** `list [-TYPE]`

**Add:** `add -TYPE n/NAME [ATTRIBUTE/VALUE]... [#/TAG]...`
+ Personal details: `add -pd n/NAME [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB] [#/TAG]...`
+ Education: `add -edu n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Achievement: `add -achv n/NAME [t/TIME] [d/DESCRIPTION] [#/TAG]...`
+ Internship: `add -int n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Project: `add -proj n/NAME [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Skill: `add -ski n/NAME [#/TAG]...`
+ Resume: `add -res n/NAME [#/TAG]...`

**Edit:** `edit -TYPE [n/NAME] [ATTRIBUTE/VALUE]... [#/TAG]...`
+ Personal `details: edit -pd [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB] [#/TAG]...`
+ Education: `edit -edu [n/NAME] [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Achievement: `edit -achv [n/NAME] [t/TIME] [d/DESCRIPTION] [#/TAG]...`
+ Internship: `edit -int [n/NAME] [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Project: `edit -proj [n/NAME] [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Skill: `edit -ski [n/NAME] [#/TAG]...`
+ Resume: `edit -res [n/NAME] [#/TAG]...`

**Find:** `find -TYPE KEYWORD [MORE_KEYWORDS]`

**Delete:** `delete -TYPE ID`

**View:** `view -TYPE ID`

**Preview Resume:** `rpreview RESUME_ID`

**Edit Resume:** `redit RESUME_ID TYPE/[ITEM_ID...] [MORE_TYPE/[ITEM_ID...]]... [#/TAG]...`

**Generate Resume:** `rgen RESUME_ID`

**Exit:** `exit`
**Edit:** `edit -TYPE [n/NAME] [ATTRIBUTE/VALUE]... [#/TAG]...`
+ Personal `details: edit -pd [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB] [#/TAG]...`
+ Education: `edit -edu [n/NAME] [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Achievement: `edit -achv [n/NAME] [t/TIME] [d/DESCRIPTION] [#/TAG]...`
+ Internship: `edit -int [n/NAME] [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Project: `edit -proj [n/NAME] [f/FROM] [t/TO] [d/DESCRIPTION] [#/TAG]...`
+ Skill: `edit -ski [n/NAME] [#/TAG]...`
+ Resume: `edit -res [n/NAME] [#/TAG]...`

**Find:** `find -TYPE KEYWORD [MORE_KEYWORDS]`

**Delete:** `delete -TYPE ID`

**View:** `view -TYPE ID`

**Preview Resume:** `rpreview RESUME_ID`

**Edit Resume:** `redit RESUME_ID TYPE/[ITEM_ID...] [MORE_TYPE/[ITEM_ID...]]... [#/TAG]...`

**Generate Resume:** `rgen RESUME_ID`

**Exit:** `exit`