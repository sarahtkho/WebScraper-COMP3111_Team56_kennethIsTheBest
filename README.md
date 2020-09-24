# COMP3111: Software Engineering Project - Webscrapper

### Team: KennethIsTheBest
> #### Team members:  
>	 Ho Wai Kin (Task 2, Task 3)  
>	 Fung Hing Lun (Task 1, Task 6)  
>	 Ho Tsz Kiu (Task 4, Task 5)  

>  We scrape items from two selling portals, Craigslist and Preloved.<br/>
>  Assumptions:
>  1. The item prices are compared in USD.
>  2. Since there may be too many pagination for a search on Craigslist, so the program may go into long waiting time or even not responding. Therefore, if a search returns more than 3 pagination, only the items from the first four pages would be scraped to avoid long waiting time.

## Project Problem Statement and Activities

### Introduction

You are going to form a team of three to work on a project. The project is about web scraping - obtaining and analysis data
from some websites automatically. You are given a skeleton code written in Java 8 that is already able to use a keyword to
fetch some data from Craiglist. Please try the [demo program](demo.jar) to have a better idea of what the skeleton code can 
do. You need Java 8 JDK to run and develop it. At the end of the file there is a guide to compile the project.


### Tasks 

![](doc-img/summary.png)

1. Fill in the tab `Summary`  
	1. Correctly filled the tab. [#1](https://github.com/khwang0/2018F-COMP3111/issues/1)  [5]
	1. Refresh contents on another search. [5]
	1. Put "-" to Average selling price, lowest selling price and latest post for result not found. [3]
	1. Put a clickable URL in the field Lowest Selling Price and Latest Post.[3]
		1. Pop up a windows/browser showing that item.[4]
1. Be able to scrape data from a single webpage of a local/international selling/reselling portals (e.g. [carousell](https://hk.carousell.com), [dcfever](https://www.dcfever.com/trading/search.php), [preloveled](https://preloved.co.uk), [taobao](https://www.taobao.com) or any similar webpage. Please noted that we only accept websites written in English or Chinese) Note, there is no requirement to handle multiple pages data. 
	1. 	Print the scraped data in the console tab [6]
	1.	Modify the class Item so that it will also record which portal this item is coming from. [5]
	1.	The returned result of the function WebScraper.scrape contains the merged data from two portals. [5]
		1. The returned result is sorted by price. If two items have the same price, item sold on Craiglist go first. If two items from the same portal has the same price, they can be sorted in any order.[4]   
1. Handle pagination 
	1. scrape data from more than one pages if the search contains more than one pages of results.[12]
	1. Correctly determine the last page of the search and scrape data from all available pages.[4] 
	1. Write to the `Console` tab and show how many pages has already been scraped during the process. So that the user would aware the program is still running. (Note: asychronized I/O is not required.) [4]
1. Fill in the tab `Table` with all result of the current search.
	1. Correctly filled the result. [4]
	1. Sort the result in ascending order on user clicking each column, and sort in descending order when user click again. [4]
	1. Make all cells in the table not editable. [4]
	1. Pop up a new windows/browser showing the item when the `URL` is clicked. [4]
	1. Refresh the table on another search.  [4]
1. Refine search.
	1. Create an extra button that shows `Refine` just below the `Go` button with at least 5px of space. Make this button right align the `Go` button.[3]  
	1. when the refine button is clicked, filter the searched data and keep those items with their titles containing the keywords typed in the text area.[5]  
		1. Update all tabs on the right. (Note: the correctness of the info of the tabs may depend on other features. This requirement needs only to trigger the update process.) [4]
		1. Make the button `Refine` disabled (grey) before any keyword is being searched, or after `Refine` is clicked once.[4]
			1. Make the button `Refine` enabled after a new search ( e.g. `Go` button is clicked).[4]
1. Finish the rest of the menu bar except `Save` and `Open`
	1. Make `About your Team` showing a new simple dialog that shows all your team members name, itsc account, and github account.[3]
	1. Make `Quit` button exiting the program and close all connections.[3]
	1. `Close` will clear the current search record and initialize all tabs on the right to their initial state.[5]
	1. `New (call Controller.actionNew())` should be renamed to `Last Search` and revert your search result to the previous search.[5] 
		1. Before any keyword is being searched, or after `Last Search` is clicked once, it should be disabled (gray and unable to be clicked).[2]
			1. `Last Search` will be enabled after a new search ((`Go`) button is clicked).[2]


---

## Technical Requirement

1. The program must use Java 8.
1. The project must use Gradle to manage.
1. The program must use JavaFX as its only GUI framework. No Swing or AWT should be allowed.
1. You may choose your own IDE. We recommend Eclipse IDE.
1. JUnit 4.12 as your testing suite
1. Jacoco as your test coverage measurement
1. A Private GitHub repository for source control
	1.	students can apply for [GitHub Education](https://education.github.com/) to enjoy the benefits of creating unlimited private repositories at GitHub
	1.	In your private repository setting page, add your team members and all our TAs as collaborators.

---

## Marking Scheme
1.	Team work
	1. A running program and the source code. (8% for each completed task, total 16% for a student)
	1. A README file stating the name of team members and their tasks assigned.
1.	Individual work - Even if you are failed to implement all tasks, the unit testing and coverage report are still required.
	1. Unit testing of your implemented tasks. (100% pass - 2%; 0% otherwise)  
	1. Coverage test (>70% branch coverage- 2% ; 50-70% branch coverage - 1%; 0% otherwise)
	1. Git commit log at GitHub
		1. 3 "non-trivial" commits (1%)
		1. At least one "non-trivial" pull request (1%)
	1. Documenting your implemented features using JavaDoc (Full mark 1%: -0.25% for each missing argument, return value, exception, class description)
		1. If a function is responsible by more than one student, all of them will receive the penalty.
1. At least 3 meeting minutes. (1%) (submitted at the end of this course)
1. A Gantt chart (1%)

---

# Webscraper

## Compile

We configure the project with Gradle. Gradle can be considered as Makefile like tools that streamline the compilation for you. In this project you
are not required to learn how to write Gradle file. If you are interested to know how, please check out [this link](http://www.vogella.com/tutorials/Gradle/article.html).

### Compile with Windows Command Prompt 

Goto your folder and type `gradlew run`. This will build and run the project. 

If you want to just rerun the project without rebuilding it, you can go to the folder `build\jar\` 
there should be a jar file (e.g. `webscraper-0.1.0.jar`). Try double click on it 
(yes, you need a GUI screen to run it). 

### Compile with Mac/Linux terminal 

Goto your folder and type `./gradlew build`. This will build and run the project.

If you want to just rerun the project without rebuilding it, you can go to the folder `build/jar/` 
there should be a jar file (e.g. `webscraper-0.1.0.jar`). Try double click on it 
(yes, you need a GUI screen to run it). 

### Compile with Eclipse

You are recommended to download the [Eclipse Photon for Windows](http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/photon/RC3/eclipse-dsl-photon-RC3-win32-x86_64.zip). 
This version of eclipse supports Gradle out of the box.  Create a new `project` > `Gradle Project` and drag 
everything from the given base code to the project. Refer to Lab 1 to bring up the `Gradle Tasks` windows and double
click on the `application` > `run` to launch the application.
