# Avatar Maker App

An Android application that creates a customizable avatar from a user's body image using MediaPipe pose detection and dynamic clothing overlays.

The project combines computer vision, body measurement extraction, and avatar customization to automatically generate a stylized avatar that matches the user's body proportions.

---

## Features

### Avatar Creation

* Generate avatars from captured photos
* Body landmark detection using MediaPipe
* Automatic body measurement extraction
* Dynamic avatar scaling

### Customization

* Multiple head styles
* Multiple hairstyles
* Multiple hats
* Multiple shirts
* Multiple pants
* Real-time avatar customization

### Body Tracking

* Pose landmark detection
* Skeleton visualization
* Torso measurement calculation
* Body proportion analysis

### Clothing System

* Shirt overlay positioning
* Pant overlay positioning
* Dynamic clothing scaling
* Automatic alignment using body landmarks

### Camera Integration

* Camera permission handling
* Photo capture using CameraX
* Image processing pipeline

---

## Current Development Status

### Implemented

* [x] Camera capture
* [x] Pose detection
* [x] Skeleton rendering
* [x] Body measurements
* [x] Avatar generation
* [x] Avatar customization
* [x] Hair selection
* [x] Head selection
* [x] Hat selection
* [x] Shirt selection
* [x] Pant selection
* [x] Clothing overlays
* [x] State management

### In Progress

* [ ] Stable live camera preview
* [ ] Improved body tracking accuracy

### Planned

* [ ] Avatar export
* [ ] Save generated avatar as image
* [ ] Additional clothing categories
* [ ] More avatar assets
* [ ] Full-body customization
* [ ] Asset packs

---

## Technology Stack

### Language

* Kotlin

### UI

* Jetpack Compose
* Material 3

### Computer Vision

* MediaPipe Tasks Vision
* Pose Landmarker

### Camera

* CameraX Core
* CameraX Camera2
* CameraX Lifecycle
* CameraX View

### Android Components

* AndroidX Core KTX
* Activity Compose
* AppCompat
* ConstraintLayout

---

## Project Architecture

```text
Avatar Maker App
│
├── Camera Layer
│   ├── Camera Capture
│   └── Image Processing
│
├── Pose Detection Layer
│   ├── MediaPipe Pose Detector
│   ├── Landmark Extraction
│   └── Body Measurements
│
├── Avatar Layer
│   ├── Avatar State
│   ├── Avatar Display
│   ├── Avatar Customizer
│   └── Asset Management
│
├── Clothing Layer
│   ├── Shirt Overlay
│   ├── Pant Overlay
│   └── Body Overlay
│
└── UI Layer
    ├── Compose Screens
    ├── Measurement Display
    ├── Pose Display
    └── Customization Controls
```

---

## Project Structure

```text
app/src/main/java/com/example/bodytracking/

├── MainActivity.kt
├── PoseDetector.kt
├── PoseState.kt
├── BodyMeasurement.kt
│
├── AvatarDisplay.kt
├── AvatarCustomizer.kt
├── AvatarOverlay.kt
├── AvatarBodyOverlay.kt
├── AvatarShirtOverlay.kt
├── AvatarClothOverlay.kt
├── AvatarImageDisplay.kt
├── AvatarAssetState.kt
│
├── MeasurementDisplay.kt
├── PoseDisplay.kt
├── ShirtDisplay.kt
├── SkeletonDot.kt
├── TorseOverlay.kt
│
├── ImageUtils.kt
├── ImageProxyextension.kt
└── Buttons.kt
```

---

## Assets

Current avatar assets include:

### Heads

* Head 1
* Head 2
* Head 3

### Hair

* Hair 1
* Hair 2

### Hats

* Hat 1
* Hat 2

### Shirts

* Shirt 1
* Shirt 2
* Shirt 3

### Pants

* Pant 1
* Pant 2

---

## Minimum Requirements

* Android 8.0 (API 26)
* Camera permission
* ARM64 Android device recommended

---

## Future Goals

The long-term vision of this project is to create a fully automated avatar generation system that:

1. Detects a user's body proportions.
2. Creates a personalized avatar.
3. Fits clothing automatically.
4. Allows full customization.
5. Exports the final avatar as an image.
6. Supports expandable asset packs.

---

## Author

Shresth Pandey

GitHub:
https://github.com/ShresthPandey313

---

## License

This project is currently under development and does not yet have an assigned license.
