# CryptoCoinTracker

CryptoCoinTracker is an Android application written in Kotlin, utilizing Kotlin DSL for a clean and readable build configuration. The app demonstrates the use of various modern Android development technologies and libraries to create a robust and efficient cryptocurrency tracking application.

## Features

- **MVVM Architecture**: The app follows the Model-View-ViewModel (MVVM) architecture pattern, promoting a clean separation of concerns and making the codebase more maintainable.
- **Clean Architecture**: Adheres to Clean Architecture principles to ensure a scalable and testable code structure.
- **Retrofit2**: Used for making API requests to fetch cryptocurrency values from a public API.
- **GSON**: Employed to parse JSON responses from the API into Kotlin data classes.
- **Room**: Serves as the local database to store cryptocurrency values for offline access.
- **LiveData**: Ensures the UI components observe changes in the data and update automatically.
- **Picasso**: Handles image loading and caching within the app.
- **Coroutines**: Used for asynchronous programming, making the app responsive and efficient.
- **Foreground Service**: Keeps the app running in the background to continuously fetch and update cryptocurrency values even when the app is closed, using CoroutineWorker for background processing.

## How It Works

1. **Data Extraction**: CryptoCoinTracker fetches real-time cryptocurrency values from a public API using Retrofit2.
2. **Data Storage**: The fetched data is parsed with GSON and inserted into a Room database.
3. **Data Display**: The app's UI observes the data stored in the Room database via LiveData and displays the cryptocurrency values using a clean and intuitive interface.
4. **Background Updates**: A Foreground Service ensures that data fetching and updating continues even when the app is closed, leveraging CoroutineWorker for efficient background operations.

## Tech Stack

- **Programming Language**: Kotlin
- **Libraries**: Retrofit2, GSON, Room, LiveData, Picasso, Coroutines
- **Architecture Patterns**: MVVM, Clean Architecture
- **Services**: Foreground Service, CoroutineWorker

## Screenshots

![Screenshot1](link-to-screenshot1)
![Screenshot2](link-to-screenshot2)

## Installation Instructions

To get started with the project, follow these steps:

1. Clone the repository:
    ```bash
    git clone https://github.com/art3mvp/crypto-coins-tracker.git
    cd  crypto-coins-tracker
    ```
2. Open the project in Android Studio.
3. Ensure you have the necessary dependencies installed.
4. Build and run the project on an emulator or physical device.

## Usage

1. Launch the app on your device.
2. The main screen will display a list of cryptocurrencies with their current values.
3. The app will update the values in the background, ensuring you always have the latest data.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## Issues and Feature Requests

If you encounter any issues or have a feature request, please create an issue in the [Issues](https://github.com/art3mvp/crypto-coins-tracker/issues) section of the repository.
