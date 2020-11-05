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
- [x] Shared element transition


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

## Shared element transition

For shared element transition, the API needs unique transition names to be mentioned for HeroView on both screens and navigate method.
Since we're using recycler view, it is not possible to use a static name for list item. And another point to note, the views will be recycled so it should be data oriented.

So I created a simple extension method that compose the transition name out of todo object and assigned the transition name on onBind.

```kotlin
fun Todo.titleTransitionName() = "todo_${id}_title"
```

While invoking the details screen, the transition name and hero element passed on to navController.

```kotlin
val extras = FragmentNavigatorExtras(
        textView to todo.titleTransitionName()
)
val navigation =
    TodoListFragmentDirections.actionTodoListFragmentToDetailsFragment(todo)
findNavController().navigate(navigation, extras)
```

To see the actual transition, we need to postpone it and execute after a layout pass. This is needed on both the fragments.
This couple of lines would do it.

```kotlin
fun onViewCreated...
...
postponeEnterTransition()
view.doOnPreDraw { startPostponedEnterTransition() }
```

In details screen, we need to define the shared element transitions for both enter and return. This should be done before the onCreateView itself.

```kotlin
 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move).apply {
                duration = DURATION_TRANSITION
            }
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }
```

Now on onCreateView, assign the transition name to hero view. And that's it.

```kotlin
binding.descriptionLabel.transitionName = args.todoItem.titleTransitionName()
```

Here's the result
<img src="https://github.com/mahendranv/todo_nav/blob/main/art/nav_shared_element.gif" width="350">