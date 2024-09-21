# Task Tracker

This is a simple command-line application which allows you to store and manage your tasks in a JSON file.

# Features

- Task Management: Create, update, view, and delete tasks.
- Task Status Tracking: Tasks can be marked as TODO, IN-PROGRESS, or DONE.
- Persistent Storage: Tasks are saved to a tasks.json file for persistent data storage.

# Installation

1. Clone the repository

```
% git clone https://github.com/mquang279/Task-Tracker.git
% cd Task-Tracker
```

2. Compile and Run through Terminal

```
% javac TaskApp.java
% java TaskApp [command] <argurment>
```

# How to use

```
//Adding a new task
% java TaskApp add <TaskName>

//Updating and Deleting a task
% java TaskApp update <ID> <TaskName>
% java TaskApp delete <ID>

//Marking a task as Done or In-progress
% java TaskApp mark-in-progress <ID>
% java TaskApp mark-done <ID>

//Listing all tasks
% java TaskApp list

//Listing tasks by status
% java TaskApp list-todo
% java TaskApp list-in-progress
% java TaskApp list-done
