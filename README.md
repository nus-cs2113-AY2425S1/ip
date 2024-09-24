# yapper.Yapper Chatbot Project

This is a project for a greenfield Java chatbot project, 
based on Duke, the National University of Singapore (NUS)'s
School of Computing (SOC)'s mascot.

It's named after the term _Yapper_, which is a slang term for someone who talks too much (without much meaningful content). 

Given below are instructions on how to use it. 

---

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/yapper/Yapper.java` file, right-click it, and choose `Run Yapper.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   
```
___________________ PROGRAM OUTPUT BELOW ___________________
Wassup! 
Ya ready for me to yap yer ear off? 
Whatchu wanna talk about? 
____________________________________________________________
To jog your memory, here's what we can discuss: 
list, if you forgot what you said. 
bye, if you want me to stop yappin. 
delete [index], if you don't want something. 
mark [index], if you're done with something. 
unmark [index], if you're not done with something. 
find [keyword], if you're looking for something. 
todo [todoDesc], to for a task with no dates. 
deadline [deadlineDesc] /by [end], for a task with an end date. 
event [eventDesc] /from [start] /to [end], for a task with a start date and an end date. 
_____________________ USER INPUT BELOW _____________________
```

The code above should appear so long as you don't have a `savefile.txt` in a `data` folder, 
in which case another message will be shown instead.

---

