# Simple Search Engine
This is the repo for my [Simple Search Engine](https://hyperskill.org/projects/89?goal=347) JetBrains Academy Project

# About
In this project you will create your own search engine, though it probably won’t compete with Google. Yours will be a simple search engine that processes a limited collection of data and searches it for a word or a phrase. It’ll be simple but cool.

# Learning outcomes
Gain confidence in your ability to work with files and the console, and learn how to optimize the search process.
This project is a part of the `kotlin` track.

# Final stage version

## Description
Improve your search engine to support complex queries containing a sequence of words and a strategy that determines how to match data.

Take, for example, these six sample lines:
```
Dwight Joseph djo@gmail.com
Rene Webb webb@gmail.com
Katie Jacobs
Erick Harrington harrington@gmail.com
Myrtle Medina
Erick Burgess
```

Let's consider the searching strategies: ALL, ANY and NONE.

If the strategy is ALL, the program should print lines containing all the words from the query.

Query:
```
Harrington Erick
```
Result:
```
Erick Harrington harrington@gmail.com
```

If the strategy is ANY, the program should print the lines containing at least one word from the query.

Query:
```
Erick Dwight webb@gmail.com
```
Result:
```
Erick Harrington harrington@gmail.com
Erick Burgess
Dwight Joseph djo@gmail.com
Rene Webb webb@gmail.com
```

If the strategy is NONE, the program should print lines that do not contain words from the query at all:

Query:
```
djo@gmail.com ERICK
```
Result:
```
Katie Jacobs
Myrtle Medina
Rene Webb webb@gmail.com
```
All the listed operations should be implemented in the `inverted index`, and the results should not contain duplicates.

Example
The lines that start with > represent the user input.
```
=== Menu ===
1. Find a person
2. Print all persons
0. Exit
> 1
 
Select a matching strategy: ALL, ANY, NONE
> ANY
 
Enter a name or email to search all suitable people.
> Katie Erick QQQ
 
3 persons found:
Katie Jacobs
Erick Harrington harrington@gmail.com
Erick Burgess
```
# Evolution
My plans are to extend this activity by adding more search strategies than the proposed.
