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
- [x] Simple navigation between fragment
- [x] Safe args
- [ ] Sending data back to the caller fragment
- [ ] Backstack handling
- [ ] What is equivalent to Activity's `noHistory`?
- [ ] Toolbar & menu update

Multiple entry points!!
- [ ] Deeplink
- [ ] Notification

Ui
- [x] Transition Animation
- [ ] Shared element transition


### Fragment transition
Fragment transition is much simpler to achieve with nav components. It's matter of defining the animation resource files and adding it to the navigation action.
Pretty cool thing about it is we can mention different animations for each of the navigation action.

Changes can be referred here
https://github.com/mahendranv/todo_nav/commit/e9ffa94c1ee5344281f12502877119a36fc5c14f

This will give you the overall idea
```xml
<action
    android:id="@+id/action_todoListFragment_to_detailsFragment"
    app:destination="@id/detailsFragment"
    app:enterAnim="@anim/slide_in"
    app:exitAnim="@anim/fade_out"
    app:popEnterAnim="@anim/fade_in"
    app:popExitAnim="@anim/slide_out" />
```

- `enterAnim` - Next destination fragment enter animation
- `exitAnim`  - How source destination should disappear
- `popExitAnim` - How should the destination fragment vanish
- `popEnterAnim` - How the source fragment should surface back

Here the animation at 3000 ms duration

<img src="https://github.com/mahendranv/todo_nav/blob/main/art/nav_transition_animation.gif" width="350">


