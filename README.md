## CSC435 Web Services Assignment 5

Description of the project can be found in the `INSTRUCTIONS.md` file.

This assignment is an extension of the previous assignments but written using Grails with Groovy, as well as the Gradle package manager instead of Maven due to the direct support out of the box for Grails.

In dev mode, Grails creats a DB instance, and writes/reads directly from that instead of using the default `create-drop` mode that Grails v3 sets.

This was chosen because the DB doesn't get removed after shutdown and just updates when needed since the domain classes don't have to be changed.