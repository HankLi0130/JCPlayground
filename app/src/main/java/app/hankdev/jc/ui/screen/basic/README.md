# Basic

## How does Jetpack Compose actually work?

Here's the heigh-level architecture:

1. **Compiler Plugin** — Compose isn't just a library; the Kotlin compiler plugin transforms your
   `@Composable` functions at compile time, inserting tracking code
2. **Slot Table** — A gap buffer data structure that stores the composition's state and structure in
   memory
3. **Snapshot System** — State objects (`mutableStateOf`) use a snapshot-based observation system to
   track reads/writes
4. **Recomposition** — Only the composables that *read* changed state get re-executed (not the whole
   tree)

## The Three Phases of Jetpack Compose

### Quick Overview

Compose renders UI through three distinct phases that run *in order*:

| Phase          | Purpose             | Input → Output                    |
|----------------|---------------------|-----------------------------------|
| 1. Composition | *What* to show      | `@Composable` functions → UI tree |
| 2. Layout      | *Where* to place it | Measurement & positioning         |
| 3. Drawing     | *How* to render it  | Pixels on screen                  |

### Phase 1: Composition

Compose executes your `@Composable` functions to build/update the **UI tree**(Slot table).

```kotlin
@Composable
fun Greeting(name: String) {
    // This code runs during Composition
    Text("Hello, $name")  // Emits a Text node to the tree
}
```

**Key insight**: Recomposition only re-runs composables whose **state has changed**.

### Phase 2: Layout

Two sub-steps for each node:

1. **Measure** - determine intrinsic size
2. Place - position with parent

```kotlin
Modifier.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)  // Measure
    layout(placeable.width, placeable.height) {
        placeable.place(0, 0)  // Place
    }
}
```

### Phase 3: Drawing

Renders the actual pixels using `Canvas` operations.

```kotlin
Modifier.drawBehind {
    drawCircle(Color.Red)  // Runs during Drawing phase
}
```

### Why This Matters for Performance

Each phase can be **skipped independently**:

```kotlin
// ❌ Bad: color change triggers recomposition
Box(Modifier.background(animatedColor))

// ✅ Good: color change only triggers redraw
Box(Modifier.drawBehind { drawRect(animatedColor) })
```