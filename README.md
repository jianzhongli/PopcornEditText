# PopcornEditText

An `EditText` that keep popping text. Requirement: `minSDK: 16`

<img src="screenshots/eng.gif" width="200"> <img src="screenshots/chn.gif" width="200">

## Usage
### 1. Setup with gradle
Add `compile 'io.github.kexanie.library:PopcornEditText:0.0.1'` to your module's `build.gradle` file.
```
dependencies {
    compile 'io.github.kexanie.library:PopcornEditText:0.0.1'
}
```

### 2. Define `PopcornEditText` in your layout file
```
<io.github.kexanie.library.PopcornEditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:endY="0dp"
    app:TTL="7000"
    app:popcorn_size="12sp"
    app:popcorn_color="@android:color/black"
    />
```

**Caution**
You should add `android:windowSoftInputMode="adjustResize"` to your activity in the manifest, otherwise the characters will fall behind the keyboard. For example: 
```
<activity android:name=".MainActivity"
    android:windowSoftInputMode="adjustResize">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

#### Attributes
|Attribute | Type | Description |
|----------|------|--------------|
|endY|dimension|The highest position that a character can jump to. Relative to the top edge of root view.|
|TTL|int|The time(ms) that a popped out characters exist.|
|popcorn_size|dimension|The `textSize` of popped out characters.|
|popcorn_color|color|The color of popped out characters.|

## Feedback
Please dont hesitate to create an issue ticket or pull request.

![](screenshots/popcorn.gif)

## Thanks
Inspired by [`BiuEditText`][BiuEditText] and this gif.

![](http://45.media.tumblr.com/cf210d7c444b3e4d5e5a49ebb0bf9dae/tumblr_ny0aidok9u1rc7zl1o3_250.gif)

Thanks [Jawnnypoo][Jawnnypoo] and his [PhysicsLayout][PhysicsLayout].

[BiuEditText]: https://github.com/xujinyang/BiuEditText
[Jawnnypoo]: https://github.com/Jawnnypoo
[PhysicsLayout]: https://github.com/Jawnnypoo/PhysicsLayout