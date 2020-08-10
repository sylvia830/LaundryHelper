# My Personal Project

##LaundryHelper

Description:

- **_What will the application do?_** 
LaundryHelp is set to provide users with information in terms of the machine availability, laundry card balance. 
This app would be extremely convenient for users as the information would assist them in planning their laundry. 
The users are able to check if the machines are available and get a list of the occupied machines as well as manage the 
balance on their laundry card. 
   
- **_Who will use it?_** 
The major users of this helper app would be those who live in residential areas 
where residents need to share multiple washing machines and dryers. Specifically, this would be greatly appreciated 
by students in residence on campus.

- **_Why is this project of interest to me?_** 
Having experienced doing laundry in residence, I found it rather time-consuming especially during peak hours when one 
had to run up and downstairs several times just to find an available slot. With a helper as such, one can invest all 
that searching time on more urgent issues.

User Stories:
- As a user, I want to be able to create a laundry task and add it to a list of existing tasks.
- As a user, I want to be able to view the list of occupied machines.
- As a user, I want to be able to check the availability of the washing machines.
- As a user, I want to be able to pay for the service using my laundry card.
- As a user, I want to be able to check the balance on my account and add value to it.
- As a user, I want to be able to delete my current task (cannot delete the saved one).
- As a user, I want to be able to save my card balance to the file.
- As a user, I want to be able to load my card balance from the file.
- As a user, I want to be able to save my current machineId to the file and load it afterwards.

Instructions for Grader
- You can generate the first required event by inputting the machineID of the machine you wish to use into the text 
field right beside the "Start" button at the top of the window (after inputting the ID, you have to press "enter" to see 
the effect). On the right side of the text box, You can also see the list of machineIDs of the occupied machines.
- You can generate the second required event by clicking on the "delete my current task" button, and you will see the 
task gone from the list. (Note: you need to delete first before saving otherwise you will still be able to see the 
"deleted" machineID when you re-run the program. Suppose machine 1, 2, 3 are unavailable. If you delete the current 
machineID, e.g. 3, and want to save machine 2, you have to re-enter 2 in the text field and press enter before you can 
successfully save it to the file. This operation won't introduce duplicates as the occupied machines in the list 
remains to be 1 and 2.)
- You can trigger my audio component by clicking on the "Start" button.
- You can save the state of my application by clicking on "Save my current machine" and "Save my current balance" 
buttons.
- You can reload the state of my application by clicking on "Check Balance" button to see the balance previously saved, 
meanwhile the machineID of the saved machine is already in the list of unavailable machines (when you rerun the program,
you'll see the machineID already there in the list).
- You can locate the pop-up dialog by clicking on "Save my current machine" and "Save my current balance" buttons.
- You can click on "Add Value" button first and enter the amount in the text field right next to it to add value to 
your balance (remember to press "enter" after the input to add value successfully).

Phase 4: Task 2
- Option 1: test and design a robust class
- In LaundryCard class, an exception is thrown in addValue method when num is less than or equal to 0.
- In LaundryCardTest, testAddValue tested the case where the exception is not expected and testAddNegativeValue tested 
the case where the exception is expected.

