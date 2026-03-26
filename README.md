# Waheed Fruit Trader

A Java-based desktop application for managing fruit trading operations, built with Swing GUI framework.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup & Installation](#setup--installation)
- [Build Instructions](#build-instructions)
- [Running the Application](#running-the-application)
- [Configuration](#configuration)
- [Architecture](#architecture)
- [Contributing](#contributing)
- [License](#license)

## Overview

Waheed Fruit Trader is a Java desktop application designed to streamline fruit trading operations. It provides a graphical user interface for managing fruit inventory, trades, and business reports.

## Features

- **Fruit Inventory Management** - Track and manage fruit stock
- **Trading Operations** - Process and record fruit transactions
- **Business Reporting** - Generate business reports and analytics
- **GUI Interface** - User-friendly Swing-based desktop interface
- **Configurable** - Properties-based configuration system
- **Logging** - Comprehensive logging with Log4j framework

## Technology Stack

- **Language**: Java
- **GUI Framework**: Java Swing
- **Build Tool**: Apache Ant
- **Logging**: Log4j / Log4j2
- **IDEs Supported**: 
  - Eclipse
  - IntelliJ IDEA
  - NetBeans
- **JDK**: Java 8 or higher (recommended)

## Project Structure

```
waheedfruittrader/
├── src/
│   ├── Fruit/                      # Fruit domain models and classes
│   ├── config/                     # Configuration management
│   ├── practise/                   # Practice and experimental code
│   ├── SwingWorkerSample.java      # Swing GUI worker thread example
│   ├── log4j2.xml                  # Log4j2 configuration
│   └── log4j.xml                   # Log4j configuration
├── lib/                            # External libraries and dependencies
├── Business_Report/                # Business documentation and reports
├── build.xml                       # Ant build script
├── config.properties               # Application configuration properties
├── .classpath                      # Eclipse classpath configuration
├── .project                        # Eclipse project configuration
├── nbproject/                      # NetBeans project configuration
├── .idea/                          # IntelliJ IDEA configuration
└── log4j.xml                       # Root logging configuration

```

## Prerequisites

- **JDK 8+** or **JDK 11/17** (recommended)
- **Apache Ant** 1.7 or higher
- Any of the following IDEs (optional):
  - Eclipse IDE
  - IntelliJ IDEA
  - NetBeans IDE

### Verify Installation

```bash
java -version
javac -version
ant -version
```

## Setup & Installation

### 1. Clone the Repository

```bash
git clone https://github.com/raffiuddind/waheedfruittrader.git
cd waheedfruittrader
```

### 2. Using Eclipse

1. Open Eclipse
2. Go to **File** → **Import** → **Existing Projects into Workspace**
3. Select the cloned directory
4. Click **Finish**
5. Right-click project → **Build Path** → **Configure Build Path** to verify JRE version

### 3. Using IntelliJ IDEA

1. Open IntelliJ IDEA
2. Go to **File** → **Open**
3. Select the project directory
4. IDEA will auto-detect project structure
5. Configure SDK: **File** → **Project Structure** → **SDKs**

### 4. Using NetBeans

1. Open NetBeans
2. Go to **File** → **Open Project**
3. Select the `waheedfruittrader` directory
4. NetBeans will recognize the project automatically

## Build Instructions

### Using Ant (Command Line)

```bash
# Build the project
ant build

# Clean previous builds
ant clean

# Build and clean
ant clean build

# Create JAR file
ant jar
```

### Using IDE

**Eclipse**: Right-click project → **Build Project**  
**IntelliJ**: Build → **Make Project**  
**NetBeans**: Build → **Build Main Project**

## Running the Application

### Using Ant

```bash
ant run
```

### Using IDE

**Eclipse**: Right-click → **Run As** → **Java Application**  
**IntelliJ**: Right-click main class → **Run**  
**NetBeans**: Run → **Run Main Project**

### Command Line (After Building)

```bash
java -cp bin com.waheedfruittrader.Main
```

## Configuration

### Application Properties

Edit `config.properties` to configure application behavior:

```properties
# Add your configuration properties here
# Example:
# app.name=Waheed Fruit Trader
# app.version=1.0.0
```

### Logging Configuration

- **Log4j**: Configure via `log4j.xml`
- **Log4j2**: Configure via `src/log4j2.xml`

Logs are typically output to:
- Console (DEBUG and above)
- File logs in `logs/` directory (if configured)

### Classpath Configuration

- **Eclipse users**: Use `.classpath` file (auto-managed)
- **Others**: Configure in your IDE's project settings

## Architecture

### Component Overview

1. **Fruit Package** (`src/Fruit/`)
   - Contains data models for fruit management
   - Handles fruit inventory and properties

2. **Config Package** (`src/config/`)
   - Configuration loading and management
   - Properties initialization

3. **Swing Components** (`SwingWorkerSample.java`)
   - GUI implementation using Swing
   - Background thread processing with SwingWorker
   - Event handling and user interactions

4. **Business Logic**
   - Trading operations processing
   - Report generation
   - Data persistence

### Logging Architecture

- Dual logging support (Log4j and Log4j2)
- Configurable log levels and output
- File and console appenders

## Development Guidelines

### Code Style

- Follow Java naming conventions
- Use meaningful variable and method names
- Document complex logic with comments
- Keep methods focused and concise

### Testing

Place test files in appropriate test directories and document test coverage.

### Adding New Features

1. Create feature branch: `git checkout -b feature/your-feature`
2. Implement changes
3. Test thoroughly
4. Commit with clear messages
5. Push and create Pull Request

## Troubleshooting

### Build Issues

**Issue**: `BUILD FAILED - cannot find symbol`  
**Solution**: Ensure all JAR files in `lib/` are in classpath (check `.classpath` or IDE settings)

**Issue**: `java.lang.UnsupportedClassVersionError`  
**Solution**: Verify JDK version matches project requirements. Update to Java 8+

### Runtime Issues

**Issue**: GUI doesn't display  
**Solution**: Check display settings and Swing configuration

**Issue**: Logging not working  
**Solution**: Verify `log4j.xml` or `log4j2.xml` is in classpath

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is currently unlicensed. For licensing information, contact the repository owner.

---

**Last Updated**: 2026-03-26 07:59:44  
**Author**: raffiuddind  
**Repository**: [raffiuddind/waheedfruittrader](https://github.com/raffiuddind/waheedfruittrader)