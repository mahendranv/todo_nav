# Simple TODO app using Android - Navigation Component

A simple in-memory to-do app that tracks well.. todo items. So I'm planning to add lots of screens and add as much as nav graphs possible. 

## Data layer
Current focus is on exploring the nav components. So the data layer follows [KISS principle](https://en.wikipedia.org/wiki/KISS_principle). 
- Request it - get it
- No errors
- No callback - no suspend function
(Note to self: extract this as a separate JAR and use it across projects)

That's it. Rest is purely about exploring the nav-component.

## TODO
- [ ] Simple navigation beetween fragment
- [ ] Safe args
- [ ] Sending data back to the caller fragment
- [ ] Backstack handling
- [ ] What is equivalent to Activity's `noHistory`?

Multiple entry points!!
- [ ] Deeplink
- [ ] Notification

Ui
- [ ] Transition Animation
- [ ] Shared element transition
