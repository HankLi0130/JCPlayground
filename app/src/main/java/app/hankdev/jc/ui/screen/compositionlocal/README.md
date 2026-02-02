# [CompositionLocal](https://developer.android.com/develop/ui/compose/compositionlocal)

CompositionLocal is a tool for passing data down through the Composition implicitly.

## Create a CompositionLocal

There are two APIs to create a CompositionLocal:

- **compositionLocalOf**: Changing the value provided during recomposition invalidates only the
  content that reads its current value.
- **staticCompositionLocalOf**: Unlike compositionLocalOf, reads of a staticCompositionLocalOf are
  not tracked by Compose. Changing the value causes the entirety of the content lambda where the
  CompositionLocal is provided to be recomposed, instead of just the places where the current value
  is read in the Composition.

If the value provided to the **CompositionLocal** is highly **unlikely to change** or will **never
change**, use **staticCompositionLocalOf** to get performance benefits.

### Key points:

| API                           | When to use                                                                      |
|-------------------------------|----------------------------------------------------------------------------------|
| compositionLocalOf            | Value may change; only readers recompose                                         |
| staticCompositionLocalOfValue | rarely/never changes; better performance but entire subtree recomposes on change |

### Usage pattern:

- Define: `val LocalXxx = compositionLocalOf { defaultValue }`
- Provide: `CompositionLocalProvider(LocalXxx provides value) { ... }`
- Consume: `val value = LocalXxx.current`