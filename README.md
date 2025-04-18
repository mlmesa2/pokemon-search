# Pokemon Searcher

A modern Android application for searching and browsing Pokemon using the PokeAPI. Built with Jetpack Compose, following MVVM architecture and clean code principles.

## Features

- 🎯 Search Pokemon by name
- 📱 Modern Material 3 UI with Jetpack Compose
- 💾 Local caching with Room Database
- 🌐 Offline support
- 🔍 Real-time search functionality

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern with clean code principles:

```
com.mlmesa.pokemonsearcher/
├── data/               # Data layer
│   ├── local/          # Room database and entities
│   ├── remote/         # API and network related code
|   ├── connectivity/   # Check internet connectivity
│   └── repository/     # Repository implementations
├── domain/             # Domain layer
│   ├── models/         # Data models
│   ├── repository/     # Repository interfaces
│   └── use_cases/      # App use cases
├── ui-presentation/    # UI layer
│   ├── screens/        # Composable screens, ViewModels
│   ├── theme           # App theme
│   └── components/     # Reusable UI components
├── di/                 # Dependency injection
├── navigation/         # Navigation components
└── common/             # Common utilities
```

### Key Components

1. **Data Layer**
   - Room Database for local caching
   - Retrofit for API calls
   - Repository pattern for data management

2. **Domain Layer**
   - Clean architecture interfaces
   - Data models

3. **Presentation Layer**
   - Jetpack Compose UI
   - ViewModels for state management
   - Material 3 design system

## Technical Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM
- **Dependency Injection**: Hilt
- **Database**: Room
- **Networking**: Retrofit
- **Image Loading**: Coil
- 
## Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Build and run the app

## Performance Optimizations

1. **Room Database**
   - Local caching
   - Offline support
   - Efficient queries

2. **Image Loading**
   - Coil for efficient image loading
   - Caching and memory management

## Best Practices

- Clean Architecture principles
- Dependency Injection with Hilt
- Immutable data structures
- Coroutines for asynchronous operations
- Proper error handling
- Efficient state management
- Material 3 design guidelines

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Acknowledgments

- PokeAPI for providing the Pokemon data
- Android team for Jetpack libraries
- Material Design team for Material 3 